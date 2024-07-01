#Author: md388b@att.com
@PTP_ORDER_MACD
Feature: To create GAMMA PTP Order in OCX


@Test_GAMMA_PTP
  Scenario Outline: Create GAMMA PTP order in Env
    Given env<eNv> is UP for PTP order Creation for 
     When CNOD of first UNI is Pushed to OCX for PTP <eNv> <stateRegion1>
     And  CNOD of UNI_B is Pushed to OCX for PTP <eNv> <stateRegion2>
     And CNOD of PTP is PUSHED to OCX for UNI_A And UNI_B <eNv> <stateRegion>
     And complete the PTP order by NOTIFY on<eNv>
    And pass assignProductId from PTP response to MACD Request<eNv>
    Then MACD order ID must be created and Save the response
    
    
    Examples: 
    | eNv   |stateRegion1|stateRegion2|
    | "ST4" |"Y"|"Y"|


    