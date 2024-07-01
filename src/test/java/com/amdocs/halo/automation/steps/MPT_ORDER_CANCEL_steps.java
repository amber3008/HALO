/*package com.amdocs.halo.automation.steps;

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

public class MPT_ORDER_CANCEL_steps 
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
	HashMap<String,List<String>> orderDetails2;
	public static String productServiceId3;
	public static String envName;
	public static String rsponse;
	public static String rsponse1;
	public static String orderId;
	public static String rsponse2;
    public static Logger logger = null;
	
	public MPT_ORDER_CANCEL_steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(MPT_ORDER_CANCEL_steps.class.getName());
		
	}
	@Given("^Env\"([^\"]*)\" is UP for MPT_Cancel order Creation for$")
	public void env_is_UP_for_MPT_Cancel_order_Creation_for(String eNv) throws Exception 
	{
		logger.info("=============================================PTP Order Creation Started=============================================");
		logger.info(eNv+" is up");
		envName=eNv;
	}

	@When("^CNOD of first UNI is pushed to OCX for MPT_Cancel\"([^\"]*)\" \"([^\"]*)\"$")
	public void cnod_of_first_UNI_is_pushed_to_OCX_for_MPT_Cancel(String eNv, String stateRegion) throws Throwable 
	{
		logger.info("==========UNI Order creation started===========");
		 logger.info("===========eNv==========");
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response=   tr1.triggerRequestUNI_stateRegion (eNv, stateRegion);
		 orderDetails=OCXOrderCreation_GAMMA.getElementFromXml(response,"productServiceId");
		 productServiceId1=orderDetails.get("productServiceId").get(0);
		 logger.info("======================Created 1st UNI============================");
		 logger.info(orderDetails);
	}

	@When("^CNOD of second UNI_B is pushed to OCX for MPT_Cancel\"([^\"]*)\" \"([^\"]*)\"$")
	public void cnod_of_second_UNI_B_is_pushed_to_OCX_for_MPT_Cancel(String eNv, String stateRegion) throws Throwable 
	{
		logger.info("==========UNI Order creation started===========");
		 logger.info("===========eNv==========");
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response1=   tr1.triggerRequestUNI_stateRegion (eNv, stateRegion);
		 orderDetails1=OCXOrderCreation_GAMMA.getElementFromXml(response1,"productServiceId");
		 productServiceId2=orderDetails1.get("productServiceId").get(0);
		 logger.info("======================Created 2nd UNI============================");
		 logger.info(orderDetails1);
	}

	@When("^CNOD of third UNI_C is pushed to OCX for MPT_Cancel\"([^\"]*)\" \"([^\"]*)\"$")
	public void cnod_of_third_UNI_C_is_pushed_to_OCX_for_MPT_Cancel(String eNv, String stateRegion) throws Throwable 
	{
		 logger.info("==========UNI Order creation started===========");
		 logger.info("===========eNv==========");
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response2=   tr1.triggerRequestUNI_stateRegion (eNv, stateRegion);
		 orderDetails2=OCXOrderCreation_GAMMA.getElementFromXml(response2,"productServiceId");
		 productServiceId3=orderDetails2.get("productServiceId").get(0);
		 logger.info("======================Created 3rd UNI============================");
		 logger.info(orderDetails2);
	}

	@When("^CNOD of MPT is PUSHED to OCX for UNI_A and UNI_B and UNI_C to cancel\"([^\"]*)\" (.*)$")
	public void cnod_of_MPT_is_PUSHED_to_OCX_for_UNI_A_and_UNI_B_and_UNI_C_to_cancel(String eNv, String stateRegion) throws Throwable
	{
		logger.info("==========MTP pushed===========");
		rsponse= TriggerCNODRequest.trigger_MTP_Request(eNv, productServiceId1, productServiceId2, productServiceId3);
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
		logger.info("======================Created MTP EVC============================");
		orderId=oaIds.get("productOrderItemId").get(1);
		Thread.sleep(20000);
	}

	@When("^MPT OA is CREATED in OCX to cancel\\.$")
	public void mpt_OA_is_CREATED_in_OCX_to_cancel() throws Exception 
	{
	   
	}

	@When("^pass productOrderItemId to MPT_Cancel and push \"([^\"]*)\"$")
	public void pass_productOrderItemId_to_MPT_Cancel_and_push(String eNv) throws Throwable 
	{
		logger.info("==========PTP Cancel Order creation started===========");
		rsponse =TriggerCNODRequest.triggerUNI_Cancel(eNv, orderId);
		oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
		logger.info("==========MPT Cancel Order created===========");
	}

	@Then("^MPT_Cancel is created in OCX\\.$")
	public void mpt_cancel_is_created_in_OCX() throws Exception 
	{
	 
	}

}
*/