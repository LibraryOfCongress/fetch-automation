@withdrawal

Feature: Withdrawal Page Functionality Validation

  Background:
    Given user navigates to FETCH Homepage
    And user logs in as a tester1


  @FETCH-867 @FETCH-825 @regression
  Scenario: User should be able to verify Withdrawal Dashboard
    When user navigates to the Withdrawal Page
    Then user verifies Withdrawal dashboard column names

      | column         |
      | Job ID #       |
      | # of Items     |
      | Status         |
      | Date Created   |
      | Completed Date |


  @regression @smoke
  Scenario: User should be able to create a Withdraw Job
    When user navigates to the Withdrawal Page
    And user clicks Create Withdraw Job
    Then user verifies "A Withdraw Job has been successfully created." alert msg
    And user verifies a Withdraw Job is created


  @assigned_user @smoke
  Scenario:  User should be able to change Assigned User within a Withdraw Job
    When user navigates to the Withdrawal Page
    And user clicks on the created Withdraw Job
    And user clicks three dot menu next to Job Number
    Then user clicks Edit
    When user selects User from dropdown
    And Save Edits button is clickable
    And Cancel edits button is clickable
    Then user clicks Save Edits button
    And user verifies "The job has been updated." alert msg
    And user verifies the assigned user has been updated


  @regression @smoke
  Scenario: User should be able to delete a Withdraw Job
    When user navigates to the Withdrawal Page
    And user clicks on the created Withdraw Job
    And user clicks three dot menu next to Job Number
    And user clicks Delete Job
    And user confirms delete job action
    Then user verifies "The Withdraw Job has been canceled." alert msg


  @FETCH-867 @FETCH-825
  Scenario: User should be able to validate Withdraw Dashboard and Job Detail UI
    When user navigates to the Withdrawal Page
    And user clicks on Withdraw Job
    Then user verifies the Withdraw Job detail page is displayed
    When user clicks three dot menu next to Job Number
    And user verifies "Edit" and "Delete Job" options are displayed
#    When user clicks three dots menu next to the Item Barcode in the table
#    Then user verifies "Remove Item" option is displayed


  @date_created
  Scenario: User should be able to verify Withdraw job created date
    When user navigates to the Withdrawal Page
    And user clicks Create Withdraw Job
    Then user verifies "A Withdraw Job has been successfully created." alert msg
    And user verifies a Withdraw Job is created
    And user verifies date created




