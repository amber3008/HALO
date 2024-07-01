@IST_OMX_OrderSearch.feature
Feature: To search order in OMX after submission

  
	@Test
  Scenario Outline: To search order in OMX after submission
		
    Given user opens browser <Env>
    And user is on login page
    And user login into HALO OMX GUI
    And user is on Home Page
   # When user clicks on Search dropdown in Order Search Tab
   # And User Searches the order
   # Then Order id is displayed
    
    Examples: 
    | Env   |
    | "ST3" |
    

