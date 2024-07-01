package com.amdocs.halo.automation.main;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
//import java.util.Random;

//import javax.xml.transform.TransformerException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.amdocs.halo.automation.base.LoggerGen;
//import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.utils.GetURLFromExcel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SaveTNAndUDR 
{
	public static Logger logger = null;
	
	 public static String getClassName()
	 {
		 return SaveTNAndUDR.class.getName();
	 }
	public static void pushSaveTelephoneNumbersReq(String env, String oaid, int requestId) throws InstantiationException, IllegalAccessException, IOException, JSONException, InterruptedException
	{
		logger=LoggerGen.logGen(getClassName());
		String getURL=GetURLFromExcel.readURLFromExcel("SAVE_ATT_TN",env);  //getURL
		String exampleRequest = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/SaveTelephoneNumbersRequest_ManualTN.json"), StandardCharsets.UTF_8);
		String updatedRequest=updateSaveTnRequest(exampleRequest,requestId);
		logger.info(updatedRequest);
		logger.info("Getting response for SORID :"+oaid+" with URL : "+getURL+oaid);
		Thread.sleep(3000);	
		Response response =given()
								.body(updatedRequest)
								.header("UAMS_USER", "Asmsa1")
								.header("UAMS_PASS", "Asmsa1")
								.contentType("application/json")
						 .when()
						 		.accept(ContentType.JSON)
						 		.post(getURL+oaid)
						  .then()
						  		 .contentType(ContentType.JSON).extract().response();	
		
		logger.info(response.getBody().prettyPrint());
	}
	
	public static String updateSaveTnRequest(String request, int requestId)
	{
		JSONObject js = new JSONObject(request);
		js.getJSONObject("TelephoneNumbersDetails").remove("requestIDX9");
		js.getJSONObject("TelephoneNumbersDetails").put("requestIDX9", requestId);
		return js.toString();
	}
	
	public static String updateSaveUDR(String request, String requestId, String oaid)
	{
		JSONObject js = new JSONObject(request);
		js.getJSONObject("SaveUserDefineRangesInProductServiceRestRequest").remove("orderNumberX9");
		js.getJSONObject("SaveUserDefineRangesInProductServiceRestRequest").put("orderNumberX9", oaid);
		
		js.getJSONObject("SaveUserDefineRangesInProductServiceRestRequest").remove("requestIDX9");
		js.getJSONObject("SaveUserDefineRangesInProductServiceRestRequest").put("requestIDX9", requestId);
		
		
		return js.toString();
	}
	
	
	public static void pushSaveUDRReq(String env, String oaid, String requestId) throws InstantiationException, IllegalAccessException, IOException, JSONException, InterruptedException
	{
		logger=LoggerGen.logGen(getClassName());
		String getURL=GetURLFromExcel.readURLFromExcel("SAVE_ATT_UDR",env);
		String exampleRequest = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/SaveUserDefineRangesInProductServiceRestRequest_ManualTN.json"), StandardCharsets.UTF_8);
		String updatedRequest=updateSaveUDR(exampleRequest,requestId, oaid);
		logger.info(updatedRequest);
		logger.info("Getting response for Order Id :"+oaid+" with URL : "+getURL);
		Thread.sleep(3000);	
		Response response =given()
								.body(updatedRequest)
								.header("UAMS_USER", "Asmsa1")
								.header("UAMS_PASS", "Asmsa1")
								.contentType("application/json")
						 .when()
						 		.accept(ContentType.JSON)
						 		.post(getURL)
						  .then()
						  		 .contentType(ContentType.JSON).extract().response();	
		
		logger.info(response.getBody().prettyPrint());
	}
}
