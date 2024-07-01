#Author: md388b@batt.com
@VNF_standAlone
Feature: To create VNF_standAlone Order in OCX

@Test_GAMMA_VNF
  Scenario Outline: Create VNF_standAlone Orders in OCX
    Given  need VNF_standAlone Request <eNv>
    And need URL to push based on the Attribute of ENV
    And If needed only, update the request with Necessary attribute
    When Push updated request with URL
    Then OA ID must be created and Save the Response

    Examples: 
      | eNv  | 
      | "ST3" | 
