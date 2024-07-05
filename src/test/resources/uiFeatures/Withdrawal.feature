@withdrawal

Feature: Withdrawal Page Functionality Validation

  Background:
    Given user navigates to the Withdrawal Page

  @FETCH-867 @FETCH-825
  Scenario: User should be able to validate Withdraw Dashboard and Job Detail UI Creation
    When user clicks on Withdraw Job
    Then user verifies the Withdraw Job detail page is displayed
    Then user verifies table tab names

      | tab                    |
      | Shelf Barcode          |
      | Tray Barcode           |
      | Barcode                |
      | Owner                  |
      | Item Status            |

    When user clicks three dot menu next to Job Number
    And user verifies "Edit" and "Delete Job" options are displayed
    When user clicks three dots menu next to the Item Barcode in the table
    Then user verifies "Remove Item" option is displayed