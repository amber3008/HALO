package com.amdocs.halo.automation.main;

//import java.io.FileInputStream;
import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Properties;

import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Document;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.base.UpdateXMLs_HALO;
import com.amdocs.halo.automation.utils.GetURLFromExcel;
//import com.amdocs.halo.automation.utils.Log;

public class TriggerFulfimentComplete extends TriggerXmls
{
	 public static String getURL;
	 public static String getRequest;
	 public static String[] data;
	 public static String envName;
	 public static final String LOG_FILE="log4j.properties";
	 public static Logger logger = null;
	 
	 
	 
	 public static  void triggerFulfimentRequest(String env, String orderId, String sgId) throws IOException, TransformerException
	   {
		 logger=LoggerGen.logGen(TriggerFulfimentComplete.class.getName());
		   envName=env;
		   TriggerXmls tg= new TriggerXmls();
		   getURL=GetURLFromExcel.readURLFromExcel("FULFILLMENT_COMPLETE_REQUEST",env);
		   Document doc = null;
		   //		   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/fulfilmentOrderComplete.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/fulfilmentOrderComplete.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			//System.out.println("URL is:"+getURL);
			logger.info("URL is:"+getURL);
			
			String updatedRequest= UpdateXMLs_HALO.updateXmlFulfilment(request, orderId, sgId);
			//String updatedRequest=data[0];
			byte[] encoded1 =updatedRequest.getBytes();
			
			//Push xmls
	 		String response=tg.pushXml(encoded1, getURL);
	 		logger.info("Response in format is:"+tg.formatResponseXml(response));
	 		//
	   }
	 
	/* public static void main(String args[]) throws IOException, TransformerException
	 {
		 triggerFulfimentRequest("ST4", "121343", "123142");
	 }*/
}
