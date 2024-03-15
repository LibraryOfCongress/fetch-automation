@regression @verification

Feature: Shelf Management Page Functionality Validation

  Background:
    Given user navigates to the Verification Page

  @FETCH-585 @FETCH-456
  Scenario: User should be able to validate Verification Job features for a Trayed Item
    When user clicks on Verification Job for a Trayed Item
    Then Tray container view is displayed
    And user scans a Tray
    And user clicks Enter Barcode button
    And user enters barcode and clicks Submit button
    Then user verifies the entered barcode is displayed
    When user selects one of the barcodes
    Then verify Enter Barcode button is changed to Edit Barcode
    And user clicks Edit Barcode button
    And user edits the barcode and clicks submit button
    And user deselects the edited barcode
    Then verify the updated barcode is displayed
    When user verifies all barcodes
    Then verify Next Tray button is activated
    And user clicks Next Tray button
    And user clicks on new tray on the modal
    Then the container is cleared out so a new tray can be scanned

  @FETCH-585 @FETCH-456
  Scenario: User should be able to validate Verification Job features for a Non-Trayed Item
    When user clicks on Verification Job for a Non-Trayed Item
    Then user verifies non-trayed items container view is displayed
    And user clicks Enter Barcode button
    And user enters barcode and clicks Submit button
    Then user verifies the entered barcode is displayed
    When user selects one of the barcodes
    Then verify Enter Barcode button is changed to Edit Barcode
    And user clicks Edit Barcode button
    And user edits the barcode and clicks submit button
    And user deselects the edited barcode
    Then verify the updated barcode is displayed
    When user verifies all the barcodes
    Then user verifies Complete Job button is activated



