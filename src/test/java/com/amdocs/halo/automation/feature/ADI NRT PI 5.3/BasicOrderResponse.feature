#Author: kd909c@att.com

@BasicOrderResponse.feature
Feature: To complete an activity forcefully 
	
	   @BasicOrderResponse
  Scenario Outline: To sned the Basic Order Response
	    Given Env<Env> is up for the basic Order Response
	    When push the Basic Order Response<orderID><activityname>
	    Then Basic Order Response is sent

	    Examples: 
	    | Env   | orderID            | activityname|
	    | "ST3" |     "202217535_1"    |     "UNI Activate Inform Billing MRC BGW"      |