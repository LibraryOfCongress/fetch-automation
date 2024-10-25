@picklist

Feature: Pick List Page Functionality Validation

  Background:
    Given user navigates to FETCH Homepage
    And user logs in as a tester1


  @regression @smoke
  Scenario: User should be able to verify Front-End Layout of Pick List Dashboard
    When user navigates to the Pick List Page
    Then user verifies the Pick List table column names

      | name              |
      | Job Number #      |
      | Building          |
      | # of Items in Job |
      | Status            |
      | Assigned User     |
      | Date Added        |
      | Date Completed    |

    And Rearrange dropdown is clickable
#    When user clicks on Pick List Job
#    Then user verifies Pick List number is displayed
#    When user clicks three dot menu next to Pick List Number
#    Then Edit option is displayed
#    And Delete Job option is displayed
#    And user verifies the Items in Job table column names
#
#      | name          |
#      | Barcode       |
#      | Tray Barcode  |
#      | Owner         |
#      | Size Class    |
#      | Item Location |
#
#    And Rearrange dropdown is clickable


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










