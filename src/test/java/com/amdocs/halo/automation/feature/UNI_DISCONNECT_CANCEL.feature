#Author: md388b@att.com
@UNI_DISCONNECT_CANCEL


Feature: To create GAMMA Order in OCX

@Test_GAMMA_UNI_Disconnect_cancel
  Scenario Outline: Create GAMMA UNI_Disconnect order in Env
    Given Env is up for UNI_Disconnect order  creation for <env> 
     When CNOD of UNI is pushed to OCX for  UNI_Disconnect<env><stateRegion>
     And Push notify on request for  UNI_Disconnect<env>
     And Push Disconnect request for  UNI_Disconnect<env>
     Then UNI_Disconnect is  created in OCX.
     And Push cancel request for UNI_DISCONNECT_CANCEL<env>
     Then UNI_DISCONNECT_CANCEL is created in OCX.
    
    
    Examples: 
    | env   |stateRegion|
    | "ST4" | "Y"|
