#Author: md388b@att.com
@UCPE_Cancel
Feature: To create UCPE cancel GAMMA Order in OCX




@Test_GAMMA_UCPE_Cancel
  Scenario Outline: Create GAMMA UCPE_Cancel order in Env
    Given Env is up for UCPE_Cancel order Creation for <eNv> 
    When CNOD of UCPE is Pushed to OCX for UCPE_Cancel<eNv>
     And Push cancel request for UCPE_Cancel<eNv>
     Then UCPE_Cancel is created in OCX.
    
    
    Examples: 
    | eNv   |
    | "ST3" |