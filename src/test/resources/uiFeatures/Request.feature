@request

Feature: Request Page Functionality Validation

  Background:
    Given user navigates to the Request Page


  @FETCH-748 @FETCH-606
  Scenario: User should be able to verify Front-End Layout of Request Dashboard
#    When user clicks Create button
#    Then user verifies the dropdown options
#
#      | option                    |
#      | Add to Pick List          |
#      | Create a Pick List        |
#      | Create Manual Requests    |
#      | Import Requests from File |
#
#    And user selects Create Manual Requests option
#    Then request job creation modal is displayed
#    When user enters an Item Barcode from an existing Shelving Job
#    And user enters Requestor Name
#    And user selects Request Type
#    And user selects Delivery Location
#    Then submit button is enabled
#    And user clicks submit button
    Then user clicks on created Request
    And user verifies Request details on Overlay Slide


  @FETCH-809 @FETCH-566
  Scenario: User should be able to create a Pick List from Request Page
    When user clicks Create button
    And user selects Create a Pick List option
    When user selects Building from dropdown
    And user clicks Submit
    Then user verifies Requests with checkboxes are displayed
    When user selects Requests
    And user clicks Create Pick List
    Then user verifies the Pick List is created
    When user clicks the alert link
    Then user is able to see the Pick List dashboard

  @FETCH-809 @FETCH-566
  Scenario: User should be able to Add to Pick List from Request Page
    When user clicks Create button
    And user selects Add to Pick List option
    When user selects Building from dropdown
    And user selects Pick List from dropdown
    And user clicks Submit
    Then user verifies Requests with checkboxes are displayed
    When user selects Requests
    And user clicks Add to Pick List
    Then user verifies items are added to the Pick List
    When user clicks the alert link
    Then user is able to see the Pick List dashboard







