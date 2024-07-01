#Author: md388b@att.com
@UNI_Cancel
Feature: To create UNI cancel GAMMA Order in OCX




@Test_GAMMA_UNI_Cancel
  Scenario Outline: Create GAMMA UNI_Disconnect order in Env
    Given Env is up for UNI_Disconnect order Creation for <Env> 
     When CNOD of UNI is Pushed to OCX for UNI_Disconnect<Env><stateRegion>
     And Push cancel request for UNI_Cancel<Env><stateRegion>
     And user opens browser <Env>
   And user is on login page
   And user login into HALO OMX GUI
   And user is on Home Page
   When user clicks on Search dropdown in Order Search Tab
   And User Searches the order
   Then Order id is displayed
     Then UNI_Cancel is created in OCX.
    
    
    Examples: 
    | Env   |stateRegion|
    | "ST3" | "O" |
    
    
    
    
@Test_GAMMA_UNI_Disconnect
  Scenario Outline: Create GAMMA UNI_Disconnect order in Env
    Given Env is up for UNI_Disconnect order creation for <Env> 
     When CNOD of UNI is pushed to OCX for UNI_Disconnect<Env> 
     And Push notify on request for UNI_Disconnect<Env>
     And Push Disconnect request for UNI_Disconnect<Env>
     Then UNI_Disconnect is created in OCX.
    
    
    Examples: 
    | Env   |
    | "ST3" | 