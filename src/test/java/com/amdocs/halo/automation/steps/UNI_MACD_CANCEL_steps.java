package com.amdocs.halo.automation.steps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.main.OCXOrderCreation_GAMMA;
import com.amdocs.halo.automation.main.TriggerCNODRequest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UNI_MACD_CANCEL_steps {
	public static String rsponse;
	public static HashMap<String, List<String>> oaIds;
	HashMap<String,List<String>> orderDetails;
	public static String assignProductId;
	public static String envName;
	public static String orderId;
	public static String orderId2;
	public static String rsponse1;
	public static String rsponse2;
	public static HashMap<String, List<String>> productNames;
	public static HashMap<String, List<String>> productNames2;
	public static Logger logger = null;
	public UNI_MACD_CANCEL_steps () throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(UNI_MACD_CANCEL_steps.class.getName());
		
	}

	@Given("^Env is up for UNI_MACD_Cancel order Creation for \"([^\"]*)\"$")
	public void env_is_up_for_UNI_MACD_Cancel_order_Creation_for(String env) throws Exception 
	{
		envName=System.getProperty("enviroment");
		 
	}

	@When("^CNOD of UNI is Pushed to OCX for UNI_MACD_Cancel\"([^\"]*)\"\"([^\"]*)\"$")
	public void cnod_of_UNI_is_Pushed_to_OCX_for_UNI_MACD_Cancel(String env, String stateRegion) throws Exception 
	{
		logger.info("==========UNI Order creation started===========");
		logger.info("==========TESTING===========");
		 logger.info(env);
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response=   tr1.triggerRequestUNI (envName, stateRegion);
		 orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(response, "productOrderItemId","assignedProductId");
		 orderId=orderDetails.get("productOrderItemId").get(0);
		 assignProductId=orderDetails.get("assignedProductId").get(0);
		 logger.info(orderDetails);  
	}

	@When("^complete the UNI order by Notify ON\"([^\"]*)\"$")
	public void complete_the_UNI_order_by_Notify_ON(String env) throws Exception 
	{
		logger.info("==========Complete the order by notify on===========");
	    rsponse =TriggerCNODRequest.triggerRequestNotifyOn(envName, orderId);
	   
		Thread.sleep(20000);
		logger.info("==========order completed===========");
	}

	@When("^change assignProductId from UNI response to UNI_MACD req and get the res\"([^\"]*)\"$")
	public void change_assignProductId_from_UNI_response_to_UNI_MACD_req_and_get_the_res(String env) throws Exception 
	{
		logger.info("==========UNI MACD order creation started===========");
		rsponse1 =TriggerCNODRequest.UNI_MACD(envName, assignProductId);
		productNames=OCXOrderCreation_GAMMA.getElementFromXml(rsponse1,"assignProductId", "productOrderItemId");
		orderId2=productNames.get("productOrderItemId").get(0);
	}

	@When("^Push cancel request for UNI_MACD_Cancel\"([^\"]*)\"$")
	public void push_cancel_request_for_UNI_MACD_Cancel(String env) throws Throwable 
	{
		logger.info("==========UNI MACD Cancel Order creation started===========");
		rsponse2 =TriggerCNODRequest.triggerUNI_Cancel(envName, orderId2);
		oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse2,"productOrderItemId");
		logger.info("==========UNI mACD Cancel Order created===========");
	}

	@Then("^UNI_MACD_Cancel is created in OCX\\.$")
	public void uni_macd_cancel_is_created_in_OCX() throws Exception 
	{
	    
	}

}
