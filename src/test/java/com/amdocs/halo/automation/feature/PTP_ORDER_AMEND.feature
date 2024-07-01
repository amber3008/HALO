#Author: md388b@att.com
@PTP_ORDER_AMEND
Feature: To create GAMMA PTP Order in OCX and Amennd


@Test_GAMMA_PTP_AMEND
  Scenario Outline: Create GAMMA PTP order in Env
    Given Env<eNv> is UP for PTP_Amend order Creation for 
     When CNOD of first UNI is pushed to OCX for PTP_Amend<eNv> <stateRegion1>
     And  CNOD of UNI_B is pushed to OCX for PTP_Amend<eNv> <stateRegion2>
     And CNOD of PTP_Amend is PUSHED to OCX for UNI_A and UNI_B<eNv> <stateRegion>
     And PTP OA is CREATED in OCX.
    And pass productOrderItemId andassignedProductId to PTP_Amend and push <eNv>
    Then PTP_Amend is created in OCX.
    
    Examples: 
    | eNv   |stateRegion1|stateRegion2|
    | "ST4" |"Y"|"Y"|


    