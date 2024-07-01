#Author: md388b@batt.com
@UNI_MACD
Feature: To create UNI Order in OCX

@Test_GAMMA_UNI_MACD
  Scenario Outline: Create UNI Orders in OCX
    Given  need UNI Request <eNv><stateRegion>
    And need URL to push based on the attribute of eNv
    When Push updated request with url UNI order created
    And complete the UNI order by notify on<eNv>
    And change assignProductId from UNI response to MACD req<eNv>
    Then MACD OA id must be created and Save the response

    Examples: 
      | eNv  | stateRegion |
      | "ST3" | "O"|
