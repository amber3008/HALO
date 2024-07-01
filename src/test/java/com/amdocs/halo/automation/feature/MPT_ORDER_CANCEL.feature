#Author: md388b@att.com
@MPT_ORDER_CANCEL
Feature: To create GAMMA MPT Order in OCX and Cancel

@Test_GAMMA_MPT_CANCEL
  Scenario Outline: Create GAMMA PTP order in Env
    Given Env<eNv> is UP for MPT_Cancel order Creation for 
     When CNOD of first UNI is pushed to OCX for MPT_Cancel<eNv> <stateRegion1>
     And  CNOD of second UNI_B is pushed to OCX for MPT_Cancel<eNv> <stateRegion2>
     And  CNOD of third UNI_C is pushed to OCX for MPT_Cancel<eNv> <stateRegion3>
     And CNOD of MPT is PUSHED to OCX for UNI_A and UNI_B and UNI_C to cancel<eNv> <stateRegion>
     And MPT OA is CREATED in OCX to cancel.
     And pass productOrderItemId to MPT_Cancel and push <eNv>
     Then MPT_Cancel is created in OCX.
    
    Examples: 
    | eNv   |stateRegion1|stateRegion2|stateRegion3|
    | "ST4" |"Y"|"O"|"Y"|


    