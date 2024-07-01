#Author: ab780k@att.com
@ISTOrderCreation_HALO_OldArch
Feature: To create MIS and IPFLEX old arch Order in OCX 
	
	@HALO_OLD_ARCH
  Scenario Outline: Validate Old Arch Order is created in OCX.
    Given notify Request is pushed to OMX for <Env> <orderType>
    When decomposeSalesOrderRequest is pushed to OMX <Env> <orderType>. 
    When manageOrderRequest of Service Group level is triggered for <testingType>
    Then Order is created in OCX.
    And If Negotiation  Need to check if any CTH is created
    And user opens browser <Env>
   And user is on login page
   And user login into HALO OMX GUI
   And user is on Home Page
   When user clicks on Search dropdown in Order Search Tab
   And User Searches the order
   Then Order id is displayed
    And Verify if IPFLEX order is created.
    And Verify all TNs are added successfully.
    And Verify For IPFLEX all the validations are completed
    And Verify IPFLEX order is submitted
  #  And user opens browser <Env>
  #  And user is on login page
 #   And user login into HALO OMX GUI
  #  And user is on Home Page
 #   When user clicks on Search dropdown in Order Search Tab
  #  And User Searches the order
  #  Then Order id is displayed
    
    
    Examples: 
    | Env   | testingType | orderType |
    | "ST5" |  "IST"      | "MIS" |

    
    
    #Type can be "IPFLEXMIS" and "MIS"