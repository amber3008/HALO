package com.amdocs.halo.automation.steps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.main.OCXOrderCreation_GAMMA;
import com.amdocs.halo.automation.main.TriggerCNODRequest;
import com.amdocs.halo.automation.main.TriggerSareaRequest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UNI_Amend_steps 

{
	public static String rsponse;
	HashMap<String,List<String>> oaIds= new HashMap<String,List<String>>();
	public static String envName;
	HashMap<String,List<String>> orderDetails;
	public static HashMap<String, List<String>> productNames;
	public static String orderId;
	public static String orderId1;
	public static String assignProductId;
    public static Logger logger = null;
	
	public UNI_Amend_steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(UNI_Amend_steps.class.getName());
		
	}
	@Given("^Env is up for UNI_Amend order creation for \"([^\"]*)\"$")
	public void env_is_up_for_UNI_Amend_order_creation_for(String Env) throws Exception
	{
		logger.info("==========UNI Order creation started===========");
		envName=System.getProperty("enviroment");

	}

	@When("^UNI order is pushed to OCX for UNI_Amend\"([^\"]*)\"\"([^\"]*)\"$")
	public void uni_order_is_pushed_to_OCX_for_UNI_Amend(String eNv, String stateRegion) throws Throwable 
	{
		logger.info("==========UNI request has been pushed===========");
      envName=System.getProperty("enviroment");
		logger.info(envName);
		String response=TriggerCNODRequest.triggerRequestUNI_stateRegion (envName, stateRegion);
		logger.info("==========Below are UNI response details for Amend===========");
		orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(response, "productOrderItemId","assignedProductId");
		orderId=orderDetails.get("productOrderItemId").get(0);
		orderId1=orderDetails.get("assignedProductId").get(0);
		logger.info(orderDetails);
	}

	@When("^pass productOrderItemId andassignedProductId to UNI_Amend and push \"([^\"]*)\"$")
	public void pass_productOrderItemId_andassignedProductId_to_UNI_Amend_and_push(String eNv) throws Throwable 
	{
		logger.info("==========UNI_Amend pushed===========");
		rsponse=TriggerCNODRequest.triggerUNI_Amend_Request(envName, orderId, orderId1);
		Thread.sleep(20000);
		oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId","assignedProductId");
	
	}

	@Then("^UNI_Amend is created in OCX\\.$")
	public void uni_amend_is_created_in_OCX() throws Exception 
	{
		logger.info("=========UNI_Amend response created===========");
	}

}
