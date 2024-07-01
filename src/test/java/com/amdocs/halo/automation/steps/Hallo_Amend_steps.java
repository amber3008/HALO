package com.amdocs.halo.automation.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;
import com.amdocs.halo.automation.main.Hallo_Amend;
import com.amdocs.halo.automation.main.OCXOrderCreation_GAMMA;
import com.amdocs.halo.automation.main.OrderCreationInOCX;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Hallo_Amend_steps 
{
	public static String envName;
	public static String NewOrderActionID;
	public static String response;
	public static String OrderActionID;
	public static String orderActionId;
	@Given("^Env\"([^\"]*)\" is UP for the Halo Amend Order$")
	public void env_is_UP_for_the_Halo_Amend_Order(String env) throws Exception {
		envName=env;   
      envName=System.getProperty("enviroment");
		System.out.println(envName+ " Is up");
	}

	@When("^Amend  is pushed \"([^\"]*)\"$")
	public void Amend_is_pushed(String orderActionID) throws Exception {
		response= Hallo_Amend.haloAmend(envName,orderActionID);

		OrderActionID=orderActionID;

	}

	@Then("^Amend Order is CREATED in OCX\\.$")
	public void amend_Order_is_CREATED_in_OCX() throws Exception {


		JSONObject obj = new JSONObject(response); 
		NewOrderActionID =obj.getJSONObject("AmendOrderActionOutput").getJSONObject("orderOperationValidationX9").getJSONArray("allowedOrdersX9").getJSONObject(0).getJSONArray("orderActionsX9").getJSONObject(0).getString("orderActionIdX9");


	}

	@Then("^Push UpdateOAAdditionalInfo request (.*)(.*)$")
	public void push_UpdateOAAdditionalInfo_request(String env, String orderActionID) throws Exception {
		//envName=env;
		System.out.println("Order Action ID is" +NewOrderActionID);
		Hallo_Amend.updateOAAdditionalInfoReq(envName,NewOrderActionID);

	}

	@Then("^Submit the order\\.$")
	public void submit_the_order() throws Exception {
		Hallo_Amend.SubmitOrder(envName,OrderActionID);
	}


}
