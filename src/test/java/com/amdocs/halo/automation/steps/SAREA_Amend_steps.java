package com.amdocs.halo.automation.steps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.main.OCXOrderCreation_GAMMA;
import com.amdocs.halo.automation.main.TriggerSareaRequest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SAREA_Amend_steps 

{
	public static String rsponse;
	public static String envName;
	HashMap<String,List<String>> orderDetails;
	public static HashMap<String, List<String>> oaIds;
	public static HashMap<String, List<String>> productNames;
	public static String orderId;
	public static String orderId1;
	public static String assignProductId;
	
	public static Logger logger = null;
	
	public SAREA_Amend_steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(SAREA_Amend_steps.class.getName());
		
	}
	
	
	@Given("^Env is up for SAREA_Amend order creation for \"([^\"]*)\"$")
	public void env_is_up_for_SAREA_Amend_order_creation_for(String Env) throws Exception
	{
		logger.info("==========SAREA Order creation started===========");
	   envName=Env;
      envName=System.getProperty("enviroment");
	}

	@When("^SAREA order is pushed to OCX for SAREA_Amend\"([^\"]*)\"$")
	public void sarea_order_is_pushed_to_OCX_for_SAREA_Amend(String Env) throws Throwable 
	{ 
		logger.info("==========SAREA request has been pushed===========");
	   String rsponse=TriggerSareaRequest.triggerRequest(envName);
	   logger.info("==========Below are Sarea response details for Amend===========");
	   orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(rsponse, "productOrderItemId","assignedProductId");
		 orderId=orderDetails.get("productOrderItemId").get(0);
		 orderId1=orderDetails.get("assignedProductId").get(0);
		  System.out.println(orderDetails);
	}

	@When("^pass productOrderItemId andassignedProductId to Amend_Sarea and push \"([^\"]*)\"$")
	public void pass_productOrderItemId_andassignedProductId_to_Amend_Sarea_and_push(String Env) throws Throwable 
	{
		logger.info("==========Sarea_Amend pushed===========");
		rsponse=TriggerSareaRequest.triggerSAREA_Amend_Request(envName, orderId, orderId1);
		Thread.sleep(20000);
		oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId","assignedProductId");
			
	}
	@Then("^SAREA_Amend is created in OCX\\.$")
	public void sarea_amend_is_created_in_OCX() throws Exception  
	{
		logger.info("==========SAREA_Amend response created===========");
	}
}
