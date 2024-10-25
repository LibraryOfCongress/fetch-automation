@refile

Feature: Refile Page Functionality Validation

  Background:
    Given user navigates to FETCH Homepage
    And user logs in as a tester1


  @FETCH-834 @FETCH-763 @regression @smoke
  Scenario: User should be able to verify Front-End Layout of Refile Dashboard
    When user navigates to the Refile Page
    Then user verifies Refile Job table is displayed
    And user verifies Refile Job table column names

      | name               |
      | Job ID #           |
      | # of Items         |
      | # of Items Shelved |
      | Assigned User      |
      | Date Created       |
      | Last Updated       |

    When user clicks Refile Queue
    Then user verifies Refile Queue table is displayed
    And user verifies Refile Queue table column names

      | name           |
      | Item Location  |
      | Container Type |
      | Media Type     |
      | Item Barcode   |
      | Owner          |
      | Container Size |

    When user clicks Create button
    Then user verifies the dropdown options

      | option                 |
      | Add Item to Queue      |
      | Add Item to Refile Job |
      | Create Refile Job      |


  @FETCH-1059 @FETCH-885
  Scenario: User should be able to verify a Tray Barcode Addition to Refile Modal
    When user clicks Accession on side navigation menu
    And user switches on Toggle Barcode Scan
    When user completes a Trayed Accession Job
    And user navigates to the Verification Page
    And user switches on Toggle Barcode Scan
    Then user completes a Tray Verification Job
    And user saves Trayed Item barcode
    When user clicks Complete Job button
    And user clicks Complete
    Then user verifies "The Job has been completed." msg
    And user swithes off Toggle Barcode Scan
    When user completes a Shelving Job
    When user clicks Request on side navigation menu
    When user clicks Create button
    And user selects Create Manual Requests option
    Then request job creation modal is displayed
    When user enters a Trayed Item Barcode from an existing Shelving Job
    And user enters Request ID
    And user enters Requester Name
    And user selects Priority
    And user selects Request Type
    And user selects Delivery Location
    Then submit button is enabled
    And user clicks submit button
    And user verifies "Successfully created the request." alert msg
    When user creates a Pick List job
    Then user switches on Toggle Barcode Scan
    And user clicks Retrieve Pick List
    Then user saves an item barcode
    When user scans a Pick List Container
    Then user verifies the item is retrieved
    When user clicks Complete Job
    And user clicks Complete
    Then user verifies "The Pick List Job has been completed." alert msg
    When user navigates to the Refile Page
    And user clicks Create button
    Then user selects Add Item to Queue option
    When user switches on Toggle Barcode Scan
    Then user scans an Item Barcode from a completed Pick List job
    When user clicks Create button
    And user selects Create Refile Job option
    When user selects Building from dropdown
    And user clicks Submit
    Then user verifies Requests with checkboxes are displayed
    When user selects Requests
    And user clicks Create Refile Job
    Then user verifies the Refile job is created
    When user clicks the alert link
    And user verifies Tray Barcode is displayed
    And user clicks on item in the table
    Then user verifies scan modal is displayed
    And user verifies the information on the scan modal

  @assigned_user @smoke
  Scenario:  User should be able to change Assigned User within a Refile Job
    When user navigates to the Refile Page
    And user clicks on Refile Job
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

