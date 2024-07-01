#Author: md388b@att.com
@SAREA_Cancel
Feature: To create SAREA cancel GAMMA Order in OCX




@Test_GAMMA_SAREA_Cancel
  Scenario Outline: Create GAMMA SAREA_Cancel order in Env
    Given Env is up for SAREA_Cancel order Creation for <Env> 
     When SAREA is Pushed to OCX for SAREA_Cancel <Env>
     And Push cancel request for SAREA_Cancel<Env>
     Then SAREA_Cancel is created in OCX.
    
    
    Examples: 
    | Env   |
    | "ST5" | 