@regression
@admin
Feature: Admin Page Functionality Validation

  Background:
    Given user navigates to the Admin Page


  @FETCH-527 @FETCH-319 @run
  Scenario: User should be able to validate Admin Dashboard icons and tabs
    When user is on the Admin Page
    Then user verifies the Admin Dashboard contains Buildings
    And Add New dropdown is displayed and clickable
    And user verifies Add New dropdown options

      | option   |
      | Building |
      | Module   |
      | Aisle    |
      | Ladder   |

    And Location Hierarchy dropdown is displayed and clickable
    And user verifies Location Hierarchy dropdown options

      | option      |
      | Bulk Upload |
      | Manual      |


  @FETCH-527 @FETCH-319
  Scenario: User should be able to see and edit shelving items of a Building
    When user selects Building
    Then user should see building's shelving items
    When user clicks three-dots on the left side of the table
    And user clicks Edit Shelf button
    Then Edit Shelf modal is displayed
    And user verifies fields on Edit Shelf modal

      | fieldname              |
      | Shelf Number           |
      | Owner                  |
      | Container Size         |
      | Max Container Capacity |
      | Shelf Width            |
      | Shelf Height           |
      | Shelf Depth            |
      | Shelf Barcode          |

    And user is able to edit all the fields
    And Update button is clickable
    And Cancel button is clickable


  @FETCH-527 @FETCH-319 @add_building
  Scenario: User should be able to validate Add Building feature
    When user clicks Add New dropdown button
    And user selects add Building option
    Then user verifies popup modal is displayed
    And Building field is displayed
    And Create button is disabled
    When user enters Building information
    Then Create button is enabled
    And user exits modal


  @FETCH-527 @FETCH-319 @add_module
  Scenario: User should be able to validate Add Module feature
    When user clicks Add New dropdown button
    And user selects add Module option
    Then user verifies popup modal is displayed
    And Building dropdown is clickable
    And Module field is disabled
    And Create button is disabled
    When user selects Building from dropdown
    Then Module field is enabled
    When user enters Module information
    Then Create button is enabled
    And user exits modal


  @FETCH-527 @FETCH-319 @add_aisle
  Scenario: User should be able to validate Add Aisle feature
    When user clicks Add New dropdown button
    And user selects add Aisle option
    Then user verifies popup modal is displayed
    And Building dropdown is clickable
    And Module field is disabled
    And Aisle field is disabled
    And Create button is disabled
    When user selects Building from dropdown
    Then Module field is enabled
    And Aisle field is enabled
    When user selects Module from dropdown
    And user enters Aisle information
    Then Create button is enabled
    And user exits modal


  @FETCH-527 @FETCH-319 @add_ladder
  Scenario: User should be able to validate Add Ladder feature
    When user clicks Add New dropdown button
    And user selects add Ladder option
    Then user verifies popup modal is displayed
    And Building dropdown is clickable
    And Module field is disabled
    And Aisle field is disabled
    And Ladder field is disabled
    And Create button is disabled
    And Side button is enabled
    When user selects Building from dropdown
    Then Module field is enabled
    And user selects Module from dropdown
    Then Aisle field is enabled
    And user selects Aisle from dropdown
    Then Ladder field is enabled
    And user selects Side
    And user enters Ladder information
    Then Create button is enabled
    And user exits modal


  @FETCH-527 @FETCH-319 @manual_LH
  Scenario: User should be able to validate Location Hierarchy Manual creation
    When user clicks Location Hierarchy dropdown button
    And user selects Manual option
    And user verifies popup modal is displayed
    When user selects Building from dropdown
    And user selects Module from dropdown
    And user selects Aisle from dropdown
    And user selects Side
    And user selects Ladder
    Then Create button is enabled
    And user exits modal


  @FETCH-527 @FETCH-319 @bulk_upload
  Scenario: User should be able to validate Location Hierarchy Bulk Upload creation
    When user clicks Location Hierarchy dropdown button
    And user selects Bulk Upload option
    And user verifies popup modal is displayed
    When user selects Building from dropdown
    And user selects Module from dropdown
    And user selects Aisle from dropdown
    And user selects Side
    And user selects Ladder
    And user uploads file
    Then Create button is enabled
    And user exits modal












