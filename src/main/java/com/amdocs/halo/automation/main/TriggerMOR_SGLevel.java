package com.amdocs.halo.automation.main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.base.UpdateXMLs;
//import com.amdocs.halo.automation.steps.OMXOrderCreationSteps;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class TriggerMOR_SGLevel extends TriggerNotifyRequest
{
	 public static String getURL;
	 public static String getRequest;
	 public static String[] sorData= new String[5];
	// public static String[] orderDetails= new String[5];;
	 public static String str1[]= new String[6];
	 public static TriggerNotifyRequest tnr;
	 public static TriggerMOR_SGLevel tmor;
	 public static TriggerXmls tg; 
	 public static byte[] encoded;
	 public static String xmlFileForDoc;
	 public static String typeOfOrder;
	 public static TriggerMORAVPN trigerAvpn;
	 public static String data[];
	 public static TriggerMOR_OALevel tmor_oa;
	 public static String typeOfTesting;
	 public static String [] wholeOrderIdAvpn= new String[6];
	 public static Logger logger = null;
	   
	   public static String getClassName()
		 {
			 return TriggerNotifyRequest.class.getName();
		 }
	 
	 public static String[] getSorData() throws IOException, TransformerException
	 {
		  return TriggerMOR_SGLevel.getSorProjId();
	 }
	 
	 public static void triggerMoRequest_SG(String env, String orderTypeOfOrder, String testingType) throws IOException, TransformerException, InterruptedException, InstantiationException, IllegalAccessException
	 {
		   logger=LoggerGen.logGen(getClassName());
		   tmor = new TriggerMOR_SGLevel();   
		   tg = new TriggerXmls();
		   typeOfOrder=orderTypeOfOrder;
		   typeOfTesting=testingType;
		   getURL=GetURLFromExcel.readURLFromExcel("MANAGE_ORDER_REQUEST",env);
		   logger.info("Type if Order is : "+typeOfOrder);
		   if(TriggerNotifyRequest.archType.equalsIgnoreCase("NEW"))
		   {
			   
			   if(typeOfOrder.equalsIgnoreCase("MIS") && OrderCreationInOCX.typeOfChange.equalsIgnoreCase("NS"))
			   {   
				   Document doc=null;
				   encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestSG_NS.xml"));
				   xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestSG_NS.xml";	       
				   tmor.pushRequest(getURL, encoded, xmlFileForDoc, testingType);
			   }
			   else if(typeOfOrder.equalsIgnoreCase("AVPN") && OrderCreationInOCX.typeOfChange.equalsIgnoreCase("NS"))
			   {
			   
				   logger.info("-------------------------Pushing AVPN SL ManageOrder Request for SG---------------------");
				   encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestAVPNSL_NS_SG.xml"));
				   xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestAVPNSL_NS_SG.xml";
				   tmor.pushRequest(getURL, encoded, xmlFileForDoc,testingType);
			   }
			   else if(typeOfOrder.equalsIgnoreCase("AVPN") && OrderCreationInOCX.typeOfChange.equalsIgnoreCase("MACD"))
			   {
				   logger.info("-------------------------Pushing AVPN MACD ManageOrder Request for SG---------------------");
				   encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestAVPN_MACD_SG.xml"));
				   xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestAVPN_MACD_SG.xml";
				   tmor.pushRequest(getURL, encoded, xmlFileForDoc,testingType);
			   }
			   else if(typeOfOrder.equalsIgnoreCase("MIS") && OrderCreationInOCX.typeOfChange.equalsIgnoreCase("MACD"))
			   {
			  // Document doc=null;
				   logger.info("-------------------------Pushing MIS MACD ManageOrder Request for SG---------------------");
				   encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestMIS_MACD_SG.xml"));
				   xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestMIS_MACD_SG.xml";
				   tmor.pushRequest(getURL, encoded, xmlFileForDoc,testingType);
			   }
			   else if(typeOfOrder.equalsIgnoreCase("BOE") && OrderCreationInOCX.typeOfChange.equalsIgnoreCase("NS"))
			   {
			  // Document doc=null;
				   logger.info("-------------------------Pushing BOE NS ManageOrder Request for SG---------------------");
				   encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestBOE_NS_SG.xml"));
				   xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestBOE_NS_SG.xml";
				   tmor.pushRequest(getURL, encoded, xmlFileForDoc,testingType);
			   }
			   else if(typeOfOrder.equalsIgnoreCase("IPFLEXMIS") && OrderCreationInOCX.typeOfChange.equalsIgnoreCase("NS"))
			   {
			  // Document doc=null;
				   logger.info("-------------------------Pushing IPFLEXMIS NS ManageOrder Request for SG---------------------");
				   encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestIPFLEXMIS_NS_SG.xml"));
				   xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestIPFLEXMIS_NS_SG.xml";
				   tmor.pushRequest(getURL, encoded, xmlFileForDoc,testingType);
			   }
		   }
		   else if(TriggerNotifyRequest.archType.equalsIgnoreCase("OLD"))
		   {
			   if(typeOfOrder.equalsIgnoreCase("MIS"))
			   {
				   logger.info("-------------------------Pushing ManageOrder Request for SG for Old Arch MIS---------------------");
				   encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequest_SG_MIS_OLD.xml"));
				   xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequest_SG_MIS_OLD.xml";
				   tmor.pushRequest(getURL, encoded, xmlFileForDoc,testingType);
			   }
			   else
			   {
				   logger.info("-------------------------Pushing ManageOrder Request for SG for Old Arch IPFLEX---------------------");
				   encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequest_SG_IPFLEX_OLD.xml"));
				   xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequest_SG_IPFLEX_OLD.xml";
				   tmor.pushRequest(getURL, encoded, xmlFileForDoc,testingType);
			   }
		   }
	}
	 
	public static void triggerMoRequest_SG_AVPNSite(String env, String testingType) throws IOException, TransformerException, InterruptedException, InstantiationException, IllegalAccessException
	{	  	
		logger=LoggerGen.logGen(getClassName());
		   getURL=GetURLFromExcel.readURLFromExcel("MANAGE_ORDER_REQUEST",env);
		   logger.info("-------------------------Pushing AVPN SITE ManageOrder Request for SG---------------------");
		   encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestAVPNSITE_NS_SG.xml"));
	       xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestAVPNSITE_NS_SG.xml";
	       tmor.pushRequest(getURL, encoded, xmlFileForDoc, testingType);
	       wholeOrderIdAvpn = TriggerMOR_SGLevel.getWholeOrderIds();
	}
		   
	public void pushRequest(String getURL,byte[] encoded, String xmlFileForDoc, String testingType) throws TransformerException, InterruptedException, IOException, InstantiationException, IllegalAccessException
	{  
		Document doc = null;
		logger=LoggerGen.logGen(getClassName());
		//Following used only for formatting
		String xmlInput = new String(encoded, StandardCharsets.UTF_8);
		String xmlInputFormatted = tg.formatResponseXml(xmlInput);
		//logger.info("Input in format is:"+xmlInputFormatted);
		
		//Read xml for Document Class 
		//String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestSG_NS.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
			
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info("URL is:"+getURL);
			
		sorData = TriggerMOR_SGLevel.getSorData();
		logger.info("SORID and Project ID is:"+sorData[1]);
		logger.info(sorData[2]);
		logger.info("Request to be updated:"+request);
		
		logger.info("-------------------------Updating Xmls--------------------------");
		if(testingType.toString().equalsIgnoreCase("IST"))
		{
			data= UpdateXMLs.updateXmlMorSGIST(request,sorData[1],sorData[2],typeOfOrder);
			
			
		}
		else
		{
			data= UpdateXMLs.updateXmlMorSG(request,sorData[1],sorData[2],typeOfOrder);
		}
		str1= data;
		String updatedRequest=data[0];
		byte[] encoded1 =updatedRequest.getBytes();
		//Push xmls
		String response=tg.pushXml(encoded1, getURL);
		logger.info("Response in format is:"+tg.formatResponseXml(response));
	 }
	   
	  /* public static String[] updateXml(String UpdatedXml, String sorId, String projId) 
	   {	
		   	Random random = new Random();
			logger.info("SOR ID is:"+sorId);
			int sgId1=random.nextInt(10000000);
			String sgId= Integer.toString(sgId1);
			int oaId1= sgId1+1;
			String oaId= Integer.toString(oaId1);
			UpdatedXml = UpdatedXml.replace("{$sorId$}", sorId);
			if(typeOfOrder.equalsIgnoreCase("MIS"))
				UpdatedXml = UpdatedXml.replace("{$projId$}", projId);
			
			UpdatedXml = UpdatedXml.replace("{$sgId$}", sgId);
			UpdatedXml = UpdatedXml.replace("{$oaId$}", oaId);
			logger.info("Request After update is :"+UpdatedXml);
			str1[0]=UpdatedXml;
			str1[1]=sgId;
			str1[2]=oaId;
			str1[3]=sorId;
			str1[5]=typeOfOrder;
			if(typeOfOrder.equalsIgnoreCase("MIS"))
				str1[4]=projId;
			return str1;
	   }
	   */
	   public static String[] getWholeOrderIds() throws IOException, TransformerException
	   {
		   return str1;
	   }
	  /* public static void main(String args[]) throws IOException, TransformerException, InstantiationException, IllegalAccessException, InterruptedException
	   {
		   triggerMoRequest_SG("ST3","MIS","NS");
	   }*/
}
