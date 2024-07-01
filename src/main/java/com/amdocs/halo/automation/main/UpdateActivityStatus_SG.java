package com.amdocs.halo.automation.main;

import java.io.FileNotFoundException;
import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.Objects;
//import java.util.Random;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
//import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.amdocs.halo.automation.base.LoggerGen;
//import com.amdocs.halo.automation.base.ParseXmls;
import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.base.UpdateXMLs_HALO;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class UpdateActivityStatus_SG extends TriggerXmls
{
	 public static String getURL;
	 public static GetProjectId getProjId;
	 public static String planId;
	 public static String[] elements;
	 public static TriggerMOR_OALevel tmor_oa;
	 public static TriggerMOR_SGLevel tmor_sg;
	 public static UpdateActivityStatus_SG updateSg;
	 public static TriggerXmls  tg;
	 public static String data;
	 public static String response;
	 public static String updatedRequest;
	 public static byte[] encoded1;
	 public static String[] emptyReferenceForComparison;
	 
	 public static Logger logger = null;
	   
	 public static String getClassName()
	 {
		 return UpdateActivityStatus_SG.class.getName();
	 }
	 public static String[] getPlanIdIdActivityIdFrmResponse(String env) throws Exception
	 {
		 logger=LoggerGen.logGen(getClassName());
		 List<List<String>> allElements= new ArrayList<List<String>>();
		 allElements=GetActivitiesOfSG.getActivitiesFrmResponse(env);
		 elements= new String[allElements.size()];
		 logger.info(allElements.size());
		 elements = allElements.stream().flatMap(l -> l.stream()).collect(Collectors.toList()).toArray(new String[0]);
		 logger.info(elements.length);
		 for(int i=0;i<elements.length;++i)
		 {
		 logger.info("Activitywise:"+elements[i]);
		 }
		 planId=elements[elements.length-1];
		 return elements; 
	 }
	 
	public static String[][] getErrorNameActivityIdFrmResponse(String[] elements1) throws Exception
	 {
		logger=LoggerGen.logGen(getClassName());
		//String lineSeparator = System.getProperty("line.separator");
		for(String str:elements1)
		{
			logger.info(str);
		}
		String[] activityWithError=new String[20];
		String[] activityWithPending=new String[20];
		//		String[] activityName=new String[4];
		String[][] activitiesDistinguished= new String[2][20];
		int i=-2;
        logger.info("============For getting the Error================");
		//int j=0;
		int k=0;
		int l=0;
		while(i<elements1.length)
		{
			i=i+4;
			if(i>elements1.length)
				break;
			
			if(elements1[i].equalsIgnoreCase("In Error") || elements1[i].equalsIgnoreCase("Pending In Error"))
			{
				activityWithError[k]=elements1[i];
				activityWithError[k+1]=elements1[i+1];
				activityWithError[k+2]=elements1[i-2];	
				//activityName[j]=elements1[i-2];
				logger.info(k);
				k=k+3;
			}
			if(elements1[i].equalsIgnoreCase("In Progress"))
			{
				activityWithPending[l]=elements1[i];
				activityWithPending[l+1]=elements1[i+1];
				activityWithPending[l+2]=elements1[i-2];
				logger.info(l);
				l=l+3;
			}
		
		}
		for(int a=0;a<activityWithPending.length;++a)
		{
			logger.info("All activities in Pending:"+activityWithPending[a]);
			activitiesDistinguished[0][a]=activityWithPending[a];
		}
		for(int b=0;b<activityWithError.length;++b)
		{
			logger.info("All activities in Error:"+activityWithError[b]);
			activitiesDistinguished[1][b]=activityWithError[b];
		}
		
		logger.info("---------------------All Activities Are--------------------");
		for(int c=0;c<activitiesDistinguished.length;++c)
		{
			for(int d=0;d<activitiesDistinguished[c].length;++d)
			{
				System.out.print(activitiesDistinguished[c][d]+"   ");
			}
			//logger.info();
		}
		return activitiesDistinguished;
	 }
	
	public static String[] retreiveAllElements() throws Exception
	 {
		
		return elements;
	 }
	public static String getPlanIdFrmResponse() throws Exception
	 {
		
		return planId;
	 }
	
	public static void updateActivityInRequest(String env) throws Exception
	{
		logger=LoggerGen.logGen(getClassName());
		String planId1= getPlanIdFrmResponse();
		tg= new TriggerXmls();
		//		UpdateActivityStatus_SG updateSg1 = new UpdateActivityStatus_SG();
		String[] elements1=retreiveAllElements();
		String[][] activityIdName= getErrorNameActivityIdFrmResponse(elements1); 
		emptyReferenceForComparison= new String[20];
		
		getURL=GetURLFromExcel.readURLFromExcel("UPDATE_ACTIVITY_REQUEST",env);
		Document doc = null;
//		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/updateActivityStatusRequest.xml"));
			
		//Following used only for formatting
		//	String xmlInput = new String(encoded, StandardCharsets.UTF_8);
		logger.info("Tg is: "+tg);
		//	String xmlInputFormatted = tg.formatResponseXml(xmlInput);
		//logger.info("Input in format is:"+xmlInputFormatted);
			
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/updateActivityStatusRequest.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
			
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info("URL is:"+getURL);
	
		
		for(int c=0;c<activityIdName.length;++c)
		{
			for(int d=0;d<activityIdName[c].length;++d)
			{
				System.out.print(activityIdName[c][d]+"   ");
			}
		}
		boolean isErrorPresent=Arrays.deepEquals(activityIdName[1], emptyReferenceForComparison);
		if(isErrorPresent)
		{
			logger.info("No activity in Error");
		}
		else
		{
			logger.info("---------------------------Getting Activities In Error----------------------------------");
			for (int i=0;i<activityIdName[1].length;i=i+3)
			{
				logger.info(activityIdName[1][i+2]);
				boolean notNull=("Inquire Order Data (Service Group)".equalsIgnoreCase(activityIdName[1][i+2])) ? true: false;
				if(notNull)
				{
					logger.info("planId is:"+planId1);
					logger.info("Status is: "+activityIdName[1][i]);
					logger.info("Activity ID is:5"+activityIdName[1][i+1]);
					logger.info("Activity Name is:"+activityIdName[1][i+2]);
					String errorId=getErrorIdActivityRequest(env, planId1, activityIdName[1][i+1]);
					updateAndPushErrorResolvedRequest(planId1,activityIdName[1][i+1], errorId,env);
					Thread.sleep(2000);
					data= updateActivityStatusToCompleteRequest(request,planId1,activityIdName[1][i+1].toString(),activityIdName[1][i+2].toString(),"Completed");
					encoded1 =data.getBytes();
					try 
					{
						response=tg.pushXml(encoded1, getURL);
					}
					catch(NullPointerException e)
					{
						logger.info("Response not needed");
						logger.info("Exception is :"+e);
					}
					//logger.info("Response in format is:"+tg.formatResponseXml(response));
					Thread.sleep(2000);
				}
				else
					break;
				logger.info("---------------------------------------------------------------");
			}
		}
		boolean isPendingPresent=Arrays.deepEquals(activityIdName[0], emptyReferenceForComparison);
		if(isPendingPresent)
		{
			logger.info("No activity in Pending");
		}
		else
		{
			logger.info("---------------------------Getting Activities In Pending and In Progress----------------------------------");
			for (int i=0;i<activityIdName[0].length;i=i+3)
			{
				boolean notNull=("Inquire Order Data (Service Group)".equalsIgnoreCase(activityIdName[0][i+2])) ? true: false;
				if(notNull)
				{
					logger.info("Request is:"+request);
					logger.info("planId is:"+planId1);
					logger.info("Status is: "+activityIdName[0][i]);
					logger.info("Activity ID is:"+activityIdName[0][i+1]);
					logger.info("Activity Name is:"+activityIdName[0][i+2]);
					Thread.sleep(2000);
					data= updateActivityStatusToCompleteRequest(request,planId1,activityIdName[0][i+1].toString(),activityIdName[0][i+2].toString(),"Completed");
					//updatedRequest=data;
					encoded1 =data.getBytes();
					logger.info(getURL);
					try 
					{
						response=tg.pushXml(encoded1, getURL);
					}
					//logger.info("Response in format is:"+tg.formatResponseXml(response));
					catch(NullPointerException e)
					{
						logger.info("Response not needed");
						logger.info("Exception is :"+e);
					}
					Thread.sleep(2000);
				}
				else
					break;
				logger.info("---------------------------------------------------------------");
			}
		}
	}
	
	public static String getErrorIdActivityRequest(String env, String planId, String activityIdName) throws Exception
	{
		logger=LoggerGen.logGen(getClassName());
		getURL=GetURLFromExcel.readURLFromExcel("GET_ACTIVITY_REQUEST",env);
		Document doc = null;
		//	byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/getActivityRequest.xml"));
		//		String xmlInput = new String(encoded, StandardCharsets.UTF_8);
		//	String xmlInputFormatted = tg.formatResponseXml(xmlInput);
		
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/getActivityRequest.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info("URL is:"+getURL);
		
		//data= updateXml(request,planId,activityIdName);
		data=UpdateXMLs_HALO.updateXml2(request,planId,activityIdName);
		updatedRequest=data;
		encoded1 =updatedRequest.getBytes();
		response=tg.pushXml(encoded1, getURL);
		logger.info("Response in format is:"+tg.formatResponseXml(response));
		Document xmlDoc = loadXMLString(response);
		//String responseInString= tg.retreiveXmlInputDocumentToXmlFile(xmlDoc);
		NodeList nodes = xmlDoc.getElementsByTagName("errorID");
		logger.info(nodes.getLength());
		Node node= nodes.item(0);
		String errorId = node.getFirstChild().getNodeValue();
		//String errorId = elem.getElementsByTagName("errorID").item(0).getTextContent();
		logger.info(errorId);
		return errorId;
		
	}
	
	 
	public static String updateActivityStatusToCompleteRequest(String UpdatedXml, String planId1, String activityId, String activityName, String status) throws FileNotFoundException, IOException 
	 {	
		logger=LoggerGen.logGen(getClassName());
		UpdatedXml = UpdatedXml.replace("{$planId$}", planId1);
		UpdatedXml = UpdatedXml.replace("{$activityId$}", activityId);
		//UpdatedXml = UpdatedXml.replace("{$activityName$}", activityName);
		UpdatedXml = UpdatedXml.replace("{$status$}", status);
		logger.info("Request After update is :"+UpdatedXml);
	 	return UpdatedXml;
     }
	
	public static String updateXmlWithErrorId(String UpdatedXml, String planId1, String activityIdName, String errorId) throws FileNotFoundException, IOException 
	 {	
		logger=LoggerGen.logGen(getClassName());
		UpdatedXml = UpdatedXml.replace("{$planId$}", planId1);
		UpdatedXml = UpdatedXml.replace("{$activityId$}", activityIdName);
		UpdatedXml = UpdatedXml.replace("{$errorId$}", errorId);
		logger.info("Request After update is :"+UpdatedXml);
	 	return UpdatedXml;
    }
	
	public static void updateAndPushErrorResolvedRequest(String planId, String activityIdName, String errorId, String env) throws Exception
	{
		logger=LoggerGen.logGen(getClassName());
		logger.info("--------------------Updating the Error Resolution------------------");
		tg= new TriggerXmls(); 
		String getURL1=GetURLFromExcel.readURLFromExcel("GET_ACTIVITY_REQUEST",env);
		Document doc = null;
		//	byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/errorResolvedRequest.xml"));
			
			//Following used only for formatting
		//	String xmlInput = new String(encoded, StandardCharsets.UTF_8);
			logger.info("Tg is: "+tg);
			//		String xmlInputFormatted = tg.formatResponseXml(xmlInput);
			//logger.info("Input in format is:"+xmlInputFormatted);
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/errorResolvedRequest.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			logger.info("URL is:"+getURL1);
			request = request.replace("{$planId$}", planId);
			request = request.replace("{$activityId$}", activityIdName);
			request = request.replace("{$errorId$}", errorId);
			logger.info("Request After update is :"+request);
			//updatedRequest=data;
			encoded1 =request.getBytes();
			try 
			{
				response=tg.pushXml(encoded1, getURL1);
			}
			catch(NullPointerException e)
			{
				logger.info("Response not needed");
				logger.info("Exception is :"+e);
			}
			//logger.info("Response in format is:"+tg.formatResponseXml(response));
			logger.info("--------------------Error Resolution Done------------------");
	}
	 
	/* public static void main(String args[]) throws Exception
	 {
		 updateSg.getPlanIdIdActivityIdFrmResponse("ST3");
		 updateSg.updateActivityInRequest("ST3");
	 }*/
	 
}
