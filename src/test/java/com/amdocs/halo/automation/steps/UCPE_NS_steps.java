package com.amdocs.halo.automation.steps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.main.OCXOrderCreation_GAMMA;
import com.amdocs.halo.automation.main.TriggerCNODRequest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UCPE_NS_steps {
	HashMap<String,List<String>> orderDetails;
	ArrayList<String> listOfCktIds;
	public static Logger logger = null;
	public UCPE_NS_steps () throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(UCPE_NS_steps.class.getName());
		
	}

	@Given("^need UCPE_NS Request \"([^\"]*)\"$")
	public void need_UCPE_NS_Request(String env) throws Throwable 
	{
		 logger.info("==========UNI Order creation started===========");
		 logger.info("==========TESTING===========");
       envName=System.getProperty("enviroment");
		 logger.info(envName);
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response=   tr1.trigger_UCPE_NS(envName);
		 orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(response, "productOrderItemId","productOrderItemStatus","productServiceId","assignedProductId","productOrderId");
		 logger.info(orderDetails); 
		 String productOrderId= orderDetails.get("productOrderId").get(0);
		 String productOrderItemId= orderDetails.get("productOrderItemId").get(0);
		 String assignedProductId= orderDetails.get("assignedProductId").get(0);
		 String productOrderItemStatus= orderDetails.get("productOrderItemStatus").get(0);
		 String productServiceId= orderDetails.get("productServiceId").get(0);
		 logger.info(productOrderId);
		 logger.info(productOrderItemId);
		 logger.info(assignedProductId);
		 logger.info(productOrderItemStatus);
		 logger.info(productServiceId);
	}

	@Given("^need URL to push Request$")
	public void need_URL_to_push_Request() throws Exception 
	{
	   
	}

	@Given("^If needed then only, update the request with Necessary attribute$")
	public void if_needed_then_only_update_the_request_with_Necessary_attribute() throws Exception 
	{
	   
	}

	@When("^PUSH Updated request with URL$")
	public void push_Updated_request_with_URL() throws Exception 
	{
	 
	}

	@Then("^OA ID must be created and Save the RESPONSE$")
	public void oa_ID_must_be_created_and_Save_the_RESPONSE() throws Exception 
	{
	   
	}


}
