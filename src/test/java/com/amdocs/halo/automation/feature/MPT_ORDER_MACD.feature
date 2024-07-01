#Author: md388b@att.com
@MPT_ORDER_MACD
Feature: To create GAMMA MPT Order in OCX


@Test_GAMMA_MPT
  Scenario Outline: Create GAMMA MPT order in Env
    Given env<eNv> is UP for MPT order Creation for 
     When CNOD of first UNI is Pushed to OCX for MPT <eNv> <stateRegion1>
     And  CNOD of UNI_B is Pushed to OCX for MPT <eNv> <stateRegion2>
     And  CNOD of UNI_C is PUSHED to OCX for MPT <eNv> <stateRegion3>
     And CNOD of MPT is PUSHED to OCX for UNI_A, UNI_B and UNI_C <eNv> <stateRegion>
     And complete the MPT order by NOTIFY on<eNv>
    And pass assignProductId from MPT response to MACD Request<eNv>
    Then MACD order ID must be created and Save the  Response
    
    
    Examples: 
    | eNv   |stateRegion1|stateRegion2|stateRegion3|
    | "ST5" |"Y"|"Y"|"O"|


    