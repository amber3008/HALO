#Author: si280e@att.com
@Test
Feature: To create GAMMA Order in OCX
	
	   @BTOCSP_ADD_CLIENT
  Scenario Outline: Create BTOCSP Order for Enviroment
	    Given Env<Env> is up for BTOCSP ADD CLIENT order creation
	    When CNOD of BTOCSP is pushed 
	    Then Order is Created
	    
	    Examples: 
	    | Env   | 
	    | "ST3" |