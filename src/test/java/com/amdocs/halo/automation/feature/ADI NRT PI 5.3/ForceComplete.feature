#Author: si280e@att.com and ax577f@att.com

@ForceComplete.feature
Feature: To complete an activity forcefully 
	
	   @FC_Complete
  Scenario Outline: To complete the activity
	    Given Env<Env> is up forcompletion of activity
	    When to complete the activity for the order<orderID><activityname>
	    Then Activity is completed

	    Examples: 
	    | Env   | orderID            | activityname|
	    | "ST3" |     "202219624_1"    |     "AVLAN Connect Assign Connection"      |