@picklist

Feature: Pick List Page Functionality Validation

  Background:
    Given user navigates to FETCH Homepage
    And user logs in as a tester1


  @regression
  Scenario: User should be able to verify Front-End Layout of Pick List Dashboard
    When user navigates to the Pick List Page
    Then user verifies the Pick List table column names

      | name              |
      | Job Number        |
      | Building          |
      | # of Items in Job |
      | Status            |
      | Assigned User     |
      | Date Added        |
      | Last Updated      |

    And Rearrange dropdown is clickable


  @FETCH-809 @FETCH-566 @regression @smoke
  Scenario: User should be able to create a Pick List
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
    When user enters shelved Item Barcode
    And user enters Request ID
    And user enters Requester Name
    And user selects Priority
    And user selects Request Type
    And user selects Delivery Location
    Then submit button is enabled
    And user clicks submit button
    And user verifies "Successfully created the request." alert msg
    When user clicks Create button
    And user selects Create a Pick List option
    When user selects Building from dropdown
    And user clicks Submit
    Then user verifies options with checkboxes are displayed
    When user selects Requests
    And user clicks Create Pick List
    Then user verifies the Pick List is created
    When user clicks the alert link
    Then user is able to see the Pick List dashboard


  @FETCH-809 @FETCH-566 @regression
  Scenario: User should be able to Add to Pick List from Request Page
    When user navigates to the Request Page
#    Then user clicks Accession on side navigation menu
#    When user completes Non-Tray Accession Job
#    When user navigates to the Verification Page
#    And user navigates to the verification job
#    And user saves Verification Job number
#    Then user verifies item barcode
#    When user clicks Complete Job button
#    And user clicks Complete
#    Then user verifies "The Job has been completed." msg
#    When user clicks Shelving on side navigation menu
#    When user completes a Shelving Job
#    When user clicks Request on side navigation menu
#    When user clicks Create button
#    And user selects Create Manual Requests option
#    Then request job creation modal is displayed
#    When user enters shelved Item Barcode
#    And user enters Request ID
#    And user enters Requester Name
#    And user selects Priority
#    And user selects Request Type
#    And user selects Delivery Location
#    Then submit button is enabled
#    And user clicks submit button
#    And user verifies "Successfully created the request." alert msg
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


  Scenario: User should be able to complete a Pick List
    When user creates a Pick List job
    Then user switches on Toggle Barcode Scan
    And user clicks Retrieve Pick List
    When user scans a Pick List Container
    Then user verifies the item is retrieved
    When user clicks Complete Job
    And user clicks Complete
    Then user verifies "The Pick List Job has been completed." alert msg


  @assigned_user @smoke
  Scenario:  User should be able to change Assigned User within a Pick List Job
    When user navigates to the Pick List Page
    And user clicks on Pick List Job
    Then user verifies job number is displayed
    And user clicks three dot menu next to Job Number
    Then Edit option is displayed
    When user clicks Edit
    Then Assign User dropdown is clickable
    And user selects User from dropdown
    And Save Edits button is clickable
    And Cancel edits button is clickable
    Then user clicks Save Edits button
    And user verifies "The job has been updated." alert msg
    And user verifies the assigned user has been updated


  @FETCH-1080 @FETCH-838 @print
  Scenario: User should be able to verify Print Options for Pick List Job Summary
    When user navigates to the Pick List Page
    Then user clicks on Pick List Job
    When user clicks three dot menu next to Job Number
    And user clicks Print Job
    Then user is able to see a print window with a batch report














