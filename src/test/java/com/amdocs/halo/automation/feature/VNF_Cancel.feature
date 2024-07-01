#Author: md388b@att.com
@VNF_Cancel
Feature: To create VNF cancel GAMMA Order in OCX




@Test_GAMMA_VNF_Cancel
  Scenario Outline: Create GAMMA VNF_Cancel order in Env
    Given Env is up for VNF_Cancel order Creation for <eNv> 
     When CNOD of VNF is Pushed to OCX for VNF_Cancel<eNv>
     And Push cancel request for VNF_Cancel<eNv>
     Then VNF_Cancel is created in OCX.
    
    
    Examples: 
    | eNv   |
    | "ST4" |