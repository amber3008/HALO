#Author: md388b@att.com
@MPT_ORDER
Feature: To create GAMMA MPT Order in OCX


@Test_GAMMA_MTP
  Scenario Outline: Create GAMMA MTP order in Env
    Given Env<eNv> is UP for MPT order Creation for 
     When First CNOD of UNI_A is pushed to OCX for MPT<eNv> <stateRegion1>
     And  Second CNOD of UNI_B is pushed to OCX for MPT<eNv> <stateRegion2>
     And  third CNOD of UNI_C is pushed to OCX for MPT<eNv> <stateRegion3>
     And CNOD of MPT is PUSHED to OCX for UNI_A and UNI_B and UNI_C<eNv> <stateRegion>
     Then MPT OA is CREATED in OCX.
    
    
    Examples: 
    | eNv   |stateRegion1|stateRegion2|stateRegion3|
    | "ST5" |"Y"|"Y"|"Y"|
    
    
    
@Test_GAMMA_MPT_AMEND
  Scenario Outline: Create GAMMA MPT order in Env
    Given Env<eNv> is UP for MPT order Creation for 
     When First CNOD of UNI_A is pushed to OCX for MPT<eNv> <stateRegion1>
     And  Second CNOD of UNI_B is pushed to OCX for MPT<eNv> <stateRegion2>
     And  third CNOD of UNI_C is pushed to OCX for MPT<eNv> <stateRegion3>
     And CNOD of MPT is PUSHED to OCX for UNI_A and UNI_B and UNI_C<eNv> <stateRegion>
     And MPT OA is CREATED in OCX.
     And pass productOrderItemId andassignedProductId to MPT_AMEND and push <eNv>
     Then MPT_AMEND is created in OCX.
    
    Examples: 
    | eNv   |stateRegion1|stateRegion2|stateRegion3|
    | "ST4" |"O"|"Y"|"O"|
    
    
    
    
@Test_GAMMA_MPT_CANCEL
  Scenario Outline: Create GAMMA PTP order in Env
    Given Env<eNv> is UP for MPT order Creation for 
     When First CNOD of UNI_A is pushed to OCX for MPT<eNv> <stateRegion1>
     And  Second CNOD of UNI_B is pushed to OCX for MPT<eNv> <stateRegion2>
     And  third CNOD of UNI_C is pushed to OCX for MPT<eNv> <stateRegion3>
     And CNOD of MPT is PUSHED to OCX for UNI_A and UNI_B and UNI_C<eNv> <stateRegion>
      And MPT OA is CREATED in OCX.
     And pass productOrderItemId to MPT_Cancel and push <eNv>
     Then MPT_Cancel is created in OCX.
    
    Examples: 
    | eNv   |stateRegion1|stateRegion2|stateRegion3|
    | "ST4" |"Y"|"O"|"Y"|