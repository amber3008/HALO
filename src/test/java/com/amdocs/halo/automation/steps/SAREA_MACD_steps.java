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

public class SAREA_MACD_steps 
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
    public SAREA_MACD_steps() throws FileNotFoundException, IOException
    {
	logger=LoggerGen.logGen(SAREA_MACD_steps.class.getName());
	
   }
	@Given("^need SAREA Request \"([^\"]*)\"$")
	public void need_SAREA_Request(String eNv) throws Throwable 
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

	@Given("^need URL to push based on the attribute$")
	public void need_URL_to_push_based_on_the_attribute() throws Exception 
	{
		
	}

	@When("^PUSH updated request with url UNI order created$")
	public void push_updated_request_with_url_UNI_order_created() throws Exception 
	{
	   
	}

	@When("^complete the SAREA order by notify on\"([^\"]*)\"$")
	public void complete_the_SAREA_order_by_notify_on(String eNv) throws Exception
	{
	    
		logger.info("==========Complete the order by notify on===========");
	    rsponse =TriggerCNODRequest.triggerRequestNotifyOn(envName, orderId);
	    oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
		Thread.sleep(20000);
		logger.info("==========order completed===========");
	}

	@When("^change assignProductId from SAREA response to MACD req\"([^\"]*)\"$")
	public void change_assignProductId_from_SAREA_response_to_MACD_req(String eNv) throws Exception 
	{
		logger.info("==========SAREA MACD order creation started===========");
		rsponse1 =TriggerCNODRequest.SAREA_MACD(envName, assignProductId);
		productNames=OCXOrderCreation_GAMMA.getElementFromXml(rsponse1,"assignedProductId");
		Thread.sleep(20000);
		logger.info("==========SAREA MACD order created===========");
	}

	@Then("^MACD order id must be created and Save the response$")
	public void macd_order_id_must_be_created_and_Save_the_response() throws Exception 
	{
	    

	}

}
