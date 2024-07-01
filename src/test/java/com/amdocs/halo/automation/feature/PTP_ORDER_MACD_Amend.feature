#Author: md388b@att.com
@PTP_ORDER_MACD_Amend
Feature: To create GAMMA PTP Order in OCX


@Test_GAMMA_PTP
  Scenario Outline: Create GAMMA PTP order in ENV
    Given env<eNv> is UP for PTP order Creation for PTP_ORDER_MACD_Amend
     When CNOD of first UNI is Pushed to OCX for PTP_ORDER_MACD_Amend <eNv> <stateRegion1>
     And  CNOD of UNI_B is Pushed to OCX for PTP_ORDER_MACD_Amend <eNv> <stateRegion2>
     And CNOD of PTP is PUSHED to OCX by UNI_A And UNI_B for PTP_ORDER_MACD_Amend<eNv> <stateRegion>
     And complete the PTP ORDER by NOTIFY on to change<eNv>
    And pass AssignProductId from PTP response to MACD Request order ID must be created and Save the Response<eNv>
    And pass productOrderItemId and AssignProductId from EVC MACD to PTP_ORDER_MACD_Amend and push <eNv>
     Then  PTP_ORDER_MACD_Amend is created in OCX.
    
    
    Examples: 
    | eNv   |stateRegion1|stateRegion2|
    | "ST3" |"O"|"O"|


    