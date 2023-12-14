@FETCH-315 @FETCH-245
Feature: Accession Jobs API


  Scenario: Create Accession Job Record
    Given user creates the request data
    And user submits POST request to Accession Jobs API endpoint
    Then user validates that status code is 201
    And user retrieves accessionJobID from response


  Scenario: Get Accession Job Details
    Given user submits GET request with accessionJobID to retrieve an Accession Job details
    Then user validates the status code is 200


  Scenario: Delete Accession Job Record
    Given user submits DELETE request with accessionJobID to Accession Job API endpoint
    Then user validates status code is 204


  Scenario: Get Accession Job List view
    Given user submits GET request to retrieve all Accession Job records
    Then user validates the status code is 200


