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

public class VNF_Cancel_steps {
	HashMap<String,List<String>> orderDetails;
	ArrayList<String> listOfCktIds;
	public static HashMap<String, List<String>> oaIds;
	public static String orderId;
	public static String rsponse;
	public static String envName;
	public static Logger logger = null;
	public VNF_Cancel_steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(VNF_Cancel_steps.class.getName());
		
	}
	
	@Given("^Env is up for VNF_Cancel order Creation for \"([^\"]*)\"$")
	public void env_is_up_for_VNF_Cancel_order_Creation_for(String env) throws Exception 
	{
	   envName=System.getProperty("enviroment");
	}

	@When("^CNOD of VNF is Pushed to OCX for VNF_Cancel\"([^\"]*)\"$")
	public void cnod_of_VNF_is_Pushed_to_OCX_for_VNF_Cancel(String env) throws Throwable 
	{
		 logger.info("==========VNF Order creation started===========");
		 logger.info("==========TESTING===========");
		 logger.info(envName);
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response=   tr1.trigger_VNF_standAlone(envName);
		 orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(response, "productOrderItemId","productOrderItemStatus","productServiceId","assignedProductId","productOrderId");
		 orderId=orderDetails.get("productOrderItemId").get(0);
		 logger.info(orderDetails); 
		 Thread.sleep(20000);
	}

	@When("^Push cancel request for VNF_Cancel\"([^\"]*)\"$")
	public void push_cancel_request_for_VNF_Cancel(String env) throws Throwable 
	{
		logger.info("==========VNF Cancel Order creation started===========");
		
		rsponse =TriggerCNODRequest.triggerUNI_Cancel(envName, orderId);
		oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
		logger.info("==========VNF Cancel Order created===========");
	}

	@Then("^VNF_Cancel is created in OCX\\.$")
	public void vnf_cancel_is_created_in_OCX() throws Exception 
	{
	   
	}
}
