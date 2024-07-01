package com.amdocs.halo.automation.steps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.main.CompleteAllActivities;
import com.amdocs.halo.automation.main.GetActivitiesOfSG;
import com.amdocs.halo.automation.main.OCXOrderCreation_GAMMA;
import com.amdocs.halo.automation.main.TriggerB2CSPOrder;
import com.amdocs.halo.automation.main.TriggerBtocspChangeForAmend;
import com.amdocs.halo.automation.main.TriggerGOD;
import com.amdocs.halo.automation.main.Trigger_MACD_Amend;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GAMMA_B2CSP_steps
{
	public static String poId;
	public static String sop;
	public static HashMap<String, List<String>> soP;
	public static HashMap<String, List<String>> poID;
	public static HashMap<String, List<String>> oID;
	public static String ctkID;
	public static String apId;
	public static String response;
	public static String orderId;
	public static String envname;
	public static HashMap<String, List<String>> orderId1;
	public static HashMap<String, List<String>> apID;
	public static HashMap<String, List<String>> productname;
	public static HashMap<String,List<String>> circuitIds;
	public static String aID;
	public static Logger logger = null;
	public static HashMap<String,String> details= new HashMap<String,String>();

	public GAMMA_B2CSP_steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(GAMMA_B2CSP_steps.class.getName());
	}

	@Given("^Env\"([^\"]*)\" is up for BTOCSP order creation$")
	public void env_is_up_for_BTOCSP_order_creation(String env) throws Exception 
	{
		// Print Env
		envname=env;
		//mvn test -Denv=ST3
		//envName=prop.getProperty("env");
		
		envname=System.getProperty("enviroment");
		logger.info(envname + " is up");
		
	}

	@Given("^CNOD of UNI_A is pushed to OCX for BTOCSP  \"([^\"]*)\"$")
	public void cnod_of_UNI_A_is_pushed_to_OCX_for_BTOCSP(String stateRegion) throws Exception 
	{
		logger.info("---------------------------UNI--------------------------");
		String rsponse=OCXOrderCreation_GAMMA.triggerGammaOrder(envname,"UNI", stateRegion, "Y", "Y", "N",null,"null");
		Thread.sleep(20000);
		circuitIds=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productServiceId");
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
		apID=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"assignedProductId"); 
		orderId=orderId1.get("productOrderItemId").get(0);
		apId=apID.get("assignedProductId").get(0);
		ctkID=circuitIds.get("productServiceId").get(0);
	}

	@Given("^Notify request is pushed$")
	public void notify_request_is_pushed() throws Exception 
	{
		logger.info("---------------------------NOTIFY--------------------------");

		response=TriggerB2CSPOrder.triggerNotifyRequest(envname,orderId);

		oID=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");

		Thread.sleep(20000);
	}


	@Given("^CNOD of ChangeCHFIS UNI is Pushed$")
	public void cnod_of_ChangeCHFIS_UNI_is_Pushed() throws Exception 
	{
		logger.info("---------------------------UNI CHANGE--------------------------");
		response=TriggerB2CSPOrder.triggerRequestUNICH(envname,apId);
		Thread.sleep(6000);  
		soP=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderId");
		sop=soP.get("productOrderId").get(0);
	}
	//-------------------------Start Order Processing------------------------

	@Given("^CNOD of Start Order Procesing is pushed for UNI CH$")
	public void cnod_of_Start_Order_Procesing_is_pushed_for_UNI_CH() throws Exception 
	{
		logger.info("---------------------Start Order Processing--------------------");
		String response=TriggerB2CSPOrder.triggerRequestStartOrderProcessing(envname,sop);



	}


	@Given("^CNOD of EVCA is Pushed By Using UNI CKTID$")
	public void cnod_of_EVCA_is_Pushed_By_Using_UNI_CKTID() throws Exception 
	{
		logger.info("---------------------------EVCA --------------------------");
		response=TriggerB2CSPOrder.triggerRequestMTPEVCA(envname,ctkID);
		logger.info("response created is : ");
		logger.info(response);
		oID=OCXOrderCreation_GAMMA.getElementFromXml(response,"productServiceId");
		productname=OCXOrderCreation_GAMMA.getElementFromXml(response,"productName");
		logger.info(oID);
		logger.info(productname);
		ctkID=oID.get("productServiceId").get(1);
		String pName;
		String pService;
		for(int i=0; i<=oID.size();++i)
		{
			pName=productname.get("productName").get(i);
			pService=oID.get("productServiceId").get(i);
			logger.info(pName+" and "+pService );
			details.put(pName,pService);

		}
		for (Entry<String, String> entry : details.entrySet())
			logger.info("Key = " + entry.getKey() +
					", Value = " + entry.getValue());



		logger.info(details.get("VPN"));


		//logger.info(ctkID);

		Thread.sleep(6000);
		// CNOD of EVC is pushed
	}



	@When("^CNOD of BTOCSP is Pshed by Using EVCV CKTID$")
	public void cnod_of_BTOCSP_is_Pshed_by_Using_EVCV_CKTID() throws Exception 
	{
		logger.info("---------------------------B2CSP--------------------------");
		response=TriggerB2CSPOrder.triggerRequestBTOCSP(envname,ctkID,"MTPEVC");
		oID=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		productname=OCXOrderCreation_GAMMA.getElementFromXml(response,"productName");
		logger.info(oID);
		logger.info(productname);
		String pName;
		String pService;
		details.clear();

		for(int i=0; i<=oID.size();++i)
		{
			pName=productname.get("productName").get(i);
			pService=oID.get("productOrderItemId").get(i);
			logger.info(pName+" and "+pService );
			details.put(pName,pService);

		}
		for (Entry<String, String> entry : details.entrySet())
			logger.info("Key = " + entry.getKey() +
					", Value = " + entry.getValue());  


	}


	@When("^CNOD of Change CIR is pushed$")
	public void cnod_of_Change_CIR_is_pushed() throws Exception 
	{
		//first client order to be should be complted and the change to be perform ...
		//-----------------------Notify ------------------------------------------
		apID=OCXOrderCreation_GAMMA.getElementFromXml(response,"assignedProductId");
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);
		logger.info("---------------------------NOTIFY--------------------------");
		response=TriggerB2CSPOrder.triggerNotifyRequest(envname,orderId);
		Thread.sleep(30000);
		logger.info("--------------------------CIR CHANGE----------------------------------");
		apId=apID.get("assignedProductId").get(0);
		response=TriggerB2CSPOrder.triggerRequestCIRCH(envname,apId);
		Thread.sleep(6000);
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);
	}

	@Given("^CNOD of Change for amend is pushed$")
	public void cnod_of_Change_for_amend_is_pushed() throws Exception 
	{
		//first client order to be should be complted and the change to be perform ...
		//-----------------------Notify ------------------------------------------
		apID=OCXOrderCreation_GAMMA.getElementFromXml(response,"assignedProductId");
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);
		logger.info("---------------------------NOTIFY--------------------------");
		response=TriggerB2CSPOrder.triggerNotifyRequest(envname,orderId);
		Thread.sleep(30000);
		logger.info("--------------------------CIR CHANGE----------------------------------");
		apId=apID.get("assignedProductId").get(0);
		response=TriggerBtocspChangeForAmend.triggerChangeForAmend(envname,apId);
		Thread.sleep(6000);
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);
	}

	@When("^CNOD of Disconnect is pushed$")
	public void cnod_of_Disconnect_is_pushed() throws Exception 
	{
		logger.info("--------------------------------Disconnect---------------------------");
		//---------------Taking the orderID and AssignProductID from B2CSP RESPONSE---------------------
		apID=OCXOrderCreation_GAMMA.getElementFromXml(response,"assignedProductId");
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		for(int i=0;i<=orderId1.size()+2;++i)
		{
			orderId=orderId1.get("productOrderItemId").get(i);
			response=TriggerB2CSPOrder.triggerNotifyRequest(envname,orderId);

		}
		logger.info("------------------------CNOD_Disconnect------------------------------------");
		Thread.sleep(600000);
		apId=apID.get("assignedProductId").get(3);
		response=TriggerB2CSPOrder.triggerDisconnctSingleAPIRequest(envname,apId);
		Thread.sleep(20000);  
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);

	}




	@Given("^CNOD of BTOCSP is Pshed by Using UNI CKTID$")
	public void cnod_of_BTOCSP_is_Pshed_by_Using_UNI_CKTID() throws Exception 
	{
		response=TriggerB2CSPOrder.triggerRequestBTOCSP(envname,ctkID,"ASEOD");
		Thread.sleep(6000);  
	}


	@When("^CNOD  of Cancel is pushed$")
	public void cnod_of_Cancel_is_pushed() throws Exception 
	{
		logger.info("/n----------------------------Cancel---------------------------------------");
		poID=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderId");
		poId=poID.get("productOrderId").get(0);
		logger.info(poId);
		response=TriggerB2CSPOrder.triggerRequestCancel(envname,poId);
		Thread.sleep(6000);  
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);

	}

	@When("^CNOD of Amend_REQ is Pushed$")
	public void cnod_of_Amend_REQ_is_Pushed() throws Exception 
	{
		Thread.sleep(20000);
		apID=OCXOrderCreation_GAMMA.getElementFromXml(response,"assignedProductId");
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);
		apId=apID.get("assignedProductId").get(0);
		response=TriggerB2CSPOrder.triggerRequestAmend(envname,orderId,apId);
		Thread.sleep(6000);
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);

	}
	@When("^CNOD of MACD Amend_REQ is Pushed$")
	public void cnod_of_MACD_Amend_REQ_is_Pushed() throws Exception 
	{
		Thread.sleep(20000);
		apID=OCXOrderCreation_GAMMA.getElementFromXml(response,"assignedProductId");
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);
		apId=apID.get("assignedProductId").get(0);
		response=Trigger_MACD_Amend.triggerRequestMACHAmend(envname,orderId,apId);
		Thread.sleep(6000);
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);

	}

	@When("^CNOD of Disconnect single API is pushed$")
	public void cnod_of_Disconnect_single_API_is_pushed() throws Exception 
	{
		logger.info("--------------------------------Disconnect---------------------------");
		//---------------Taking the orderID and AssignProductID from B2CSP RESPONSE---------------------
		apID=OCXOrderCreation_GAMMA.getElementFromXml(response,"assignedProductId");
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);
		response=TriggerB2CSPOrder.triggerNotifyRequest(envname,orderId);
		Thread.sleep(20000);
		logger.info("------------------------CNOD_Disconnect------------------------------------");
		Thread.sleep(70000);
		apId=apID.get("assignedProductId").get(0);
		response=TriggerB2CSPOrder.triggerDisconnctSingleAPIRequest(envname,apId);
		Thread.sleep(6000);
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);

	}


	@Given("^CNOD of BTOB is pushed$")
	public void cnod_of_BTOB_is_pushed() throws Exception 
	{
		System.out.println("***************************************"+envname);
		response=TriggerB2CSPOrder.triggerRequestBTOB(envname);
		Thread.sleep(6000);
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);
	}

	@Given("^Env\"([^\"]*)\" is up for BTOB order creation$")
	public void env_is_up_for_BTOB_order_creation(String env) throws Exception 
	{
		// Print Env
		logger.info(env + " is up");
		envname=env;
	}

	@When("^CNOD of Disconnect To API is pushed$")
	public void cnod_of_Disconnect_To_API_is_pushed() throws Exception 
	{
		logger.info("--------------------------------Disconnect---------------------------");
		//---------------Taking the orderID and AssignProductID from B2CSP RESPONSE---------------------
		apID=OCXOrderCreation_GAMMA.getElementFromXml(response,"assignedProductId");
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		for(int i=0;i<=orderId1.size();++i)
		{
			orderId=orderId1.get("productOrderItemId").get(i);
			response=TriggerB2CSPOrder.triggerNotifyRequest(envname,orderId);

		}
		logger.info("------------------------CNOD_Disconnect------------------------------------");
		Thread.sleep(300000);
		apId=apID.get("assignedProductId").get(1);
		response=TriggerB2CSPOrder.triggerDisconnctSingleAPIRequest(envname,apId);
		Thread.sleep(6000);
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);
	}


	@Given("^Env\"([^\"]*)\" is up for Multiple_SCMC order creation$")
	public void env_is_up_for_Multiple_SCMC_order_creation(String env) throws Exception 
	{
		// Print Env
		logger.info(env + " is up");
		envname=env;

	}

	@When("^CNOD of Multiple_SCMC is pushed$")
	public void cnod_of_Multiple_SCMC_is_pushed() throws Exception 
	{
		response=TriggerB2CSPOrder.triggerRequestMultiple_SCMC(envname);
		Thread.sleep(10000);
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);
	}

	@Then("^Multiple_SCMC OA is created in OCX\\.$")
	public void multiple_scmc_OA_is_created_in_OCX() throws Exception 
	{

	}


	@Then("^BTOCSP OA is created in OCX\\.$")
	public void btocsp_OA_is_created_in_OCX() throws Exception 
	{
		//Retreive Order Action ID
	}
	@Then("^BTOB OA is crated in OCX$")
	public void btob_OA_is_crated_in_OCX() throws Exception {

	}

	@When("^print the value of A(\\d+)$")
	public void print_the_value_of_A(int arg1) throws Exception 
	{
		System.out.println(arg1);
	}

	@Given("^Env\"([^\"]*)\" is up for APORT order creation$")
	public void env_is_up_for_APORT_order_creation(String env) throws Exception 
	{
		// Print Env
		logger.info(env + " is up");
		envname=env;
	}

	@When("^CNOD of APORT is pushed$")
	public void cnod_of_APORT_is_pushed() throws Exception 
	{

		response=TriggerB2CSPOrder.triggerRequestAPORT(envname);
		Thread.sleep(6000);
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);
	}


	@When("^CNOD of APORT CH is Pushed$")
	public void cnod_of_APORT_CH_is_Pushed() throws Exception 
	{
		apID=OCXOrderCreation_GAMMA.getElementFromXml(response,"assignedProductId");
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);
		apId=apID.get("assignedProductId").get(0);
		logger.info("---------------------------NOTIFY--------------------------");
		response=TriggerB2CSPOrder.triggerNotifyRequest(envname,orderId);
		Thread.sleep(30000);
		logger.info("---------------------------APORT CH CHANGE--------------------------");
		//CNOD of CHFIS UNI is pushed
		response=TriggerB2CSPOrder.triggerRequestAPORTCH(envname,apId);
		Thread.sleep(10000);  
		soP=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderId");
		sop=soP.get("productOrderId").get(0);

	}

	@When("^Pull the Get Order Data$")
	public void pull_the_Get_Order_Data() throws Exception
	{
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);
		response=TriggerGOD.triggerRequestGOD(envname,orderId);
		Thread.sleep(6000);
	}
	
	@When("^getProjectID is pushed$")
	public void getprojectid_is_pushed() throws Exception {
		orderId1=OCXOrderCreation_GAMMA.getElementFromXml(response,"productOrderItemId");
		orderId=orderId1.get("productOrderItemId").get(0);
		response=TriggerB2CSPOrder.getProjIdFrmResponse_OA(envname,orderId);
		
		HashMap<String, List<String>> projectID=OCXOrderCreation_GAMMA.getElementFromXml(response,"projectId");
		String PID=projectID.get("projectId").get(0);
		System.out.println(PID);
		// List<List<String>> allElements= new ArrayList<List<String>>();
		 response=GetActivitiesOfSG.getActivitiesFrmResponse(envname,PID);
		 CompleteAllActivities.getActivityInfo(response);
		 
		 
		
		 
	}

	@Then("^APORT OA is created in OCX\\.$")
	public void aport_OA_is_created_in_OCX() throws Exception 
	{
	

	}

	

}
