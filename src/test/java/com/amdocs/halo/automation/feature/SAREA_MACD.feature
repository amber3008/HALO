#Author: md388b@batt.com
@SAREA_MACD
Feature: To create UNI Order in OCX

@Test_GAMMA_SAREA_MACD
  Scenario Outline: Create UNI Orders in OCX
    Given  need SAREA Request <eNv>
    And need URL to push based on the attribute
    When PUSH updated request with url UNI order created
    And complete the SAREA order by notify on<eNv>
    And change assignProductId from SAREA response to MACD req<eNv>
    Then MACD order id must be created and Save the response

    Examples: 
      | eNv  | 
      | "ST4" | 