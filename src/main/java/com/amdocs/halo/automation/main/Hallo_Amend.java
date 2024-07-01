package com.amdocs.halo.automation.main;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.amdocs.halo.automation.utils.GetURLFromExcel;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Hallo_Amend 
{
	public static  ArrayList<List<String>> ordrExecutionDetails;
		public static String haloAmend(String env, String oaid) throws InstantiationException, IllegalAccessException, IOException, JSONException
		{
			String getURL=GetURLFromExcel.readURLFromExcel("Hallo_Amend",env);
			
			String request = updatHalloAmendOrder(oaid);
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
		  return response.prettyPrint();
		 
		}
		
		
		public static String updatHalloAmendOrder(String oaid) throws IOException
		{
			String request = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/Hallo_Amend.json"));
			request=request.replace("{$orderActionID$}",oaid);
			return request;
		}
		
		public static void updateOAAdditionalInfoReq(String env, String oaidnumber) throws InstantiationException, IllegalAccessException, IOException, JSONException, InterruptedException
		{
			System.out.println("Enter into the Additional Info");
			Thread.sleep(6000);
			String getURL="http://zltv9973.vci.att.com:41400/rp-webapp-9/ordering/OAsAdditionalInfo";
			System.out.println(getURL);
			String requestUpdate = updateOAInAmend(oaidnumber);
			System.out.println("Getting response with URL : "+getURL);
			Response response =given()
									.body(requestUpdate)
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
		
		public static String updateOAInAmend(String oaidnumber) throws IOException
		{
			String requestUpdate = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/UpdateOAAdditionalInfoReq.json"));
			requestUpdate=requestUpdate.replace("{$orderActionID$}",oaidnumber);
			return requestUpdate;
		}
		
		public static void SubmitOrder(String env, String oaid) throws InstantiationException, IllegalAccessException, IOException, JSONException
		{
			//String getURL=GetURLFromExcel.readURLFromExcel("SUBMIT_ORDER_REQUEST",env);
			String getURL="http://zltv9973.vci.att.com:41400/rp-webapp-9/ordering/SubmitOrderAction";
			String request = SubmitReq(oaid);
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
		
		
		public static String SubmitReq(String oaid) throws IOException
		{
			String request = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/SubmitRequest.json"));
			request=request.replace("{$OrderActionID$}",oaid);
			return request;
		}


		public static ArrayList<List<String>> retreiveOrdrDetails() {
			return ordrExecutionDetails;
		}
		

}
