#Author: ab780k@att.com
@ADINRTPI5.3
Feature: To test Scenario 1
	
	Background:  Order is created in OMX
    Given notify Request is pushed to OMX.
    When manageOrderRequest of Service Group level is triggered.
    And  in OMX order reaches OA level
    Then manageOrderRequest of OA level is triggered.
    And OA is created in OMX.
	
	@Test
  Scenario: Validate HW Retreival not needed.
    Given OMX Order is created in OMX
    When Order reaches Update Equipment Activity
    Then CAMO triggers asynch ManageCPEWFResponse to OMX with CPE Status as 8.
    And HW Retreival Activity is skipped.
