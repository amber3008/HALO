package com.amdocs.halo.automation.main;

//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.amdocs.halo.automation.base.ParseXmls;
import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.base.UpdateXMLs_HALO;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class UpdateActivityStatus_OA extends ParseXmls
{
	public static TriggerXmls  tg;
	public static String[] emptyReferenceForComparison;
	public static String getURL;
	public static String data;
	public static byte[] encoded1;
	public static String response;
	public static GetProjectId getProjId;
	public static  String[] sordata= new String[6];
	public static String[] elements;
	public static String planId;
	 
	public static void updateActivityInRequest_OA(String env) throws Exception
	{
		String[] planId=getPlanIdIdActivityIdFrmResponse_OA(env);
		String planId1=planId[planId.length-1];
		tg= new TriggerXmls();
		
		String[][] activityIdName= UpdateActivityStatus_SG.getErrorNameActivityIdFrmResponse(elements); 
		emptyReferenceForComparison= new String[20];
		
		getURL=GetURLFromExcel.readURLFromExcel("UPDATE_ACTIVITY_REQUEST",env);
		Document doc = null;
		//	byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/updateActivityStatusRequest.xml"));
			
		//Following used only for formatting
		//		String xmlInput = new String(encoded, StandardCharsets.UTF_8);
		System.out.println("Tg is: "+tg);
		////	String xmlInputFormatted = tg.formatResponseXml(xmlInput);
		//System.out.println("Input in format is:"+xmlInputFormatted);
			
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/updateActivityStatusRequest.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
			
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		System.out.println("URL for Update Activity Request  is : "+getURL);
	
		
		for(int c=0;c<activityIdName.length;++c)
		{
			for(int d=0;d<activityIdName[c].length;++d)
			{
				System.out.print(activityIdName[c][d]+"   ");
			}
			System.out.println();
		}
		boolean isErrorPresent=Arrays.deepEquals(activityIdName[1], emptyReferenceForComparison);
		if(isErrorPresent)
		{
			System.out.println("No activity in Error");
		}
		else
		{
			System.out.println("---------------------------Getting Activities In Error----------------------------------");
			for (int i=0;i<activityIdName[1].length;i=i+3)
			{
				System.out.println(activityIdName[1][i+2]);
	//			boolean notNull=("Inquire Order Data (Service Group)".equalsIgnoreCase(activityIdName[1][i+2])) ? true: false;
				/*if(notNull)
				{*/
					System.out.println("planId is:"+planId1);
					System.out.println("Status is: "+activityIdName[1][i]);
					System.out.println("Activity ID is:5"+activityIdName[1][i+1]);
					System.out.println("Activity Name is:"+activityIdName[1][i+2]);
					String errorId=UpdateActivityStatus_SG.getErrorIdActivityRequest(env, planId1, activityIdName[1][i+1]);
					UpdateActivityStatus_SG.updateAndPushErrorResolvedRequest(planId1,activityIdName[1][i+1], errorId,env);
					Thread.sleep(2000);
					data= UpdateActivityStatus_SG.updateActivityStatusToCompleteRequest(request,planId1,activityIdName[1][i+1].toString(),activityIdName[1][i+2].toString(),"Completed");
					encoded1 =data.getBytes();
					try 
					{
						response=tg.pushXml(encoded1, getURL);
					}
					catch(NullPointerException e)
					{
						System.out.println("Response not needed");
					}
					//System.out.println("Response in format is:"+tg.formatResponseXml(response));
					Thread.sleep(2000);
				//}
				//else
					//break;
				System.out.println("---------------------------------------------------------------");
			}
		}
		
		boolean isPendingPresent=Arrays.deepEquals(activityIdName[0], emptyReferenceForComparison);
		if(isPendingPresent)
		{
			System.out.println("No activity in Pending");
		}
		else
		{
			System.out.println("---------------------------Getting Activities In Pending and In Progress----------------------------------");
			for (int i=0;i<activityIdName[0].length;i=i+3)
			{
			//	boolean notNull=("Inquire Order Data (Service Group)".equalsIgnoreCase(activityIdName[0][i+2])) ? true: false;
				/*if(notNull)
				{*/
					System.out.println("Request is:"+request);
					System.out.println("planId is:"+planId1);
					System.out.println("Status is: "+activityIdName[0][i]);
					System.out.println("Activity ID is:"+activityIdName[0][i+1]);
					System.out.println("Activity Name is:"+activityIdName[0][i+2]);
					if(activityIdName[0][i+2]==null)
						break;
					else
					{
						data= UpdateActivityStatus_SG.updateActivityStatusToCompleteRequest(request,planId1,activityIdName[0][i+1].toString(),activityIdName[0][i+2].toString(),"Completed");
						
						//updatedRequest=data;
						encoded1 =data.getBytes();
						System.out.println(getURL);
						try 
						{
							response=tg.pushXml(encoded1, getURL);
						}
					//	System.out.println("Response in format is:"+tg.formatResponseXml(response));
						catch(NullPointerException e)
						{
							System.out.println("Response not needed");
							System.out.println("Exception is :"+e);
						}
						Thread.sleep(60000);
					}
				/*}
				else
					break;*/
				System.out.println("---------------------------------------------------------------");
			}
		}
		
	}
	
	public static void updateActivityInRequest_OA_Site(String env, String activityName) throws Exception
	{
		String[] planId=getPlanIdIdActivityIdFrmResponse_OA_Site(env);
		String planId1=planId[planId.length-1];
		tg= new TriggerXmls();
		
		String[][] activityIdName= UpdateActivityStatus_SG.getErrorNameActivityIdFrmResponse(elements); 
		emptyReferenceForComparison= new String[20];
		
		getURL=GetURLFromExcel.readURLFromExcel("UPDATE_ACTIVITY_REQUEST",env);
		Document doc = null;
	//	byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/updateActivityStatusRequest.xml"));
			
		//Following used only for formatting
	//	String xmlInput = new String(encoded, StandardCharsets.UTF_8);
		System.out.println("Tg is: "+tg);
	//	String xmlInputFormatted = tg.formatResponseXml(xmlInput);
		//System.out.println("Input in format is:"+xmlInputFormatted);
			
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/updateActivityStatusRequest.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
			
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		System.out.println("URL for Update Activity Request  is : "+getURL);
	
		
		for(int c=0;c<activityIdName.length;++c)
		{
			for(int d=0;d<activityIdName[c].length;++d)
			{
				System.out.print(activityIdName[c][d]+"   ");
			}
			System.out.println();
		}
		boolean isErrorNotPresent=Arrays.deepEquals(activityIdName[1], emptyReferenceForComparison);
		if(isErrorNotPresent)
		{
			System.out.println("No activity in Error");
		}
		else
		{
			System.out.println("---------------------------Getting Activities In Error----------------------------------");
			for (int i=0;i<activityIdName[1].length;i=i+3)
			{
				System.out.println(activityIdName[1][i+2]);
		//		boolean notNull=("Inquire Order Data (Service Group)".equalsIgnoreCase(activityIdName[1][i+2])) ? true: false;
				/*if(notNull)
				{*/
					System.out.println("planId is:"+planId1);
					System.out.println("Status is: "+activityIdName[1][i]);
					System.out.println("Activity ID is:5"+activityIdName[1][i+1]);
					System.out.println("Activity Name is:"+activityIdName[1][i+2]);
					String errorId=UpdateActivityStatus_SG.getErrorIdActivityRequest(env, planId1, activityIdName[1][i+1]);
					UpdateActivityStatus_SG.updateAndPushErrorResolvedRequest(planId1,activityIdName[1][i+1], errorId,env);
					Thread.sleep(2000);
					data= UpdateActivityStatus_SG.updateActivityStatusToCompleteRequest(request,planId1,activityIdName[1][i+1].toString(),activityIdName[1][i+2].toString(),"Completed");
					encoded1 =data.getBytes();
					try 
					{
						response=tg.pushXml(encoded1, getURL);
					}
					catch(NullPointerException e)
					{
						System.out.println("Response not needed");
					}
					//System.out.println("Response in format is:"+tg.formatResponseXml(response));
					Thread.sleep(2000);
				//}
				//else
					//break;
				System.out.println("---------------------------------------------------------------");
			}
		}
		
		boolean isPendingNotPresent=Arrays.deepEquals(activityIdName[0], emptyReferenceForComparison);
		if(isPendingNotPresent)
		{
			System.out.println("No activity in Pending");
		}
		else
		{
			System.out.println("---------------------------Getting Activities In Pending and In Progress----------------------------------");
			for (int i=0;i<activityIdName[0].length;i=i+3)
			{
				//		boolean notNull=("Inquire Order Data (Service Group)".equalsIgnoreCase(activityIdName[0][i+2])) ? true: false;
				/*if(notNull)
				{*/
					System.out.println("Request is:"+request);
					System.out.println("planId is:"+planId1);
					System.out.println("Status is: "+activityIdName[0][i]);
					System.out.println("Activity ID is:"+activityIdName[0][i+1]);
					System.out.println("Activity Name is:"+activityIdName[0][i+2]);
					if(activityIdName[0][i+2]==null)
						break;
					else
					{
						data= UpdateActivityStatus_SG.updateActivityStatusToCompleteRequest(request,planId1,activityIdName[0][i+1].toString(),activityIdName[0][i+2].toString(),"Completed");
						
						//updatedRequest=data;
						encoded1 =data.getBytes();
						System.out.println(getURL);
						try 
						{
							response=tg.pushXml(encoded1, getURL);
						}
					//	System.out.println("Response in format is:"+tg.formatResponseXml(response));
						catch(NullPointerException e)
						{
							System.out.println("Response not needed");
							System.out.println("Exception is :"+e);
						}
						Thread.sleep(60000);
					}
				/*}
				else
					break;*/
				System.out.println("---------------------------------------------------------------");
			}
		}
		
	}
	
	public static List<String> getProjIdFrmResponse_OA(String env) throws Exception
	   {
		   sordata=TriggerMOR_OALevel.getWholeIds();
		   TriggerXmls tg= new TriggerXmls();
		   getURL=GetURLFromExcel.readURLFromExcel("ORDER_SEARCH_REQUEST_OMX",env);
		   Document doc = null;
		   //	   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/hierarchySearchRequest.xml"));
		   
		 //Following used only for formatting
		   //		String xmlInput = new String(encoded, StandardCharsets.UTF_8);
			//		String xmlInputFormatted = tg.formatResponseXml(xmlInput);
			//System.out.println("Input in format is:"+xmlInputFormatted);
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/hierarchySearchRequest.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			System.out.println("URL for Order Search in OMX is : "+getURL);
			
			
			/*str1[0]=UpdatedXml;
			str1[1]=sgId;
			str1[2]=oaId;
			str1[3]=sorId;
			str1[4]=projId;*/
			
			String xmlUpdated= UpdateXMLs_HALO.updateXml1(request,sordata[2]);
			byte[] encoded1 =xmlUpdated.getBytes();
			
			
			//Push xmls
			try
			{
				response=tg.pushXml(encoded1, getURL);
			}
			catch(IndexOutOfBoundsException e)
			{
				response=tg.pushXml(encoded1, getURL);
			}
			//return response;
			System.out.println("Response in format is:"+tg.formatResponseXml(response));
	 		List<String> elements= getElementFromXml(response,"projectId");
	 		System.out.println("Project ID of OA is :"+elements.get(2));
	 		//return elements.get(2);
	 		return elements;
	   }
	
		public static List<String> getElementFromXml(String response, String tagName) throws Exception 
		{
			Document xmlDoc = loadXMLString(response);
			NodeList nodeList = xmlDoc.getElementsByTagName(tagName);
			List<String> ids = new ArrayList<String>(nodeList.getLength());
			for(int i=0;i<nodeList.getLength(); i++) 
			{
				Node x = nodeList.item(i);
				ids.add(x.getFirstChild().getNodeValue());    
				System.out.println("The IDs for "+tagName+" is:"+ids.get(i));
			}
			return ids;
		}
		public static List<List<String>> getActivitiesFrmResponse_OA(String env) throws Exception
		{
		  TriggerXmls tg= new TriggerXmls();
		   getURL=GetURLFromExcel.readURLFromExcel("GET_VISIBILITY_PLAN_REQUEST",env);
		   Document doc = null;
		   //	   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/getVisibilityPlanRequest.xml"));
		   
		   //Following used only for formatting
		   //	String xmlInput = new String(encoded, StandardCharsets.UTF_8);
		   //	String xmlInputFormatted = tg.formatResponseXml(xmlInput);
		    Thread.sleep(8000);
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/getVisibilityPlanRequest.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			System.out.println("URL for getHierarchy Request is : "+getURL);
			Thread.sleep(8000);
			List<String> projId= getProjIdFrmResponse_OA(env);
			System.out.println("Project IDs are : "+projId);
			
			//String xmlUpdated= GetActivitiesOfSG.updateXml(request,projId.get(2));
			String xmlUpdated= UpdateXMLs_HALO.updateXmls(request,projId.get(2));
			byte[] encoded1 =xmlUpdated.getBytes();
			
			
			//Push xmls
	 		String response=tg.pushXml(encoded1, getURL);
	 		System.out.println("Response in format is:"+tg.formatResponseXml(response));
	 		
	 		//Getting Elements for following attributes
	 		ArrayList<String> str = new ArrayList<String>();
	 		str.add("ns2:activity");
	 		str.add("ns2:activityName");
	 		str.add("ns2:status");
	 		str.add("ns2:state");
	 		str.add("ns2:activityId");
	 		
	 		return GetActivitiesOfSG.getElementFromXml(response,str);
	 		
	 }
		
		public static ArrayList<String> pushingXmlforGetVisibility(byte[] encoded) throws TransformerException
		{
			String response=tg.pushXml(encoded1, getURL);
	 		System.out.println("Response in format is:"+tg.formatResponseXml(response));
	 		
	 		//Getting Elements for following attributes
	 		ArrayList<String> str = new ArrayList<String>();
	 		str.add("ns2:activity");
	 		str.add("ns2:activityName");
	 		str.add("ns2:status");
	 		str.add("ns2:state");
	 		str.add("ns2:activityId");
	 		
	 		return str;
		}
	
		
		public static List<List<String>> getActivitiesFrmResponse_OA_Site(String env) throws Exception
		{
		  TriggerXmls tg= new TriggerXmls();
		   getURL=GetURLFromExcel.readURLFromExcel("GET_VISIBILITY_PLAN_REQUEST",env);
		   Document doc = null;
		   //	   byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/getVisibilityPlanRequest.xml"));
		   
		   //Following used only for formatting
		   //		String xmlInput = new String(encoded, StandardCharsets.UTF_8);
		   //	String xmlInputFormatted = tg.formatResponseXml(xmlInput);
		    Thread.sleep(8000);
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/getVisibilityPlanRequest.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			System.out.println("URL for getHierarchy Request is : "+getURL);
			Thread.sleep(8000);
			List<String> projId= getProjIdFrmResponse_OA(env);
			System.out.println("Project IDs are : "+projId);
			
			//String xmlUpdated= GetActivitiesOfSG.updateXml(request,projId.get(4));
			String xmlUpdated= UpdateXMLs_HALO.updateXmls(request,projId.get(4));
			byte[] encoded1 =xmlUpdated.getBytes();
			
			
			//Push xmls
	 		String response=tg.pushXml(encoded1, getURL);
	 		System.out.println("Response in format is:"+tg.formatResponseXml(response));
	 		
	 		//Getting Elements for following attributes
	 		ArrayList<String> str = new ArrayList<String>();
	 		str.add("ns2:activity");
	 		str.add("ns2:activityName");
	 		str.add("ns2:status");
	 		str.add("ns2:state");
	 		str.add("ns2:activityId");
	 		
	 		return GetActivitiesOfSG.getElementFromXml(response,str);
	 		
	 }	
	 public static String[] getPlanIdIdActivityIdFrmResponse_OA(String env) throws Exception
	 { 
		 List<List<String>> allElements= new ArrayList<List<String>>();
		 allElements=getActivitiesFrmResponse_OA(env);
		 //if(orderType=="AVPN")
		// return getPlanIdActId(allElements);
		 elements= new String[allElements.size()];
		 System.out.println(allElements.size());
		 elements = allElements.stream().flatMap(l -> l.stream()).collect(Collectors.toList()).toArray(new String[0]);
		 System.out.println(elements.length);
		 for(int i=0;i<elements.length;++i)
		 {
		 System.out.println("Activitywise:"+elements[i]);
		 }
		 planId=elements[elements.length-1];
		 return elements; 
	 }
	 
	 public static String[] getPlanIdIdActivityIdFrmResponse_OA_Site(String env) throws Exception
	 { 
		 List<List<String>> allElements= new ArrayList<List<String>>();
		 allElements=getActivitiesFrmResponse_OA_Site(env);
		 //if(orderType=="AVPN")
		// return getPlanIdActId(allElements);
		 elements= new String[allElements.size()];
		 System.out.println(allElements.size());
		 elements = allElements.stream().flatMap(l -> l.stream()).collect(Collectors.toList()).toArray(new String[0]);
		 System.out.println(elements.length);
		 for(int i=0;i<elements.length;++i)
		 {
		 System.out.println("Activitywise:"+elements[i]);
		 }
		 planId=elements[elements.length-1];
		 return elements; 
	 }
	 
	 public String getPlanId(String env)
	 {
		 return planId;
	 }
	 
	 /*public static String[] getPlanIdActId(List<List<String>> allElements) throws Exception
	 {
		 elements= new String[allElements.size()];
		 System.out.println(allElements.size());
		 elements = allElements.stream().flatMap(l -> l.stream()).collect(Collectors.toList()).toArray(new String[0]);
		 System.out.println(elements.length);
		 for(int i=0;i<elements.length;++i)
		 {
		 System.out.println("Activitywise:"+elements[i]);
		 }
		 planId=elements[elements.length-1];
		 return elements; 
	 }*/
}
