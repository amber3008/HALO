package com.amdocs.halo.automation.steps;

//import com.amdocs.halo.automation.main.OrderCreationInOCX;
import com.amdocs.halo.automation.main.TriggerDecomposeSalesOrderRequest_MIS;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;

public class OCX_OrderCreationAVPN
{
	//public static TriggerDecomposeSalesOrderRequest_MIS tds;
	//public static OCXOrderCreationOld_MIS_Steps ocxMis;

	
	@When("^createAndStartOrderRequest is pushed to OMX\\.$")
	public void createandstartorderrequest_is_pushed_to_OMX() throws Exception 
	{
		//ocxMis = new OCXOrderCreationOld_MIS_Steps();
		System.out.println("Env to send CASO is: "+OCXOrderCreationOld_MIS_Steps.envName);
		try
		{
			//OrderCreationInOCX.casoPush(ocxMis.envName);
		}
		catch(PendingException e)
		{
			System.out.println(e);
		}
	    
	}
	
}
