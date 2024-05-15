@regression @verification

Feature: Verification Page Functionality Validation

  Background:
    Given user navigates to the Verification Page

  @FETCH-585 @FETCH-456 @trayed
  Scenario: User should be able to validate Verification Job features for a Trayed Item
    When user clicks on Verification Job for a Trayed Item
    Then Tray container view is displayed
    And user scans a Tray
    And user clicks Enter Barcode button
    And user enters barcode and clicks Submit button
    Then user verifies the entered barcode is displayed
    When user selects the barcode
    Then verify Enter Barcode button is changed to Edit Barcode
    And user clicks Edit Barcode button
    And user edits the barcode and clicks submit button
    Then verify the edited barcode is displayed under Scanned Items
    And verify Next Tray button is activated
    And user clicks Next Tray button
    And user clicks on new tray on the modal
    Then the container is cleared out so a new tray can be scanned

  @FETCH-585 @FETCH-456 @nontrayed
  Scenario: User should be able to validate Verification Job features for a Non-Trayed Item
    When user clicks on Verification Job for a Non-Trayed Item
    Then user verifies non-trayed items container view is displayed
    And user clicks Enter Barcode button
    And user enters barcode and clicks Submit button
    Then verify the entered barcode is displayed under Scanned Items
    When user selects the barcode
    Then verify Enter Barcode button is changed to Edit Barcode
    And user clicks Edit Barcode button
    And user edits the barcode and clicks submit button
    Then verify the edited barcode is displayed under Scanned Items
    When user verifies all the barcodes
    Then user verifies Complete Job button is activated
    And user selects the barcode
    And user clicks Delete
    And user clicks Confirm


  @FETCH-569 @FETCH-475
  Scenario: User should be able to validate Verification Job Batch Sheet Template
    When user navigates to the verification job link
    And user clicks Complete Job button
    And user clicks Complete&Print button
    Then user is able to see a print window with a batch report


  @FETCH-627 @FETCH-582 @nontrayed_verification
  Scenario: User should be able to go through the Verification workflow process from start to finish for a Non-Trayed Job
    When user clicks most recent Verification Job for a Non-Trayed Item
    And user clicks three dot menu next to Job Number
    And user clicks Edit
    And user edits Owner field
    And user edits Container Size field
    And user edits Media Type field
    And user clicks Save Edits
    When user clicks Enter Barcode button
    And user enters the barcode and clicks Submit button
    Then user verifies "The job has been updated." alert msg
    And verify the entered barcode is displayed
    When user selects the barcode
    Then verify Enter Barcode button is changed to Edit Barcode
    And user clicks Edit Barcode button
    And user edits the barcode and clicks Submit button
    Then user verifies "The item has been updated." alert msg
    And verify the edited barcode is displayed
    And user verifies all the barcodes
    Then user verifies "The item has been validated." alert msg
    When user selects the barcode
    And user clicks Delete
    And user clicks Confirm
    Then user verifies "The selected item(s) has been removed." alert msg
    When user clicks Pause Job button
    Then user verifies "Job Status has been updated to: Paused" alert msg
    When user clicks Resume Job button
    Then user verifies "Job Status has been updated to: Running" alert msg
    When user clicks Complete Job button
    And user clicks Complete
    Then user verifies "The Job has been completed." msg


  @FETCH-627 @FETCH-582 @smoke @trayed_verification
  Scenario: User should be able to go through the Verification workflow process from start to finish for a Trayed Job
    When user clicks most recent Verification Job for a Trayed Item
    And user scans Tray Barcode
    And user clicks three dot menu next to Job Number
    And user clicks Edit
    And user edits Container Size
    And user edits Media Type
    And user clicks Save Edits
    Then user verifies "The job has been updated." alert msg
    When user clicks Enter Barcode button
    And user enters the barcode and clicks Submit button
    And verify the entered barcode is displayed
    When user selects the barcode
    Then verify Enter Barcode button is changed to Edit Barcode
    And user clicks Edit Barcode button
    And user edits the barcode and clicks Submit button
    Then user verifies "The item has been added." alert msg
    And verify the edited barcode is displayed
    And user verifies the barcode
    Then user verifies "The item has been validated." alert msg
    When user selects the barcode
    And user clicks Delete
    And user clicks Confirm
    Then user verifies "The selected item(s) has been removed." alert msg
    When user clicks Pause Job button
    Then user verifies "Job Status has been updated to: Paused" alert msg
    When user clicks Resume Job button
    Then user verifies "Job Status has been updated to: Running" alert msg
    When user clicks Complete Job button
    And user clicks Complete
    Then user verifies "The Job has been completed." msg
















