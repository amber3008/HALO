#Author: md388b@att.com
@SAREA_Amend


Feature: To create Amend SAREA Order in OCX

@Test_GAMMA_SAREA_Amend
  Scenario Outline: Create GAMMA SAREA_Amend order in Env
    Given Env is up for SAREA_Amend order creation for <Env> 
     When SAREA order is pushed to OCX for SAREA_Amend<Env>
     And pass productOrderItemId andassignedProductId to Amend_Sarea and push <Env>
     Then SAREA_Amend is created in OCX.
    
    
    Examples: 
    | Env   |
    | "ST3" |