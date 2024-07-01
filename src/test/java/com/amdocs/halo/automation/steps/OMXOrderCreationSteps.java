package com.amdocs.halo.automation.steps;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.main.TriggerMORAVPN;

/***
 * 
 * @author ab780k
 *
 */


import com.amdocs.halo.automation.main.TriggerMOR_OALevel;
import com.amdocs.halo.automation.main.TriggerMOR_SGLevel;
import com.amdocs.halo.automation.main.TriggerNotifyRequest;
import com.amdocs.halo.automation.main.UpdateActivityStatus_OA;
import com.amdocs.halo.automation.main.UpdateActivityStatus_SG;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OMXOrderCreationSteps 
{
	public static TriggerNotifyRequest tnr;
	public static UpdateActivityStatus_SG updateSg;
	public static UpdateActivityStatus_OA updateOa;
	String [] data;
	String [] sorProjID;
	public static String [] wholeOrderIds= new String[6];
	public static String [] wholeOrderIdAvpn= new String[6];
	public static TriggerMOR_SGLevel tmor;
	public static TriggerMOR_OALevel tmor_oa;
	public static String envName;
	public static String typeOfOrder;
	public static String typeOfTesting;
	public static TriggerMORAVPN tmor_avpn;
	public static Logger logger = null;
	
	public OMXOrderCreationSteps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(OMXOrderCreationSteps.class.getName());
	}
	
	@Given("^notify Request is pushed to OMX for \"([^\"]*)\" \"([^\"]*)\"$")
	public void notify_Request_is_pushed_to_OMX_for(String env, String orderType) throws Exception 
	{
		envName=env;
      envName=System.getProperty("enviroment");
		typeOfOrder=orderType;
		System.out.println(orderType);
		logger.info("--------------------Triggering Notify Request-----------------");
		try
		{
			tnr = new TriggerNotifyRequest();
			data= tnr.triggerRequest(env,orderType);
			sorProjID= tnr.getSorProjId();
			logger.info("--------------------SOR ID while testing-----------------");
			logger.info(sorProjID[1]);
			logger.info("--------------------PROJ ID while testing-----------------");
			logger.info(sorProjID[2]);
			logger.info("--------------------Notify Request Triggered-----------------");
		}
		catch(PendingException e)
		{
			logger.info(e);
		}
	}
	


	
	@When("^manageOrderRequest of Service Group level is triggered for \"([^\"]*)\"$")
	public void manageorderrequest_of_Service_Group_level_is_triggered_for(String testingType) throws Exception 
	{
		typeOfTesting= testingType;
		try
		{
			tmor= new TriggerMOR_SGLevel();
			logger.info("--------------------Triggering SG Level MOR-----------------");
			logger.info("Type Of order is : "+typeOfOrder);
			logger.info("Testing Type is : "+testingType);
			tmor.triggerMoRequest_SG(envName,typeOfOrder,testingType);
			logger.info("--------------------SG Level MOR Triggered-----------------");
			Thread.sleep(10000);
			wholeOrderIds = TriggerMOR_SGLevel.getWholeOrderIds();
			logger.info("SOR ID is: "+sorProjID[1]);
			logger.info("SG ID is: "+wholeOrderIds[1]);
			logger.info("Order ID is: "+wholeOrderIds[2]);
		}
		catch(PendingException e)
		{
			logger.info(e);
		}
		
		/*if(typeOfOrder.equalsIgnoreCase("AVPN"))
		{
			logger.info("--------------------Triggering SG Level MOR AVPN SITE-----------------");
			tmor.triggerMoRequest_SG_AVPNSite(envName,testingType);
			logger.info("--------------------AVPN SIte SG Level MOR Triggered-----------------");
			wholeOrderIds = TriggerMOR_SGLevel.getWholeOrderIds();
		}*/
	}

	@When("^in OMX order reaches OA level$")
	public void in_OMX_order_reaches_OA_level() throws Exception 
	{
		try
		{
			updateSg= new UpdateActivityStatus_SG();
			Thread.sleep(10000);
			logger.info("--------------------Activities in Error are getting checked and progressed-----------------");
			updateSg.getPlanIdIdActivityIdFrmResponse(envName);
			updateSg.updateActivityInRequest(envName);
		}
		catch(PendingException e)
		{
			logger.info(e);
		}
	}

	@Then("^manageOrderRequest of OA level is triggered\\.$")
	public void manageorderrequest_of_OA_level_is_triggered() throws Exception 
	{
		Thread.sleep(4000);
		try
		{
			logger.info("--------------------Triggering OA Level MOR---------------------");
			tmor_oa= new TriggerMOR_OALevel();
			tmor_oa.triggerMoRequest_OA(envName);
			
			logger.info("--------------------OA Level MOR triggered-----------------------");
			logger.info("SG ID for "+typeOfOrder+" is: "+wholeOrderIds[1]);
			logger.info("Order ID for "+typeOfOrder+" is: "+wholeOrderIds[2]);
			if(typeOfOrder.equalsIgnoreCase("AVPN"))
			{
				OMXOrderCreationSteps omxSteps= new OMXOrderCreationSteps();
				logger.info("--------------------Triggering MOR SITE SG for AVPN---------------------");
				tmor_avpn.triggerMoRequest_SiteSG(envName,typeOfTesting);
				wholeOrderIdAvpn = TriggerMOR_SGLevel.getWholeOrderIds();
				
				logger.info("--------------------Activities for SITE Level in Error are getting checked and progressed-----------------");
				omxSteps.in_OMX_order_reaches_OA_level();
				
				logger.info("--------------------Triggering MOR SITE OA for AVPN---------------------");
				tmor_avpn.triggerMoRequest_SiteOA(envName);
				
				logger.info("--------------------Triggered MORs AVPNS-----------------------");
			}			
		}
		catch(PendingException e)
		{
			logger.info(e);
		}
	}

	@Then("^OA is created in OMX\\.$")
	public void oa_is_created_in_OMX() throws Exception 
	{
		logger.info("--------------------OA is created in OMX-----------------");
		logger.info("SOR ID for "+typeOfOrder+" is: "+sorProjID[1]);
	}
	
	@Then("^errors in order are checked\\.$")
	public void errors_in_order_are_checked() throws Exception 
	{
		try
		{
			updateOa= new UpdateActivityStatus_OA();
			Thread.sleep(10000);
			logger.info("--------------------Activities in Error are getting checked and progressed-----------------");
			UpdateActivityStatus_OA.getPlanIdIdActivityIdFrmResponse_OA(envName);
			UpdateActivityStatus_OA.updateActivityInRequest_OA(envName);
			
			if(typeOfOrder.equalsIgnoreCase("AVPN"))
			{
				logger.info("--------------------Activities in Error are getting checked and progressed for Site---------------------");
				UpdateActivityStatus_OA.getPlanIdIdActivityIdFrmResponse_OA_Site(envName);
				UpdateActivityStatus_OA.updateActivityInRequest_OA_Site(envName,null);
			}
			logger.info("SOR ID for "+typeOfOrder+" is: "+sorProjID[1]);
		}
		catch(PendingException e)
		{
			logger.info(e);
		}
	}
	
	@Given("^notify Request is pushed to OMX for \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void notify_Request_is_pushed_to_OMX_for(String env, String orderType,String whatChange) throws Exception {
		TriggerNotifyRequest.whatToChange=whatChange;
		//envName=env;
		typeOfOrder=orderType;
		logger.info("--------------------Triggering Notify Request-----------------");
		try
		{
			tnr = new TriggerNotifyRequest();
			System.out.println(orderType);
			data= tnr.triggerRequest(envName,orderType);
			sorProjID= tnr.getSorProjId();
			logger.info("--------------------SOR ID while testing-----------------");
			logger.info(sorProjID[1]);
			logger.info("--------------------PROJ ID while testing-----------------");
			logger.info(sorProjID[2]);
			logger.info("--------------------Notify Request Triggered-----------------");
		}
		catch(PendingException e)
		{
			logger.info(e);
		}
	   
	}
	
	
}
