package com.amdocs.halo.automation.steps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.main.OCXOrderCreation_GAMMA;
import com.amdocs.halo.automation.main.TriggerB2CSP;

//import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GAMMAOrderCreationAVPN_Steps 
{
	public static Logger logger = null;
	public static String envName="null";
	public static String isAdxValue;
	HashMap<String,List<String>> orderActionIds= new HashMap<String,List<String>>();
	HashMap<String,List<String>> productNames= new HashMap<String,List<String>>();
	
	public GAMMAOrderCreationAVPN_Steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(GAMMAOrderCreationAVPN_Steps.class.getName());
	}
	
	@Given("^Env\"([^\"]*)\" is up for AVLAN order creation$")
	public void env_is_up_for_AVLAN_order_creation(String env) throws Exception 
	{
		logger.info("=============================================AVPN GAMMA Order Creation Started=============================================");
		logger.info(env+" is up");		
		envName=env;
      envname=System.getProperty("enviroment");
	}

	@Given("^No of VLAN (\\d+)$")
	public void no_of_VLAN(int arg1) throws Exception 
	{
	   
	}

	@Given("^CNOD of APORT and AVLAN is pushed to OCX$")
	public void cnod_of_APORT_and_AVLAN_is_pushed_to_OCX() throws Exception 
	{
		logger.info("======================Creating APORT AVLAN Order============================");
		Thread.sleep(50000);
		String rsponse=OCXOrderCreation_GAMMA.triggerGammaOrder(envName,"AVPN", "null", "null", "null", "null",null,"null");
		Thread.sleep(20000);
		orderActionIds=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
		Thread.sleep(20000);
		productNames=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productName");
		Thread.sleep(20000);
		logger.info("======================Created APORT AVLAN Order============================");
	}	  
	
	@Then("^AVPN GAMMA Order is created in OCX\\.$")
	public void avpn_GAMMA_Order_is_created_in_OCX() throws Exception 
	{
		logger.info("==================Below are the AVPN Order Details==========================");
		
		for(int i=0; i<productNames.get("productName").size();++i)
		{
			logger.info("Order Action ID of:  "+productNames.get("productName").get(i)+"  is : "+orderActionIds.get("productOrderItemId").get(i));
			Thread.sleep(4000);
		}
		logger.info("=============================================AVPN GAMMA Order Creation Started=============================================");
	}
	
	@Given("^Env\"([^\"]*)\" is up for BTWOCSP order creation for \"([^\"]*)\"$")
	public void env_is_up_for_BTWOCSP_order_creation_for(String env, String isAdx) throws Exception 
	{
		
		envName=env;
		isAdxValue=isAdx;
	}

	@When("^CNOD of BTWOCSP is pushed to OCX$")
	public void cnod_of_BTWOCSP_is_pushed_to_OCX() throws Exception 
	{
		logger.info("======================Creating B2CSP Order====================");
		//Thread.sleep(50000);
		String rsponse=TriggerB2CSP.triggerB2CSPRequest(envName,"B2CSP" , isAdxValue);
		Thread.sleep(10000);
		orderActionIds=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItem");
		Thread.sleep(10000);
		productNames=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productName");
		Thread.sleep(10000);
		logger.info("======================Created B2CSP Order============================");
	}


	@Then("^OA is created in OCX\\.$")
	public void oa_is_created_in_OCX() throws Exception 
	{
	
	}
	
}
