package com.amdocs.halo.automation.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//import java.nio.file.Files;
//import java.nio.file.Paths;

import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.amdocs.halo.automation.base.HaloOrGammOrder;
import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.TriggerXmls;
//import com.amdocs.halo.automation.base.UpdateXMLs_HALO;
import com.amdocs.halo.automation.base.UpdateXMLs_GAMMA;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class TriggerCNODRequest 
{
	public static String[] data;
	public static String cnodUniResponse;
	public static Logger logger = null;
	
	public static String getClassName()
	 {
		 return TriggerCNODRequest.class.getName();
	 }
	
	public static String SAREA_MACD(String eNv, String assignProductId)throws IOException, TransformerException, InterruptedException 
	{ 
		   TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST", eNv);
		   System.out.println(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/SAREA_MACD.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/SAREA_MACD.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			System.out.println(request);
			System.out.println(assignProductId);
			
			
			
			String cnod = UpdateXMLs_GAMMA.updateXML_DISCONNECT(request, assignProductId);
			
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
	
	public static String trigger_SAREA_MACD_Amend_Request(String env, String productOrderItemId, String assignedProductId) throws Throwable
	{
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("SARE_Amend", env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/SAREA_MACD_Amend.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/SAREA_MACD_Amend.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			String cnod = UpdateXMLs_GAMMA.updateSAREA_Amend(request,productOrderItemId ,assignedProductId);
			
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
	
	public static String trigger_UCPE_NS(String env) throws Throwable

	{
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
		   //envName=env;
		   //typeOfOrder=orderType;
		   TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/UCPE_NS.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/UCPE_NS.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			//logger.info("URL is:"+getURL);
			//logger.info("URL is:"+getURL);
			
			
			//byte[] encoded1 =updatedRequest.getBytes();
			byte[] encoded2 =request.getBytes();
			
			
			//Push xmls
			String response=tg.pushXml(encoded2, getURL);
			//logger.info(getURL);
			
			logger.info("Response in format is:"+tg.formatResponseXml(response));
			return response;
			
	}
	
	public static String triggerUNI_Amend_Request(String env, String productOrderItemId, String assignedProductId) throws Throwable
	{
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("AMEND_API", env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/UNI_Amend.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/UNI_Amend.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			String cnod = UpdateXMLs_GAMMA.updateSAREA_Amend(request,productOrderItemId ,assignedProductId);
			
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
	
	public static String UNI_MACD(String env, String assignProductId)throws IOException, TransformerException, InterruptedException 
	{
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
		   TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST", env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/UNI_MACD.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/UNI_MACD.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			logger.info(assignProductId);
			
			
			
			String cnod = UpdateXMLs_GAMMA.updateXML_DISCONNECT(request, assignProductId);
			
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
	
	public static String trigger_UNI_MACD_Amend_Request(String env, String productOrderItemId, String assignedProductId) throws Throwable
	{
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("AMEND_API", env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/UNI_MACD_Amend.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/UNI_MACD_Amend.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			String cnod = UpdateXMLs_GAMMA.updateSAREA_Amend(request,productOrderItemId ,assignedProductId);
			
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
	
	public static String triggerRequest(String env) throws Throwable
	{
	
		//logger=LoggerGen.logGen(getClassName());
		   //envName=env;
		   //typeOfOrder=orderType;
		   TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		   System.out.println(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/Sarea.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/Sarea.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			System.out.println(request);
			//logger.info("URL is:"+getURL);
			//logger.info("URL is:"+getURL);
			
			//data= UpdateXMLs.updateXml(request, orderType);
			//String updatedRequest=data[0];
			//byte[] encoded1 =updatedRequest.getBytes();
			byte[] encoded1 =request.getBytes();
			
			
			//Push xmls
	 		String response=tg.pushXml(encoded1, getURL);
	 		System.out.println("Response in format is:"+tg.formatResponseXml(response));
	 		//return data;
			return response;
	}
	public static String triggerRequestUNI(String env, String stateRegion) throws IOException, TransformerException
	{
		
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
	    TriggerXmls tg= new TriggerXmls();
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		logger.info(getURL);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/UNI_CNOD.xml"));
			
			//Read xml for Document Class 
	    String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/UNI_CNOD.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
			
			

		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info(request);
		//logger.info("URL is:"+getURL);
		//logger.info("URL is:"+getURL);
			
		String data= UpdateXMLs_GAMMA.updateXmlUNI(request, stateRegion);
			
		//byte[] encoded1 =updatedRequest.getBytes();
		byte[] encoded2 =data.getBytes();
			
			
		//Push xmls
		String response=tg.pushXml(encoded2, getURL);
		logger.info("Response in format is:"+tg.formatResponseXml(response));
		return response;
	}
	
	public static String VNF_MACD(String env, String assignProductId)throws IOException, TransformerException, InterruptedException 
	{
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
		   TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST", env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/VNF_MACD.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/VNF_MACD.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			logger.info(assignProductId);
			
			
			
			String cnod = UpdateXMLs_GAMMA.updateXML_DISCONNECT(request, assignProductId);
			
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
	
	public static String triggerRequestUNI_stateRegion(String env, String stateRegion) throws Throwable

	{
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
		   //envName=env;
		   //typeOfOrder=orderType;
		   TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/UNI_CNOD.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/UNI_CNOD.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			//logger.info("URL is:"+getURL);
			//logger.info("URL is:"+getURL);
			
			String data= UpdateXMLs_GAMMA.updateXmlUNI(request, stateRegion);
			
			//byte[] encoded1 =updatedRequest.getBytes();
			byte[] encoded2 =data.getBytes();
			
			
			//Push xmls
			String response=tg.pushXml(encoded2, getURL);
			//logger.info(getURL);
			logger.info("Response in format is:"+tg.formatResponseXml(response));
			return response;
			
	}
	
	public static String trigger_MTP_Request(String env, String productServiceId1, String productServiceId2, String productServiceId3) throws Throwable
	{
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
	       TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/MTP.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/MTP.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			

			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			//logger.info("URL is:"+getURL);
			//logger.info("URL is:"+getURL);
			
			String cnod = UpdateXMLs_GAMMA.update_MTP(request, productServiceId1 ,productServiceId2, productServiceId3);
			
			byte[] encoded1 =cnod.getBytes();
			//Push xmls
		  String response=tg.pushXml(encoded1, getURL);
		  logger.info("Response in format is:"+tg.formatResponseXml(response));
		  return response;	
		  }
	
	public static String trigger_EVC_MACD_Amend_Request(String env, String productOrderItemId, String assignedProductId) throws Throwable
	{
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("AMEND_API", env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/EVC_MACD_Amend.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/EVC_MACD_Amend.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			String cnod = UpdateXMLs_GAMMA.updateSAREA_Amend(request,productOrderItemId ,assignedProductId);
			
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
	
	public static String triggerEVC_MACD(String env, String assignProductId) throws Throwable
	{
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST", env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/EVC_MACD.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/EVC_MACD.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			logger.info(assignProductId);
			
			
			
			String cnod = UpdateXMLs_GAMMA.updateXML_DISCONNECT(request, assignProductId);
			
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
	
	public static String trigger_PTP_Request(String env, String productServiceId1, String productServiceId2) throws Throwable
	{
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
	       TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/PTP.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/PTP.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			

			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			//logger.info("URL is:"+getURL);
			//logger.info("URL is:"+getURL);
			
			String cnod = UpdateXMLs_GAMMA.update_PTP(request, productServiceId1 ,productServiceId2);
			
			byte[] encoded1 =cnod.getBytes();
			//Push xmls
		  String response=tg.pushXml(encoded1, getURL);
		  logger.info("Response in format is:"+tg.formatResponseXml(response));
		  return response;	
		  }
	
	public static String triggerEVC_Amend_Request(String env, String productOrderItemId, String assignedProductId) throws Throwable
	{
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CANCEL_GAMMA", env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/EVC_Amend.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/EVC_Amend.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			String cnod = UpdateXMLs_GAMMA.updateEVC_Amend(request,productOrderItemId ,assignedProductId);
			
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

	
	public static String triggerRequestUNI(String eNv) throws IOException, TransformerException
	{
	
		logger=LoggerGen.logGen(getClassName());
		HaloOrGammOrder.setIsHaloGamma("GAMMA", eNv);
		   //envName=env;
		   //typeOfOrder=orderType;
		   TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",eNv);
		   logger.info(getURL);
		   Document doc = null;
		   //   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/UNI_CNOD.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/UNI_CNOD.xml";
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
	 		cnodUniResponse=response;
	 		return response;
	}
	
	public static String getCnodUniResponse()
	{
		return cnodUniResponse;
	}
	public static String triggerRequestNotifyOn(String Env, String orderActionId)throws IOException, TransformerException, InterruptedException 
			{ 
		 logger=LoggerGen.logGen(getClassName());
					TriggerXmls tg= new TriggerXmls();
				   String getURL=GetURLFromExcel.readURLFromExcel("NOTIFY_ON", Env);
				   logger.info(getURL);
				   Document doc = null;
				   //	   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/Notify_On.xml"));
					
					//Read xml for Document Class 
					String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/Notify_On.xml";
					doc= tg.readInputXmls(xmlFileForDoc);
					
					
					//doc=tg.readXmls(xmlInputFormatted);
					String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
					logger.info(request);
					logger.info(orderActionId);
					String[] orderId = orderActionId.split("_");
					
					
					String cnod = UpdateXMLs_GAMMA.updateXML_NotifyOn(request, orderId[0]);
					
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
	public static String UNI_Disconnect(String Env, String assignProductId)throws IOException, TransformerException, InterruptedException 
	{ 
		 logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST", Env);
		   logger.info(getURL);
		   Document doc = null;
		   //	   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/UNI_Disconnect.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/UNI_Disconnect.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			logger.info(assignProductId);
			
			
			
			String cnod = UpdateXMLs_GAMMA.updateXML_DISCONNECT(request, assignProductId);
			
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
	public static String triggerRequestUNI_Cancel(String Env, String stateRegion) throws Throwable
	{
		 logger=LoggerGen.logGen(getClassName());
		
		   TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",Env);
		   logger.info(getURL);
		   Document doc = null;
		   //	   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/UNI_CNOD.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/UNI_CNOD.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			//logger.info("URL is:"+getURL);
			
			
			//data= UpdateXMLs.updateXml(request, orderType);
			//String updatedRequest=data[0];
			//byte[] encoded1 =updatedRequest.getBytes();
			byte[] encoded1 =request.getBytes();
			
			
			//Push xmls
	 		String response=tg.pushXml(encoded1, getURL);
	 		logger.info("Response in format is:"+tg.formatResponseXml(response));
	 		return response;
	}
	
	public static String triggerUNI_Cancel(String Env, String productOrderItemId) throws Throwable, TransformerException, InterruptedException
	{
		 logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CANCEL_GAMMA", Env);
		   logger.info(getURL);
		   Document doc = null;
		   //	   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/UNI_Cancel.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/UNI_Cancel.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			
			
			String orderId = productOrderItemId;
			logger.info(orderId);
			
			String cnod = UpdateXMLs_GAMMA.updateXML_Cancel(request, orderId);
			
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
	
	public static String trigger_VNF_standAlone(String env) throws Throwable

	{
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
		   //envName=env;
		   //typeOfOrder=orderType;
		   TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/VNF_standAlone.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/VNF_standAlone.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			//logger.info("URL is:"+getURL);
			//logger.info("URL is:"+getURL);
			
			
			//byte[] encoded1 =updatedRequest.getBytes();
			byte[] encoded2 =request.getBytes();
			
			
			//Push xmls
			String response=tg.pushXml(encoded2, getURL);
			//logger.info(getURL);
			
			logger.info("Response in format is:"+tg.formatResponseXml(response));
			return response;
			
	}
	/*public String getURL(String env) throws Throwable
	{
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		return getURL;
		
	}
	
	public static String trigger_VNF_standAlone(String env) throws Throwable

	{
		HaloOrGammOrder.setIsHaloGamma("GAMMA", env);
		logger=LoggerGen.logGen(getClassName());
		   //envName=env;
		   //typeOfOrder=orderType;
		   TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		   logger.info(getURL);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/VNF_standAlone.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/VNF_standAlone.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info(request);
			//logger.info("URL is:"+getURL);
			//logger.info("URL is:"+getURL);
			
			
			//byte[] encoded1 =updatedRequest.getBytes();
			byte[] encoded2 =request.getBytes();
			
			
			//Push xmls
			String response=tg.pushXml(encoded2, getURL);
			//logger.info(getURL);
			
			logger.info("Response in format is:"+tg.formatResponseXml(response));
			return response;
			
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
