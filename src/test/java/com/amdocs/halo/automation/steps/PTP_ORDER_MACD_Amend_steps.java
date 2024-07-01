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

public class PTP_ORDER_MACD_Amend_steps 
{
	HashMap<String,List<String>> oaIds= new HashMap<String,List<String>>();
	HashMap<String,List<String>> oaIds2= new HashMap<String,List<String>>();
	HashMap<String,List<String>> productNames= new HashMap<String,List<String>>();
	HashMap<String,List<String>> productName= new HashMap<String,List<String>>();
	HashMap<String,List<String>> productNames2= new HashMap<String,List<String>>();
	HashMap<String,List<String>> circuitIdEvc= new HashMap<String,List<String>>();
	public static HashMap<String, List<String>> productOrderId;
	HashMap<String,List<String>> orderDetails;
	public static String productServiceId1;
	HashMap<String,List<String>> orderDetails1;
	public static String productServiceId2;
	public static String envName;
	public static String rsponse;
	public static String rsponse2;
	public static String rsponse3;
	public static String orderId;
	public static String order;
	public static String orderI;
	public static String orderId2;
	public static String rsponse1;
	public static HashMap<String, List<String>> oaIds3;
	public static String assignProductId;
    public static Logger logger = null;
	
	public PTP_ORDER_MACD_Amend_steps () throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(PTP_ORDER_MACD_Amend_steps .class.getName());
		
	}
	@Given("^env\"([^\"]*)\" is UP for PTP order Creation for PTP_ORDER_MACD_Amend$")
	public void env_is_UP_for_PTP_order_Creation_for_PTP_ORDER_MACD_Amend(String eNv) throws Exception 
	{
		logger.info("=============================================PTP Order Creation Started=============================================");
		logger.info(eNv+" is up");
		envName=eNv;
      envName=System.getProperty("enviroment");
	   
	}

	@When("^CNOD of first UNI is Pushed to OCX for PTP_ORDER_MACD_Amend \"([^\"]*)\" \"([^\"]*)\"$")
	public void cnod_of_first_UNI_is_Pushed_to_OCX_for_PTP_ORDER_MACD_Amend(String eNv, String stateRegion1) throws Throwable 
	{
		logger.info("==========UNI Order creation started===========");
		 logger.info("===========envName==========");
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response=   tr1.triggerRequestUNI_stateRegion (envName, stateRegion1);
		 orderDetails=OCXOrderCreation_GAMMA.getElementFromXml(response,"productServiceId");
		 productServiceId1=orderDetails.get("productServiceId").get(0);
		 logger.info("======================Created 1st UNI============================");
		 logger.info(orderDetails); 
	}

	@When("^CNOD of UNI_B is Pushed to OCX for PTP_ORDER_MACD_Amend \"([^\"]*)\" \"([^\"]*)\"$")
	public void cnod_of_UNI_B_is_Pushed_to_OCX_for_PTP_ORDER_MACD_Amend(String eNv, String stateRegion2) throws Throwable 
	{
		logger.info("==========UNI Order creation started===========");
		 logger.info("===========envName==========");
		 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		 String response=   tr1.triggerRequestUNI_stateRegion (envName, stateRegion2);
		 orderDetails1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productServiceId");
		 productServiceId2=orderDetails1.get("productServiceId").get(0);
		 logger.info("======================Created 2nd UNI============================");
		 logger.info(orderDetails1);
	}

	@When("^CNOD of PTP is PUSHED to OCX by UNI_A And UNI_B for PTP_ORDER_MACD_Amend\"([^\"]*)\" (.*)$")
	public void cnod_of_PTP_is_PUSHED_to_OCX_by_UNI_A_And_UNI_B_for_PTP_ORDER_MACD_Amend(String eNv, String stateRegion) throws Throwable 
	{
		logger.info("==========PTP pushed===========");
		rsponse= TriggerCNODRequest.trigger_PTP_Request(envName, productServiceId1, productServiceId2);
		Thread.sleep(20000);
		oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
		Thread.sleep(20000);
		oaIds2= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"assignedProductId");
		Thread.sleep(20000);
		productNames=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productName");
		Thread.sleep(20000);
		circuitIdEvc=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productServiceId");
		Thread.sleep(20000);
		productOrderId=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderId");
		logger.info("======================Created PTP EVC============================");
		orderI=oaIds.get("productOrderItemId").get(0);
		 assignProductId=oaIds2.get("assignedProductId").get(0);
	}

	@When("^complete the PTP ORDER by NOTIFY on to change\"([^\"]*)\"$")
	public void complete_the_PTP_ORDER_by_NOTIFY_on_to_change(String eNv) throws Exception 
	{

		logger.info("==========Complete the order by notify on===========");
	    rsponse1 =TriggerCNODRequest.triggerRequestNotifyOn(envName, orderI);
	    oaIds3= OCXOrderCreation_GAMMA.getElementFromXml(rsponse1,"productOrderItemId");
		Thread.sleep(20000);
		logger.info("==========order completed==========="); 
	}

	@When("^pass AssignProductId from PTP response to MACD Request order ID must be created and Save the Response\"([^\"]*)\"$")
	public void pass_AssignProductId_from_PTP_response_to_MACD_Request_order_ID_must_be_created_and_Save_the_Response(String eNv) throws Throwable 
	{
		logger.info("==========PTP MACD order creation started===========");
		rsponse2 =TriggerCNODRequest.triggerEVC_MACD(envName, assignProductId);
		productName=OCXOrderCreation_GAMMA.getElementFromXml(rsponse2,"productOrderItemId");
		productNames2=OCXOrderCreation_GAMMA.getElementFromXml(rsponse2,"assignedProductId");
		Thread.sleep(20000);
		logger.info("==========PTP MACD order created===========");
		order=productName.get("productOrderItemId").get(0);
		orderId2=productNames2.get("assignedProductId").get(0);
		Thread.sleep(20000);
	}

	@When("^pass productOrderItemId and AssignProductId from EVC MACD to PTP_ORDER_MACD_Amend and push \"([^\"]*)\"$")
	public void pass_productOrderItemId_and_AssignProductId_from_EVC_MACD_to_PTP_ORDER_MACD_Amend_and_push(String eNv) throws Throwable 
	{
		logger.info("==========SAREA_MACD_Amend pushed===========");
		rsponse3=TriggerCNODRequest.trigger_EVC_MACD_Amend_Request(envName, order, orderId2);
		Thread.sleep(20000);
		oaIds3= OCXOrderCreation_GAMMA.getElementFromXml(rsponse3,"productOrderItemId","assignedProductId");
	}

	@Then("^PTP_ORDER_MACD_Amend is created in OCX\\.$")
	public void ptp_order_macd_amend_is_created_in_OCX() throws Exception 
	{
	   
	}
}
