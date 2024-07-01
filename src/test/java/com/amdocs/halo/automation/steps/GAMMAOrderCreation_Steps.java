package com.amdocs.halo.automation.steps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
//import org.testng.collections.Sets;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.main.OCXOrderCreation_GAMMA;

//import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GAMMAOrderCreation_Steps 
{
	HashMap<String,List<String>> oaIds= new HashMap<String,List<String>>();
	//HashMap<String,List<String>> sgIds= new HashMap<String,List<String>>();
	HashMap<String,List<String>> apIds= new HashMap<String,List<String>>();
	HashMap<String,List<String>> productNames= new HashMap<String,List<String>>();
	HashMap<String,List<String>> circuitIdA= new HashMap<String,List<String>>();
	HashMap<String,List<String>> circuitIdEvc= new HashMap<String,List<String>>();
	HashMap<String,List<String>> productOrderId= new HashMap<String,List<String>>();
	String [] uniDetails= null;
	public static String isAdxValue;
	public static String envName;
	public static String stteRgion;
	public static String response;
	public static OCXOrderCreation_GAMMA gammaOcx;
	ArrayList<String> listOfCktIds= new ArrayList<String>();
	
	public static Logger logger = null;
	
	public GAMMAOrderCreation_Steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(GAMMAOrderCreation_Steps.class.getName());
		gammaOcx= new OCXOrderCreation_GAMMA();
	}

	
	@Given("^Env\"([^\"]*)\" is up for MPT order creation for \"([^\"]*)\"$")
	public void env_is_up_for_MPT_order_creation_for(String env, String isAdx) throws Exception 
	{
		Thread.sleep(20000);
		logger.info("=============================================MPT Order Creation Started=============================================");
		logger.info(env+" is up");
		isAdxValue=isAdx;
		envName=env;
		envName=System.getProperty("enviroment");
		logger.info(envName + " is up");
		
	}
	
	@When("^CNOD of UNI_A is pushed to OCX for MPT \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void cnod_of_UNI_A_is_pushed_to_OCX_for_MPT(String env, String stateRegion, String isFttbNeeded, String isTSP) throws Exception 
	{
		logger.info("======================Creating 1st UNI============================");
		Thread.sleep(50000);
		String rsponse=OCXOrderCreation_GAMMA.triggerGammaOrder(env,"UNI", stateRegion, isFttbNeeded, isAdxValue, isTSP,null,"null");
		Thread.sleep(20000);
		circuitIdA=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productServiceId");
		
		listOfCktIds.add(0,circuitIdA.get("productServiceId").get(0));
		circuitIdA=null;
		logger.info("======================Created 1st UNI============================");
		
		gammaOcx.saveGammaOrderDetails("UNI A",rsponse, isAdxValue, stateRegion, isFttbNeeded, isTSP, null, null);
		
	}

	@When("^CNOD of UNI_B is pushed to OCX for MPT\"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"  \"([^\"]*)\"$")
	public void cnod_of_UNI_B_is_pushed_to_OCX_for_MPT(String env, String stateRegion, String isFttbNeeded, String isTSP) throws Exception 
	{
		logger.info("======================Creating 2nd UNI============================");
		Thread.sleep(50000);
		String rsponse=OCXOrderCreation_GAMMA.triggerGammaOrder(env,"UNI", stateRegion, isFttbNeeded, isAdxValue, isTSP,null,null);
		Thread.sleep(20000);
		circuitIdA=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productServiceId");
		listOfCktIds.add(1,circuitIdA.get("productServiceId").get(0));
		circuitIdA=null;
		logger.info("======================Created 2nd UNI============================");
		
		gammaOcx.saveGammaOrderDetails("UNI B",rsponse, isAdxValue, stateRegion, isFttbNeeded, isTSP,null,null);
	}

	@When("^CNOD of UNI_C is pushed to OCX for MPT\"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"  \"([^\"]*)\"$")
	public void cnod_of_UNI_C_is_pushed_to_OCX_for_MPT(String env, String stateRegion, String isFttbNeeded, String isTSP) throws Exception 
	{
		logger.info("======================Creating 3rd UNI============================");
		Thread.sleep(50000);
		String rsponse=OCXOrderCreation_GAMMA.triggerGammaOrder(env,"UNI", stateRegion, isFttbNeeded, isAdxValue, isTSP,null,null);
		Thread.sleep(20000);
		circuitIdA=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productServiceId");
		listOfCktIds.add(2,circuitIdA.get("productServiceId").get(0));
		circuitIdA=null;
		logger.info("======================Created 3rd UNI============================");
		
		gammaOcx.saveGammaOrderDetails("UNI C",rsponse, isAdxValue, stateRegion, isFttbNeeded, isTSP,null, null);
	}

	

	@When("^CNOD of MPT is pushed to OCX for \"([^\"]*)\" \"([^\"]*)\"$")
	public void cnod_of_MPT_is_pushed_to_OCX_for(String env, String whichUNIADXHost) throws Exception 
	{
		logger.info("==================Below are the UNI Order Details==========================");
		Thread.sleep(20000);
		logger.info("All the Circuit IDs are: ");
		for(int i=0; i<listOfCktIds.size();++i)
		{
			logger.info(listOfCktIds.get(i));
		}
		
		logger.info("======================Creating MPT EVC============================");
		response=OCXOrderCreation_GAMMA.triggerGammaOrder(envName,"MPT", "null", "null",isAdxValue,"null",listOfCktIds,whichUNIADXHost);
		Thread.sleep(20000);
		oaIds= OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		Thread.sleep(20000);
		productNames=OCXOrderCreation_GAMMA.getElementFromXml(response,"productName");
		Thread.sleep(20000);
		circuitIdEvc=OCXOrderCreation_GAMMA.getElementFromXml(response,"productServiceId");
		Thread.sleep(20000);
		logger.info("======================Created MPT EVC============================");
		
		
	}

	
	@Then("^MPT OA is created in OCX\\.$")
	public void mpt_OA_is_created_in_OCX() throws Exception 
	{
		logger.info("==================Below are the EVC Order Details==========================");
		
		for(int i=0; i<productNames.get("productName").size();++i)
		{
			logger.info("Order Action ID of Circuit: "+circuitIdEvc.get("productServiceId").get(i)+" for Product Name: "+productNames.get("productName").get(i)+" is : "+oaIds.get("productOrderItemId").get(i));
			Thread.sleep(4000);
			
			gammaOcx.saveGammaOrderDetails("MPT "+productNames.get("productName").get(i),response, isAdxValue, null, null, null, circuitIdEvc.get("productServiceId").get(i), oaIds.get("productOrderItemId").get(i));
		}
		
		logger.info("=============================================MPT Order Creation Completed=============================================");
		Thread.sleep(60000);
		logger.info("\n\n\n\n\n");
		gammaOcx.publishAllOrders();
	}

}
