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



public class UNI_CNOD_steps 
{
	HashMap<String,List<String>> orderDetails;
	ArrayList<String> listOfCktIds;
	
	public static Logger logger = null;
	
	public UNI_CNOD_steps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(UNI_CNOD_steps.class.getName());
	}
	
	@Given("^need UNI Request \"([^\"]*)\"$")
	public void need_UNI_Request(String eNv) throws Throwable 
	{
      envName=System.getProperty("enviroment");
		 System.out.println(envName);
		  
	//	 TriggerCNODRequest tr1 = new TriggerCNODRequest();
		    
		 String response=   TriggerCNODRequest.triggerRequestUNI(envName);
		 orderDetails= OCXOrderCreation_GAMMA.getElementFromXml(response, "productOrderItemId","productOrderItemStatus","productServiceId","assignedProductId","productOrderId");
		 System.out.println(orderDetails);
		 String productOrderId= orderDetails.get("productOrderId").get(0);
		 String productOrderItemId= orderDetails.get("productOrderItemId").get(0);
		 String assignedProductId= orderDetails.get("assignedProductId").get(0);
		 String productOrderItemStatus= orderDetails.get("productOrderItemStatus").get(0);
		 String productServiceId= orderDetails.get("productServiceId").get(0);
		 System.out.println(productOrderId);
		 System.out.println(productOrderItemId);
		 System.out.println(assignedProductId);
		 System.out.println(productOrderItemStatus);
		 System.out.println(productServiceId);
		 
		 
	}
	
}
