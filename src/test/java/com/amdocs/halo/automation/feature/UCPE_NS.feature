#Author: md388b@batt.com
@UCPE_NS
Feature: To create UCPE_NS Order in OCX

@Test_GAMMA_UCPE
  Scenario Outline: Create UCPE_NS Orders in OCX
    Given  need UCPE_NS Request <env>
    And need URL to push Request
    And If needed then only, update the request with Necessary attribute
    When PUSH Updated request with URL
    Then OA ID must be created and Save the RESPONSE

    Examples: 
      | env  | 
      | "ST4" | 
