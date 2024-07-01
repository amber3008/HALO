package com.amdocs.halo.automation.main;

import static io.restassured.RestAssured.given;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
//import org.w3c.dom.Document;

import com.amdocs.halo.automation.base.LoggerGen;
//import com.amdocs.halo.automation.base.TriggerXmls;
//import com.amdocs.halo.automation.steps.GAMMAOrderCreation_Steps;
import com.amdocs.halo.automation.utils.GetURLFromExcel;
//import com.jayway.jsonpath.DocumentContext;
//import com.jayway.jsonpath.JsonPath;

//import io.restassured.RestAssured;
//import io.restassured.mapper.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


public class OCXOrderStatus 
{
	
	public static String orderIDMis=null;
	public static String orderIdAcms=null;
	public static String orderIDIpflex=null;
	public static Logger logger = null;
	public static ArrayList<String> arMisAvpn= new ArrayList<String>();
	public static String url;
	public static HashMap<String,String> orderActionIdswType;
	public static HashMap<String,String> orderIdActionStatus;
	public static int ipflex=0;
	public static int avpnSite=0;
	
	   
	   public static String getClassName()
		 {
			 return OCXOrderStatus.class.getName();
		 }
	   
	   public static void updateUrl(String env) throws IOException
	   {
		   String getURL=GetURLFromExcel.readURLFromExcel("ORDER_SEARCH_REQUEST_OCX",env);
		   url=getURL;   
	   }
	   
