Feature: Validate add Place API

  @AddPlaceAPI
  Scenario Outline: Test for add place api
    Given add place payload "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "post" http call
    Then api call is success with status code 200
    And  "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify placeID created with "<name>" from "getPlaceAPI"
    Examples:
      | name  | language  | address                      |
      | vinay | French-IN | vinay, side layout, cohen 09 |

  @DeletePlaceAPI
  Scenario: Test for delete place api
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "POST" http call
    Then api call is success with status code 200
    And "status" in response body is "OK"