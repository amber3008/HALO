package com.amdocs.halo.automation.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.TriggerXmls;
//import com.amdocs.halo.automation.base.UpdateXMLs;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class CompleteAllActivities
{
	public static HashMap<String, List<String>> name= new HashMap<String, List<String>>();
	public static HashMap<String, List<String>> status= new HashMap<String, List<String>>();
	public static HashMap<String, List<String>> activityId= new HashMap<String, List<String>>();
	public static HashMap<String, List<String>> planID= new HashMap<String, List<String>>();
	
	public static HashMap<String, List<String>> state= new HashMap<String, List<String>>();
	public static HashMap<String, List<String>> errorID= new HashMap<String, List<String>>();
	public static String planId;
	public static String envName;
	public static String errorId;
	public static Logger logger = null;
	public static HashMap<String,List<String>> orderDetails;

	public static String getClassName()
	{
		return CompleteAllActivities.class.getName();
	}


	public static void updateHashMap(String response) throws Exception
	{
		name=OCXOrderCreation_GAMMA.getElementFromXml(response,"ns2:activityName");

		activityId=OCXOrderCreation_GAMMA.getElementFromXml(response,"ns2:activityId");
		state=OCXOrderCreation_GAMMA.getElementFromXml(response,"ns2:state");
		planID=OCXOrderCreation_GAMMA.getElementFromXml(response,"ns2:planId");
		status=OCXOrderCreation_GAMMA.getElementFromXml(response,"ns2:status");
		planId=planID.get("ns2:planId").get(0);
		System.out.println("---------------------------updateHashMAP is Done--------------------------");

	}


	public static void getActivityInfo(String response) throws Exception
	{
		updateHashMap(response);

		int count=0;

		while(count<OCXOrderCreation_GAMMA.numberOfTagName)
		{
			System.out.println(count);
			if(status.get("ns2:status").get(count).equals("In Progress")) 
			{
				System.out.println("******************Order In Progress ***************************");
				System.out.println(name.get("ns2:activityName").get(count));
				System.out.println(status.get("ns2:status").get(count));
				System.out.println(activityId.get("ns2:activityId").get(count));


			}
			count++;

		}




	}
	
	

	public static void completeActivity(String response,String PID,String activityName,String envname) throws Exception
	{
		envName=envname;
		updateHashMap(response);
		int count=0;
		while(count<state.get("ns2:state").size())
		{

			System.out.println(count+"debug");
			if((name.get("ns2:activityName").get(count)).equals(activityName)) 
			{
				String activity=activityId.get("ns2:activityId").get(count);
				System.out.println((state.get("ns2:state").get(count)).equals("In Error"));
				if((state.get("ns2:state").get(count)).equals("In Error"))
				{
					System.out.println("********************Activity in Error*************************");
					errorId=getErrorId(planId,activity);
					errorResolved(planId,activity,errorId);
					Thread.sleep(6000);
					activityComplete(planId,activity);
					
				}
				if((state.get("ns2:state").get(count)).equals("In Progress"))
				{
					System.out.println("******************Order In Progress ***************************");
					System.out.println("activity ID "+activity);
					System.out.println("activity name "+name.get("ns2:activityName").get(count));
					activityComplete(planId,activity);
					break;
				}
			}

			count++;
		}
	}
	
	public static String getErrorId(String planId,String activityId) throws Exception
	{
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		System.out.println(envName);
		String getURL=GetURLFromExcel.readURLFromExcel("GET_ACTIVITY_REQUEST",envName);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/getActivity.xml"));
		String xmlInput = new String(encoded, StandardCharsets.UTF_8);
		String xmlInputFormatted = tg.formatResponseXml(xmlInput);
		Thread.sleep(8000);
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/getActivity.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info("URL for getHierarchy Request is : "+getURL);
		Thread.sleep(8000);
		request=request.replace("{$PlanID$}",planId);
		request=request.replace("{$ActivityID$}",activityId);
		System.out.println(request);
		byte[] encoded1 =request.getBytes();

		String response=tg.pushXml(encoded1, getURL);
		logger.info("Response in format is:"+tg.formatResponseXml(response));
		errorID=OCXOrderCreation_GAMMA.getElementFromXml(response,"errorID");
		errorId=errorID.get("errorID").get(0);
		logger.info("error ID is : "+errorId);
		return errorId;
	}
	public static void activityComplete(String pID,String aID) throws Exception
	{


		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		System.out.println(envName);
		String getURL=GetURLFromExcel.readURLFromExcel("CompleteActivity",envName);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CompleteActivity.xml"));
		String xmlInput = new String(encoded, StandardCharsets.UTF_8);
		String xmlInputFormatted = tg.formatResponseXml(xmlInput);
		Thread.sleep(8000);
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CompleteActivity.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info("URL for getHierarchy Request is : "+getURL);
		Thread.sleep(8000);
		request=request.replace("{$plainID$}",pID);
		request=request.replace("{$activityID$}",aID);
		System.out.println(request);
		byte[] encoded1 =request.getBytes();

		String response=tg.pushXml(encoded1, getURL);
		//logger.info("Response in format is:"+tg.formatResponseXml(response));

	}
	
	public static void errorResolved(String planId,String activityId,String errorID) throws FileNotFoundException, IOException, TransformerException, InterruptedException
	{
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		System.out.println(envName);
		String getURL=GetURLFromExcel.readURLFromExcel("GET_ACTIVITY_REQUEST",envName);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/errorResolvedRequest.xml"));
		String xmlInput = new String(encoded, StandardCharsets.UTF_8);
		String xmlInputFormatted = tg.formatResponseXml(xmlInput);
		Thread.sleep(8000);
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/errorResolvedRequest.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info("URL for getHierarchy Request is : "+getURL);
		Thread.sleep(8000);
		request=request.replace("{$planId$}",planId);
		request=request.replace("{$activityId$}",activityId);
		request=request.replace("{$errorId$}",errorID);
		
		System.out.println(request);
		byte[] encoded1 =request.getBytes();

		String response=tg.pushXml(encoded1, getURL);
		//logger.info("Response in format is:"+tg.formatResponseXml(response));
		
	}
}
