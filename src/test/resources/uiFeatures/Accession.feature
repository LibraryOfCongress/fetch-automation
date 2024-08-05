@accession

Feature: Accession Page Functionality Validation

  Background:
    Given user navigates to the Accession Page


  @FETCH-314 @FETCH-249 @regression @smoke
  Scenario: User should be able to verify Front-End Layout for Accession Job of Trayed Items
    When user clicks Start Accession button
    And user selects Trayed Accession
    Then user verifies required and optional fields on Start New Accession modal

      | fieldname             |
      | Owner (Required)      |
      | Media Type (Optional) |

    And Owner dropdown is clickable
    And Media Type field is clickable
    And back button is clickable
    And cancel button is enabled
    And submit button is disabled
    Then user is able to return to the Start Accession single action square screen


  @FETCH-314 @FETCH-249 @regression @smoke
  Scenario: User should be able to verify Front-End Layout for Accession Job of Non-Trayed Items
    When user clicks Start Accession button
    And user selects Non-Tray Accession
    Then user verifies required and optional fields on Start New Accession modal

      | fieldname                 |
      | Owner (Required)          |
      | Container Size (Optional) |
      | Media Type (Optional)     |

    And Owner dropdown is clickable
    And Container Size dropdown is clickable
    And Media Type field is clickable
    And back button is clickable
    And cancel button is enabled
    And submit button is disabled
    Then user is able to return to the Start Accession single action square screen


  @FETCH-904 @FETCH-773 @regression @smoke
  Scenario: User should be able to create a new Accession Job when required field is selected
    When user clicks Start Accession button
    And user selects Trayed Accession
    And user selects all required fields
    Then submit button is enabled and clickable
    And user clicks cancel button
    When user clicks Start Accession button
    And user selects Non-Tray Accession
    And user selects all required fields
    Then submit button is enabled


  @FETCH-904 @FETCH-773 @negative
  Scenario: User should not be able to create a new Accession Job if required field is not selected
    When user clicks Start Accession button
    And user selects Trayed Accession
    And user selects Media Type
    Then submit button is disabled
    And user clicks cancel button
    When user clicks Start Accession button
    And user selects Non-Tray Accession
    And user selects Container Size
    And user selects Media Type
    Then submit button is disabled


  @FETCH-904 @FETCH-773 @regression @smoke
  Scenario: User should be able to see a New Scanned Item as a First Item in the table
    When user logs in as a tester
    And user switches on Toggle Barcode Scan
    When user clicks Start Accession button
    And user selects Trayed Accession
    And user selects all required fields
    Then user clicks submit button
    Then user scans Barcode
    And user enters barcode by scanning
    And user enters a second barcode by scanning
    Then user verifies that new added barcode is displayed first in the table
    When user disables Toggle Barcode Scan
    And user clicks three dot menu next to Accession Job Number
    And user clicks Cancel Job
    Then user confirms cancellation


  @FETCH-358 @FETCH-194 @search @regression @smoke
  Scenario: User should be able to verify dropdown search functionality
    When user clicks Start Accession button
    And user selects Trayed Accession
    And user types "<search_query>" in the Owner dropdown search field
    And Owner dropdown should display options related to search query
    Then user selects an option from the Owner dropdown
    And user types "<search_query>" in the Media Type dropdown search field
    And Media Type dropdown should display options related to search query
    Then user selects an option from the Media Type dropdown


  @FETCH-545 @FETCH-455 @add_tray
  Scenario: User should be able to Add Tray to Trayed Assession Job
    When user logs in as a tester
    And user switches on Toggle Barcode Scan
    When user clicks Start Accession button
    And user selects Trayed Accession
    And user selects all required fields
    And user selects Media Type
    And user clicks submit button
    And user scans Barcode
    And user enters barcode by scanning
    And user disables Toggle Barcode Scan
    When user selects one of the barcodes in the table
    Then user verifies Enter Barcode button is changed to Edit Barcode
    And user clicks Edit Barcode button
    Then verify new modal allowing to edit the barcode is displayed
    And user edits the barcode and clicks submit button
    Then verify Add Tray button is activated
    And user clicks Add Tray button
    Then verify new modal Select Tray is displayed
    And user clicks add tray on the modal
    And the container is cleared out so a new tray can be scanned
    When user clicks three dot menu next to Accession Job Number
    And user clicks Cancel Job
    Then user confirms cancellation


  @FETCH-545 @FETCH-455 @FETCH-627 @FETCH-582 @nontrayed_accession @regression @smoke
  Scenario: User should be able to go through the Accession workflow process from start to finish for a Non-Trayed Job
    When user clicks Start Accession button
    And user selects Non-Tray Accession
    And user selects all fields
    And user clicks submit button
    Then user verifies "An Accession Job has successfully been created." alert msg
    When user clicks three dot menu next to Accession Job Number
    And user clicks Edit
    And user edits Container Size
    And user edits Media Type
    And user clicks Save Edits
    Then user verifies "The job has been updated." alert msg
    When user clicks Enter Barcode button
    And user enters barcode and clicks Submit button
    When user clicks Enter Barcode button
    Then user enters second barcode and clicks Submit button
    When user selects one of the barcodes in the table
    Then user verifies Enter Barcode button is changed to Edit Barcode
    When user clicks Edit Barcode button
    And user edits the barcode and clicks submit button
    Then user verifies "The item has been updated." alert msg
    Then user verifies that edited barcode is displayed
    When user selects one of the barcodes in the table
    And user clicks Delete
    And user clicks Confirm
    Then user verifies "The selected item(s) has been removed." alert msg
    When user clicks Pause Job button
    Then user verifies "Job Status has been updated to: Paused" alert msg
    When user clicks Resume Job button
    Then user verifies "Job Status has been updated to: Running" alert msg
    And user clicks three dot menu next to Accession Job Number
    And user clicks Cancel Job
    Then user verifies warning message
    And user confirms cancellation
