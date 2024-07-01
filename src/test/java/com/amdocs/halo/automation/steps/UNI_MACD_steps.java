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

public class UNI_MACD_steps 
{
	public static String rsponse;
	public static HashMap<String, List<String>> oaIds;
	HashMap<String,List<String>> orderDetails;
	public static String assignProductId;
	public static String envName;
	public static String orderId;
	public static String rsponse1;
	public static HashMap<String, List<String>> productNames;
     public static Logger logger = null;
     public UNI_MACD_steps() throws FileNotFoundException, IOException
     {
	logger=LoggerGen.logGen(UNI_MACD_steps.class.getName());
	
    }

	@Given("^need UNI Request \"([^\"]*)\"\"([^\"]*)\"$")
	public void need_UNI_Request(String eNv, String stateRegion) throws Throwable 
	{
		envName=System.getProperty("enviroment");
		logger.info("==========UNI Order creation started===========");
		logger.info("==========TESTING===========");
		 logger.info(eNv);
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response=   tr1.triggerRequestUNI (envName, stateRegion);
		 orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(response, "productOrderItemId","assignedProductId");
		 orderId=orderDetails.get("productOrderItemId").get(0);
		 assignProductId=orderDetails.get("assignedProductId").get(0);
		 logger.info(orderDetails); 
	}

	@Given("^need URL to push based on the attribute of eNv$")
	public void need_URL_to_push_based_on_the_attribute_of_eNv() throws Exception 
	{

		logger.info("==========UNI ORDER CREATED===========");
	    
	}
	@When("^Push updated request with url UNI order created$")
	public void push_updated_request_with_url_UNI_order_created() throws Exception 
	{
		
	}

	@When("^complete the UNI order by notify on\"([^\"]*)\"$")
	public void complete_the_UNI_order_by_notify_on(String eNv) throws Exception
	{
		logger.info("==========Complete the order by notify on===========");
	    rsponse =TriggerCNODRequest.triggerRequestNotifyOn(envName, orderId);
	    oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
		Thread.sleep(20000);
		logger.info("==========order completed===========");
		
	   
	}

	@When("^change assignProductId from UNI response to MACD req\"([^\"]*)\"$")
	public void change_assignProductId_from_UNI_response_to_MACD_req(String eNv) throws Exception 
	{
		logger.info("==========UNI MACD order creation started===========");
		rsponse1 =TriggerCNODRequest.UNI_MACD(envName, assignProductId);
		productNames=OCXOrderCreation_GAMMA.getElementFromXml(rsponse1,"assignedProductId");
		Thread.sleep(20000);
		logger.info("==========UNI MACD order created===========");
	}

	@Then("^MACD OA id must be created and Save the response$")
	public void macd_OA_id_must_be_created_and_Save_the_response() throws Exception 
	{
	  
	}
}
