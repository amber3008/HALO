#Author: ab780k@att.com
@OCXOrderCreation_AVPN
Feature: To create AVPN arch Order in OCX 
	
	@Test
  Scenario Outline: Validate Order is created in OCX.
    Given Env<Env> is up
    When createAndStartOrderRequest is pushed to OMX. 
    Then Order is created in OCX.
    
    
    Examples: 
    | Env   | 
    | "ST3" |

    
    
    
@Test_GAMMA_AVPN
  Scenario Outline: Create GAMMA APORT and AVLAN order in Env
    Given Env<Env> is up for AVLAN order creation 
     And No of VLAN <noOfVlan> 
     And  CNOD of APORT and AVLAN is pushed to OCX 
     Then AVPN GAMMA Order is created in OCX.
    
    
    Examples: 
    | Env   |   noOfVlan |
    | "ST4" |   0        |