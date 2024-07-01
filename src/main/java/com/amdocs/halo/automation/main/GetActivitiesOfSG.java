package com.amdocs.halo.automation.main;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.ListIterator;
//import java.util.Random;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.ParseXmls;
import com.amdocs.halo.automation.base.TriggerXmls;

import com.amdocs.halo.automation.base.UpdateXMLs_GAMMA;
import com.amdocs.halo.automation.base.UpdateXMLs_HALO;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class GetActivitiesOfSG extends ParseXmls
{
	public static String getURL;
	public static GetActivitiesOfSG getProjId;
	public static Logger logger = null;
	   
	   public static String getClassName()
		 {
			 return GetActivitiesOfSG.class.getName();
		 } 
	   public static String getActivitiesFrmResponse(String env,String projId) throws Exception
		 {
			 logger=LoggerGen.logGen(getClassName());
			  TriggerXmls tg= new TriggerXmls();
			   getURL=GetURLFromExcel.readURLFromExcel("GET_VISIBILITY_PLAN_REQUEST",env);
			   Document doc = null;
			   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/getVisibilityPlanRequest.xml"));
			   
			   //Following used only for formatting
				String xmlInput = new String(encoded, StandardCharsets.UTF_8);
				String xmlInputFormatted = tg.formatResponseXml(xmlInput);
			    Thread.sleep(8000);
				
				//Read xml for Document Class 
				String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/getVisibilityPlanRequest.xml";
				doc= tg.readInputXmls(xmlFileForDoc);
				
				//doc=tg.readXmls(xmlInputFormatted);
				String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
				logger.info("URL for getHierarchy Request is : "+getURL);
				Thread.sleep(8000);
				//String projId= GetProjectId.getProjIdFrmResponse(env);
				
				String xmlUpdated= UpdateXMLs_GAMMA.updateXmls(request,projId);
				byte[] encoded1 =xmlUpdated.getBytes();
				
				
				//Push xmls
		 		String response=tg.pushXml(encoded1, getURL);
		 		logger.info("Response in format is:"+tg.formatResponseXml(response));
		 		
		 		//Getting Elements for following attributes
		 		
		 		
		 		return response;
		 		
		 }
	 
	 public static List<List<String>> getActivitiesFrmResponse(String env) throws Exception
	 {
		 logger=LoggerGen.logGen(getClassName());
		  TriggerXmls tg= new TriggerXmls();
		   getURL=GetURLFromExcel.readURLFromExcel("GET_VISIBILITY_PLAN_REQUEST",env);
		   Document doc = null;
		   //	   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/getVisibilityPlanRequest.xml"));
		   
		   //Following used only for formatting
		   //		String xmlInput = new String(encoded, StandardCharsets.UTF_8);
			//		String xmlInputFormatted = tg.formatResponseXml(xmlInput);
		    Thread.sleep(8000);
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/getVisibilityPlanRequest.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info("URL for getHierarchy Request is : "+getURL);
			Thread.sleep(8000);
			String projId= GetProjectId.getProjIdFrmResponse(env);
			
			String xmlUpdated= UpdateXMLs_HALO.updateXmls(request,projId);
			byte[] encoded1 =xmlUpdated.getBytes();
			
			
			//Push xmls
	 		String response=tg.pushXml(encoded1, getURL);
	 		logger.info("Response in format is:"+tg.formatResponseXml(response));
	 		
	 		//Getting Elements for following attributes
	 		ArrayList<String> str = new ArrayList<String>();
	 		str.add("ns2:activity");
	 		str.add("ns2:activityName");
	 		str.add("ns2:status");
	 		str.add("ns2:state");
	 		str.add("ns2:activityId");
	 		
	 		return getElementFromXml(response,str);
	 		
	 }
	 
	/* public static String updateXml(String UpdatedXml, String projId) 
	   {
			UpdatedXml = UpdatedXml.replace("{$projectId$}", projId);
			logger.info("Request After update is :"+UpdatedXml);
			return UpdatedXml;
	   }*/
	 
	 public static List<List<String>> getElementFromXml(String response, ArrayList<String> attributes) throws Exception 
	 {
		 List<List<String>> allElements= new ArrayList<List<String>>();
		 Document xmlDoc = loadXMLString(response);
		 int i=0;
		 NodeList nodes = xmlDoc.getElementsByTagName(attributes.get(0));
		 attributes.remove(0);
		 for(i=0; i < nodes.getLength(); i++) 
		 {
		  	List<String> ids = new ArrayList<String>(nodes.getLength());
		   	Element elem=(Element)nodes.item(i);
		   	for(String name :attributes) 
		   	{
		   		String value = elem.getElementsByTagName(name).item(0).getTextContent();
	    		ids.add(value);	
		    }
		    allElements.add(i,ids);
		  }
		 
		// Arrays.asList(getPlanId(response));
		 
		 allElements.add(allElements.size(), Arrays.asList(getPlanId(response)));
		 return allElements;
	}

	 public static String getPlanId(String response) throws Exception
	 {
		 logger=LoggerGen.logGen(getClassName());
		 logger.info("Response is : "+response);
		 Document xmlDoc = loadXMLString(response);
		 NodeList nodes= xmlDoc.getElementsByTagName("ns2:planId");
		    Node x = nodes.item(0);
 		 return x.getTextContent();
	 }
	
	 
	/* public static void main(String args[]) throws Exception
	 {
		 logger.info(GetActivitiesOfSG.getActivitiesFrmResponse("ST3"));
	 }*/
}
