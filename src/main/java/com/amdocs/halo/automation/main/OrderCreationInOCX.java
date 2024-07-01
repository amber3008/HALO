package com.amdocs.halo.automation.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.base.UpdateXMLs;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

/***
 * 
 * @author ab780k
 *
 */

public class OrderCreationInOCX 
{
	public static String data[]= new String[3];
	public static String updatedRequest;
	public static String updatedRequestKeys;
	public static String getUrl;
	public static String responsetoString;
	public static String typeOfChange;
	public static String typeOfOrder;
	public static Logger logger = null;
	public static  ArrayList<List<String>> ordrCreationDetails;
	public static HashMap<String,String> keysAndIds= new HashMap<String,String>();
	public static String archType;
	public static boolean isCasoClassTriggered;
	   
	public static String getClassName()
	{
		return OrderCreationInOCX.class.getName();
	}
	public static void casoPush(String env, String orderType, String SORID, String projId, String changeType, String whatChange) throws Exception
	{
		isCasoClassTriggered=true;
		logger=LoggerGen.logGen(getClassName());
		typeOfChange=changeType;
		typeOfOrder=orderType;
		String response="";
		TriggerXmls tg= new TriggerXmls();
		getUrl=GetURLFromExcel.readURLFromExcel("CASO_REQUEST",env);
		Document doc = null;
		byte[] encoded=(byte[]) null;
		String xmlFileForDoc=null;
		ArrayList<Object> al= new ArrayList<Object>();
		al=getRequestAndXml(env,orderType,changeType,whatChange);
		encoded= (byte[]) al.get(0);
		xmlFileForDoc=(String) al.get(1);
		
		ArrayList<List<String>> ordrDetails= new ArrayList<List<String>>();
		
		doc= tg.readInputXmls(xmlFileForDoc);
		
		//doc=tg.readXmls(xmlInputFormatted);
		String finalRequest= null;
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info("URL for pushing CASO Request is :"+getUrl);
		
		if(changeType.equalsIgnoreCase("MACD"))
		{
			updatedRequest= UpdateXMLs.updateXmlIST(request, SORID, projId);
			UpdateXMLs.createKeysAndIds();
		    finalRequest= UpdateXMLs.updateXmlKeys(updatedRequest,"CHV0");
		}
		else
			finalRequest= UpdateXMLs.updateXmlIST(request, SORID, projId);

		byte[] encoded1=finalRequest.getBytes();
		
		//Push xmls
		try 
		{
			response=tg.pushXml(encoded1, getUrl);
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			logger.info("Response not present");
		}
		responsetoString=response;
 		logger.info("Response in format is:"+tg.formatResponseXml(response));
 		List<String> sgId= GetProjectId.getElementFromXml(responsetoString, "orderIdX9");
 		ordrDetails.add(0, sgId);
 		
 		List<String> serviceType=GetProjectId.getElementFromXml(responsetoString, "productX9");
 		ordrDetails.add(1, serviceType);
 		
 		List<String> orderNumber=GetProjectId.getElementFromXml(responsetoString, "orderNumberX9");	
 		ordrDetails.add(2, orderNumber);
 		
 		List<String> orderStatus=GetProjectId.getElementFromXml(responsetoString, "orderActionStatusX9");
 		ordrDetails.add(3, orderStatus);
 		
 		ordrCreationDetails=null;
 		ordrCreationDetails=ordrDetails;
	}
	
	
	public static ArrayList<Object> getRequestAndXml(String env, String orderType, String changeType, String whatChange) throws IOException
	{
		logger=LoggerGen.logGen(getClassName());
		ArrayList<Object> al= new ArrayList<Object>();
		Document doc = null;
		byte[] encoded=(byte[]) null;
		String xmlFileForDoc=null;
		if (changeType.equalsIgnoreCase("NS"))
			whatChange=null;
		
		if(env.equalsIgnoreCase("ST4") && orderType.equalsIgnoreCase("MIS") && changeType.equalsIgnoreCase("NS") && whatChange == null)
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_MIS_QA12_NS.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_MIS_QA12_NS.xml";
		}
		else if(env.equalsIgnoreCase("ST4") && orderType.equalsIgnoreCase("AVPN") && changeType.equalsIgnoreCase("NS") && whatChange == null)
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPNSL_QA12_NS.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPNSL_QA12_NS.xml";
		}
		
		else if(env.equalsIgnoreCase("ST3") && orderType.equalsIgnoreCase("MIS") && changeType.equalsIgnoreCase("NS") && whatChange == null)
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_MIS_QA14_NS.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_MIS_QA14_NS.xml";
		}
		else if(env.equalsIgnoreCase("ST3") && orderType.equalsIgnoreCase("AVPN") && changeType.equalsIgnoreCase("NS") && whatChange == null)
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPNSL_QA14_NS.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPNSL_QA14_NS.xml";
		}
		else if(env.equalsIgnoreCase("ST3") && orderType.equalsIgnoreCase("AVPN") && changeType.equalsIgnoreCase("MACD") && whatChange == null)
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPN_QA14_V0.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPN_QA14_V0.xml";
		}
		else if(env.equalsIgnoreCase("ST3") && orderType.equalsIgnoreCase("MIS") && changeType.equalsIgnoreCase("MACD") && whatChange == null)
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_MIS_QA14_V0.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_MIS_QA14_V0.xml";
		}
		else if(env.equalsIgnoreCase("ST5") && orderType.equalsIgnoreCase("MIS") && changeType.equalsIgnoreCase("NS") && whatChange == null)
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_MIS_QA13_NS.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_MIS_QA13_NS.xml";
		}
		else if(env.equalsIgnoreCase("DEV4") && orderType.equalsIgnoreCase("MIS") && changeType.equalsIgnoreCase("NS") && whatChange == null)
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_MIS_DEV4_NS.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_MIS_DEV4_NS.xml";
		}
		else if(env.equalsIgnoreCase("ST5") && orderType.equalsIgnoreCase("IPFLEXMIS") && changeType.equalsIgnoreCase("NS") && whatChange == null)
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_IPFLEXMIS_QA13_NS.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_IPFLEXMIS_QA13_NS.xml";
		}
		else if(env.equalsIgnoreCase("ST3") && orderType.equalsIgnoreCase("IPFLEXMIS") && changeType.equalsIgnoreCase("NS") && whatChange == null)
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_IPFLEXMIS_QA14_NS.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_IPFLEXMIS_QA14_NS.xml";
		}
		else if(env.equalsIgnoreCase("ST4") && orderType.equalsIgnoreCase("BOE") && changeType.equalsIgnoreCase("NS") && whatChange == null)
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_BOE_QA12_NS.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_BOE_QA12_NS.xml";
		}
		else if(env.equalsIgnoreCase("ST3") && orderType.equalsIgnoreCase("BOE") && changeType.equalsIgnoreCase("NS") && whatChange == null)
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_BOE_QA14_NS.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_BOE_QA14_NS.xml";
		}
		else if(env.equalsIgnoreCase("ST5") && orderType.equalsIgnoreCase("AVPN") && changeType.equalsIgnoreCase("NS") && whatChange == null)
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPNSL_QA13_NS.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPNSL_QA13_NS.xml";
		}
		else if(env.equalsIgnoreCase("ST4") && orderType.equalsIgnoreCase("MIS") && changeType.equalsIgnoreCase("MACD") && whatChange.equalsIgnoreCase("ChangePortSpeed"))
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_MACD_v0_ChangePortSpeed_QA12.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_MACD_v0_ChangePortSpeed_QA12.xml";
		}
		else if(env.equalsIgnoreCase("ST3") && orderType.equalsIgnoreCase("MIS") && changeType.equalsIgnoreCase("MACD") && whatChange.equalsIgnoreCase("ChangeCPE"))
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_MACD_v0_ChangeCPE_QA14.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_MACD_v0_ChangeCPE_QA14.xml";
		}
		al.add(encoded);
		al.add(xmlFileForDoc);
		return al;
	}
	
	public static ArrayList<List<String>> casoPushAvpnSite(String env,String SORID, String projId) throws Exception
	{
		logger=LoggerGen.logGen(getClassName());
		ArrayList<List<String>> ordrDetailsAvpn= new ArrayList<List<String>>();
		String response="";
		TriggerXmls tg= new TriggerXmls();
		//String getURL=GetURLFromExcel.readURLFromExcel("CASO_REQUEST",env);
		Document doc = null;
		byte[] encoded=(byte[]) null;
		String xmlFileForDoc=null;
		if(env.equalsIgnoreCase("ST3"))
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPN_QA14_NS.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPN_QA14_NS.xml";
		}
		else if(env.equalsIgnoreCase("ST4"))
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPN_QA12_NS.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPN_QA12_NS.xml";
		}
		else if(env.equalsIgnoreCase("ST5"))
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPN_QA13_NS.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPN_QA13_NS.xml";
		}
		doc= tg.readInputXmls(xmlFileForDoc);
		
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info("URL for pushing CASO Request is :"+getUrl);
		
		//data= UpdateXMLs.updateXmlAVPN(request);
		updatedRequest= UpdateXMLs.updateXmlIST(request, SORID, projId);
		
		byte[] encoded1=updatedRequest.getBytes();
		
		//Push xmls
		try 
		{
			response=tg.pushXml(encoded1, getUrl);
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			logger.info("Response not present");
		}
 		
 		logger.info("Response in format is:"+tg.formatResponseXml(response));
 		
 		List<String> orderIdAvpn= GetProjectId.getElementFromXml(response, "orderIdX9");
 		ordrDetailsAvpn.add(0,orderIdAvpn);

 		List<String> serviceTypeAvpn=GetProjectId.getElementFromXml(response, "productX9");
 		ordrDetailsAvpn.add(1,serviceTypeAvpn);
 		
 		List<String> orderNumberAvpn=GetProjectId.getElementFromXml(response, "orderNumberX9");
 		ordrDetailsAvpn.add(2,orderNumberAvpn);
 		
 		List<String> orderStatus=GetProjectId.getElementFromXml(response, "orderActionStatusX9");
 		ordrDetailsAvpn.add(3,orderStatus);
 		
 		ordrCreationDetails=null;
 		ordrCreationDetails=ordrDetailsAvpn;
 		
 		return ordrDetailsAvpn;		
	}
	
	public static ArrayList<List<String>> retreiveCASOOrdrDetails()
	{
		return ordrCreationDetails;	
	}
	
	
	public static void casoPushMACD(String env, String orderType, String SORID, String projId, String whatChange) throws Exception
	{
		isCasoClassTriggered=true;
		logger=LoggerGen.logGen(getClassName());
		casoPush(env,orderType,SORID,projId,"MACD", whatChange);
		Thread.sleep(10000);
		String response="";
		TriggerXmls tg= new TriggerXmls();
		
		ArrayList<List<String>> ordrDetailsMACD= new ArrayList<List<String>>();
		
		Document doc = null;
		byte[] encoded=(byte[]) null;
		String xmlFileForDoc=null;
		
		if(orderType.equalsIgnoreCase("AVPN") && env.equalsIgnoreCase("ST3"))
		{	
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPN_QA14_V1.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPN_QA14_V1.xml";
		}
		else if(orderType.equalsIgnoreCase("BOE"))
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_MIS_QA14_V1.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_MIS_QA14_V1.xml";
		}
		else if(orderType.equalsIgnoreCase("MIS") && whatChange.equalsIgnoreCase("ChangePortSpeed") && env.equalsIgnoreCase("ST4"))
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_MACD_v1_ChangePortSpeed_QA12.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_MACD_v1_ChangePortSpeed_QA12.xml";
		}
		else if(orderType.equalsIgnoreCase("MIS") && whatChange.equalsIgnoreCase("ChangeCPE") && env.equalsIgnoreCase("ST3"))
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_MACD_v1_ChangeCPE_QA14.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_MACD_v1_ChangeCPE_QA14.xml";
		}
		
		doc= tg.readInputXmls(xmlFileForDoc);
		
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info("URL for pushing CASO Request is :"+getUrl);
		
		List<String> orderId= GetProjectId.getElementFromXml(responsetoString, "orderIdX9");
		
		
	    
	    
		updatedRequest= UpdateXMLs.updateXml(request, orderId.get(0), projId, SORID) ;
		
	    String finalRequest= UpdateXMLs.updateXmlKeys(updatedRequest,"CHANGE PORT SPEED");
		byte[] encoded1=finalRequest.getBytes();
			
		//Push xmls
		try 
		{
			response=tg.pushXml(encoded1, getUrl);
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			logger.info("Response not present");
		}
		
		logger.info("Response in format is:"+tg.formatResponseXml(response));
		
		List<String> orderIdMACDs= GetProjectId.getElementFromXml(response, "orderIdX9");
		ordrDetailsMACD.add(0,orderIdMACDs);

 		List<String> serviceTypeMACD=GetProjectId.getElementFromXml(response, "productX9");
 		ordrDetailsMACD.add(1,serviceTypeMACD);
 		
 		List<String> orderNumberMACD=GetProjectId.getElementFromXml(response, "orderNumberX9");
 		ordrDetailsMACD.add(2,orderNumberMACD);
 		
 		List<String> orderStatusMACD=GetProjectId.getElementFromXml(response, "orderActionStatusX9");
 		ordrDetailsMACD.add(3,orderStatusMACD);
		
 		ordrCreationDetails=null;
 		ordrCreationDetails=ordrDetailsMACD;
	}

	
	
	public  static String getSorProjectId()
	{
		return data[1];
	}
}
