package com.amdocs.halo.automation.main;

//import java.io.IOException;

//import java.nio.file.Files;
//import java.nio.file.Paths;

//import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.TriggerXmls;
//import com.amdocs.halo.automation.base.UpdateXMLs_HALO;
import com.amdocs.halo.automation.base.UpdateXMLs_GAMMA;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class TriggerSareaRequest 
{
	public static Logger logger = null;
	public static String getClassName()
	 {
		 return TriggerSareaRequest.class.getName();
	 }
	
	public static String triggerRequest(String env) throws Throwable
	{
	
		logger=LoggerGen.logGen(getClassName());
		   //envName=env;
		   //typeOfOrder=orderType;
		   TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		   logger.info(getURL);
		   Document doc = null;
		   //	   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/Sarea.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/Sarea.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			//logger.info("URL is:"+getURL);
			//logger.info("URL is:"+getURL);
			
			//data= UpdateXMLs.updateXml(request, orderType);
			//String updatedRequest=data[0];
			//byte[] encoded1 =updatedRequest.getBytes();
			byte[] encoded1 =request.getBytes();
			
			
			//Push xmls
	 		String response=tg.pushXml(encoded1, getURL);
	 		logger.info("Response in format is:"+tg.formatResponseXml(response));
	 		//return data;
			return response;
	}
	
	public static String triggerSAREA_Amend_Request(String Env, String productOrderItemId, String assignedProductId) throws Throwable
	{
		TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CANCEL_GAMMA", Env);
		   System.out.println(getURL);
		   Document doc = null;
		   //		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/Amend.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/Amend.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			System.out.println(request);
			String cnod = UpdateXMLs_GAMMA.updateSAREA_Amend(request,productOrderItemId ,assignedProductId);
			
			//logger.info("URL is:"+getURL);
			//logger.info("URL is:"+getURL);
			
			//data= UpdateXMLs.updateXml(request, orderType);
			//String updatedRequest=data[0];
			//byte[] encoded1 =updatedRequest.getBytes();
			byte[] encoded1 =cnod.getBytes();
				//Push xmls
			String response=tg.pushXml(encoded1, getURL);
			System.out.println("Response in format is:"+tg.formatResponseXml(response));
			return response;
	}
	/*public static String triggerSAREA_Amend_Request(String Env, String productNames) throws Throwable
	{
		TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("SARE_Amend", Env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/SAREA_Amend.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/SAREA_Amend.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			
			
			
			
			
			String cnod = UpdateXml_Others.updateSAREA_Amend(request, productNames);
			
			//logger.info("URL is:"+getURL);
			//logger.info("URL is:"+getURL);
			
			//data= UpdateXMLs.updateXml(request, orderType);
			//String updatedRequest=data[0];
			//byte[] encoded1 =updatedRequest.getBytes();
			byte[] encoded1 =cnod.getBytes();
				//Push xmls
			String response=tg.pushXml(encoded1, getURL);
			logger.info("Response in format is:"+tg.formatResponseXml(response));
			return response;
	}
	/*public static String triggerSAREA_Amend_Request_B(String Env, String productOrderItemId,String assignedProductId) throws Throwable
	{
		TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("SARE_Amend", Env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/SAREA_Amend.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/SAREA_Amend.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			
			
			
			String orderId1 = assignedProductId;
			logger.info(orderId1);
			
			String cnod = UpdateXml_Others.updateSAREA_Amend_B(request,orderId1);
			
			//logger.info("URL is:"+getURL);
			//logger.info("URL is:"+getURL);
			
			//data= UpdateXMLs.updateXml(request, orderType);
			//String updatedRequest=data[0];
			//byte[] encoded1 =updatedRequest.getBytes();
			byte[] encoded1 =cnod.getBytes();
				//Push xmls
			String response=tg.pushXml(encoded1, getURL);
			logger.info("Response in format is:"+tg.formatResponseXml(response));
			return response;
	}
	
	
	/*public String getURL(String env) throws Throwable
	{
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		return getURL;
		
	}
	
	public String pushxml(String request, String getURL) throws Throwable
	{
		byte[] encoded1 =request.getBytes();
		//Push xmls
		TriggerXmls tg= new TriggerXmls();
		String response=tg.pushXml(encoded1, getURL);
		//logger.info("Response in format is:"+tg.formatResponseXml(response));
		return response;
		
	}*/
}
