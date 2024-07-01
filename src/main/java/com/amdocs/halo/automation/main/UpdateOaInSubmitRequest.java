package com.amdocs.halo.automation.main;

/***
 * 
 * @author ab780k
 *
 */

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.amdocs.halo.automation.utils.GetURLFromExcel;
//import com.jayway.jsonpath.Configuration;
//import com.jayway.jsonpath.DocumentContext;
//import com.jayway.jsonpath.JsonPath;
//import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
//import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateOaInSubmitRequest 
{
	public static void pushSubmitOrderRequest(String request, String env) throws InstantiationException, IllegalAccessException, IOException, JSONException
	{
		String getURL=GetURLFromExcel.readURLFromExcel("SUBMIT_ORDER_REQUEST",env);
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
	
	public static void getKey(JSONObject json, String key) throws InstantiationException, IllegalAccessException, IOException, JSONException
	{
		 Iterator<?> keys;
		 String nextKeys;
		 boolean exists=json.has(key);
		if(!exists)
		{
			keys=json.keys();
			while(keys.hasNext())
			{
				nextKeys=(String) keys.next();
				if (json.get(nextKeys) instanceof JSONObject) 
				{
					if(exists == false)
						getKey(json.getJSONObject(nextKeys),key);
				}
				else if(json.get(nextKeys) instanceof JSONArray)
				{
					JSONArray jsonArray=json.getJSONArray(nextKeys);
					for(int i=0;i<jsonArray.length();++i)
					{
						String jsonArrayString=jsonArray.get(i).toString();
						JSONObject innerJson = new JSONObject(jsonArrayString);
						if(exists == false)
							getKey(innerJson,key);
					}
				}
				else
					System.out.println(json.get(key));
			}
		}
		else
			System.out.println(json.get(key));
	}
	
	public static String updateOaIdInRequest(String request,String oaid)
	{
		JSONObject js = new JSONObject(request);
		js.getJSONObject("SubmitOrderActionReq").getJSONArray("orderRefX9").getJSONObject(0).remove("orderNumberX9");
		js.getJSONObject("SubmitOrderActionReq").getJSONArray("orderRefX9").getJSONObject(0).put("orderNumberX9", oaid);
		return js.toString();
	}
	
	public static void main(String args[]) throws InstantiationException, IllegalAccessException, IOException
	{
		String response = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/Response1.json"), StandardCharsets.UTF_8);
		
		System.out.println(response);
		JSONObject jsonObject = new JSONObject(response);
		getKey(jsonObject,"serviceTypeX9");
				
	}

}
