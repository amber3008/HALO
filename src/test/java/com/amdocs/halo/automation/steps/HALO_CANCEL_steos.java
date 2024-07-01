package com.amdocs.halo.automation.steps;

import com.amdocs.halo.automation.main.CancelOrderInput;
import com.amdocs.halo.automation.main.Trigger_Halo_Cancel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HALO_CANCEL_steos 
{
	public static String envName;
	
	@Given("^Env is up \"([^\"]*)\" for the HALO CANCEL$")
	public void env_is_up_for_the_HALO_CANCEL(String env) throws Exception {
		envName=env;
      envName=System.getProperty("enviroment");
	    
	}

	@When("^OrderCancel is pushed \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$")
	public void ordercancel_is_pushed(String orderID, String url, String oaID) throws Exception {
		Trigger_Halo_Cancel.cancelOrderInput(envName,orderID);
	}
	
	@Then("^order is created$")
	public void order_is_created() throws Exception {
	  
	}


}
