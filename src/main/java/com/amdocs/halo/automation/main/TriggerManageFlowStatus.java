package com.amdocs.halo.automation.main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.base.UpdateXMLs;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class TriggerManageFlowStatus 
{
	 public static String getURL;
	 public static String getRequest;
	 public static String bvoipOrderId;
	 public static String updatedRequest;
	 public static String[] sorData= new String[5];
	// public static String[] orderDetails= new String[5];
	 public static String str1[]= new String[6];
	 public static TriggerNotifyRequest tnr;
	 public static TriggerMOR_SGLevel tmor;
	 public static TriggerXmls tg; 
	 public static String typeOfOrder;
	 public static TriggerMORAVPN trigerAvpn;
	 public static String data;
	 public static TriggerMOR_OALevel tmor_oa;
	 public static String typeOfTesting;
	 public static String [] wholeOrderIdAvpn= new String[6];
	 public static Logger logger = null;
	 
	public static String getClassName()
	 {
		 return TriggerManageFlowStatus.class.getName();
	 }

	public static String[] getSorData() throws IOException, TransformerException
	{
		return TriggerMOR_SGLevel.getSorProjId();
	}
	
	public static  void pushRequest(String env) throws TransformerException, InterruptedException, IOException, InstantiationException, IllegalAccessException
	{  
		logger=LoggerGen.logGen(getClassName());
		getURL=GetURLFromExcel.readURLFromExcel("MANAGE_FLOW_STATUS",env);
		TriggerXmls tg= new TriggerXmls();
		Document doc = null;
		byte[]  encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageFlowStatusRequest.xml"));
		String  xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageFlowStatusRequest.xml";
		
		//Following used only for formatting
		String xmlInput = new String(encoded, StandardCharsets.UTF_8);
		String xmlInputFormatted = tg.formatResponseXml(xmlInput);
		doc= tg.readInputXmls(xmlFileForDoc);
		logger.info("Input in format is:"+xmlInputFormatted);
		
		//Read xml for Document Class 
		doc= tg.readInputXmls(xmlFileForDoc);
			
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		
		 bvoipOrderId= OCXOrderStatus.returnOrderId("BVOIP");
		System.out.println("bvoip order id is " +bvoipOrderId);
		 updatedRequest= UpdateXMLs.updateXmls(request,bvoipOrderId);
		
		logger.info("URL is:"+updatedRequest);
			
		byte[] encoded1 =updatedRequest.getBytes();
		//Push xmls
		String response=tg.pushXml(encoded1, getURL);
		logger.info("Response in format is:"+tg.formatResponseXml(response));
	 }
	public static  void pushRequestIPFLEXMIS(String env,String ipflex) throws TransformerException, InterruptedException, IOException, InstantiationException, IllegalAccessException
	{  
		logger=LoggerGen.logGen(getClassName());
		getURL=GetURLFromExcel.readURLFromExcel("MANAGE_FLOW_STATUS",env);
		TriggerXmls tg= new TriggerXmls();
		Document doc = null;
		byte[]  encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageFlowStatusRequest.xml"));
		String  xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageFlowStatusRequest.xml";
		
		//Following used only for formatting
		String xmlInput = new String(encoded, StandardCharsets.UTF_8);
		String xmlInputFormatted = tg.formatResponseXml(xmlInput);
		doc= tg.readInputXmls(xmlFileForDoc);
		logger.info("Input in format is:"+xmlInputFormatted);
		
		//Read xml for Document Class 
		doc= tg.readInputXmls(xmlFileForDoc);
			
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			 
		System.out.println("bvoip order id is " +ipflex);
		 updatedRequest= UpdateXMLs.updateXmls(request,ipflex);
		
		
		logger.info("URL is:"+updatedRequest);
			
		byte[] encoded1 =updatedRequest.getBytes();
		//Push xmls
		String response=tg.pushXml(encoded1, getURL);
		logger.info("Response in format is:"+tg.formatResponseXml(response));
	 }
	
}
