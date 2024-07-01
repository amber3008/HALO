#Author: md388b@att.com
@PTP_ORDER_Cancel
Feature: To create GAMMA PTP Order in OCX and Cancel


@Test_GAMMA_PTP_Cancel
  Scenario Outline: Create GAMMA PTP order in Env
    Given Env<eNv> is UP for PTP_Cancel order Creation for 
     When CNOD of first UNI is pushed to OCX for PTP_Cancel<eNv> <stateRegion1>
     And  CNOD of UNI_B is pushed to OCX for PTP_Cancel<eNv> <stateRegion2>
     And CNOD of PTP_Cancel is PUSHED to OCX for UNI_A and UNI_B<eNv> <stateRegion>
     And PTP OA is CREATED in OCX for cancel.
    # And pass productOrderItemId to PTP_Cancel and push <eNv>
     #Then PTP_Cancel is created in OCX.
    
    Examples: 
    | eNv   |stateRegion1|stateRegion1|
    | "ST3" |"Y"|"Y"|


    