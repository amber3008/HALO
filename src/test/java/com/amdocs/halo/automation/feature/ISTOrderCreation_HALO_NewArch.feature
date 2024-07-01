#Author: ab780k@att.com
@ISTOrderCreation_HALO_NewArch
Feature: To create HALO order in OCX and OMX
	
	@HALO_NEW_ARCH
  Scenario Outline: Validate New Arch Order is created in OMX.
    Given notify Request is pushed to OMX for <Env> <orderType> 
    And CASO is pushed to OCX <Env> <orderType> <changeType> <whatChange>
    When manageOrderRequest of Service Group level is triggered for <testingType>
    And  in OMX order reaches OA level
    Then submit of OA level is triggered.
    And OA is created in OMX.
   	And Fulfillment is sent to OMX
   	#And If Negotiation  Need to check if any CTH is created
   	And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    And Same thing should happen for AVPN SITE in case of AVPN
    #And If Negotiation  Need to check if any CTH is created
    And Verify if IPFLEX order is created.
    And Verify all TNs are added successfully.
    And Verify For IPFLEX all the validations are completed
    And Verify IPFLEX order is submitted
    And Fulfillment is sent to OMX
   
    
    Examples: 
    | Env   | orderType | testingType | changeType | whatChange        |
    | "ST3" | "IPFLEXMIS"     |  "IST"      |    "NS"	   | "ChangePortSpeed" |
    
    
    	@HALO_NEW_ARCH_new_For_MACD
  Scenario Outline: Validate New Arch Order is created in OMX.
    Given notify Request is pushed to OMX for <Env> <orderType> <whatChange>
    And CASO is pushed to OCX <Env> <orderType> <changeType> <whatChange>
    When manageOrderRequest of Service Group level is triggered for <testingType>
    And  in OMX order reaches OA level
    Then submit of OA level is triggered.
    And OA is created in OMX.
   	And Fulfillment is sent to OMX
   #	And If Negotiation  Need to check if any CTH is created
   	And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    And Same thing should happen for AVPN SITE in case of AVPN
   # And If Negotiation  Need to check if any CTH is created
    And Verify if IPFLEX order is created.
    And Verify all TNs are added successfully.
    And Verify For IPFLEX all the validations are completed
    And Verify IPFLEX order is submitted
    And Fulfillment is sent to OMX
   
    
    Examples: 
    | Env   | orderType | testingType | changeType | whatChange  |
    | "ST3" | "MIS"     |  "IST"      |    "MACD"	 | "ChangeCPE" |
    
    
    
    
    #orderType can be AVPN, BOE or MIS
    #changeType can be MACD or NS
    #whatChange can be ChangePortSpeed, ChangeCPE (only InCase of MACD) Otherwise it will be null
    
    
  