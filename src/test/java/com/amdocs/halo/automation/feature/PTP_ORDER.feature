#Author: md388b@att.com
@PTP_ORDER
Feature: To create GAMMA PTP Order in OCX


@Test_GAMMA_PTP
  Scenario Outline: Create GAMMA PTP order in Env
    Given Env<eNv> is UP for PTP order Creation for 
     When CNOD of first UNI is pushed to OCX for PTP<eNv> <stateRegion1>
     And  CNOD of UNI_B is pushed to OCX for PTP<eNv> <stateRegion2>
     And CNOD of PTP is PUSHED to OCX for UNI_A and UNI_B<eNv> <stateRegion>
     Then PTP OA is CREATED in OCX.
    
    
    Examples:
    | eNv   |stateRegion1|stateRegion2|
    | "ST4" |"N"|"Y"|


    