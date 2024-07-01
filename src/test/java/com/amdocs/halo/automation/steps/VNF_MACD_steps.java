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

public class VNF_MACD_steps {
	public static String rsponse;
	public static HashMap<String, List<String>> oaIds;
	HashMap<String,List<String>> orderDetails;
	public static String assignProductId;
	public static String envName;
	public static String orderId;
	public static String rsponse1;
	public static HashMap<String, List<String>> productNames;
     public static Logger logger = null;
     public VNF_MACD_steps() throws FileNotFoundException, IOException
     {
	logger=LoggerGen.logGen(VNF_MACD_steps.class.getName());
	
    }

	@Given("^need VNF Request \"([^\"]*)\"$")
	public void need_VNF_Request(String env) throws Throwable 
	{
		logger.info("==========UNI Order creation started===========");
		logger.info("==========TESTINGd===========");
      envName=System.getProperty("enviroment");
		 logger.info(envName);
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response=   tr1.trigger_VNF_standAlone (envName);
		 orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(response, "productOrderItemId","assignedProductId");
		 orderId=orderDetails.get("productOrderItemId").get(0);
		 assignProductId=orderDetails.get("assignedProductId").get(0);
		 logger.info(orderDetails); 
	}

	@Given("^need URL to PUSH based on the Attribute of ENv$")
	public void need_URL_to_PUSH_based_on_the_Attribute_of_ENv() throws Exception 
	{
	    
	}

	@When("^Push updated request with url VNF order created$")
	public void push_updated_request_with_url_VNF_order_created() throws Exception 
	{
	   
	}

	@When("^complete the VNF order by notify on\"([^\"]*)\"$")
	public void complete_the_VNF_order_by_notify_on(String env) throws Exception 
	{
		logger.info("==========Complete the order by notify on===========");
	    rsponse =TriggerCNODRequest.triggerRequestNotifyOn(envName, orderId);
	    
		Thread.sleep(20000);
		logger.info("==========order completed===========");
	  
	}

	@When("^change assignProductId from VNF response to MACD req\"([^\"]*)\"$")
	public void change_assignProductId_from_VNF_response_to_MACD_req(String env) throws Exception 
	{
		logger.info("==========VNF MACD order creation started===========");
		rsponse1 =TriggerCNODRequest.VNF_MACD(envName, assignProductId);
		logger.info("==========VNF MACD order created===========");
	}

	@Then("^VNF_MACD OA id must be created and Save the response$")
	public void vnf_macd_OA_id_must_be_created_and_Save_the_response() throws Exception 
	{
	   
	}
}
