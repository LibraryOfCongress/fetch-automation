@withdrawal

Feature: Withdrawal Page Functionality Validation

  Background:
    Given user navigates to the Withdrawal Page
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
    When user clicks Create Withdraw Job
    Then user verifies "A Withdraw Job has been successfully created." alert msg
    And user verifies a Withdraw Job is created


  @regression
  Scenario: User should be able to assign a User to a Withdraw Job
    When user clicks on the created Withdraw Job
    And user clicks three dot menu next to Job Number
    Then user clicks Edit
    When user selects User from dropdown
    Then user clicks Save Edits button
    And user verifies "The job has been updated." alert msg
    And user verifies the assigned user has been updated


  @regression @smoke
  Scenario: User should be able to delete a Withdraw Job
    When user clicks on the created Withdraw Job
    And user clicks three dot menu next to Job Number
    And user clicks Delete Job
    And user confirms delete job action
    Then user verifies "The Withdraw Job has been canceled." alert msg


  @FETCH-867 @FETCH-825
  Scenario: User should be able to validate Withdraw Dashboard and Job Detail UI
    When user clicks on Withdraw Job
    Then user verifies the Withdraw Job detail page is displayed
    When user clicks three dot menu next to Job Number
    And user verifies "Edit" and "Delete Job" options are displayed
#    When user clicks three dots menu next to the Item Barcode in the table
#    Then user verifies "Remove Item" option is displayed



