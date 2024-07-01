package com.amdocs.halo.automation.main;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.xml.transform.TransformerException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateOAAdditionalInfoReq 
{
	public static Logger logger = null;
	
	 public static String getClassName()
	 {
		 return UpdateOAAdditionalInfoReq.class.getName();
	 }
	public static void updateOAAdditionalInfoReq(String request, String env) throws InstantiationException, IllegalAccessException, IOException, JSONException
	{
		String getURL=GetURLFromExcel.readURLFromExcel("RETRIEVE_OA_ADD_INFO",env);
		System.out.println("Getting response with URL : "+getURL);
		Response response =given()
								.body(request)
								.header("UAMS_USER", "Asmsa1")
								.header("UAMS_PASS", "Asmsa1")
								.contentType("application/json")
						 .when()
						 		.accept(ContentType.JSON)
						 		.put(getURL)
						 		//.post(getURL)
						  .then()
						  		 .contentType(ContentType.JSON).extract().response();
	  response.prettyPrint();	
	}
	
	public static String updateOaIdInRequest(String request,String oaid)
	{
		JSONObject js = new JSONObject(request);
		js.getJSONObject("UpdateOAAdditionalInfoReq").getJSONArray("orderActionInfoListX9").getJSONObject(0).remove("orderActionIdX9");
		js.getJSONObject("UpdateOAAdditionalInfoReq").getJSONArray("orderActionInfoListX9").getJSONObject(0).put("orderActionIdX9", oaid);
		return js.toString();
	}
	
	public static void saveOAExternalValsInput(String env, String oaid) throws InstantiationException, IllegalAccessException, IOException, JSONException, InterruptedException
	{
		logger=LoggerGen.logGen(getClassName());
		String getURL=GetURLFromExcel.readURLFromExcel("SAVE_OA_VAL",env);  //getURL
		System.out.println("Getting response with URL : "+getURL);
		String exampleRequest = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/SaveOAExternalValsInput.json"), StandardCharsets.UTF_8);
		logger.info(exampleRequest);
		logger.info("Getting response for OA :"+oaid+" with URL : "+getURL+oaid);
		Thread.sleep(3000);	
		Response response =given()
								.body(exampleRequest)
								.header("UAMS_USER", "Asmsa1")
								.header("UAMS_PASS", "Asmsa1")
								.contentType("application/json")
						 .when()
						 		.accept(ContentType.JSON)
						 		.put(getURL+oaid)
						  .then()
						  		 .contentType(ContentType.JSON).extract().response();	
		
		logger.info(response.getBody().prettyPrint());
	}
	
	/*public static void main(String args[]) throws InstantiationException, IllegalAccessException, IOException, TransformerException, JSONException, InterruptedException
	{
		TriggerXmls tg= new TriggerXmls();
		String updateOAAdditionalInfoReq = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/UpdateOAAdditionalInfoReq.json"), StandardCharsets.UTF_8);
		System.out.println(updateOAAdditionalInfoReq);
		JSONObject jsonObject = new JSONObject(updateOAAdditionalInfoReq);
		String updatedRequest=updateOaIdInRequest(updateOAAdditionalInfoReq,"20980810");
		String formattedRequest=tg.formatResponseJson(updatedRequest);
		System.out.println("Request after update is : "+formattedRequest);
		updateOAAdditionalInfoReq(formattedRequest,"ST3");	
		
		saveOAExternalValsInput("ST4","654687");
	}*/

}
