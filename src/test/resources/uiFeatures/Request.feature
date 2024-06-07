@request

Feature: Request Page Functionality Validation

  Background:
    Given user navigates to the Request Page


  @FETCH-748 @FETCH-606
  Scenario: User should be able to verify Front-End Layout of Request Dashboard
    When user clicks Create button
    Then user verifies the dropdown options

      | option                    |
      | Add to Pick List          |
      | Create a Pick List        |
      | Create Manual Requests    |
      | Import Requests from File |

    And user selects Create Manual Requests option
    Then request job creation modal is displayed
    When user enters an Item Barcode from an existing Shelving Job
    And user enters Requestor Name
    And user selects Request Type
    And user selects Delivery Location
    Then submit button is enabled
    And user clicks submit button
    Then user clicks on created Request
    And user verifies Request details on Overlay Slide