#    When user clicks Complete Job button
#    And user clicks Complete
#    Then user verifies "The Job has been completed and moved for verification."


  @FETCH-627 @FETCH-582 @trayed_accession @regression @smoke
  Scenario: User should be able to go through the Accession workflow process from start to finish for a Trayed Job
    When user logs in as a tester
    And user switches on Toggle Barcode Scan
    When user clicks Start Accession button
    And user selects Trayed Accession
    And user selects all required fields
    Then user selects Media Type
    And user clicks submit button
    Then user verifies "An Accession Job has successfully been created." alert msg
    And user scans Barcode
    When user enters barcode by scanning
    And user verifies that scanned barcode is displayed
    Then user disables Toggle Barcode Scan
    When user clicks Enter Barcode button
    Then verify a modal with manual barcode entry is displayed
    And user enters barcode and clicks Submit button
    When user clicks three dot menu next to Accession Job Number
    And user clicks Edit
    And user edits Container Size
    And user edits Media Type
    And user clicks Save Edits
    Then user verifies "The tray has been updated." alert msg
    When user selects one of the barcodes in the table
    Then user verifies Enter Barcode button is changed to Edit Barcode
    When user clicks Edit Barcode button
    And user edits the barcode and clicks submit button
    Then user verifies "The item has been updated." alert msg
    When user selects one of the barcodes in the table
    And user clicks Delete
    And user clicks Confirm
    Then user verifies "The selected item(s) has been removed." alert msg
    When user clicks Pause Job button
    Then user verifies "Job Status has been updated to: Paused" alert msg
    When user clicks Resume Job button
    Then user verifies "Job Status has been updated to: Running" alert msg
    And user clicks three dot menu next to Accession Job Number
    And user clicks Cancel Job
    Then user verifies warning message
    And user confirms cancellation
#    When user clicks Complete Job button
#    And user clicks Complete
#    Then user verifies "The Job has been completed and moved for verification."


  @FETCH-586 @FETCH-474 @print
  Scenario: User should be able to validate Accession Job Batch Sheet Template Creation
    When user clicks Start Accession button
    And user selects Non-Tray Accession
    And user selects all fields
    And user clicks submit button
    And user clicks Enter Barcode button
    Then user enters item barcode
    When user clicks Complete Job button
    And user clicks Complete&Print button
    Then user is able to see a print window with a batch report


  @FETCH-917 @FETCH-735 @delete_tray @regression
  Scenario: User should be able to cancel an Accession Job
    When user logs in as a tester
    And user switches on Toggle Barcode Scan
    When user clicks Start Accession button
    And user selects Trayed Accession
    And user selects all required fields
    Then user selects Media Type
    And user clicks submit button
    Then user verifies "An Accession Job has successfully been created." alert msg
    And user scans Barcode
    When user enters barcode by scanning
    Then user disables Toggle Barcode Scan
    When user clicks three dot menu next to Accession Job Number
    When user clicks Edit Tray Barcode
    And user edits Tray Barcode
    Then user submits the change
    Then user verifies "The tray has been updated." alert msg
    And user clicks three dot menu next to Accession Job Number
    Then user clicks Delete Tray
    And user verifies delete tray warning message
    Then user confirms delete action


  @FETCH-917 @FETCH-735 @cancel_accession @regression
  Scenario: User should be able to cancel an Accession Job
    When user selects an Accession Job
    And user clicks three dot menu next to Accession Job Number
    And user clicks Cancel Job
    Then user verifies warning message
    And user confirms cancellation
















































