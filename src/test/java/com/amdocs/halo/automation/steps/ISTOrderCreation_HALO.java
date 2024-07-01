package com.amdocs.halo.automation.steps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

//import com.amdocs.halo.automation.base.DBConnection;
import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.main.OCXOrderStatus;
import com.amdocs.halo.automation.main.OrderCreationInOCX;
import com.amdocs.halo.automation.main.SaveTNAndUDR;
import com.amdocs.halo.automation.main.TriggerFulfimentComplete;
import com.amdocs.halo.automation.main.TriggerMOR_SGLevel;
import com.amdocs.halo.automation.main.TriggerManageFlowStatus;
import com.amdocs.halo.automation.main.TriggerNotifyRequest;
import com.amdocs.halo.automation.main.UpdateOAAdditionalInfoReq;
import com.amdocs.halo.automation.main.UpdateOaInSubmitRequest;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ISTOrderCreation_HALO 
{
	public static String envName;
	public static String whatToChange;
	public static TriggerNotifyRequest tnr;
	public static String typeOfOrder;
	public static String typeOfChange;
	public static String statusOfOrder;
	public static String orderActionId;
	public static String serviceGroupId;
	public static String [] sorProjID;
	public static String [] orderCreationStatus;
	public static ArrayList<String> ar;
	public static ArrayList<String> ar1;
	public static ArrayList<String> ar2;
	public static ArrayList<String> ar3;
	public static int ipflex=0;
	public static String bvoipOrderId;
	public static int requestId;
	public static String bvflexOrderId;
	
	public static TriggerMOR_SGLevel tmor;
	public static Logger logger = null;
	public static int hasIpflex=0;
	public static int fulfilment=0;
	
	public ISTOrderCreation_HALO() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(ISTOrderCreation_HALO.class.getName());
	}
	
	@Given("^CASO is pushed to OCX \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" (.*)$")
	public void caso_is_pushed_to_OCX(String env, String orderType, String changeType, String whatChange) throws Exception
	{
		TriggerNotifyRequest.archType="NEW";
		Thread.sleep(18000);
		envName=env;
      envName=System.getProperty("enviroment");
	//	TriggerNotifyRequest.whatToChange=whatChange;
		
		//String[] split = whatChange.split("\*"\s*");
		
		typeOfOrder=orderType;
		typeOfChange=changeType;
		OrderCreationInOCX ordrCreatnOCX= new OrderCreationInOCX();
		OCXOrderStatus ocxStatus= new OCXOrderStatus();
		sorProjID= tnr.getSorProjId();
		logger.info("Env is : "+env);
		logger.info("SOR ID is : "+sorProjID[1]);
		logger.info("Projet ID is : "+sorProjID[2]);
		logger.info("changeType is : "+changeType);
		logger.info("What type of MACD is : "+TriggerNotifyRequest.whatToChange);
		
		if(changeType.equalsIgnoreCase("NS"))
			ordrCreatnOCX.casoPush(envName, orderType, sorProjID[1], sorProjID[2],changeType, TriggerNotifyRequest.whatToChange);
		else
			ordrCreatnOCX.casoPushMACD(envName, orderType, sorProjID[1], sorProjID[2], TriggerNotifyRequest.whatToChange);
		Thread.sleep(20000);
		ArrayList<List<String>> ordrDetailsCaso=OrderCreationInOCX.retreiveCASOOrdrDetails();
		
		String ordrStatus=ordrDetailsCaso.get(3).get(0);
		logger.info("The status of Order is : "+ordrStatus);
		statusOfOrder=null;
		statusOfOrder=ordrStatus;
		
		String ordrActnId=ordrDetailsCaso.get(2).get(0);
		logger.info("The Order Action ID is : "+ordrActnId);
		orderActionId=null;
		orderActionId=ordrActnId;
		
		String sgId=ordrDetailsCaso.get(0).get(0);
		logger.info("The Service group ID is : "+sgId);
		serviceGroupId=null;
		serviceGroupId=sgId;
		
    	String srviceTyp=ordrDetailsCaso.get(1).get(0);
		logger.info("The Service Type is : "+srviceTyp);
		
		if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("IPFLEXMIS"))
		{
		    bvflexOrderId=ordrDetailsCaso.get(2).get(5);
			logger.info("The IPFLEX Order Action ID is : "+bvflexOrderId);
		}

		
		OCXOrderStatus.getOcxOrderStatusUrl(sorProjID[1], envName, TriggerNotifyRequest.archType);
	}
	
	/*
	 * @Given("^CASO is pushed to OCX \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	 * public void caso_is_pushed_to_OCX(String env, String orderType, String
	 * changeType) throws Exception { TriggerNotifyRequest.archType="NEW";
	 * Thread.sleep(18000); envName=env; typeOfOrder=orderType;
	 * typeOfChange=changeType; OrderCreationInOCX ordrCreatnOCX= new
	 * OrderCreationInOCX(); OCXOrderStatus ocxStatus= new OCXOrderStatus();
	 * sorProjID= tnr.getSorProjId(); if(changeType.equalsIgnoreCase("NS"))
	 * ordrCreatnOCX.casoPush(env, orderType, sorProjID[1],
	 * sorProjID[2],changeType); else ordrCreatnOCX.casoPushMACD(env, orderType,
	 * sorProjID[1], sorProjID[2]); Thread.sleep(20000); ArrayList<List<String>>
	 * ordrDetailsCaso=OrderCreationInOCX.retreiveCASOOrdrDetails();
	 * 
	 * String ordrStatus=ordrDetailsCaso.get(3).get(0);
	 * logger.info("The status of Order is : "+ordrStatus); statusOfOrder=null;
	 * statusOfOrder=ordrStatus;
	 * 
	 * String ordrActnId=ordrDetailsCaso.get(2).get(0);
	 * logger.info("The Order Action ID is : "+ordrActnId); orderActionId=null;
	 * orderActionId=ordrActnId;
	 * 
	 * String sgId=ordrDetailsCaso.get(0).get(0);
	 * logger.info("The Service group ID is : "+sgId); serviceGroupId=null;
	 * serviceGroupId=sgId;
	 * 
	 * String srviceTyp=ordrDetailsCaso.get(1).get(0);
	 * logger.info("The Service Type is : "+srviceTyp);
	 * 
	 * OCXOrderStatus.getOcxOrderStatusUrl(sorProjID[1], envName,
	 * TriggerNotifyRequest.archType);
	 * 
	 * }
	 */
	
	
	@Then("^submit of OA level is triggered\\.$")
	public void submit_of_OA_level_is_triggered() throws Exception 
	{
		logger.info("==============If order not submitted , Submitting theorder==================");
	    logger.info("The status of the order is: "+statusOfOrder);
	    ar1=OCXOrderStatus.getOcxOrderStatusUrlForOrderId(orderActionId, envName, "NEW");
	    if(statusOfOrder.equalsIgnoreCase("DE"))
	    {
	    	logger.info("The order is successfully submitted from OCX");
	    	ar1=OCXOrderStatus.getOcxOrderStatusUrlForOrderId(orderActionId, envName, "NEW");
	    }
	    if(statusOfOrder.equalsIgnoreCase("NE") && (!TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("BOE")))
	    {
			//String columnValue=DBConnection.executeQueryForOrderStatus(orderActionId, envName);
			//DBConnection.executeTnrangeTnParameter(envName);
	    	String submitOrderRequest = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/SubmitOrderActionRequest.json"), StandardCharsets.UTF_8);
			JSONObject jsonObject = new JSONObject(submitOrderRequest);
			String updatedRequest=UpdateOaInSubmitRequest.updateOaIdInRequest(submitOrderRequest,orderActionId);
			logger.info("Request after update is : "+updatedRequest);
			Thread.sleep(10000);
			UpdateOaInSubmitRequest.pushSubmitOrderRequest(updatedRequest,envName);	
			Thread.sleep(60000);
			
			logger.info("The status of the order is : "+ar1.get(0));
	    }
	    if(statusOfOrder.equalsIgnoreCase("NE") && (TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("BOE")))
	    {
	    	//bvoipOrderId= OCXOrderStatus.returnOrderId("BVOIP");
			Random random = new Random();
			logger.info("BVOIP Order ID is : "+orderActionId);
			requestId=random.nextInt(100000);
			String requestIdSt=Integer.toString(requestId);
			SaveTNAndUDR.pushSaveTelephoneNumbersReq(envName, orderActionId, requestId);
			Thread.sleep(4000);
			SaveTNAndUDR.pushSaveUDRReq(envName,orderActionId,requestIdSt);
			Thread.sleep(4000);
			
			UpdateOAAdditionalInfoReq.saveOAExternalValsInput(envName, orderActionId);
			Thread.sleep(10000);
			String submitOrderRequest = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/SubmitOrderActionRequest.json"), StandardCharsets.UTF_8);
			JSONObject jsonObject = new JSONObject(submitOrderRequest);
			String updatedRequest=UpdateOaInSubmitRequest.updateOaIdInRequest(submitOrderRequest,orderActionId);
			UpdateOaInSubmitRequest.pushSubmitOrderRequest(updatedRequest,envName);	
			Thread.sleep(60000);
			
			TriggerManageFlowStatus.pushRequest(envName);
	    }
	    if(statusOfOrder.equalsIgnoreCase("NE") && (TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("IPFLEXMIS")))
	    {
	    	//bvoipOrderId= OCXOrderStatus.returnOrderId("BVOIP");
			Random random = new Random();
			logger.info("IPFLEX Order ID is : "+bvflexOrderId);
			requestId=random.nextInt(100000);
			String requestIdSt=Integer.toString(requestId);
			SaveTNAndUDR.pushSaveTelephoneNumbersReq(envName, bvflexOrderId, requestId);
			Thread.sleep(4000);
			SaveTNAndUDR.pushSaveUDRReq(envName,bvflexOrderId,requestIdSt);
			Thread.sleep(4000);
			
			UpdateOAAdditionalInfoReq.saveOAExternalValsInput(envName, bvflexOrderId);
			Thread.sleep(10000);
			String submitOrderRequest = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/SubmitOrderActionRequest.json"), StandardCharsets.UTF_8);
			JSONObject jsonObject = new JSONObject(submitOrderRequest);
			String updatedRequest=UpdateOaInSubmitRequest.updateOaIdInRequest(submitOrderRequest,bvflexOrderId);
			UpdateOaInSubmitRequest.pushSubmitOrderRequest(updatedRequest,envName);	
			Thread.sleep(60000);
			
			TriggerManageFlowStatus.pushRequestIPFLEXMIS(envName,bvflexOrderId);
	    }
	}
	
	@Then("^Fulfillment is sent to OMX$")
	public void fulfillment_is_sent_to_OMX() throws Exception 
	{  
		if(fulfilment==1)
		{
			if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("BOE"))
			{
				logger.info("Fullfilment is already completed for BOE order");
			}
			
			if(hasIpflex==1 && !TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("BOE"))
			{
				bvoipOrderId= OCXOrderStatus.returnOrderId("BVOIP");
				ArrayList<String> a=OCXOrderStatus.getOcxOrderStatusUrlForOrderId(bvoipOrderId, envName, "NEW");
				String orderStatus=a.get(0);
				logger.info("Status of the order  for Fulfilment is : "+orderStatus);
				if(orderStatus.equalsIgnoreCase("DE"))
				{
					logger.info("==========================================================================================");
					logger.info("=======================FULLFILMENT OF THE ORDER INITIATING==================================");
					Thread.sleep(20000);
					TriggerFulfimentComplete.triggerFulfimentRequest(envName, orderActionId, serviceGroupId);
					Thread.sleep(30000);			
				}
				else
				{
					logger.info("Order not submitted successfully, First submit the order  and then fulfillment will be sent.");
				}
				logger.info("=======================FULLFILMENT OF THE ORDER COMPLETED==================================");	
			}
		}
	
		else 
		{
			if(hasIpflex==0)
			{	
				Thread.sleep(300000);
				ArrayList<String> a=OCXOrderStatus.getOcxOrderStatusUrlForOrderId(orderActionId, envName, "NEW");
				String orderStatus=a.get(0);
				logger.info("Status of the order  for Fulfilment is : "+orderStatus);
				if(orderStatus.equalsIgnoreCase("DE"))
				{
					logger.info("==========================================================================================");
					logger.info("=======================FULLFILMENT OF THE ORDER INITIATING==================================");
					Thread.sleep(20000);
					TriggerFulfimentComplete.triggerFulfimentRequest(envName, orderActionId, serviceGroupId);
					Thread.sleep(30000);			
				}
				else
				{
					logger.info("Order not submitted successfully, First submit the order  and then fulfillment will be sent.");
				}
				logger.info("=======================FULLFILMENT OF THE ORDER COMPLETED==================================");	
				
			}	
			fulfilment++;
			if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("IPFLEXMIS"))
				hasIpflex++;
		}
	}
	
	@Then("^Same thing should happen for AVPN SITE in case of AVPN$")
	public void same_thing_should_happen_for_AVPN_SITE_in_case_of_AVPN() throws Exception 
	{
		if(typeOfOrder.equalsIgnoreCase("MIS") || typeOfOrder.equalsIgnoreCase("BOE"))
		{
			logger.info("This is only for AVPN order , not for MIS orders");
		}
		else if(typeOfOrder.equalsIgnoreCase("AVPN") && typeOfChange.equalsIgnoreCase("NS"))
		{
			logger.info("==============Triggering AVPN SITE CASO==================");
			
			OrderCreationInOCX ordrCreatnOCX= new OrderCreationInOCX();
			
			ArrayList<List<String>> ordrDetailsAvpn=ordrCreatnOCX.casoPushAvpnSite(envName, sorProjID[1], sorProjID[2]);
			
			String ordrStatus=ordrDetailsAvpn.get(3).get(0);
			logger.info("The status of Order is : "+ordrStatus);
			
			String ordrActnId=ordrDetailsAvpn.get(2).get(0);
			logger.info("The Order Action ID is : "+ordrActnId);
			orderActionId=null;
			orderActionId=ordrActnId;
			
			String sgId=ordrDetailsAvpn.get(0).get(0);
			logger.info("The Service group ID is : "+sgId);
			serviceGroupId=null;
			serviceGroupId=sgId;
			
			String srviceTyp=ordrDetailsAvpn.get(1).get(0);
			logger.info("The Service Type is : "+srviceTyp);
			
			
			Thread.sleep(30000);
			
			logger.info("==============Triggering AVPN SITE MOR SG==================");
			
			tmor= new TriggerMOR_SGLevel();
			logger.info("--------------------Triggering SG Level MOR-----------------");
			tmor.triggerMoRequest_SG_AVPNSite(envName,TriggerMOR_SGLevel.typeOfTesting);
			logger.info("--------------------SG Level MOR Triggered-----------------");
			Thread.sleep(10000);
			
			logger.info("==============If order not submitted , Submitting theorder==================");
			
			logger.info("The status of the order is: "+ordrStatus);
		    if(ordrStatus.equalsIgnoreCase("DE"))
		    {
		    	logger.info("The order is successfully submitted from OCX");
		    }
		    if(ordrStatus.equalsIgnoreCase("NE"))
		    {
		    	String submitOrderRequest = FileUtils.readFileToString(new File("src/main/java/com/amdocs/halo/automation/xmls/SubmitOrderActionRequest.json"), StandardCharsets.UTF_8);
				JSONObject jsonObject = new JSONObject(submitOrderRequest);
				String updatedRequest=UpdateOaInSubmitRequest.updateOaIdInRequest(submitOrderRequest,ordrActnId);
				logger.info("Request after update is : "+updatedRequest);
				Thread.sleep(10000);
				UpdateOaInSubmitRequest.pushSubmitOrderRequest(updatedRequest,envName);	
				Thread.sleep(60000);
				ar1=OCXOrderStatus.getOcxOrderStatusUrlForOrderId(ordrActnId, envName, "NEW");
				logger.info("The status of the order is : "+ar1.get(0));
		    }
		    
		    logger.info("==========================================================================================");
			logger.info("==========================================================================================");
			logger.info("=======================FULLFILMENT OF THE AVPN SITE ORDER INITIATING==================================");
			Thread.sleep(20000);
			if(ar1.get(0).equalsIgnoreCase("DE"))
			{
				logger.info("==========================================================================================");
				logger.info("==========================================================================================");
				logger.info("=======================FULLFILMENT OF THE ORDER INITIATING==================================");
				Thread.sleep(20000);
				TriggerFulfimentComplete.triggerFulfimentRequest(envName, ordrActnId, ar1.get(2));
				Thread.sleep(30000);			
			}
			else
			{
				logger.info("Order not submitted successfully, First submit the order  and then fulfillment will be sent.");
			}
			logger.info("=======================FULLFILMENT OF THE ORDER COMPLETED==================================");	
			Thread.sleep(30000);
			logger.info("=======================FULLFILMENT OF THE AVPN SITE ORDER COMPLETED==================================");
		
		}
	}

}
