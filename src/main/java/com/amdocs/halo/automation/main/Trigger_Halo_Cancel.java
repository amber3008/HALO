package com.amdocs.halo.automation.main;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Trigger_Halo_Cancel {
	
	public static void cancelOrderInput(String env, String oaid) throws InstantiationException, IllegalAccessException, IOException, JSONException
	{
		String getURL=GetURLFromExcel.readURLFromExcel("CANCEL_ORDER_INPUT",env);
		String request = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/HALO_cancel.json"), StandardCharsets.UTF_8);
		request=request.replace("{$OrderID$}", oaid);
		//String request = updateOaIdInRequest(oaid);
		System.out.println("Getting response with URL : "+getURL);
		System.out.println(request);
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
	

}
