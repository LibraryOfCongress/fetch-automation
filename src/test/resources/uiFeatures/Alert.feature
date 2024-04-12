@alert

Feature: Alert Notification Validation
  Background:
    Given user navigates to the testing link

  @FETCH-457 @FETCH-330
  Scenario: User is able to verify UI Alert Notification
    When user clicks on the Show Generic Alert button
    Then user verifies UI alert on top of the screen is visible
    And user is able to click X to cancel alert
    When user clicks on the Show Persistent Alert button
    Then user verifies alert popup is visible
    And user is able to click cancel button

    @accessionJobAlerts
    Scenario: User is able to validate UI Alert Notifications on Accession Job Page
      When user navigates to Accession Job link
      And user clicks Enter Barcode button
      And user enters "001123" barcode and clicks Submit button
      Then user verifies "The item has been added." alert msg
      When user selects one of the barcodes in the table
      And user clicks Delete
      And user clicks Confirm
      Then user verifies "The selected item(s) has been removed." alert msg

