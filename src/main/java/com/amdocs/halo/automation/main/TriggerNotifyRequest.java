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
import com.amdocs.halo.automation.utils.GetURLFromExcel;
import com.amdocs.halo.automation.utils.Log;

/**
 * TriggerNotifyRequest
 *
 */
public class TriggerNotifyRequest extends TriggerXmls
{
   public static String getURL;
   public static String getRequest;
   public static String[] data;
   public static String envName;
   public static Logger logger = null;
   public static String archType=null;
   public static String typeOfOrder=null;
   public static String whatToChange=null;
   public static String getClassName()
	 {
		 return TriggerNotifyRequest.class.getName();
	 }
   
   public static String[] triggerRequest(String env, String orderType) throws IOException, TransformerException
   {
	   logger=LoggerGen.logGen(getClassName());
	   envName=env;
	   typeOfOrder=orderType;
	   TriggerXmls tg= new TriggerXmls();
	   getURL=GetURLFromExcel.readURLFromExcel("NOTIFY_REQUEST",env);
	   Document doc = null;
	   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/notifyRequest.xml"));
		
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/notifyRequest.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		
		
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info("URL is:"+getURL);
		logger.info("URL is:"+getURL);
		
		data= UpdateXMLs.updateXml(request, typeOfOrder);
		String updatedRequest=data[0];
		byte[] encoded1 =updatedRequest.getBytes();
		//Push xmls
 		String response=tg.pushXml(encoded1, getURL);
 		logger.info("Response in format is:"+tg.formatResponseXml(response));
 		return data;
   }
   
   
   
   public static String[] getSorProjId() throws IOException, TransformerException
   {
	   return data;
   }
   
}
