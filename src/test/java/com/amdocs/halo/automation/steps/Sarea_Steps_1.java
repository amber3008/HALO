package com.amdocs.halo.automation.steps;

import org.testng.annotations.Test;

import com.amdocs.halo.automation.main.TriggerSareaRequest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Sarea_Steps_1 
{
	
	public String envName;
	public String URL;
	
	@Test
	//@Given("^need SAREA Request to get response\"([^\"]*)\"$")
	public void need_SAREA_Request_to_get_response() throws Throwable 
	{
	    //System.out.println(env);
		 envName=System.getProperty("enviroment");
	//get the sarea req
	//  TriggerSareaRequest tr1 = new TriggerSareaRequest();
	    
	    TriggerSareaRequest.triggerRequest(envName);
	  
	   
	}

	@Test
	//@Given("^need URL to Push based on the attribute of  env$")
	public void need_URL_to_Push_based_on_the_attribute_of_env() throws Exception
	{
		/*TriggerSareaRequest tr2 = new TriggerSareaRequest();
		System.out.println(tr2.getURL(envName));
		URL=tr2.getURL(envName);
	  //get the url  
*/	}

	@Test
	//@Given("^If needed only, update the   Request with necessary attribute$")
	public void if_needed_only_update_the_Request_with_necessary_attribute() throws Exception  
	{
	  //update the req  
	}

	@Test
	//@When("^Push updated request with  URL$")
	public void push_updated_request_with_URL() throws Exception 
	{
	/*	TriggerSareaRequest tr3 = new TriggerSareaRequest();
		 TriggerXmls tg= new TriggerXmls();
	String response=tr3.pushxml(envName, URL);
	System.out.println("Response in format is:"+tg.formatResponseXml(response));*/
	   //push the req
	}

	@Test
	//@Then("^OA id must be created and Save the Response$")
	public void oa_id_must_be_created_and_Save_the_Response() throws Exception  
	{
	    //save the response and save the order id
	}

}
