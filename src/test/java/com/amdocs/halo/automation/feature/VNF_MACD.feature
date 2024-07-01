#Author: md388b@batt.com
@VNF_MACD
Feature: To create VNF Order in OCX

@Test_GAMMA_VNF_MACD
  Scenario Outline: Create VNF Orders in OCX
    Given  need VNF Request <env>
    And need URL to PUSH based on the Attribute of ENv
    When Push updated request with url VNF order created
    And complete the VNF order by notify on<env>
    And change assignProductId from VNF response to MACD req<env>
    Then VNF_MACD OA id must be created and Save the response

    Examples: 
      | env  | 
      | "ST3" | 
