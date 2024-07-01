#Author: ab780k@att.com
@OMXOrderCreation_NewArch
Feature: To create New Arch Order in OMX
	
	@Test
  Scenario Outline: Validate Order is created in OMX.
    Given notify Request is pushed to OMX for <Env> <orderType>
    When manageOrderRequest of Service Group level is triggered for <testingType>
    And  in OMX order reaches OA level
    Then manageOrderRequest of OA level is triggered.
    And OA is created in OMX.
    And errors in order are checked.
    
    Examples: 
    | Env   | orderType | testingType |
    | "ST5" | "IPFLEXMIS"    | "ST"        | 
