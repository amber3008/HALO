#Author: md388b@att.com
@MPT_ORDER_MACD_Amend
Feature: To create GAMMA PTP Order in OCX


@Test_GAMMA_PTP
  Scenario Outline: Create GAMMA PTP order in ENV
    Given env<eNv> is UP for PTP order Creation for MPT_ORDER_MACD_Amend
     When CNOD of first UNI is Pushed to OCX for MPT_ORDER_MACD_Amend <eNv> <stateRegion1>
     And  CNOD of UNI_B is Pushed to OCX for MPT_ORDER_MACD_Amend <eNv> <stateRegion2>
     And  CNOD of UNI_C is Pushed to OCX for MPT_ORDER_MACD_Amend <eNv> <stateRegion3>
     And CNOD of PTP is PUSHED to OCX by UNI_A, UNI_B and UNI_C for MPT_ORDER_MACD_Amend<eNv> <stateRegion>
     And complete the PTP ORDER by NOTIFY on to CHange<eNv>
    And pass AssignProductId from MTP response to MACD Request order ID must be Created and Save the Response<eNv>
    And pass AssignProductId and productOrderItemId  from EVC MACD to MPT_ORDER_MACD_Amend and push <eNv>
     Then  MPT_ORDER_MACD_Amend is created in OCX.
    
    
    Examples: 
    | eNv   |stateRegion1|stateRegion2|stateRegion3|
    | "ST5" |"O"|"Y"|"Y"|


    