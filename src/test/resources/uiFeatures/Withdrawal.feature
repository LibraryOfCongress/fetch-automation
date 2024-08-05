@withdrawal

Feature: Withdrawal Page Functionality Validation

  Background:
    Given user navigates to the Withdrawal Page

  @FETCH-867 @FETCH-825 @regression
  Scenario: User should be able to validate Withdraw Dashboard and Job Detail UI Creation
    When user navigates to the Withdrawal Page
    When user clicks on Withdraw Job
    Then user verifies the Withdraw Job detail page is displayed
    Then user verifies Withdraw Job table column names

      | column                 |
      | Shelf Barcode          |
      | Tray Barcode           |
      | Barcode                |
      | Owner                  |
      | Item Status            |

    When user clicks three dot menu next to Job Number
    And user verifies "Edit" and "Delete Job" options are displayed
    When user clicks three dots menu next to the Item Barcode in the table
    Then user verifies "Remove Item" option is displayed


#  @FETCH-893 @FETCH-826
#  Scenario: User should be able to verify Ability to Create/Complete Withdraw Process
#    Given user completed Non-Tray Accession Job
#    And user completed Non-Tray Verification Job
