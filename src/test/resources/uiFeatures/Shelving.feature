@shelving
Feature: Shelving Page Functionality Validation

  Background:
    Given user navigates to Shelving Page

  @FETCH-313 @FETCH-165 @regression
  Scenario: User should be able to validate Shelf Management icons and tabs
    When user is on Shelving page
    Then the hamburger menu is clickable
    And the search bar is visible
    And the login button is visible
    And Filter icon is clickable
    And Rearrange dropdown is clickable
    And Create Shelving Job button is clickable


  Scenario: User should be able to select multiple options from Rearrange dropdown
    When user clicks on Rearrange dropdown
    Then user verifies dropdown options

      | optionname             |
      | Job Number             |
      | # of Containers in Job |
      | Status                 |
      | Assigned User          |
      | Date Added             |

    And user verifies dropdown checkboxes are clickable
    And user verifies dropdown options match shelf table column options

  @regression
  Scenario: User should be able to select multiple options from Rearrange Dropdown
    When user clicks on Rearrange dropdown
    Then user verifies all options are selected
    And user is able to deselect all the options
    And user selects options A, B and C from the dropdown
    Then selected options are displayed on the page

  @regression
  Scenario: User should be able to validate fields of Create Shelving Job From Verification modal
    When user clicks on Create Shelving Job button
    And user selects From Verification Job option
    And user selects Yes
    Then a new modal with shelving location options along with the verification job selection is displayed
    And user verifies Create Shelving Job modal sections

      | section                           |
      | Assign Shelving Location?         |
      | Verification Job List:            |
      | Please Select Shelving Locations: |

    And user verifies Create Shelving Job modal dropdown fields

      | field                             |
      | Please Select Verification Job(s) |
      | Building                          |
      | Module                            |
      | Aisle                             |
      | Side                              |
      | Ladder                            |

    And cancel button is clickable
    And submit button is enabled and clickable


  @FETCH-360 @FETCH-336 @wip
  Scenario: User should be able to rearrange tables columns to their preferred order
    When user clicks on Rearrange dropdown
    And user clicks Rearrange Columns toggle switch
    And user drags each menu item to their preferred order
    And user switches off the Rearrange Columns toggle
    Then user verifies the Shelf Table column names


  @FETCH-648 @FETCH-380 @regression
  Scenario: User should be able to create a Shelving Job from Verification Jobs
    When user clicks on Create Shelving Job button
    And user selects From Verification Job option
    And user selects No
    Then user selects a Verification Job from the Verification Job(s) List
    And user selects a Building from Shelving Locations
    And user clicks Submit
    Then user verifies the Shelving Job is created
    And user is navigated to the shelving detail page
    And user clicks Shelving on side navigation menu
    When user clicks on Create Shelving Job button
    And user selects From Verification Job option
    And user selects Yes
    Then a new modal with shelving location options along with the verification job selection is displayed


  @FETCH-625 @FETCH-382
  Scenario:  User should be able to change Shelving Address within a Shelving Job
    When user navigates to Shelving Job
    And user clicks three dot menu next to a container that has shelving location information
    Then user should see Edit Location option
    And user clicks Edit Location button
    Then Edit Shelving Location modal is displayed
    When user clicks three dot menu next to a container that does not have a shelving location information
    Then user should see Assign Location option
    And user clicks Assign Location button
    Then Assign Shelving Location modal is displayed


  @FETCH-684 @FETCH-438
  Scenario: User should be able to verify the Shelving Assign Process
    When user navigates to Shelving Job with Running Status
    And user clicks three dot menu next to Job Number
    And user clicks Edit
    Then Assign User dropdown is clickable
    And Save Edits button is clickable
    And Cancel edits button is clickable


  @FETCH-685 @FETCH-439
  Scenario: User should be able to create a Shelving Job from Direct to Shelve Method
    When user clicks on Create Shelving Job button
    And user selects Direct To Shelve option
    And user selects a Building from Shelving Locations
    And user clicks Submit
    Then user verifies "A Direct Shelving Job has been successfully created." notification


  @FETCH-686 @FETCH-383
  Scenario: User should be able to create Shelving Job from Verification Job, assign Location and save the Job
    When user clicks Login icon on dashboard
    And user enters "Admin" username
    And user enters "password" password
    And user clicks login button
    And user clicks Login icon on dashboard
    When user switches on Toggle Barcode Scan
    Then verify barcode scanning is enabled
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
    And user selects Trayed Accession
    And user selects all required fields
    And user selects Media Type
    And user clicks submit button
    And user scans Barcode
    And user enters barcode by scanning
    And user enters barcode by scanning
    When user clicks Complete Job button
    And user clicks Complete
    Then user verifies "The Job has been completed and moved for verification."
    When user clicks Verification on side navigation menu
    And user clicks most recent Verification Job for a Trayed Item
    Then user scans Tray Barcode
    And user disables Toggle Barcode Scan
    And user verifies all the barcodes
    When user clicks three dot menu next to Job Number
    And user clicks Edit
    And user edits Container Size
    Then user clicks Save Edits
    And user saves Verification Job number
    When user clicks Complete Job button
    And user clicks Complete
    Then user verifies "The Job has been completed." msg
    When user clicks Shelving on side navigation menu
    And user clicks on Create Shelving Job button
    And user selects From Verification Job option
    And user selects Yes
    Then user selects a created Verification Job
    And user selects a Building from Shelving Locations
    And user selects Aisle from dropdown
    And user selects Right side
    And user selects Ladder
    And user clicks Submit
    Then user verifies "A Shelving Job has been successfully created." notification
    And user verifies the Status is "Created"
    And user clicks Login icon on dashboard
    And user sets input delay
    When user switches on Toggle Barcode Scan
    And user clicks Login icon on dashboard
    When user clicks Execute Job
    Then user verifies the Status is "Running"
    And user scans Container
    Then user scans shelf to verify Container
    When user clicks three dot menu next to Container
    And user clicks Edit Location
    And user selects Ladder
    And user selects Shelf
    And user selects Shelf Position
    And user clicks Submit
    Then user verifies "The container has been updated." message
    When user clicks Pause Job button
    Then user verifies "Job Status has been updated to: Paused" alert msg
    When user clicks Resume Job button
    Then user verifies "Job Status has been updated to: Running" alert msg
    When user clicks Complete Job
    And user clicks Complete
    Then user verifies "The Shelving Job has been completed." alert msg

























