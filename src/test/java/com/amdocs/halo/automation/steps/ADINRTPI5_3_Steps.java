package com.amdocs.halo.automation.steps;

//import com.amdocs.halo.automation.main.TriggerNotifyRequest;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ADINRTPI5_3_Steps 
{
	@Given("^OMX Order is created in OMX$")
	public void omx_Order_is_created_in_OMX() throws Exception 
	{
	    
		throw new PendingException();
	}

	@When("^Order reaches Update Equipment Activity$")
	public void order_reaches_Update_Equipment_Activity() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^CAMO triggers asynch ManageCPEWFResponse to OMX with CPE Status as (\\d+)\\.$")
	public void camo_triggers_asynch_ManageCPEWFResponse_to_OMX_with_CPE_Status_as(int arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^HW Retreival Activity is skipped\\.$")
	public void hw_Retreival_Activity_is_skipped() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@Given("^method a is executed  \"([^\"]*)\"$")
	public void method_a_is_executed(String arg1) throws Exception {
	    System.out.println("Method A is executed");
	//    TriggerNotifyRequest tnr= new TriggerNotifyRequest();
	//    tnr = new TriggerNotifyRequest();
	//	tnr.triggerRequest("ST4","MIS");
	}

	@When("^methoed b is executed$")
	public void methoed_b_is_executed() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Method B is executed");
	}

	@When("^method c is executed$")
	public void method_c_is_executed() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Method C is executed");
	}

	@Then("^methoed d should be executed$")
	public void methoed_d_should_be_executed() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Method D is executed");
	}
}
