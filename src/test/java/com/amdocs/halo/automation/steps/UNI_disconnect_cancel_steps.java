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

public class UNI_disconnect_cancel_steps {
	HashMap<String,List<String>> orderDetails;
	HashMap<String,List<String>> orderDetails1;
	public static String envName;
	public static String orderId;
	public static String orderId1;
	public static String assignProductId;
	public static String rsponse;
	public static String rsponse1;
	public static String rsponse2;
	public static HashMap<String, List<String>> oaIds;
	public static HashMap<String, List<String>> oaIds2;
	public static HashMap<String, List<String>> productNames;
	
	public static Logger logger = null;
	public UNI_disconnect_cancel_steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(UNI_disconnect_cancel_steps.class.getName());
		
	}

	@Given("^Env is up for UNI_Disconnect order  creation for \"([^\"]*)\"$")
	public void env_is_up_for_UNI_Disconnect_order_creation_for(String eNv) throws Exception 
	{
		envName=System.getProperty("enviroment");
	}

	@When("^CNOD of UNI is pushed to OCX for  UNI_Disconnect\"([^\"]*)\"\"([^\"]*)\"$")
	public void cnod_of_UNI_is_pushed_to_OCX_for_UNI_Disconnect(String env, String stateRegion) throws Throwable 
	{
		logger.info("==========UNI Order creation started===========");
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response=   tr1.triggerRequestUNI_stateRegion (envName, stateRegion);
		 orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(response, "productOrderItemId","assignedProductId");
		 orderId1=orderDetails.get("productOrderItemId").get(0);
		 assignProductId=orderDetails.get("assignedProductId").get(0);
		 logger.info(orderDetails); 
	}

	@When("^Push notify on request for  UNI_Disconnect\"([^\"]*)\"$")
	public void push_notify_on_request_for_UNI_Disconnect(String env) throws Exception 
	{
		logger.info("==========Complete the order by notify on===========");
	    rsponse =TriggerCNODRequest.triggerRequestNotifyOn(envName, orderId1);
	    oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse);
		Thread.sleep(20000);
		logger.info("==========order completed===========");
	}

	@When("^Push Disconnect request for  UNI_Disconnect\"([^\"]*)\"$")
	public void push_Disconnect_request_for_UNI_Disconnect(String env) throws Exception 
	{
		logger.info("==========UNI Disconnect order creation started===========");
		rsponse2=TriggerCNODRequest.UNI_Disconnect(envName, assignProductId);
		productNames=OCXOrderCreation_GAMMA.getElementFromXml(rsponse2,"productOrderItemId");
		orderId=productNames.get("productOrderItemId").get(0);
		Thread.sleep(20000);
		logger.info("==========UNI Disconnect order created===========");
	}

	@Then("^UNI_Disconnect is  created in OCX\\.$")
	public void uni_disconnect_is_created_in_OCX() throws Exception 
	{
	    
	}

	@Then("^Push cancel request for UNI_DISCONNECT_CANCEL\"([^\"]*)\"$")
	public void push_cancel_request_for_UNI_DISCONNECT_CANCEL(String env) throws Throwable 
	{
		logger.info("==========UNI disc Cancel Order creation started===========");
		rsponse1 =TriggerCNODRequest.triggerUNI_Cancel(envName, orderId);
		oaIds2= OCXOrderCreation_GAMMA.getElementFromXml(rsponse1);
		logger.info("==========UNI disc Cancel Order created===========");
	}

	@Then("^UNI_DISCONNECT_CANCEL is created in OCX\\.$")
	public void uni_disconnect_cancel_is_created_in_OCX() throws Exception 
	{
		logger.info("==========UNI disc Cancel Order created==========="); 
	}

}
