package com.amdocs.halo.automation.main;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.amdocs.halo.automation.utils.GetURLFromExcel;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RetrieveOAAdditionalInfoReq 
{
	public static void retrieveOAAdditionalInfoReq(String request, String env) throws InstantiationException, IllegalAccessException, IOException, JSONException
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
						 		.post(getURL)
						  .then()
						  		 .contentType(ContentType.JSON).extract().response();
	  response.prettyPrint();	
	}
	
	public static String updateOaIdInRequest(String request,String oaid)
	{
		JSONObject js = new JSONObject(request);
		js.getJSONObject("RetrieveOAAdditionalInfoReq").getJSONArray("orderRefX9").getJSONObject(0).remove("orderNumberX9");
		js.getJSONObject("RetrieveOAAdditionalInfoReq").getJSONArray("orderRefX9").getJSONObject(0).put("orderNumberX9", oaid);
		return js.toString();
	}
	
	public static void main(String args[]) throws InstantiationException, IllegalAccessException, IOException
	{
		String retrieveOAAdditionalInfoReq = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/RetrieveOAAdditionalInfoReq.json"), StandardCharsets.UTF_8);
		System.out.println(retrieveOAAdditionalInfoReq);
	//	JSONObject jsonObject = new JSONObject(retrieveOAAdditionalInfoReq);
		String updatedRequest=updateOaIdInRequest(retrieveOAAdditionalInfoReq,"202080810");
		System.out.println("Request after update is : "+updatedRequest);
		retrieveOAAdditionalInfoReq(updatedRequest,"ST3");		
	}
}
