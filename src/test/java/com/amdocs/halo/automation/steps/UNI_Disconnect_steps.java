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

public class UNI_Disconnect_steps 
{
	HashMap<String,List<String>> orderDetails;
	HashMap<String,List<String>> orderDetails1;
	public static String envName;
	public static String orderId;
	public static String assignProductId;
	public static String rsponse;
	public static String rsponse1;
	public static HashMap<String, List<String>> oaIds;
	public static HashMap<String, List<String>> productNames;
	public static Logger logger = null;
	
	public UNI_Disconnect_steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(UNI_Disconnect_steps.class.getName());
	}
	

	@Given("^Env is up for UNI_Disconnect order creation for \"([^\"]*)\"$")
	public void env_is_up_for_UNI_Disconnect_order_creation_for(String Env) throws Exception 
	{
		envName=System.getProperty("enviroment");
	}

	@When("^CNOD of UNI is pushed to OCX for UNI_Disconnect\"([^\"]*)\"$")
	public void cnod_of_UNI_is_pushed_to_OCX_for_UNI_Disconnect(String Env) throws Throwable 
	{
		 logger.info(envName);
		
		  
	//	 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		    
		 String response=TriggerCNODRequest.triggerRequestUNI(envName);
		 orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(response, "productOrderItemId","assignedProductId");
		 orderId=orderDetails.get("productOrderItemId").get(0);
		  assignProductId=orderDetails.get("assignedProductId").get(0);
		  logger.info(orderDetails);
	}

	@When("^Push notify on request for UNI_Disconnect\"([^\"]*)\"$")
	public void push_notify_on_request_for_UNI_Disconnect(String Env) throws Throwable
	{
	    rsponse =TriggerCNODRequest.triggerRequestNotifyOn(envName, orderId);
	    oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
		Thread.sleep(60000);
		
	}
	@When("^Push Disconnect request for UNI_Disconnect\"([^\"]*)\"$")
	public void push_Disconnect_request_for_UNI_Disconnect(String Env) throws Exception
	{
		
		rsponse1 =TriggerCNODRequest.UNI_Disconnect(envName, assignProductId);
		productNames=OCXOrderCreation_GAMMA.getElementFromXml(rsponse1,"assignedProductId");
		Thread.sleep(20000);
		
	}

	@Then("^UNI_Disconnect is created in OCX\\.$")
	public void uni_disconnect_is_created_in_OCX() throws Exception 
	{
	    
	}

	
}
