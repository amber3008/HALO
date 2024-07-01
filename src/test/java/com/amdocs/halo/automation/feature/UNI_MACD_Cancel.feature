#Author: md388b@att.com
@UNI_MACD_Cancel
Feature: To create UNI cancel GAMMA Order in OCX




@Test_GAMMA_UNI_MACD_Cancel
  Scenario Outline: Create GAMMA UNI_MACD_Cancel order in Env
    Given Env is up for UNI_MACD_Cancel order Creation for <eNv> 
    When CNOD of UNI is Pushed to OCX for UNI_MACD_Cancel<eNv><stateRegion>
    And complete the UNI order by Notify ON<eNv>
    And change assignProductId from UNI response to UNI_MACD req and get the res<eNv>
    And Push cancel request for UNI_MACD_Cancel<eNv>
    Then UNI_MACD_Cancel is created in OCX.
    
    
    Examples: 
    | eNv   |stateRegion|
    | "ST3" | "Y" |