@regression
@FETCH-314 @FETCH-249 @accession
Feature: Accession Job Validation

  Background:
    Given user navigates to Item Management Page


  Scenario: Verify Front-End Layout for Accession Job of Trayed Items
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
    And user selects Trayed Accession
    Then user verifies required and optional fields on Start New Accession modal

      | fieldname             |
      | Owner (Required)      |
      | Media Type (Optional) |

    And user verifies Owner field options
    And user verifies Media Type field options
    And user selects all required fields
    And user is able to click Back button
    And user is able to click Cancel button
    Then user is able to return to the Start Accession single action square screen


  Scenario: Verify Front-End Layout for Accession Job of Non-Trayed Items
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
    And user selects Non-Tray Accession
    Then user verifies required and optional fields on Start New Accession modal

      | fieldname             |
      | Owner (Required)      |
      | Media Type (Optional) |

    And user verifies Owner field options
    And user verifies Media Type field options
    And user selects all required fields
    And user is able to click Back button
    And user is able to click Cancel button
    Then user is able to return to the Start Accession single action square screen


  Scenario: Verify all Dropdown Options in Start New Accession modal fields
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
    And user selects Trayed Accession
    When user clicks Select Owner
    Then user is able to choose any option from Owner dropdown field
    When user clicks Select Media Type
    Then user is able to choose any option from Media Type dropdown field


#       @FETCH-358 @FETCH-194 @trayed
#       Scenario: Verify Accession Process for Trayed Item
#         When user clicks Accession on side navigation menu
#         And user clicks Start Accession button
#         And user selects Trayed Accession
#         And user selects all required fields
#         And user clicks Submit button
#         And user clicks Scan Barcode
#         And user is able to edit Container Size and Media Type fields of the panel
#         And user is able to cancel edits
#         And user is able to edit Container Size and Media Type fields of the panel
#         And user is able to save edits
#         And Add Item button is enabled and clickable
#         And Pause Job button is enabled and clickable
#         And Complete Job button is enabled and clickable
#         When user checks an Item
#         Then Delete button is enabled
#         When user clicks Pause Job button
#         Then Add Item, Delete and Complete Job buttons are disabled


  @FETCH-358 @FETCH-194 @search
  Scenario: Verify dropdown search functionality
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
    And user selects Trayed Accession
    And user types "<search_query>" in the Owner dropdown search field
    Then Owner dropdown should display options related to "<search_query>"
    And user selects an option from the Owner dropdown
    And the selected Owner option should be displayed on the page
#    And user types "<search_query>" in the Container Size dropdown search field
#    Then Container Size dropdown should display options related to "<search_query>"
#    And user selects an option from the Container Size dropdown
#    And the selected Container Size option should be displayed on the page
    And user types "<search_query>" in the Media Type dropdown search field
    Then Media Type dropdown should display options related to "<search_query>"
    And user selects an option from the Media Type dropdown
    And the selected Media Type option should be displayed on the page


#  @FETCH-364 @FETCH-184 @nontrayed
#  Scenario: Verify Accession Process for Non-Trayed Item
#    When user clicks Accession on side navigation menu
#    And user clicks Start Accession button
#    And user selects Non-Tray Accession
#    And user selects all required fields
#    And user clicks Submit button
#    And user is able to edit Container Size and Media Type fields of the panel
#    And user is able to cancel edits
#    And user is able to edit Container Size and Media Type fields of the panel
#    And user is able to save edits
#    And Complete Job button is enabled and clickable
#    When user checks an Item
#    Then Delete button is enabled
#    And when user clicks Delete button
#    Then verify a modal confirming delete action appears
#    When user clicks Complete Job button
#    Then verify a modal confirming complete job action appears


  @FETCH-545 @FETCH-455 @trayed
  Scenario: Verify Accession Process for Trayed Item
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
    And user selects Trayed Accession
    And user selects all required fields
    And user clicks Submit button
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
    Then verify the updated barcode is displayed under Scanned Items
    When user verifies all barcodes
    Then verify Add Tray button is activated
    And user clicks Add Tray button
    Then verify new modal Select Tray is displayed
    And user clicks add tray on the modal
    Then verify "Successfully added a tray to the job" alert message is displayed
    And the container is cleared out so a new tray can be scanned


  @FETCH-545 @FETCH-455 @nontrayed
  Scenario: Verify Accession Process for Non-Trayed Item
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
    And user selects Non-Tray Accession
    And user selects all required fields
    And user clicks Submit button
    And user clicks Enter Barcode button
    Then verify a modal with manual barcode entry is displayed
    And user enters barcode and clicks Submit button
    Then verify the entered barcode is displayed under Scanned Items
    When user selects one of the barcodes in the table
    Then verify Enter Barcode button is changed to Edit Barcode
    And user clicks Edit Barcode button
    Then verify new modal allowing to edit the barcode is displayed
    And user edits the barcode and clicks submit button
    Then verify the updated barcode is displayed under Scanned Items























