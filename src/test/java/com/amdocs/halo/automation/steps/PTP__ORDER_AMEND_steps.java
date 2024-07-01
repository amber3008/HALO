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

public class PTP__ORDER_AMEND_steps 
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
	public static String orderId1;
    public static Logger logger = null;
		
	public PTP__ORDER_AMEND_steps() throws FileNotFoundException, IOException
	{
			logger=LoggerGen.logGen(PTP__ORDER_AMEND_steps.class.getName());
			
	}
	@Given("^Env\"([^\"]*)\" is UP for PTP_Amend order Creation for$")
	public void env_is_UP_for_PTP_Amend_order_Creation_for(String eNv) throws Exception 
	{
		logger.info("=============================================PTP Order Creation Started=============================================");
		logger.info(eNv+" is up");
		envName=eNv;
      envName=System.getProperty("enviroment");
	}

	@When("^CNOD of first UNI is pushed to OCX for PTP_Amend\"([^\"]*)\" \"([^\"]*)\"$")
	public void cnod_of_first_UNI_is_pushed_to_OCX_for_PTP_Amend(String eNv, String stateRegion) throws Throwable 
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

	@When("^CNOD of UNI_B is pushed to OCX for PTP_Amend\"([^\"]*)\" \"([^\"]*)\"$")
	public void cnod_of_UNI_B_is_pushed_to_OCX_for_PTP_Amend(String eNv, String stateRegion) throws Throwable 
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

	@When("^CNOD of PTP_Amend is PUSHED to OCX for UNI_A and UNI_B\"([^\"]*)\" (.*)$")
	public void cnod_of_PTP_Amend_is_PUSHED_to_OCX_for_UNI_A_and_UNI_B(String eNv, String stateRegion) throws Throwable 
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
		orderId=oaIds.get("productOrderItemId").get(0);
		 orderId1=oaIds2.get("assignedProductId").get(0);
		 Thread.sleep(20000);
	}

	@When("^pass productOrderItemId andassignedProductId to PTP_Amend and push \"([^\"]*)\"$")
	public void pass_productOrderItemId_andassignedProductId_to_PTP_Amend_and_push(String eNv) throws Throwable 
	{
		logger.info("==========PTP_Amend pushed===========");
		rsponse=TriggerCNODRequest.triggerEVC_Amend_Request(envName, orderId, orderId1);
		Thread.sleep(20000);
		oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId","assignedProductId"); 
		
		 
	}

	@Then("^PTP_Amend is created in OCX\\.$")
	public void ptp_amend_is_created_in_OCX() throws Exception 
	{
	   
	}
}
