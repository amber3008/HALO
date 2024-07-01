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



public class UNI_Cancel_steps 
{
	public static HashMap<String, List<String>> oaIds;
	public static String rsponse;
	HashMap<String,List<String>> orderDetails;
	public static String orderId;
	
	public static Logger logger = null;
	
	public UNI_Cancel_steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(UNI_Cancel_steps.class.getName());
	}

	
	@Given("^Env is up for UNI_Disconnect order Creation for \"([^\"]*)\"$")
	public void env_is_up_for_UNI_Disconnect_order_Creation_for(String Env) throws Exception 
	{
	   
	}

	@When("^CNOD of UNI is Pushed to OCX for UNI_Disconnect\"([^\"]*)\"\"([^\"]*)\"$")
	public void cnod_of_UNI_is_Pushed_to_OCX_for_UNI_Disconnect(String Env, String stateRegion) throws Throwable 
	{
      envName=System.getProperty("enviroment");
		logger.info(envName);
		
		  
		// TriggerCNODRequest tr1 = new TriggerCNODRequest();
		    
		 String response=TriggerCNODRequest.triggerRequestUNI_Cancel(envName, stateRegion);
		 orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(response, "productOrderItemId","assignedProductId");
		 orderId=orderDetails.get("productOrderItemId").get(0);
		  
		  logger.info(orderDetails); 
	}

	@When("^Push cancel request for UNI_Cancel\"([^\"]*)\"\"([^\"]*)\"$")
	public void push_cancel_request_for_UNI_Cancel(String Env, String stateRegion) throws Throwable 
	{
		 rsponse =TriggerCNODRequest.triggerUNI_Cancel(envName, orderId);
		    oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
			Thread.sleep(20000);
	}

	@Then("^UNI_Cancel is created in OCX\\.$")
	public void uni_cancel_is_created_in_OCX() throws Exception 
	{
	    
	}
}
