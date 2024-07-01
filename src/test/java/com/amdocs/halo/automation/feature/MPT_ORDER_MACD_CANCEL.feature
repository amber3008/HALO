#Author: md388b@att.com
@MPT_ORDER_MACD_CANCEL
Feature: To create GAMMA MPT Order in OCX


@Test_GAMMA_MPT
  Scenario Outline: Create GAMMA MPT order in ENV
    Given env<eNv> is UP for MPT order Creation for MPT_ORDER_MACD_CANCEL
     When CNOD of first UNI is Pushed to OCX for MPT_ORDER_MACD_CANCEL <eNv> <stateRegion1>
     And  CNOD of UNI_B is Pushed to OCX for MPT_ORDER_MACD_CANCEL <eNv> <stateRegion2>
     And  CNOD of UNI_C is Pushed to OCX for MPT <eNv> <stateRegion3>
     And CNOD of MPT is PUSHED to OCX by UNI_A, UNI_B and UNI_C for MPT_ORDER_MACD_CANCEL<eNv> <stateRegion>
     And complete the MPT ORDER by NOTIFY on<eNv>
    And pass AssignProductId from MPT response to MACD Requestorder ID must be created and Save the response<eNv>
    And pass productOrderItemId to MPT_ORDER_MACD_CANCEL and push <eNv>
     Then  MPT_ORDER_MACD_CANCEL is created in OCX.
    
    
    Examples: 
   | eNv   |stateRegion1|stateRegion2|stateRegion3
    | "ST4" |"O"|"Y"|"Y"|


    