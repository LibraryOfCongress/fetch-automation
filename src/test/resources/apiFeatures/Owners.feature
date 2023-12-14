@FETCH-312 @FETCH-303
Feature: Owners API

@owner_tier
  Scenario Outline: Create Owner Tier Record
    Given user creates a new record with following data <level>, "<name>"
    And user submits POST request to Owner Tier API endpoint
    Then user validates if status code is 200
    And user validates if the value of name in response is "<name>"
    And user retrieves recordID from response
    Examples:
    |level |name            |
    |4     |McDonalds       |

  @owner_tier
  Scenario: Get Owner Tier Detals view
    Given user submits GET request with recordID to get an Owner Tier details
    Then user validates if status code is 200

  @owner_tier
  Scenario: Delete Owner Tier Record
    Given user submits DELETE request with recordID to Owner Tier API endpoint
    Then user validates status code is 204

  @owner_tier
  Scenario: Get Owner Tier List view
    Given user submits GET request to retrieve all Owner Tier records
    Then user validates if status code is 200

@owner @run
  Scenario Outline: Create Owner Record
    Given Owner Tier recordID is created
    And user creates the request data with "<name>", <owner_tier_id>
    And user submits POST request to Owner API endpoint
    Then user validates if status code is 200
    And user validates if the value of name in response is "<name>"
    And user retrieves ownerID from response
  Examples:
  |name                    |owner_tier_id |
  |Library                 |135           |

  @owner
  Scenario: Get Owner Detals view
    Given user submits GET request with ownerID to get an Owner details
    Then user validates if status code is 200

  @owner
  Scenario: Delete Owner Record
    Given user submits DELETE request with ownerID to Owner API endpoint
    Then user validates status code is 204

  @owner
  Scenario: Get Owner List view
    Given user submits GET request to retrieve all Owner records
    Then user validates if status code is 200












