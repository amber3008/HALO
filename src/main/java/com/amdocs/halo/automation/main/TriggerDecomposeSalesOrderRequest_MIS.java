package com.amdocs.halo.automation.main;

import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Random;

import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.base.UpdateXMLs_HALO;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class TriggerDecomposeSalesOrderRequest_MIS 
{
	   public static String getURL;
	   public static String getRequest;
	   public static String[] data;
	   public static Logger logger = null;
	   public static String archType;
	   
	   public static String getClassName()
		 {
			 return TriggerDecomposeSalesOrderRequest_MIS.class.getName();
		 }
	  
	   public static String[] triggerDSRequest(String env, String orderType, String sorId, String projectId) throws IOException, TransformerException
	   {
		   logger=LoggerGen.logGen(getClassName());
		   TriggerXmls tg= new TriggerXmls();
		   archType="OLD";
		   getURL=GetURLFromExcel.readURLFromExcel("DECOMPOSE_SALES_ORDER_REQUEST",env);
		   Document doc = null;
		   //	   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/decomposeSalesOrderRequest.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/decomposeSalesOrderRequest.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info("URL is:"+getURL);
			
			data= UpdateXMLs_HALO.updateXmlDecompose(request, sorId,projectId);
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

	   
	 /*  public static void main(String args[]) throws IOException, TransformerException
	   {
		   TriggerDecomposeSalesOrderRequest_MIS.triggerDSRequest("ST4");
	   }*/
}
