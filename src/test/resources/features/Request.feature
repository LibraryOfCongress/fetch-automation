@request

Feature: Request Page Functionality Validation

  Background:
    Given user navigates to FETCH Homepage
    And user logs in as a tester1


  @FETCH-748 @FETCH-606 @regression @smoke
  Scenario: User should be able to verify Front-End Layout of Request Dashboard
    When user navigates to the Request Page
    And user clicks Create button
    Then user verifies the dropdown options

      | option                    |
      | Add to Pick List          |
      | Create a Pick List        |
      | Create Manual Requests    |
      | Import Requests from File |

    And user selects Create Manual Requests option
    Then request job creation modal is displayed


  @FETCH-903 @FETCH-802 @positive @regression
  Scenario: User should be able to verify Required Fields for Manual Request
    When user navigates to the Request Page
    And user clicks Create button
    And user selects Create Manual Requests option
    Then request job creation modal is displayed
    When user enters an item barcode
    And user enters Request ID
    Then submit button is enabled


  @FETCH-903 @FETCH-802 @negative
  Scenario: User should be able to verify Required Fields for Manual Request
    When user navigates to the Request Page
    And user clicks Create button
    And user selects Create Manual Requests option
    Then request job creation modal is displayed
    And user enters Requester Name
    And user selects Priority
    And user selects Request Type
    And user selects Delivery Location
    Then request submit button is disabled


  @positive @manual_request @smoke
  Scenario: User should be able to create a Manual Request
    When user clicks Accession on side navigation menu
    And user completes a Non-Tray Accession Job
    When user navigates to the Verification Page
    And user navigates to the verification job
    And user saves Verification Job number
    Then user verifies item barcode
    When user clicks Complete Job button
    And user clicks Complete
    Then user verifies "The Job has been completed." msg
    When user clicks Shelving on side navigation menu
    When user completes a Shelving Job
    When user clicks Request on side navigation menu
    When user clicks Create button
    And user selects Create Manual Requests option
    Then request job creation modal is displayed
    When user enters an Item Barcode from an existing Shelving Job
    And user enters Request ID
    And user enters Requester Name
    And user selects Priority
    And user selects Request Type
    And user selects Delivery Location
    Then submit button is enabled
    And user clicks submit button
    And user verifies "Successfully created the request." alert msg
    Then user clicks on created Request
    And user verifies Request details on Overlay Slide


  @FETCH-903 @FETCH-802 @negative
  Scenario: User should be able to verify the Duplicate Item is not Added to Requests
    When user navigates to the Request Page
    And user has an Item present in request table
    And user clicks Create button
    And user selects Create Manual Requests option
    Then request job creation modal is displayed
    When user enters an existing in request table Item barcode
    And user enters Request ID
    Then submit button is enabled
    And user clicks submit button
    Then user verifies item already requested error msg


  @negative @manual_request
  Scenario: User should be able to verify Request Is Not Created When Incorrect Item barcode Is Entered
    When user navigates to the Request Page
    And user clicks Create button
    And user selects Create Manual Requests option
    Then request job creation modal is displayed
    When user enters an incorrect Item Barcode
    And user enters Request ID
    Then submit button is enabled
    And user clicks submit button
    Then user verifies "Barcode value 1234567890 not found" alert msg


  @FETCH-809 @FETCH-566 @regression @smoke
  Scenario: User should be able to create a Pick List from Request Page
    When user navigates to the Request Page
    And user clicks Create button
    And user selects Create a Pick List option
    When user selects Building from dropdown
    And user clicks Submit
    Then user verifies options with checkboxes are displayed
    When user selects Requests
    And user clicks Create Pick List
    Then user verifies the Pick List is created
    When user clicks the alert link
    Then user is able to see the Pick List dashboard


  @date_created
  Scenario: User should be able to verify Pick List job created date
    When user navigates to the Request Page
    And user clicks Create button
    And user selects Create a Pick List option
    When user selects Building from dropdown
    And user clicks Submit
    Then user verifies options with checkboxes are displayed
    When user selects Requests
    And user clicks Create Pick List
    Then user verifies the Pick List is created
    When user clicks the alert link
    Then user is able to see the Pick List dashboard
    And user verifies date created


  @FETCH-809 @FETCH-566 @regression
  Scenario: User should be able to Add to Pick List from Request Page
    When user navigates to the Request Page
    Then user clicks Accession on side navigation menu
    When user completes a Non-Tray Accession Job
    When user navigates to the Verification Page
    And user navigates to the verification job
    And user saves Verification Job number
    Then user verifies item barcode
    When user clicks Complete Job button
    And user clicks Complete
    Then user verifies "The Job has been completed." msg
    When user clicks Shelving on side navigation menu
    When user completes a Shelving Job
    When user clicks Request on side navigation menu
    When user clicks Create button
    And user selects Create Manual Requests option
    Then request job creation modal is displayed
    When user enters an Item Barcode from an existing Shelving Job
    And user enters Request ID
    And user enters Requester Name
    And user selects Priority
    And user selects Request Type
    And user selects Delivery Location
    Then submit button is enabled
    And user clicks submit button
    And user verifies "Successfully created the request." alert msg
    When user clicks Create button
    And user selects Add to Pick List option
    When user selects Building from dropdown
    And user selects Pick List from dropdown
    And user clicks Submit
    Then user verifies options with checkboxes are displayed
    When user selects Requests
    And user clicks Add to Pick List
    Then user verifies items are added to the Pick List
    When user clicks the alert link
    Then user is able to see the Pick List dashboard









