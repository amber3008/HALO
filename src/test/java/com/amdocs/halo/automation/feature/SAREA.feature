#Author: md388b@batt.com
@SAREA
Feature: To create UNI Order in OCX

@Test_GAMMA_SAREA
  Scenario Outline: Create SAREA Orders in OCX
    Given  need SAREA Request to get response<env>
    And need URL to Push based on the attribute of  env
    And If needed only, update the   Request with necessary attribute
    When Push updated request with  URL
    Then OA id must be created and Save the Response

    Examples: 
      | env  | 
      | "ST3" |
