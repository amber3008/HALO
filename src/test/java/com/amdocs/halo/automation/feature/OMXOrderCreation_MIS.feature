#Author: ab780k@att.com
@OMXOrderCreation
Feature: To create Order in OMX
	
	@Test
  Scenario Outline: Validate Order is created in OMX.
    Given notify Request is pushed to OMX for <Env>
    When manageOrderRequest of Service Group level is triggered.
    And  in OMX order reaches OA level
    Then manageOrderRequest of OA level is triggered.
    And OA is created in OMX.
    
    Examples: 
    | Env   |
    | "ST3" |
