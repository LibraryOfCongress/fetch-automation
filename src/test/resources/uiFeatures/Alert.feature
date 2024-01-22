
@FETCH-457 @FETCH-330 @alert
Feature: Alert Notification Validation
  Background:
    Given user navigates to the testing link

  Scenario: Verify UI Alert Notification
    When user clicks on the Show Generic Alert button
    Then user verifies UI alert on top of the screen is visible
    And user is able to click X to cancel alert
    When user clicks on the Show Persistent Alert button
    Then user verifies alert popup is visible
    And user is able to click cancel button
