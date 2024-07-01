#Author: md388b@att.com
@SAREA_DISCONNECT


Feature: To create GAMMA Order in OCX

@Test_GAMMA_SAREA_DISCONNECT
  Scenario Outline: Create GAMMA SAREA_DISCONNECT order in Env
    Given Env is up for SAREA_DISCONNECT order creation for <env> 
     When CNOD of SAREA is pushed to OCX for SAREA_DISCONNECT<env>
     And Push notify on request for SAREA_DISCONNECT<env>
     And Push Disconnect request for SAREA_DISCONNECT<env>
     Then SAREA_DISCONNECT is created in OCX.
    
    
    Examples: 
    | env   |
    | "ST4" |
