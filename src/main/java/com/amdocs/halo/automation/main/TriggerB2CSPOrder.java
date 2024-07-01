package com.amdocs.halo.automation.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.transform.TransformerException;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.amdocs.halo.automation.base.HaloOrGammaOrder;
import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.base.UpdateXMLs_GAMMA;

import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class TriggerB2CSPOrder 
{

	public static Logger logger = null;
	public static HashMap<String,List<String>> orderDetails;

	public static String getClassName()
	{
		return TriggerB2CSPOrder.class.getName();
	}

	public static String triggerRequestBTOCSP(String env,String cktID,String ptl) throws Exception
	{
		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CNOD_B2CSP.xml"));
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CNOD_B2CSP.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		Thread.sleep(6000);
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		//logger.info("Request is:"+request);
		Thread.sleep(6000);
		logger.info("URL is:"+getURL);
		Thread.sleep(6000);
		String CNOD=UpdateXMLs_GAMMA.updateBTOCSPRequest(request,cktID,ptl);
		byte[] encoded1 =CNOD.getBytes();
		//Push xmls
		String response=tg.pushXml(encoded1, getURL);
		Thread.sleep(6000);
		logger.info("Response in format is:"+tg.formatResponseXml(response));
		Thread.sleep(6000);
		//return data;
		orderDetails=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId","assignedProductId");
		logger.info(orderDetails);
		logger.info(orderDetails.get("productOrderItemId").get(0));
		return response;
	}

	public static String triggerNotifyRequest(String env, String orderType) throws IOException, TransformerException, InterruptedException
	{
		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls(); 
		String getURL=GetURLFromExcel.readURLFromExcel("NOTIFY_ON", env); 
		logger.info(getURL); 
		Document doc = null; 
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/NotifyRequest-ON.xml")); 
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/NotifyRequest-ON.xml"; 
		doc= tg.readInputXmls(xmlFileForDoc); 
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc); 
		//logger.info(request); 
		logger.info(orderType); 
		String[] orderId = orderType.split("_"); 	
		String cnod = UpdateXMLs_GAMMA.updateNotifyRequest(request, orderId[0]); 
		byte[] encoded1 =cnod.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}	
	public static String triggerRequestUNICH(String env,String apID) throws IOException, InterruptedException, TransformerException
	{
		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		logger.info(getURL);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CNOD_UNI_CHFIS.xml"));
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CNOD_UNI_CHFIS.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		Thread.sleep(6000);
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		//logger.info("Request is:"+request);
		logger.info(apID);
		String CNOD=UpdateXMLs_GAMMA.updateXML_B2CSP(request,apID);
		byte[] encoded1 =CNOD.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}

	public static String triggerRequestStartOrderProcessing(String env, String sop) throws IOException, TransformerException, InterruptedException
	{
		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls(); 
		String getURL=GetURLFromExcel.readURLFromExcel("START_ORDER_PROCESS", env); 
		logger.info(getURL); 
		Document doc = null; 
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/StartOrderProcessing.xml")); 
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/StartOrderProcessing.xml"; 
		doc= tg.readInputXmls(xmlFileForDoc); 
		//doc=tg.readXmls(xmlInputFormatted); 
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc); 
		//logger.info(request); 
		//calling to updateXMLs files
		String cnod = UpdateXMLs_GAMMA.updateRequestStartOrderProcessing(request, sop); 
		byte[] encoded1 =cnod.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}

	public static String triggerRequestMTPEVCA(String env,String ctkID) throws IOException, InterruptedException, TransformerException
	{
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		logger.info(getURL);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/MTP_EVC.xml"));
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/MTP_EVC.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		Thread.sleep(6000);
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		//logger.info("Request is:"+request);
		logger.info(ctkID);
		String CNOD=UpdateXMLs_GAMMA.updateEVCRequest(request,ctkID);
		byte[] encoded1 =CNOD.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}
	public static String triggerRequestCIRCH(String env,String apID) throws IOException, InterruptedException, TransformerException
	{
		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		logger.info(getURL);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CHANGE_CIR_REQ.xml"));
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CHANGE_CIR_REQ.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		Thread.sleep(6000);
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		//logger.info("Request is:"+request);
		logger.info(apID);
		String CNOD=UpdateXMLs_GAMMA.updateXML_CIR_REQ(request,apID);
		byte[] encoded1 =CNOD.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}

	public static String triggerRequestDisconnect_VPN(String env,String apID,String apID1,String apID2,String apID3) throws IOException, InterruptedException, TransformerException
	{

		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		logger.info(getURL);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/Disconnect_VPN.xml"));
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/Disconnect_VPN.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		Thread.sleep(6000);
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		//logger.info("Request is:"+request);
		logger.info(apID);
		String CNOD=UpdateXMLs_GAMMA.updateXML_Disconnect_VPN(request,apID,apID1,apID2,apID3);
		byte[] encoded1 =CNOD.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}

	public static String triggerRequestCancel(String env,String poId) throws IOException, InterruptedException, TransformerException
	{

		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		String getURL=GetURLFromExcel.readURLFromExcel("CANCEL_API",env);
		logger.info(getURL);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/cancel.xml"));
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/cancel.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		Thread.sleep(6000);
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		//logger.info("Request is:"+request);
		logger.info(poId);
		//----------------calling Update Xmls
		String CNOD=UpdateXMLs_GAMMA.updateXML_Cancel_REQ(request,poId);
		byte[] encoded1 =CNOD.getBytes();  
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}

	public static String triggerRequestAmend(String env,String oID,String apID) throws IOException, InterruptedException, TransformerException
	{

		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		String getURL=GetURLFromExcel.readURLFromExcel("AMEND_API",env);
		logger.info(getURL);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/Amend.xml"));
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/Amend.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		Thread.sleep(6000);
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		//logger.info("Request is:"+request);
		String CNOD=UpdateXMLs_GAMMA.updateAmendRequest(request,oID,apID);
		byte[] encoded1 =CNOD.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}

	public static String triggerDisconnctSingleAPIRequest(String env,String apID) throws IOException, InterruptedException, TransformerException
	{

		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		logger.info(getURL);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/Disconnect_Single_APi.xml"));
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/Disconnect_Single_APi.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		Thread.sleep(6000);
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		//logger.info("Request is:"+request);
		logger.info(apID);
		String CNOD=UpdateXMLs_GAMMA.updateXML_Disconnct_Single(request,apID);
		byte[] encoded1 =CNOD.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}

	public static String triggerRequestBTOB(String env) throws IOException, TransformerException, InterruptedException
	{
		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls(); 
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST", env); 
		logger.info(getURL); 
		Document doc = null; 
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CNOD_B2B.xml")); 
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CNOD_B2B.xml"; 
		doc= tg.readInputXmls(xmlFileForDoc); 
		//doc=tg.readXmls(xmlInputFormatted); 
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info(request);
		byte[] encoded1 =request.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}
	public static String triggerRequestDisconnect_TO_API(String env,String apID,String apID1) throws IOException, InterruptedException, TransformerException
	{

		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		logger.info(getURL);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CNOD_TO_API_Disconnect.xml"));
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CNOD_TO_API_Disconnect.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		Thread.sleep(6000);
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		//logger.info("Request is:"+request);
		logger.info(apID);
		String CNOD=UpdateXMLs_GAMMA.updateXML_Disconnect_TO_API(request,apID,apID1);
		byte[] encoded1 =CNOD.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}
	
	public static String triggerRequestMultiple_SCMC(String env) throws IOException, TransformerException, InterruptedException
	{
		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls(); 
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST", env); 
		logger.info(getURL); 
		Document doc = null; 
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CNOD_Multiple_SCMC.xml")); 
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CNOD_Multiple_SCMC.xml"; 
		doc= tg.readInputXmls(xmlFileForDoc); 
		//doc=tg.readXmls(xmlInputFormatted); 
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info(request);
		byte[] encoded1 =request.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}
	
	
	public static String triggerRequestAPORT(String env) throws IOException, TransformerException, InterruptedException
	{
		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls(); 
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST", env); 
		logger.info(getURL); 
		Document doc = null; 
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CNOD_APORT.xml")); 
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CNOD_APORT.xml"; 
		doc= tg.readInputXmls(xmlFileForDoc); 
		//doc=tg.readXmls(xmlInputFormatted); 
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info(request);
		byte[] encoded1 =request.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}
	
	public static String triggerRequestAPORTCH(String env,String apID) throws IOException, InterruptedException, TransformerException
	{

		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		// String  envName=env;
		//   typeOfOrder=orderType;
		TriggerXmls tg= new TriggerXmls();
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		logger.info(getURL);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/APORT_CH_REQ.xml"));
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/APORT_CH_REQ.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		Thread.sleep(6000);
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		String CNOD=UpdateXMLs_GAMMA.updateXML_APORTCH(request,apID);
		byte[] encoded1 =CNOD.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}
	
	
	
	public static String getProjIdFrmResponse_OA(String env , String orderID) throws Exception
	   {
		   
		   TriggerXmls tg= new TriggerXmls();
		   String getURL=GetURLFromExcel.readURLFromExcel("ORDER_SEARCH_REQUEST_OMX",env);
		   Document doc = null;
		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/hierarchySearchRequest.xml"));
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/hierarchySearchRequest.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			Thread.sleep(6000);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			System.out.println("URL for Order Search in OMX is : "+getURL);
			String[] orderId = orderID.split("_"); 
		
			Thread.sleep(6000);
			String xmlUpdated= UpdateXMLs_GAMMA.updateXml1(request,orderId[0]);
		
			byte[] encoded1 =xmlUpdated.getBytes();
			String response=tg.pushXml(encoded1, getURL); 
			
			//logger.info("Response in format is:"+tg.formatResponseXml(response)); 
			return response;
			
	   }

	

	/*	


	public static void main(String[] args) throws IOException, TransformerException, InterruptedException 
	{
		TriggerB2CSPOrder.triggerRequest("ST3");

	}*/



}
