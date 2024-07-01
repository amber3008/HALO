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

public class SAREA_Cancel_steps 
{
	public static String envName;
	public static HashMap<String, List<String>> orderDetails;
	public static String orderId;
	public static String rsponse;
	public static HashMap<String, List<String>> oaIds;
	public static Logger logger = null;
	public SAREA_Cancel_steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(SAREA_Cancel_steps.class.getName());
		
	}

	@Given("^Env is up for SAREA_Cancel order Creation for \"([^\"]*)\"$")
	public void env_is_up_for_SAREA_Cancel_order_Creation_for(String Env) throws Exception 
	{
		envName=Env;
      envName=System.getProperty("enviroment");
	}

	@When("^SAREA is Pushed to OCX for SAREA_Cancel \"([^\"]*)\"$")
	public void sarea_is_Pushed_to_OCX_for_SAREA_Cancel(String Env) throws Throwable 
	{
		logger.info(envName);
		String rsponse2=TriggerSareaRequest.triggerRequest(envName);
		
		 //TriggerCNODRequest tr1 = new TriggerCNODRequest();
		    
		 //String response=tr1.triggerRequestUNI_Cancel(envName, stateRegion);
		 orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(rsponse2, "productOrderItemId","assignedProductId");
		 orderId=orderDetails.get("productOrderItemId").get(0);
		  
		  logger.info(orderDetails);   
	}

	@When("^Push cancel request for SAREA_Cancel\"([^\"]*)\"$")
	public void push_cancel_request_for_SAREA_Cancel(String Env) throws Throwable 
	{
		 rsponse =TriggerCNODRequest.triggerUNI_Cancel(envName, orderId);
		    oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
			Thread.sleep(20000);
	}

	@Then("^SAREA_Cancel is created in OCX\\.$")
	public void sarea_cancel_is_created_in_OCX() throws Exception 
	{
	    
	}
}
