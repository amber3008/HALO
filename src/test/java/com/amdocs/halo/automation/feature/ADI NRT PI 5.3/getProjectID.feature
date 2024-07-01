#Author: si280e@att.com
@getProjectID.feature
Feature: To create GAMMA Order in OCX
	
	   @getProjectID
  Scenario Outline: Create BTOCSP Order for Enviroment
	    Given Env<Env> is up for BTOCSP order creation
	    When getProjectID is pushed
	   
	    
	    Examples: 
	    | Env   |
	    | "ST5" |