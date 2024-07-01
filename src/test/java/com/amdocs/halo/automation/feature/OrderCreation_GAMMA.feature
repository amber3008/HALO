#Author: ab780k@att.com
@OrderCreationGAMMA
Feature: To create GAMMA Order in OCX
	
	@Test_GAMMA_MTP
  Scenario Outline: Create GAMMA UNI and MPT order in Env
    Given Env<Env> is up for MPT order creation for <isADX>
    When CNOD of UNI_A is pushed to OCX for MPT <Env> <stateRegion1> <isFttbNeeded1> <isTSP1>
    And CNOD of UNI_B is pushed to OCX for MPT<Env> <stateRegion2> <isFttbNeeded2>  <isTSP2>
    And CNOD of UNI_C is pushed to OCX for MPT<Env> <stateRegion3> <isFttbNeeded3>  <isTSP3>
    And CNOD of MPT is pushed to OCX for <Env> <whichUNIADXHost> 
    Then MPT OA is created in OCX.
    
    
    Examples: 
    | Env   | stateRegion1   | isFttbNeeded1   | isADX    |  isTSP1   | stateRegion2   | isFttbNeeded2   |  isTSP2   | stateRegion3   | isFttbNeeded3   |  isTSP3   | whichUNIADXHost |
    | "ST5" |     "Y"        |      "N"        |  "Y"     |   "N"     |     "N"        |      "N"        |   "Y"     |     "O"        |      "Y"        |   "N"     |       "C"       |




# stateRegion can be Y, O , N
# isFttbNeeded can be only Y or N. 
# isADX can be Y or N.
# isTSP cane be Y or N.
# if isADX is N, then whichUNIADXHost should be "null"
# if isADX is Y, then whichUNIADXHost should be either "A", "B", "C"



@Test_GAMMA_PTP
  Scenario Outline: Create GAMMA PTP order in Env
    Given Env<Env> is up for PTP order creation for <isADX>
     When CNOD of UNI_A is pushed to OCX for PTP<Env> <stateRegion1> <isFttbNeeded1>  <isTSP1>
     And  CNOD of UNI_B is pushed to OCX for PTP<Env> <stateRegion2> <isFttbNeeded2> <isTSP2>
     And CNOD of PTP is pushed to OCX for UNI_A and UNI_B <whichUNIADXHost>
     Then PTP OA is created in OCX.
    
    
    Examples: 
    | Env   |stateRegion1   | isFttbNeeded   | isADX   |  isTSP   |stateRegion2   | isFttbNeeded2   |  isTSP2   | whichUNIADXHost |
    | "ST5" |    "N"        |      "N"       |  "Y"    |   "N"    |    "Y"        |      "N"        |   "N"     |      "B"				|


# if isADX is N, then whichUNIADXHost should be "null"
# if isADX is Y, then whichUNIADXHost should be either "A", "B"


