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

public class SAREA_disconnect_steps {
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
	
	public SAREA_disconnect_steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(SAREA_disconnect_steps.class.getName());
		
	}
	

	@Given("^Env is up for SAREA_DISCONNECT order creation for \"([^\"]*)\"$")
	public void env_is_up_for_SAREA_DISCONNECT_order_creation_for(String Env) throws Throwable 
	{
		
	    envName=Env;
      envName=System.getProperty("enviroment");
	    
	}

	@When("^CNOD of SAREA is pushed to OCX for SAREA_DISCONNECT\"([^\"]*)\"$")
	public void cnod_of_SAREA_is_pushed_to_OCX_for_SAREA_DISCONNECT(String Env) throws Throwable 
	{

		logger.info("==========SAREA request has been pushed===========");
	   
		TriggerCNODRequest tr1 = new TriggerCNODRequest();
	    String response=tr1.triggerRequest(envName);
		 orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(response, "productOrderItemId","assignedProductId");
		 orderId=orderDetails.get("productOrderItemId").get(0);
		 assignProductId=orderDetails.get("assignedProductId").get(0);
		 logger.info(orderDetails); 
	    logger.info("==========SAREA order created==========="); 
	   
	}

	@When("^Push notify on request for SAREA_DISCONNECT\"([^\"]*)\"$")
	public void push_notify_on_request_for_SAREA_DISCONNECT(String Env) throws Exception 
	{
		logger.info("==========Complete the order by notify on===========");
	    rsponse =TriggerCNODRequest.triggerRequestNotifyOn(envName, orderId);
	    oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
		Thread.sleep(20000);
		logger.info("==========order completed===========");
		
	}

	@When("^Push Disconnect request for SAREA_DISCONNECT\"([^\"]*)\"$")
	public void push_Disconnect_request_for_SAREA_DISCONNECT(String Env) throws Exception 
	{
		logger.info("==========SAREA Disconnect order creation started===========");
		rsponse1 =TriggerCNODRequest.UNI_Disconnect(envName, assignProductId);
		productNames=OCXOrderCreation_GAMMA.getElementFromXml(rsponse1,"assignedProductId");
		Thread.sleep(20000);
		logger.info("==========SAREA Disconnect order created==========="); 
	}

	@Then("^SAREA_DISCONNECT is created in OCX\\.$")
	public void sarea_disconnect_is_created_in_OCX() throws Exception 
	{
	    
	}

}
