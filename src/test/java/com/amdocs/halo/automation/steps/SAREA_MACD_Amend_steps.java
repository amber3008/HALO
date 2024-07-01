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

public class SAREA_MACD_Amend_steps 
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
    public SAREA_MACD_Amend_steps() throws FileNotFoundException, IOException
    {
	logger=LoggerGen.logGen(SAREA_MACD_Amend_steps.class.getName());
	
   }
	@Given("^Env is up for SAREA_MACD_Amend order creation for \"([^\"]*)\"$")
	public void env_is_up_for_SAREA_MACD_Amend_order_creation_for(String eNv) throws Exception 
	{
	   envName=System.getProperty("enviroment");
	}

	@When("^SAREA order is pushed to OCX for SAREA_MACD_Amend\"([^\"]*)\"$")
	public void sarea_order_is_pushed_to_OCX_for_SAREA_MACD_Amend(String eNv) throws Throwable 
	{
		envName=System.getProperty("enviroment");
		logger.info("==========SAREA Order creation started===========");
		TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response=   tr1.triggerRequest(envName);
		 orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(response, "productOrderItemId","assignedProductId");
		 orderId=orderDetails.get("productOrderItemId").get(0);
		 assignProductId=orderDetails.get("assignedProductId").get(0);
		 logger.info(orderDetails);   
	}

	@When("^complete the ORDER by notify on\"([^\"]*)\"$")
	public void complete_the_ORDER_by_notify_on(String eNv) throws Exception 
	{
		logger.info("==========Complete the order by notify on===========");
	    rsponse =TriggerCNODRequest.triggerRequestNotifyOn(envName, orderId);
	    oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
		Thread.sleep(20000);
		logger.info("==========order completed==========="); 
	}

	@When("^Perform MACD operation for above sarea order\"([^\"]*)\"$")
	public void perform_MACD_operation_for_above_sarea_order(String eNv) throws Exception
	{
		logger.info("==========SAREA MACD order creation started===========");
		rsponse1 =TriggerCNODRequest.SAREA_MACD(envName, assignProductId);
		orderDetails1= OCXOrderCreation_GAMMA.getElementFromXml(rsponse1, "productOrderItemId","assignedProductId");
		Thread.sleep(20000);
		logger.info("==========SAREA MACD order created===========");
		 orderId1=orderDetails1.get("productOrderItemId").get(0);
		 assignProductId=orderDetails1.get("assignedProductId").get(0);
		 logger.info(orderDetails1);
	}

	@When("^pass productOrderItemId and assignedProductId to SAREA_MACD_Amend and push \"([^\"]*)\"$")
	public void pass_productOrderItemId_and_assignedProductId_to_SAREA_MACD_Amend_and_push(String eNv) throws Throwable 
	{
		logger.info("==========SAREA_MACD_Amend pushed===========");
		String rsponse2=TriggerCNODRequest.trigger_SAREA_MACD_Amend_Request(envName, orderId1, assignProductId);
		Thread.sleep(20000);
		oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse2,"productOrderItemId","assignedProductId");
	}

	@Then("^SAREA_MACD_Amend is created in OCX\\.$")
	public void sarea_macd_amend_is_created_in_OCX() throws Exception 
	{
		
	}


}
