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

public class PTP_ORDER_CANCEL_steps 
{
	HashMap<String,List<String>> oaIds= new HashMap<String,List<String>>();
	HashMap<String,List<String>> oaIds2= new HashMap<String,List<String>>();
	HashMap<String,List<String>> productNames= new HashMap<String,List<String>>();
	HashMap<String,List<String>> circuitIdEvc= new HashMap<String,List<String>>();
	public static HashMap<String, List<String>> productOrderId;
	HashMap<String,List<String>> orderDetails;
	public static String productServiceId1;
	HashMap<String,List<String>> orderDetails1;
	public static String productServiceId2;
	public static String envName;
	public static String rsponse;
	public static String orderId;
    public static Logger logger = null;
	
	public PTP_ORDER_CANCEL_steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(PTP_ORDER_CANCEL_steps.class.getName());
		
	}
	@Given("^Env\"([^\"]*)\" is UP for PTP_Cancel order Creation for$")
	public void env_is_UP_for_PTP_Cancel_order_Creation_for(String eNv) throws Exception 
	{
		logger.info("=============================================PTP Order Creation Started=============================================");
		logger.info(eNv+" is up");
		envName=eNv;
      envName=System.getProperty("enviroment");
	}

	@When("^CNOD of first UNI is pushed to OCX for PTP_Cancel\"([^\"]*)\" \"([^\"]*)\"$")
	public void cnod_of_first_UNI_is_pushed_to_OCX_for_PTP_Cancel(String eNv, String stateRegion) throws Throwable 
	{
		logger.info("==========UNI Order creation started===========");
		 logger.info("===========eNv==========");
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response=   tr1.triggerRequestUNI_stateRegion (envName, stateRegion);
		 orderDetails=OCXOrderCreation_GAMMA.getElementFromXml(response,"productServiceId");
		 productServiceId1=orderDetails.get("productServiceId").get(0);
		 logger.info("======================Created 1st UNI============================");
		 logger.info(orderDetails);
	}

	@When("^CNOD of UNI_B is pushed to OCX for PTP_Cancel\"([^\"]*)\" (.*)$")
	public void cnod_of_UNI_B_is_pushed_to_OCX_for_PTP_Cancel(String eNv, String stateRegion) throws Throwable 
	{
		logger.info("==========UNI Order creation started===========");
		 logger.info("===========eNv==========");
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response=   tr1.triggerRequestUNI_stateRegion (envName, stateRegion);
		 orderDetails1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productServiceId");
		 productServiceId2=orderDetails1.get("productServiceId").get(0);
		 logger.info("======================Created 2nd UNI============================");
		 logger.info(orderDetails1);
	}

	@When("^CNOD of PTP_Cancel is PUSHED to OCX for UNI_A and UNI_B\"([^\"]*)\" (.*)$")
	public void cnod_of_PTP_Cancel_is_PUSHED_to_OCX_for_UNI_A_and_UNI_B(String eNv, String stateRegion) throws Throwable  
	{
		logger.info("==========PTP pushed===========");
		rsponse= TriggerCNODRequest.trigger_PTP_Request(envName, productServiceId1, productServiceId2);
		Thread.sleep(20000);
		oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
		Thread.sleep(20000);
		oaIds2= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"assignedProductId");
		Thread.sleep(20000);
		productNames=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productName");
		Thread.sleep(20000);
		circuitIdEvc=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productServiceId");
		Thread.sleep(20000);
		productOrderId=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderId");
		logger.info("======================Created PTP EVC============================");
		orderId=oaIds.get("productOrderItemId").get(2);
		Thread.sleep(20000);
		
		 
	}

	@When("^PTP OA is CREATED in OCX for cancel\\.$")
	public void ptp_OA_is_CREATED_in_OCX_for_cancel() throws Exception 
	{
	  
	}

	@When("^pass productOrderItemId to PTP_Cancel and push \"([^\"]*)\"$")
	public void pass_productOrderItemId_to_PTP_Cancel_and_push(String eNv) throws Throwable 
	{
		logger.info("==========PTP Cancel Order creation started===========");
		rsponse =TriggerCNODRequest.triggerUNI_Cancel(envName, orderId);
		oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
		logger.info("==========UNI Cancel Order created===========");
	}

	@Then("^PTP_Cancel is created in OCX\\.$")
	public void ptp_cancel_is_created_in_OCX() throws Exception 
	{
	  
	}
}
