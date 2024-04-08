@FETCH-312 @FETCH-303 @run
Feature: Owners API


  @owner_tier
  Scenario Outline: Validate CRUD functions of Owner Tier And Owner
    Given user submits GET request to retrieve all Owner Tier records
    Then user validates if status code is 200
    When user creates request data with <level>, "<name>"
    And user submits POST request to owners_tiers endpoint
    Then user validates if status code is 201
    And user validates if the value of name in response is "Fiestco"
    Then user retrieves owner_tier_id from response
    And user submits PATCH request to owners_tiers endpoint
    Then user validates if status code is 200
    And user verifies updated name in response is "ABC"
    When user submits GET request to retrieve all Owner records
    Then user validates if status code is 200
    And user submits POST request to owners endpoint
    Then user validates if status code is 201
    And user validates if the value of name in response is "Library"
    Then user retrieves owner_id from response
    And user submits PATCH request to owners endpoint
    Then user validates if status code is 200
    And user verifies updated name in response is "Pest Control"
    And user deletes Owner record
    And user deletes Owner Tier record

    Examples:
      | level | name    |
      | 3     | Fiestco |























