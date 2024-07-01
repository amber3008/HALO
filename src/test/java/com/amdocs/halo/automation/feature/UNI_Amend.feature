#Author: md388b@att.com
@UNI_Amend


Feature: To create Amend UNI Order in OCX

@Test_GAMMA_UNI_Amend
  Scenario Outline: Create GAMMA UNI_Amend order in Env
    Given Env is up for UNI_Amend order creation for <eNv> 
     When UNI order is pushed to OCX for UNI_Amend<eNv><stateRegion>
     And pass productOrderItemId andassignedProductId to UNI_Amend and push <eNv>
     Then UNI_Amend is created in OCX.
    
    
    Examples: 
    | eNv   |stateRegion|
    | "ST4" | "Y"|