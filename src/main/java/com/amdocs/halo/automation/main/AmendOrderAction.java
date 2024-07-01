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

public class AmendOrderAction 
{
	public static void amendOrderAction(String env, String oaid) throws InstantiationException, IllegalAccessException, IOException, JSONException
	{
		String getURL=GetURLFromExcel.readURLFromExcel("RETRIEVE_OA_ADD_INFO",env);
		String request = updateOaIdInRequest(oaid);
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
	
	public static String updateOaIdInRequest(String oaid) throws IOException
	{
		String request = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/AmendOrderActionInput.json"), StandardCharsets.UTF_8);
		JSONObject js = new JSONObject(request);
		js.getJSONObject("AmendOrderActionInput").getJSONArray("orderActionsToAmendX9").getJSONObject(0).remove("orderNumberX9");
		js.getJSONObject("AmendOrderActionInput").getJSONArray("orderActionsToAmendX9").getJSONObject(0).put("orderNumberX9", oaid);
		return js.toString();
	}
}

