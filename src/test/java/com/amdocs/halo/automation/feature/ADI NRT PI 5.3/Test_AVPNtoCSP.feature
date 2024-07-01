#Author: si280e@att.com
@Test_AVPNtoCSP.feature
Feature: To create GAMMA Order in OCX
	
	   @test_avpn
  Scenario Outline: Create BTOCSP Order for Enviroment
	    Given Env<Env> is up for AVPNTOCSP order creation
	    When CNOD of AVPNtoCSP is pushed
	    Then Orderis created
	    
	    Examples: 
	    | Env   |
	    | "ST3" | 