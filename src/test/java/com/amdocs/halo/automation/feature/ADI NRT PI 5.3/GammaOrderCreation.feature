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
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
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
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
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
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   | stateRegion1   | 
    | "ST5" |     "Y"        | 
    
    
      @BTOCSP_ASEOD_MACD
  Scenario Outline: Create BTOCSP Order for Enviroment
    Given Env<Env> is up for BTOCSP order creation
    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
    And Notify request is pushed
    And CNOD of ChangeCHFIS UNI is Pushed 
    And CNOD of BTOCSP is Pshed by Using UNI CKTID
    And CNOD of Start Order Procesing is pushed for UNI CH  
    When CNOD of Change CIR is pushed
    Then BTOCSP OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   | stateRegion1   | 
    | "ST3" |     "Y"        |
    
    
    @BTOCSP_ASEOD_Disconnect_Cancel
  Scenario Outline: Create BTOCSP Order for Enviroment
      Given Env<Env> is up for BTOCSP order creation
    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
    And Notify request is pushed
    And CNOD of ChangeCHFIS UNI is Pushed 
    And CNOD of BTOCSP is Pshed by Using UNI CKTID
    And CNOD of Start Order Procesing is pushed for UNI CH  
    And CNOD of Disconnect is pushed
    When CNOD  of Cancel is pushed
    Then BTOCSP OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   | stateRegion1   | 
    | "ST5" |     "Y"        | 
	
	
	
	 @BTOCSP_ASEOD_MACD_Disconnect
  Scenario Outline: Create BTOCSP Order for Enviroment
      Given Env<Env> is up for BTOCSP order creation
    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
    And Notify request is pushed
    And CNOD of ChangeCHFIS UNI is Pushed 
    And CNOD of BTOCSP is Pshed by Using UNI CKTID
    And CNOD of Start Order Procesing is pushed for UNI CH  
    And CNOD of Change CIR is pushed
    When CNOD of Disconnect single API is pushed 
    Then BTOCSP OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   | stateRegion1   | 
    | "ST5" |     "Y"        |
    
    
     @BTOCSP_ASEOD_MACD_Cancel
  Scenario Outline: Create BTOCSP Order for Enviroment
      Given Env<Env> is up for BTOCSP order creation
	    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
	    And Notify request is pushed
	    And CNOD of ChangeCHFIS UNI is Pushed 
	    And CNOD of BTOCSP is Pshed by Using UNI CKTID
	    And CNOD of Start Order Procesing is pushed for UNI CH  
	    And CNOD of Change CIR is pushed
	    When CNOD  of Cancel is pushed 
	    Then BTOCSP OA is created in OCX.
	    And user opens browser <Env>
	    And user is on login page
	    And user login into HALO OMX GUI
	    And user is on Home Page
	    When user clicks on Search dropdown in Order Search Tab
	    And User Searches the order
	    Then Order id is displayed
    
    
    Examples: 
    | Env   | stateRegion1   | 
    | "ST3" |     "Y"        |
    
    
      @BTOCSP_ASEOD_MACD_Amend
  Scenario Outline: Create BTOCSP Order for Enviroment
    Given Env<Env> is up for BTOCSP order creation
	    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
	    And Notify request is pushed
	    And CNOD of ChangeCHFIS UNI is Pushed 
	    And CNOD of BTOCSP is Pshed by Using UNI CKTID
	    And CNOD of Start Order Procesing is pushed for UNI CH
	    And CNOD of Change for amend is pushed
	    When CNOD of MACD Amend_REQ is Pushed 
	    Then BTOCSP OA is created in OCX.
	    And user opens browser <Env>
	    And user is on login page
	    And user login into HALO OMX GUI
	    And user is on Home Page
	    When user clicks on Search dropdown in Order Search Tab
	    And User Searches the order
	    Then Order id is displayed
	    
	    
	    Examples: 
	    | Env   | stateRegion1   | 
	    | "ST3" |     "Y"        | 
	    
    
     @BTOCSP_MTPEVC
  Scenario Outline: Create BTOCSP Order for Enviroment
	    Given Env<Env> is up for BTOCSP order creation
	    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
	    And Notify request is pushed
	    And CNOD of ChangeCHFIS UNI is Pushed 
	    And CNOD of EVCA is Pushed By Using UNI CKTID
	    When CNOD of BTOCSP is Pshed by Using EVCV CKTID 
	    Then BTOCSP OA is created in OCX.
	    And user opens browser <Env>
	    And user is on login page
	    And user login into HALO OMX GUI
	    And user is on Home Page
	    When user clicks on Search dropdown in Order Search Tab
	    And User Searches the order
	    Then Order id is displayed
	    
	    
	    Examples: 
	    | Env   | stateRegion1   | 
	    | "ST3" |     "Y"        | 
    
	   @BTOCSP_MTPEVC_Cancel
  Scenario Outline: Create BTOCSP Order for Enviroment
	    Given Env<Env> is up for BTOCSP order creation
	    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
	    And Notify request is pushed
	    And CNOD of ChangeCHFIS UNI is Pushed 
	    And CNOD of EVCA is Pushed By Using UNI CKTID
	    And CNOD of BTOCSP is Pshed by Using EVCV CKTID   
	    And CNOD of Start Order Procesing is pushed for UNI CH 
	    When CNOD  of Cancel is pushed
	    Then BTOCSP OA is created in OCX.
	    And user opens browser <Env>
	    And user is on login page
	    And user login into HALO OMX GUI
	    And user is on Home Page
	    When user clicks on Search dropdown in Order Search Tab
	    And User Searches the order
	    Then Order id is displayed
	    
	    
	    Examples: 
	    | Env   | stateRegion1   | 
	    | "ST5" |     "Y"        | 



	@BTOCSP_MTPEVC_Disconnect
  Scenario Outline: Create BTOCSP Order for Enviroment
    Given Env<Env> is up for BTOCSP order creation
    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
    And Notify request is pushed
    And CNOD of ChangeCHFIS UNI is Pushed 
    And CNOD of EVCA is Pushed By Using UNI CKTID
    And CNOD of BTOCSP is Pshed by Using EVCV CKTID    
    And CNOD of Start Order Procesing is pushed for UNI CH
    When CNOD of Disconnect is pushed
    Then BTOCSP OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   | stateRegion1   | 
    | "ST5" |     "Y"        | 
   

    
   

	@BTOCSP_MTPEVC_Disconnect_Cancel
  Scenario Outline: Create BTOCSP Order for Enviroment
    Given Env<Env> is up for BTOCSP order creation
    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
    And Notify request is pushed
    And CNOD of ChangeCHFIS UNI is Pushed 
    And CNOD of EVCA is Pushed By Using UNI CKTID
    And CNOD of BTOCSP is Pshed by Using EVCV CKTID    
    And CNOD of Start Order Procesing is pushed for UNI CH
    And CNOD of Disconnect is pushed
    When CNOD  of Cancel is pushed
    Then BTOCSP OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   | stateRegion1   | 
    | "ST5" |     "Y"        | 
    
    

	
  @BTOCS_MTPEVC_Amend
  Scenario Outline: Create BTOCSP Order for Enviroment
    Given Env<Env> is up for BTOCSP order creation
    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
    And Notify request is pushed
    And CNOD of ChangeCHFIS UNI is Pushed 
    And CNOD of EVCA is Pushed By Using UNI CKTID
    And CNOD of BTOCSP is Pshed by Using EVCV CKTID 
    And CNOD of Start Order Procesing is pushed for UNI CH
    When CNOD of Amend_REQ is Pushed 
    Then BTOCSP OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   | stateRegion1   | 
    | "ST4" |     "Y"        | 
    
       
  @BTOCSP_MTPEVC_MACD
  Scenario Outline: Create BTOCSP Order for Enviroment  
    Given Env<Env> is up for BTOCSP order creation
    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
    And Notify request is pushed
    And CNOD of ChangeCHFIS UNI is Pushed 
    And CNOD of EVCA is Pushed By Using UNI CKTID
    And CNOD of BTOCSP is Pshed by Using EVCV CKTID 
    And CNOD of Start Order Procesing is pushed for UNI CH
    When CNOD of Change CIR is pushed
    Then BTOCSP OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   | stateRegion1   | 
    | "ST5" |     "Y"        | 
    
     @BTOCSP_MTPEVC_MACD_Disconnect
  Scenario Outline: Create BTOCSP Order for Enviroment  
    Given Env<Env> is up for BTOCSP order creation
    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
    And Notify request is pushed
    And CNOD of ChangeCHFIS UNI is Pushed 
    And CNOD of EVCA is Pushed By Using UNI CKTID
    And CNOD of BTOCSP is Pshed by Using EVCV CKTID 
    And CNOD of Start Order Procesing is pushed for UNI CH
    And CNOD of Change CIR is pushed
    When CNOD of Disconnect single API is pushed 
    Then BTOCSP OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   | stateRegion1   | 
    | "ST5" |     "Y"        | 
    
    
      @BTOCSP_MTPEVC_MACD_Cancel
  Scenario Outline: Create BTOCSP Order for Enviroment  
		    Given Env<Env> is up for BTOCSP order creation
		    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
		    And Notify request is pushed
		    And CNOD of ChangeCHFIS UNI is Pushed 
		    And CNOD of EVCA is Pushed By Using UNI CKTID
		    And CNOD of BTOCSP is Pshed by Using EVCV CKTID 
		    And CNOD of Start Order Procesing is pushed for UNI CH
		    And CNOD of Change CIR is pushed
		    When CNOD  of Cancel is pushed 
		    Then BTOCSP OA is created in OCX.
		    And user opens browser <Env>
		    And user is on login page
		    And user login into HALO OMX GUI
		   And user is on Home Page
		    When user clicks on Search dropdown in Order Search Tab
		   And User Searches the order
		  Then Order id is displayed
		    
		    
		    Examples: 
		    | Env   | stateRegion1   | 
		    | "ST5" |     "Y"        | 
    
     @BTOCSP_MTPEVC_MACD_AMEND
  Scenario Outline: Create BTOCSP Order for Enviroment
	    Given Env<Env> is up for BTOCSP order creation
	    And CNOD of UNI_A is pushed to OCX for BTOCSP  <stateRegion1> 
	    And Notify request is pushed
	    And CNOD of ChangeCHFIS UNI is Pushed 
	    And CNOD of EVCA is Pushed By Using UNI CKTID
	    And CNOD of BTOCSP is Pshed by Using EVCV CKTID 
	    And CNOD of Change for amend is pushed
	    When CNOD of MACD Amend_REQ is Pushed 
	    Then BTOCSP OA is created in OCX.
	    And user opens browser <Env>
	    And user is on login page
	    And user login into HALO OMX GUI
	    And user is on Home Page
	    When user clicks on Search dropdown in Order Search Tab
	    And User Searches the order
	    Then Order id is displayed
	    
	    
	    Examples: 
	    | Env   | stateRegion1   | 
	    | "ST3" |     "Y"        | 
    
    
    
     @BtoB
  Scenario Outline: Create BTOB Order for Enviroment
	    Given Env<Env> is up for BTOB order creation
	    When CNOD of BTOB is pushed
	    Then BTOCSP OA is created in OCX.
	    And user opens browser <Env>
	    And user is on login page
	    And user login into HALO OMX GUI
	    And user is on Home Page
	    When user clicks on Search dropdown in Order Search Tab
	    And User Searches the order
	    Then Order id is displayed
	    
	    
	    Examples: 
	    | Env   |
	    | "ST5" |    
	    
    
    

    
      @BtoB_Disconnect
  Scenario Outline: Create BTOB Order for Enviroment
    Given Env<Env> is up for BTOB order creation
    And CNOD of BTOB is pushed
    When CNOD of Disconnect To API is pushed
    Then BTOB OA is crated in OCX
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   |
    | "ST5" |    
    
    
      @BtoB_Cancel
  Scenario Outline: Create BTOB Order for Enviroment
    Given Env<Env> is up for BTOB order creation
    And CNOD of BTOB is pushed
    When CNOD  of Cancel is pushed
    Then BTOB OA is crated in OCX
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
   
    
   
    
    Examples: 
    | Env   |
    | "ST5" |    
    
    
      @BtoB_Change
  Scenario Outline: Create BTOB Order for Enviroment
   Given Env<Env> is up for BTOB order creation
   And CNOD of BTOB is pushed
   When CNOD of Change CIR is pushed
   Then BTOB OA is crated in OCX
   And user opens browser <Env>
   And user is on login page
   And user login into HALO OMX GUI
   And user is on Home Page
   When user clicks on Search dropdown in Order Search Tab
   And User Searches the order
   Then Order id is displayed
    
    Examples: 
    | Env   |
    | "ST3" |  
      
    
          @BtoB_MACD_AMEND
  Scenario Outline: Create BTOB Order for Enviroment
    Given Env<Env> is up for BTOB order creation
    And CNOD of BTOB is pushed
    And CNOD of Change for amend is pushed
    When CNOD of MACD Amend_REQ is Pushed
    Then BTOCSP OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    
    Examples: 
    | Env   |
    | "ST3" |    
    
    
    @BtoB_Change_Disconnect
  Scenario Outline: Create BTOB Order for Enviroment
    Given Env<Env> is up for BTOB order creation
    And CNOD of BTOB is pushed
    And CNOD of Change CIR is pushed
    When CNOD of Disconnect single API is pushed
    Then BTOB OA is crated in OCX
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
   
   
    
    Examples: 
    | Env   |
    | "ST3" |  
    
    
     @BtoB_Change_Cancel
  Scenario Outline: Create BTOB Order for Enviroment
   Given Env<Env> is up for BTOB order creation
   And CNOD of BTOB is pushed
   And CNOD of Change CIR is pushed
   When CNOD  of Cancel is pushed
   Then BTOB OA is crated in OCX
   And user opens browser <Env>
   And user is on login page
   And user login into HALO OMX GUI
   And user is on Home Page
   When user clicks on Search dropdown in Order Search Tab
   And User Searches the order
   Then Order id is displayed
    
    
    
    Examples: 
    | Env   |
    | "ST3" | 
    
    
    
    
     @Multiple_SCMC
  Scenario Outline: Create Multiple_SCMC Order for Enviroment
    Given Env<Env> is up for Multiple_SCMC order creation
    When CNOD of Multiple_SCMC is pushed
    Then Multiple_SCMC OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   |
    | "ST5" |
     
    
    
      @Multiple_SCMC_Disconnect
  Scenario Outline: Create Multiple_SCMC Order for Enviroment
    Given Env<Env> is up for Multiple_SCMC order creation
    And CNOD of Multiple_SCMC is pushed
    When CNOD of Disconnect is pushed
    Then Multiple_SCMC OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   |
    | "ST3" |
    
    
       @Multiple_SCMC_Cancel
  Scenario Outline: Create Multiple_SCMC Order for Enviroment
    Given Env<Env> is up for Multiple_SCMC order creation
    And CNOD of Multiple_SCMC is pushed
    When CNOD  of Cancel is pushed
    Then Multiple_SCMC OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   |
    | "ST3" |
     
     
     @Multiple_SCMC_MACD
  Scenario Outline: Create Multiple_SCMC Order for Enviroment
    Given Env<Env> is up for Multiple_SCMC order creation
    And CNOD of Multiple_SCMC is pushed
    When CNOD of Change CIR is pushed
    Then Multiple_SCMC OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   |
    | "ST3" |
     
     
     @Multiple_SCMC_MACD_Disconnect
  Scenario Outline: Create Multiple_SCMC Order for Enviroment
    Given Env<Env> is up for Multiple_SCMC order creation
    And CNOD of Multiple_SCMC is pushed
    And CNOD of Change CIR is pushed
    When CNOD of Disconnect single API is pushed
    Then Multiple_SCMC OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   |
    | "ST3" |
     
     @Multiple_SCMC_MACD_Cancel
  Scenario Outline: Create Multiple_SCMC Order for Enviroment
    Given Env<Env> is up for Multiple_SCMC order creation
    And CNOD of Multiple_SCMC is pushed
    And CNOD of Change CIR is pushed
    When CNOD  of Cancel is pushed
    Then Multiple_SCMC OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
    
    Examples: 
    | Env   |
    | "ST3" |
     
     
     @APORT
   Scenario Outline: Create APORT Order for Enviroment
	    Given Env<Env> is up for APORT order creation
	    And CNOD of APORT is pushed
	    When Pull the Get Order Data
	    Then APORT OA is created in OCX.
	    And user opens browser <Env>
	    And user is on login page
	    And user login into HALO OMX GUI
	    And user is on Home Page
	    When user clicks on Search dropdown in Order Search Tab
	    And User Searches the order
	    Then Order id is displayed
	    
	      Examples: 
	    | Env   |
	    | "ST3" |
    
    
     @APORT_Cancel
   Scenario Outline: Create APORT Order for Enviroment
    Given Env<Env> is up for APORT order creation
    And CNOD of APORT is pushed
    When CNOD  of Cancel is pushed
    Then APORT OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
     
      Examples: 
    | Env   |
    | "ST3" |
     
     
      @APORT_Disconnect
   Scenario Outline: Create APORT Order for Enviroment
    Given Env<Env> is up for APORT order creation
    And CNOD of APORT is pushed
    When CNOD of Disconnect single API is pushed
    Then APORT OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
     
      Examples: 
    | Env   |
    | "ST3" |
    
    @APORT_MACD
   Scenario Outline: Create APORT Order for Enviroment
    Given Env<Env> is up for APORT order creation
    And CNOD of APORT is pushed
    When CNOD of APORT CH is Pushed 
    Then APORT OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
    
     
      Examples: 
    | Env   |
    | "ST3" |
    
      @APORT_MACD_Disconnect
   Scenario Outline: Create APORT Order for Enviroment
    Given Env<Env> is up for APORT order creation
    And CNOD of APORT is pushed
    And CNOD of APORT CH is Pushed 
    When CNOD of Disconnect single API is pushed
    Then APORT OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
   
    
     
      Examples: 
    | Env   |
    | "ST3" |
    
    
     @APORT_MACD_Cancel
   Scenario Outline: Create APORT Order for Enviroment
    Given Env<Env> is up for APORT order creation
    And CNOD of APORT is pushed
    And CNOD of APORT CH is Pushed 
    When CNOD  of Cancel is pushed
    Then APORT OA is created in OCX.
    And user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
    When user clicks on Search dropdown in Order Search Tab
    And User Searches the order
    Then Order id is displayed
   
    
     
      Examples: 
    | Env   |
    | "ST3" |
    
    @RCPC
   Scenario Outline: Create RCPC Order for Enviroment
#   Given Env<Env> is up for RCPC
   Given CNOD of RCPC is pushed<catalogOfferID>
   Then APORT OA is created in OCX.
   	
   	
   	
   	
   	   Examples: 
    | Env   | catalogOfferID |
    | "ST3" | "6040915"      |
    
    
       @BtoB_Cancel_jenkins
  Scenario Outline: Create BTOB Order for Enviroment
    Given Env<Env> is up for BTOB order creation
    And CNOD of BTOB is pushed
    When CNOD  of Cancel is pushed
    Then BTOB OA is crated in OCX
   
   
    
    Examples: 
    | Env   |
    | "ST3" |  
    
    
       @BtoB_getProjectID
  Scenario Outline: Create BTOB Order for Enviroment
	    Given Env<Env> is up for BTOB order creation
	    And CNOD of BTOB is pushed
	    When getProjectID is pushed
	    Then BTOCSP OA is created in OCX.
		   
	    
	    
	    Examples: 
	    | Env   |
	    | "ST5" |    
	    
     
     