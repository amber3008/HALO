#Author: md388b@att.com
@SAREA_DISCONNECT_CANCEL


Feature: To create GAMMA Order in OCX

@Test_GAMMA_SAREA_DISCONNECT_CANCEL
  Scenario Outline: Create GAMMA SAREA_DISCONNECT_CANCEL order in Env
    Given Env is up for SAREA_DISCONNECT_CANCEL order creation for <env> 
     When CNOD of SAREA is pushed to OCX for SAREA_DISCONNECT_CANCEL<env>
     And Push notify on request for SAREA_DISCONNECT_CANCEL<env>
     And Push Disconnect request for SAREA_DISCONNECT_CANCEL<env>
     Then SAREA_DISCONNECT  is created in OCX.
    And Push cancel request for SAREA_DISCONNECT_CANCEL<eNv>
     Then SAREA_DISCONNECT_CANCEL  is created in OCX.
    
    Examples: 
    | env   |
    | "ST4" |
