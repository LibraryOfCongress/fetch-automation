@regression @accession

Feature: Accession Page Functionality Validation

  Background:
    Given user navigates to Item Management Page

  @FETCH-314 @FETCH-249
  Scenario: User should be able to verify Front-End Layout for Accession Job of Trayed Items
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
    And user selects Trayed Accession
    Then user verifies required and optional fields on Start New Accession modal

      | fieldname             |
      | Owner (Required)      |
      | Media Type (Optional) |

    And Owner dropdown is clickable
    And Container Size dropdown is clickable
    And Media Type field is clickable
    And back button is clickable
    And cancel button is enabled
    And submit button is disabled
    Then user is able to return to the Start Accession single action square screen


  @FETCH-314 @FETCH-249
  Scenario: User should be able to verify Front-End Layout for Accession Job of Non-Trayed Items
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
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

  @positive
  Scenario: User should be able to create a new Accession Job when required field is selected
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
    And user selects Trayed Accession
    And user selects all required fields
    Then submit button is enabled and clickable
    And user clicks cancel button
    When user clicks Start Accession button
    And user selects Non-Tray Accession
    And user selects all required fields
    Then submit button is enabled

  @negative
  Scenario: User should not be able to create a new Accession Job if required field is not selected
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
    And user selects Trayed Accession
    And user selects an option from the Media Type dropdown
    Then submit button is disabled
    And user clicks cancel button
    When user clicks Start Accession button
    And user selects Non-Tray Accession
    And user selects an option from the Container Size dropdown
    And user selects an option from the Media Type dropdown
    Then submit button is disabled


  @FETCH-358 @FETCH-194 @search @trayed
  Scenario: User should be able to verify dropdown search functionality
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
    And user selects Trayed Accession
    And user types "<search_query>" in the Owner dropdown search field
    And Owner dropdown should display options related to "<search_query>"
    Then user selects an option from the Owner dropdown
    And user types "<search_query>" in the Media Type dropdown search field
    And Media Type dropdown should display options related to "<search_query>"
    Then user selects an option from the Media Type dropdown


  @FETCH-545 @FETCH-455 @trayed @wip
  Scenario: User should be able to verify Accession Process for Trayed Item
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
    And user selects Trayed Accession
    And user selects all required fields
    And user clicks submit button
    And user scans Barcode
    Then verify that Enter Barcode button is enabled
    And user clicks Enter Barcode button
    Then verify a modal with manual barcode entry is displayed
    And user enters barcode and clicks Submit button
    Then verify the entered barcode is displayed under Scanned Items
    When user selects one of the barcodes in the table
    Then verify Enter Barcode button is changed to Edit Barcode
    And user clicks Edit Barcode button
    Then verify new modal allowing to edit the barcode is displayed
    And user edits the barcode and clicks submit button
    Then verify the edited barcode is displayed under Scanned Items
    Then verify Add Tray button is activated
    And user clicks Add Tray button
    Then verify new modal Select Tray is displayed
    And user clicks add tray on the modal
    Then verify "Successfully added a tray to the job" alert message is displayed
    And the container is cleared out so a new tray can be scanned


  @FETCH-545 @FETCH-455 @nontrayed
  Scenario: User should be able to verify Accession Process for Non-Trayed Item
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
    And user selects Non-Tray Accession
    And user selects all fields
    And user clicks submit button
    And user clicks Enter Barcode button
    Then verify a modal with manual barcode entry is displayed
    And user enters barcode and clicks Submit button
    Then verify the entered barcode is displayed under Scanned Items
    When user selects one of the barcodes in the table
    Then verify Enter Barcode button is changed to Edit Barcode
    And user clicks Edit Barcode button
    Then verify new modal allowing to edit the barcode is displayed
    And user edits the barcode and clicks submit button
    Then verify the edited barcode is displayed under Scanned Items
    And user selects one of the barcodes in the table
    And user clicks Delete
    And user clicks Confirm


  @FETCH-586 @FETCH-474
  Scenario: User should be able to validate Accession Job Batch Sheet Template Creation
    When user navigates to the accession job link
    And user clicks Complete Job button
    And user clicks Complete&Print button
    Then user is able to see a print window with a batch report

  @FETCH-627 @FETCH-582 @trayed @wip
  Scenario: User should be able to go through the Trayed Accession workflow process from Start to Finish
    When user clicks Start Accession button
    And user selects Trayed Accession
    And user selects all required fields
    And user clicks submit button
    And user scans a Tray




























