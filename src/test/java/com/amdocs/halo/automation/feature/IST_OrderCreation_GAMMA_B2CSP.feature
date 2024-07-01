#Author: ab780k@att.com
@IST_OrderCreation_GAMMA_B2CSP
Feature: To create B2CSP Order in OCX
	
	@Test_GAMMA_B2CSP
  Scenario Outline: Create BTWOCSP Order in OCX in Env
    Given Env<Env> is up for BTWOCSP order creation for <isADX>
    When CNOD of BTWOCSP is pushed to OCX
    Then OA is created in OCX.
    
    
    Examples: 
    | Env   | isADX    |
    | "ST3" |     "Y"  |

