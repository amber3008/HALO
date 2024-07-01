#Author: md388b@att.com
@SAREA_MACD_Amend


Feature: To create Amend UNI Order in OCX

@Test_SAREA_MACD_Amend
  Scenario Outline: Create GAMMA SAREA_MACD_Amend order in Env
    Given Env is up for SAREA_MACD_Amend order creation for <eNv> 
     When SAREA order is pushed to OCX for SAREA_MACD_Amend<eNv>
     And complete the ORDER by notify on<eNv>
     And Perform MACD operation for above sarea order<eNv>
     And pass productOrderItemId and assignedProductId to SAREA_MACD_Amend and push <eNv>
     Then SAREA_MACD_Amend is created in OCX.
    
    
    Examples: 
    | eNv   |
    | "ST4" | 