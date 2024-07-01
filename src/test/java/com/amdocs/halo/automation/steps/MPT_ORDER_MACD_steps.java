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

public class MPT_ORDER_MACD_steps 

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
	
	public static String assignProductId;
	public static String rsponse2;
    public static Logger logger = null;
	
	public MPT_ORDER_MACD_steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(MPT_ORDER_MACD_steps.class.getName());
		
	}
	@Given("^env\"([^\"]*)\" is UP for MPT order Creation for$")
	public void env_is_UP_for_MPT_order_Creation_for(String eNv) throws Exception 
	{
		logger.info("=============================================PTP Order Creation Started=============================================");
		logger.info(eNv+" is up");
		envName=eNv;
      envName=System.getProperty("enviroment");
	}

	@When("^CNOD of first UNI is Pushed to OCX for MPT \"([^\"]*)\" \"([^\"]*)\"$")
	public void cnod_of_first_UNI_is_Pushed_to_OCX_for_MPT(String eNv, String stateRegion) throws Throwable 
	{
		logger.info("==========UNI Order creation started===========");
		 logger.info("===========envName==========");
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response=   tr1.triggerRequestUNI_stateRegion (envName, stateRegion);
		 orderDetails=OCXOrderCreation_GAMMA.getElementFromXml(response,"productServiceId");
		 productServiceId1=orderDetails.get("productServiceId").get(0);
		 logger.info("======================Created 1st UNI============================");
		 logger.info(orderDetails);
	}

	@When("^CNOD of UNI_B is Pushed to OCX for MPT \"([^\"]*)\" \"([^\"]*)\"$")
	public void cnod_of_UNI_B_is_Pushed_to_OCX_for_MPT(String eNv, String stateRegion) throws Throwable 
	{
		logger.info("==========UNI Order creation started===========");
		 logger.info("===========envName==========");
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response1=   tr1.triggerRequestUNI_stateRegion (envName, stateRegion);
		 orderDetails1=OCXOrderCreation_GAMMA.getElementFromXml(response1,"productServiceId");
		 productServiceId2=orderDetails1.get("productServiceId").get(0);
		 logger.info("======================Created 2nd UNI============================");
		 logger.info(orderDetails1);
	}
	@When("^CNOD of UNI_C is PUSHED to OCX for MPT \"([^\"]*)\" \"([^\"]*)\"$")
	public void cnod_of_UNI_C_is_PUSHED_to_OCX_for_MPT(String eNv, String stateRegion) throws Throwable
	{
		 logger.info("==========UNI Order creation started===========");
		 logger.info("===========eNv==========");
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response2=   tr1.triggerRequestUNI_stateRegion(envName, stateRegion);
		 orderDetails2=OCXOrderCreation_GAMMA.getElementFromXml(response2,"productServiceId");
		 productServiceId3=orderDetails2.get("productServiceId").get(0);
		 logger.info("======================Created 3rd UNI============================");
		 logger.info(orderDetails2);
	}

	@When("^CNOD of MPT is PUSHED to OCX for UNI_A, UNI_B and UNI_C \"([^\"]*)\" (.*)$")
	public void cnod_of_MPT_is_PUSHED_to_OCX_for_UNI_A_UNI_B_and_UNI_C(String eNv, String stateRegion) throws Throwable 
	{

		logger.info("==========MTP pushed===========");
		rsponse= TriggerCNODRequest.trigger_MTP_Request(envName, productServiceId1, productServiceId2, productServiceId3);
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
		orderId=oaIds.get("productOrderItemId").get(0);
		 assignProductId=oaIds2.get("assignedProductId").get(0);
	}

	@When("^complete the MPT order by NOTIFY on\"([^\"]*)\"$")
	public void complete_the_MPT_order_by_NOTIFY_on(String eNv) throws Exception 
	{
		logger.info("==========Complete the order by notify on===========");
	    rsponse =TriggerCNODRequest.triggerRequestNotifyOn(envName, orderId);
	    oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
		Thread.sleep(20000);
		logger.info("==========order completed==========="); 
	}

	@When("^pass assignProductId from MPT response to MACD Request\"([^\"]*)\"$")
	public void pass_assignProductId_from_MPT_response_to_MACD_Request(String eNv) throws Throwable 
	{
		logger.info("==========SAREA MACD order creation started===========");
		rsponse1 =TriggerCNODRequest.triggerEVC_MACD(envName, assignProductId);
		productNames=OCXOrderCreation_GAMMA.getElementFromXml(rsponse1,"assignedProductId");
		Thread.sleep(20000);
		logger.info("==========SAREA MACD order created===========");
	}

	@Then("^MACD order ID must be created and Save the  Response$")
	public void macd_order_ID_must_be_created_and_Save_the_Response() throws Exception 
	{
	   
	}

}