	public static ArrayList<String> getOcxOrderStatusUrl(String sorId, String env, String archType) throws InstantiationException, IllegalAccessException, IOException, InterruptedException
	{	
		updateUrl(env);
		logger=LoggerGen.logGen(getClassName());
		ArrayList<String> ar= new ArrayList<String>();
		OCXOrderStatus ocxStatus= new OCXOrderStatus();
		String exampleRequest = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/SearchOrderRequest.json"), StandardCharsets.UTF_8);
		logger.info(exampleRequest);
		logger.info("Getting response for SORID :"+sorId+" with URL : "+url+sorId);
		Thread.sleep(10000);	
		Response response =given()
								.body(exampleRequest)
								.header("UAMS_USER", "Asmsa1")
								.header("UAMS_PASS", "Asmsa1")
								.contentType("application/json")
						 .when()
						 		.accept(ContentType.JSON)
						 		.post(url+sorId)
						  .then()
						  		 .contentType(ContentType.JSON).extract().response();	
		
		ocxStatus.getOrderAttributes(response);
		
		String status ="";
		if(TriggerNotifyRequest.archType.equalsIgnoreCase("OLD"))
		{	
			if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("MIS") || TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("IPFLEXMIS"))
				status= returnStatus("MISM");
		}
		else
		{	
			if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("AVPN"))
				status= returnStatus("SLCM");
			else if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("BOE"))
				status= returnStatus("BVOIP");
			else
				status= returnStatus("SCM");
		}
		ar.add(status);
		
		//	String orderType="";
		
		logger.info("Status is : "+status);
		if(archType.toString().equalsIgnoreCase("OLD"))
		{	
			orderIDMis= orderActionIdswType.get("MISM").toString();
			ar.add(orderIDMis);
			System.out.println("MIS Order ID is : "+orderIDMis);
			orderIdAcms = orderActionIdswType.get("ACSM").toString();
			System.out.println("ACSM Order ID is : "+orderIdAcms);
			
			if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("IPFLEXMIS"))
				orderIDIpflex = orderActionIdswType.get("BVOIP").toString();
				
		}
		if(archType.toString().equalsIgnoreCase("NEW"))
		{	
			if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("AVPN") && avpnSite==1)
				orderIDMis= orderActionIdswType.get("SCM").toString();
			if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("AVPN") && avpnSite==0)
			{
				orderIDMis= orderActionIdswType.get("SLCM").toString();
				avpnSite++;
			}
			if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("BOE"))
				orderIDMis= orderActionIdswType.get("BVOIP").toString();
			
			ar.add(orderIDMis);
			orderIdAcms=null;
		}
		
		logger.info("Order ID is :"+orderIDMis);
		String SgId= response.jsonPath().getString("SearchOrderResponse.orderDataX9[0].orderIdX9");
		ar.add(SgId);
		
		ar.add(orderIdAcms);
		ar.add(orderIDIpflex);
		
		logger.info("SG ID is :"+SgId);
		arMisAvpn=ar;
		return ar;
	}
	
	public void getOrderAttributes(Response response)
	{
		 HashMap<String,String> orderActionIdswTyp=new HashMap<String,String>(); 
		 HashMap<String,String> orderIdActionsStatus=new HashMap<String,String>();
		 ArrayList<String> serviceType= new ArrayList<String>();
		 ArrayList<String> orderActionIds= new ArrayList<String>();
		 ArrayList<String> statuses= new ArrayList<String>();
		 
		 int end=2;
		 int k=0;
		 for(int j=0; j<end; ++j)
		 {
			 for(int i=0;i<5;++i)
			 {	 											   
				 serviceType.add(response.jsonPath().getString("SearchOrderResponse.orderDataX9["+j+"].orderActionDataX9["+i+"].serviceTypeX9"));
				 orderActionIds.add(response.jsonPath().getString("SearchOrderResponse.orderDataX9["+j+"].orderActionDataX9["+i+"].orderActionIdX9"));
				 statuses.add(response.jsonPath().getString("SearchOrderResponse.orderDataX9["+j+"].orderActionDataX9["+i+"].statusX9"));
				 orderIdActionsStatus.put(serviceType.get(k), statuses.get(k));
				 orderActionIdswTyp.put(serviceType.get(k), orderActionIds.get(k));	
				 k++;
			 }
		 }
		 for(Map.Entry<String,String> entry: orderIdActionsStatus.entrySet())
		 {
			 logger.info(entry.getKey() + "  :  " + entry.getValue());
		 }
		 System.out.println("--------------------------------------------------------------------------------");
		 for(Map.Entry<String,String> entry: orderActionIdswTyp.entrySet())
		 {
			 logger.info(entry.getKey() + "  :  " + entry.getValue());
		 }
		 
		 orderActionIdswType=orderActionIdswTyp;
		 orderIdActionStatus=orderIdActionsStatus;
	}
	
	public static String returnStatus(String orderType)
	{
		return orderIdActionStatus.get(orderType);
	}
	
	public static String returnOrderId(String orderType)
	{
		return orderActionIdswType.get(orderType);
	}
	
	public static ArrayList<String> getOcxOrderStatusUrlForAVPNSITE(String sorId, String env, String archType) throws InstantiationException, IllegalAccessException, IOException, InterruptedException
	{
		logger=LoggerGen.logGen(getClassName());
		ArrayList<String> ar= new ArrayList<String>();
		ArrayList<String> arAvpn= new ArrayList<String>();
		String getURL=GetURLFromExcel.readURLFromExcel("ORDER_SEARCH_REQUEST_OCX",env);
		String exampleRequest = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/SearchOrderRequest.json"), StandardCharsets.UTF_8);
		logger.info(exampleRequest);
		logger.info("Getting response for SORID :"+sorId+" with URL : "+getURL+sorId);
		Thread.sleep(10000);	
		ValidatableResponse resp =given()
								.body(exampleRequest)
								.header("UAMS_USER", "Asmsa1")
								.header("UAMS_PASS", "Asmsa1")
								.contentType("application/json")
						 .when()
						 		.accept(ContentType.JSON)
						 		.post(getURL+sorId)
						  .then()
						  		 .contentType(ContentType.JSON);
		
		Response response=resp.extract().response();
		resp.extract().jsonPath().getList("serviceTypeX9");
		
		
		logger.info(response.body());
		String status= response.jsonPath().getString("SearchOrderResponse.orderDataX9[1].orderActionDataX9[0].statusX9");
		ar.add(status);
		
		orderIDMis= response.jsonPath().getString("SearchOrderResponse.orderDataX9[1].orderActionDataX9[0].orderNumberX9");
		ar.add(orderIDMis);
		
		logger.info("Order ID is :"+orderIDMis);
		
		String SgId= response.jsonPath().getString("SearchOrderResponse.orderDataX9[1].orderIdX9");
		ar.add(SgId);
		
		logger.info("SG ID is :"+SgId);
		
		arAvpn=ar;
		arMisAvpn=arAvpn;
		return ar;
	}
	
	
	public static ArrayList<String> getOcxOrderStatusUrlForOrderId(String orderId, String env, String archType) throws InstantiationException, IllegalAccessException, IOException, InterruptedException
	{
		logger=LoggerGen.logGen(getClassName());
		ArrayList<String> ar= new ArrayList<String>();
		String getURL=GetURLFromExcel.readURLFromExcel("ORDER_SEARCH_REQUEST_ORDERID_OCX",env);
		String exampleRequest = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/SearchOrderRequest.json"), StandardCharsets.UTF_8);
		logger.info(exampleRequest);
		logger.info("Getting response for ORDERID :"+orderId+" with URL : "+getURL+orderId);
		Thread.sleep(10000);	
		ValidatableResponse resp =given()
								.body(exampleRequest)
								.header("UAMS_USER", "Asmsa1")
								.header("UAMS_PASS", "Asmsa1")
								.contentType("application/json")
						 .when()
						 		.accept(ContentType.JSON)
						 		.post(getURL+orderId)
						  .then()
						  		 .contentType(ContentType.JSON);
		
		Response response=resp.extract().response();
		resp.extract().jsonPath().getList("serviceTypeX9");
		
		
		logger.info(response.body());
		String status= response.jsonPath().getString("SearchOrderResponse.orderDataX9[0].orderActionDataX9[0].statusX9");
		ar.add(status);
		logger.info("Status of order is :"+status);
		
		orderIDMis= response.jsonPath().getString("SearchOrderResponse.orderDataX9[0].orderActionDataX9[0].orderNumberX9");
		ar.add(orderIDMis);						   
		
		logger.info("Order ID is :"+orderIDMis);
		
		String SgId= response.jsonPath().getString("SearchOrderResponse.orderDataX9[0].orderIdX9");
		ar.add(SgId);
		
		logger.info("SG ID is :"+SgId);
		
		
		return ar;
	}
	
	
	/*public static void getDataFromXmlResponse() throws InstantiationException, IllegalAccessException, IOException, InterruptedException
	{
		String getURL=GetURLFromExcel.readURLFromExcel("CASO_REQUEST","ST4");
		System.out.println(getURL);
		TriggerXmls tg= new TriggerXmls();
		
		 Document doc = null;
		   //byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPNSL_QA12_NS.xml"));
			
			//Read xml for Document Class 
			String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CASO_AVPNSL_QA12_NS.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			System.out.println(request);
			byte[] encoded1 =request.getBytes();
		
		ValidatableResponse resp =given()
				.body(encoded1)
				.accept(ContentType.XML)
		 .when()		 		
		 		.post(getURL)
		  .then()
		  .log().all();
		  		 //.body(hasXPath("/S:Envelope/S:Body/ns2:createAndStartOrderResponse/CreateAndStartOrderOutput/orderInformationX9/orderIdX9"), );
		  		 

		//String orderId=resp.extract().xmlPath().get("/S:Envelope/S:Body/ns2:createAndStartOrderResponse/CreateAndStartOrderOutput/orderInformationX9/orderIdX9");
		//System.out.println(orderId);
		//String oldNewArch=resp.extract().jsonPath().getString("SearchOrderResponse.orderDataX9[0].orderActionDataX9[0].newOldArchIndX9");
	//	return oldNewArch;
	}*/
	
	/*public static void main(String args[]) throws InstantiationException, IllegalAccessException, IOException, InterruptedException
	{
		OCXOrderStatus.getDataFromXmlResponse();
	}*/
}



