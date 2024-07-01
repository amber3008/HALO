#Author: si280e@att.com
@GammaOrderCreation.feature
Feature: To create GAMMA Order in OCX
	
	   @BTOCSP_ASEOD
  Scenario Outline: Create BTOCSP Order for Enviroment
	    Given Env<Env> is up for BTOCSP order creation
	    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
	    And Notify request is pushed
	    And CNOD of ChangeCHFIS UNI is Pushed 
	    And CNOD of BTOCSP is Pshed by Using UNI CKTID
	    When CNOD of Start Order Procesing is pushed for UNI CH
	    Then BTOCSP OA is created in OCX.
	   
	    
	    Examples: 
	    | Env   | stateRegion1   | 
	    | "ST5" |     "Y"        |
		
		 @BTOCSP_ASEOD_Disconnect
  Scenario Outline: Create BTOCSP Order for Enviroment
    Given Env<Env> is up for BTOCSP order creation
    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
    And Notify request is pushed
    And CNOD of ChangeCHFIS UNI is Pushed 
    And CNOD of BTOCSP is Pshed by Using UNI CKTID
    And CNOD of Start Order Procesing is pushed for UNI CH    
    When CNOD of Disconnect is pushed
    Then BTOCSP OA is created in OCX.
    
    Examples: 
    | Env   | stateRegion1   | 
    | "ST3" |     "Y"        | 
    
	  @BTOCSP_ASEOD_Cancel
  Scenario Outline: Create BTOCSP Order for Enviroment
    Given Env<Env> is up for BTOCSP order creation
    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
    And Notify request is pushed
    And CNOD of ChangeCHFIS UNI is Pushed 
    And CNOD of BTOCSP is Pshed by Using UNI CKTID
    And CNOD of Start Order Procesing is pushed for UNI CH    
    When CNOD  of Cancel is pushed
    Then BTOCSP OA is created in OCX.
  
    
    
    Examples: 
    | Env   | stateRegion1   | 
    | "ST4" |     "Y"        | 
    
    
     @BTOCS_ASEOD_Amend
  Scenario Outline: Create BTOCSP Order for Enviroment
    Given Env<Env> is up for BTOCSP order creation
    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
    And Notify request is pushed
    And CNOD of ChangeCHFIS UNI is Pushed 
    And CNOD of BTOCSP is Pshed by Using UNI CKTID  
    And CNOD of Start Order Procesing is pushed for UNI CH  
    When CNOD of Amend_REQ is Pushed 
    Then BTOCSP OA is created in OCX.
    
    
    Examples: 
    | Env   | stateRegion1   | 
    | "ST5" |     "Y"        | 
	 
      
    
	
	
	
    
   
    