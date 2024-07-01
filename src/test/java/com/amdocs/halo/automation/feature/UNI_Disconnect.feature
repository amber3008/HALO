#Author: md388b@att.com
@UNI_Disconnect
Feature: To create GAMMA Order in OCX




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
