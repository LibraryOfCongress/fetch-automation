@regression
@FETCH-314 @FETCH-249 @accessionjob
Feature: Accession Job Validation
  Background:
    Given user navigates to Item Management Page

  Scenario: Verify Front-End Layout for Accession Job of Trayed Items
     When user clicks Accession button on side navigation menu
     And user hovers over Start Accession button
     And user clicks Start Accession button
     And user selects Trayed Accession
     Then user verifies required and optional fields on Start New Accession modal

      |fieldname                     |
      |Owner (Required)              |
      |Container Size (Required)     |
      |Media Type (Optional)         |

     And user verifies Owner field options
     And user verifies Container Size field options
     And user verifies Media Type field options
     And user selects all required fields
     And user is able to click Back button
     And user is able to click Cancel button
     Then user is able to return to the Start Accession single action square screen


   Scenario: Verify Front-End Layout for Accession Job of Non-Trayed Items
    When user clicks Accession button on side navigation menu
    And user hovers over Start Accession button
    And user clicks Start Accession button
    And user selects Non-Tray Accession
    Then user verifies required and optional fields on Start New Accession modal

      |fieldname                     |
      |Owner (Required)              |
      |Container Size (Required)     |
      |Media Type (Optional)         |

     And user verifies Owner field options
     And user verifies Container Size field options
     And user verifies Media Type field options
     And user selects all required fields
     And user is able to click Back button
     And user is able to click Cancel button
     Then user is able to return to the Start Accession single action square screen


     Scenario: Verify all Dropdown Options in Start New Accession modal fields
       When user clicks Accession button on side navigation menu
       And user hovers over Start Accession button
       And user clicks Start Accession button
       And user selects Trayed Accession
       When user clicks on select Owner button
       Then user is able to choose any option from dropdown field














