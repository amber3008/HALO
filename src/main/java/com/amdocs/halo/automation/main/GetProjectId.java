package com.amdocs.halo.automation.main;

import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
//import java.util.Random;


import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.ParseXmls;
import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.base.UpdateXMLs_HALO;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class GetProjectId extends ParseXmls
{
	 public static String getURL;
	 public static TriggerNotifyRequest tnr;
	 public static TriggerMOR_SGLevel tmor;
	 public static GetProjectId getProjId;   
	 public static String response;
	 public static List<String> elements;
	 public static Logger logger = null;
	   
	   public static String getClassName()
		 {
			 return GetProjectId.class.getName();
		 }
	 
	 public static String getProjIdFrmResponse(String env) throws Exception
	   {
		 logger=LoggerGen.logGen(getClassName());
		   TriggerXmls tg= new TriggerXmls();
		   getURL=GetURLFromExcel.readURLFromExcel("ORDER_SEARCH_REQUEST_OMX",env);
		   Document doc = null;
	//   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/hierarchySearchRequest.xml"));
		   
		 //Following used only for formatting
		//	String xmlInput = new String(encoded, StandardCharsets.UTF_8);
			//	String xmlInputFormatted = tg.formatResponseXml(xmlInput);
			//logger.info("Input in format is:"+xmlInputFormatted);
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/hierarchySearchRequest.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info("URL for Order Search in OMX is : "+getURL);
			
			String sgId= GetProjectId.getSgId();
			
			String xmlUpdated= UpdateXMLs_HALO.updateXml1(request,sgId);
			byte[] encoded1 =xmlUpdated.getBytes();
			Thread.sleep(7000);
			//Push xmls
			try
			{
				response=tg.pushXml(encoded1, getURL);
			//return response;
			logger.info("Response in format is:"+tg.formatResponseXml(response));
	 		elements= getElementFromXml(response,"projectId");
	 		logger.info("Project ID of SG is :"+elements.get(1));
			}
			catch(IndexOutOfBoundsException e)
			{
				response=tg.pushXml(encoded1, getURL);
			}
			
	 		return elements.get(1);
	   }
	 
	 public static List<String> getElementFromXml(String response, String tagName) throws Exception {
		    Document xmlDoc = loadXMLString(response);
		    NodeList nodeList = xmlDoc.getElementsByTagName(tagName);
		    List<String> ids = new ArrayList<String>(nodeList.getLength());
		    for(int i=0;i<nodeList.getLength(); i++) 
		    {
		        Node x = nodeList.item(i);
		        ids.add(x.getFirstChild().getNodeValue());            
		    }
		  
		    return ids;
		}
	 
	/* public static String updateXml(String UpdatedXml, String sgId) 
	   {	
			UpdatedXml = UpdatedXml.replace("{$sgId$}", sgId);
			logger.info("Request After update is :"+UpdatedXml);
			return UpdatedXml;
	   }*/
	 
	 public static String getSgId() throws IOException, TransformerException
	 {
		 String[] orderDetails=  TriggerMOR_SGLevel.getWholeOrderIds();
		 return orderDetails[1];
	 }
	   
	 /*public static void main(String args[]) throws Exception
	 {
		 GetProjectId.getProjIdFrmResponse("ST3");
	 }*/
}
