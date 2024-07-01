package com.amdocs.halo.automation.steps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.main.OCXOrderCreation_GAMMA;
import com.amdocs.halo.automation.main.TriggerCNODRequest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UNI_MACD_Amend_steps 
{
	public static String rsponse;
	public static HashMap<String, List<String>> oaIds;
	HashMap<String,List<String>> orderDetails;
	HashMap<String,List<String>> orderDetails1;
	public static String assignProductId;
	public static String envName;
	public static String orderId;
	public static String orderId1;
	public static String rsponse1;
	public static HashMap<String, List<String>> productNames;
     public static Logger logger = null;
     public UNI_MACD_Amend_steps() throws FileNotFoundException, IOException
     {
	logger=LoggerGen.logGen(UNI_MACD_Amend_steps.class.getName());
	
    }
	@Given("^Env is up for UNI_MACD_Amend order creation for \"([^\"]*)\"$")
	public void env_is_up_for_UNI_MACD_Amend_order_creation_for(String eNv) throws Exception 
	{
		envName=System.getProperty("enviroment");
	}

	@When("^UNI order is pushed to OCX for UNI_MACD_Amend\"([^\"]*)\"\"([^\"]*)\"$")
	public void uni_order_is_pushed_to_OCX_for_UNI_MACD_Amend(String eNv, String stateRegion) throws Exception 
	{
		logger.info("==========UNI Order creation started===========");
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response=   tr1.triggerRequestUNI (envName, stateRegion);
		 orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(response, "productOrderItemId","assignedProductId");
		 orderId=orderDetails.get("productOrderItemId").get(0);
		 assignProductId=orderDetails.get("assignedProductId").get(0);
		 logger.info(orderDetails); 
	}


    @When("^complete the order by notify on\"([^\"]*)\"$")
    public void complete_the_order_by_notify_on(String eNv) throws Exception 
	{
		logger.info("==========Complete the order by notify on===========");
	    rsponse =TriggerCNODRequest.triggerRequestNotifyOn(envName, orderId);
	    oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
		Thread.sleep(20000);
		logger.info("==========order completed===========");
	}

    @When("^Perform MACD operation\"([^\"]*)\"$")
    public void perform_MACD_operation(String eNv) throws Exception 
	{
    	logger.info("==========UNI MACD order creation started===========");
    	String rsponse1 =TriggerCNODRequest.UNI_MACD(envName, assignProductId);
		logger.info("==========UNI MACD order created===========");
		orderDetails1= OCXOrderCreation_GAMMA.getElementFromXml(rsponse1, "productOrderItemId","assignedProductId");
		 orderId1=orderDetails1.get("productOrderItemId").get(0);
		 assignProductId=orderDetails1.get("assignedProductId").get(0);
		 logger.info(orderDetails1);
	}

    @When("^pass productOrderItemId andassignedProductId to UNI_MACD_Amend and push \"([^\"]*)\"$")
    public void pass_productOrderItemId_andassignedProductId_to_UNI_MACD_Amend_and_push(String eNv) throws Throwable 
	{
		logger.info("==========UNI_Amend pushed===========");
		String rsponse2=TriggerCNODRequest.trigger_UNI_MACD_Amend_Request(envName, orderId1, assignProductId);
		Thread.sleep(20000);
		oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse2,"productOrderItemId","assignedProductId");
	}

	@Then("^UNI_MACD_Amend is created in OCX\\.$")
	public void uni_macd_amend_is_created_in_OCX() throws Exception 
	{
	   
	}


}
