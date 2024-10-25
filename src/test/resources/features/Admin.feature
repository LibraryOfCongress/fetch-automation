@admin

Feature: Admin Page Functionality Validation

  Background:
    Given user navigates to FETCH Homepage
    And user logs in as an admin


  @FETCH-527 @FETCH-319
  Scenario: User should be able to validate Location Manager links
    When user clicks Admin on side navigation menu
    When user clicks Location Manager Link
    And user verifies Location Manager dropdown options

      | option    |
      | Buildings |
      | Modules   |
      | Aisles    |
      | Ladders   |
      | Shelves   |


  Scenario: User should be able to verify Buildings Management page
    When user navigates to the Admin Page
    And user clicks Location Manager Link
    And user selects Building
    And user verifies table columns

      | column       |
      | Building     |
      | Created Date |
      | Last Updated |

      Then user verifies Add Building button is clickable


  Scenario: User should be able to verify Buildings Management page
    When user navigates to the Admin Page
    And user clicks Location Manager Link
    And user selects Building
    And user verifies table columns

      | column       |
      | Building     |
      | Created Date |
      | Last Updated |

    Then user verifies Add Building button is clickable

# feature_to_be_updated
#  @FETCH-527 @FETCH-319
#  Scenario: User should be able to see and edit shelving items of a Building
#    When user navigates to the Admin Page
#    And user clicks Location Manager Link
#    And user selects Building
#    Then user should see building's shelving items
#    When user clicks three-dots on the left side of the table
#    And user clicks Edit Shelf button
#    Then Edit Shelf modal is displayed
#    And user verifies fields on Edit Shelf modal
#
#      | fieldname              |
#      | Shelf Number           |
#      | Owner                  |
#      | Container Size         |
#      | Max Container Capacity |
#      | Shelf Width            |
#      | Shelf Height           |
#      | Shelf Depth            |
#      | Shelf Barcode          |
#
#    And user is able to edit all the fields
#    And Update button is clickable
#    And Cancel button is clickable

# feature_to_be_updated
#  @FETCH-527 @FETCH-319 @add_building
#  Scenario: User should be able to validate Add Building feature
#    When user navigates to the Admin Page
#    And user clicks Buildings
#    And user clicks Add New dropdown button
#    And user selects add Building option
#    Then user verifies popup modal is displayed
#    And Building field is displayed
#    And Create button is disabled
#    When user enters Building information
#    Then Create button is enabled
#    And user exits modal

# feature_to_be_updated
#  @FETCH-527 @FETCH-319 @add_module
#  Scenario: User should be able to validate Add Module feature
#    When user navigates to the Admin Page
#    And user clicks Buildings
#    And user clicks Add New dropdown button
#    And user selects add Module option
#    Then user verifies popup modal is displayed
#    And Building dropdown is clickable
#    And Module field is disabled
#    And Create button is disabled
#    When user selects Building from dropdown
#    Then Module field is enabled
#    When user enters Module information
#    Then Create button is enabled
#    And user exits modal

# feature_to_be_updated
#  @FETCH-527 @FETCH-319 @add_aisle
#  Scenario: User should be able to validate Add Aisle feature
#    When user navigates to the Admin Page
#    And user clicks Buildings
#    And user clicks Add New dropdown button
#    And user selects add Aisle option
#    Then user verifies popup modal is displayed
#    And Building dropdown is clickable
#    And Module field is disabled
#    And Aisle field is disabled
#    And Create button is disabled
#    When user selects Building from dropdown
#    Then Module field is enabled
#    And Aisle field is enabled
#    When user selects Module from dropdown
#    And user enters Aisle information
#    Then Create button is enabled
#    And user exits modal

# feature_to_be_updated
#  @FETCH-527 @FETCH-319 @add_ladder
#  Scenario: User should be able to validate Add Ladder feature
#    When user navigates to the Admin Page
#    And user clicks Buildings
#    And user clicks Add New dropdown button
#    And user selects add Ladder option
#    Then user verifies popup modal is displayed
#    And Building dropdown is clickable
#    And Module field is disabled
#    And Aisle field is disabled
#    And Ladder field is disabled
#    And Create button is disabled
#    And Side button is enabled
#    When user selects Building from dropdown
#    Then Module field is enabled
#    And user selects Module from dropdown
#    Then Aisle field is enabled
#    And user selects Aisle from dropdown
#    Then Ladder field is enabled
#    And user selects Side
#    And user enters Ladder information
#    Then Create button is enabled
#    And user exits modal

# feature_to_be_updated
#  @FETCH-527 @FETCH-319 @manual_LH
#  Scenario: User should be able to validate Location Hierarchy Manual creation
#    When user navigates to the Admin Page
#    And user clicks Buildings
#    And user clicks Location Hierarchy dropdown button
#    And user selects Manual option
#    And user verifies popup modal is displayed
#    When user selects Building from dropdown
#    And user selects Module from dropdown
#    And user selects Aisle from dropdown
#    And user selects Side
#    And user selects Ladder
#    Then Create button is enabled
#    And user exits modal

# feature_to_be_updated
#  @FETCH-527 @FETCH-319 @bulk_upload
#  Scenario: User should be able to validate Location Hierarchy Bulk Upload creation
#    When user navigates to the Admin Page
#    And user clicks Buildings
#    And user clicks Location Hierarchy dropdown button
#    And user selects Bulk Upload option
#    And user verifies popup modal is displayed
#    When user selects Building from dropdown
#    And user selects Module from dropdown
#    And user selects Aisle from dropdown
#    And user selects Side
#    And user selects Ladder
#    And user uploads file
#    Then Create button is enabled
#    And user exits modal


  @FETCH-819 @FETCH-547
  Scenario: User should be able to validate Group Management UI
    When user navigates to the Admin Page
    And user clicks Groups and Permissions
    Then user verifies the dashboard contains Groups
    When user clicks Add New Group
    And user enters Group Name
    And user clicks Submit
    Then user verifies a new Group is created
    And user closes alert message
    When user clicks three dot menu next to group name
    Then user verifies all the options

      | option                    |
      | Edit Permissions          |
      | Add/Edit User(s) in Group |
      | Rename Group Name         |
      | Delete Group              |

    When user clicks Edit Permissions
    Then user verifies permissions tab names

      | name         |
      | ACCESSION    |
      | VERIFICATION |
      | SHELVING     |
      | REQUEST      |
      | PICKLIST     |
      | REFILE       |
      | WITHDRAW     |
      | REPORTING    |

    And user adds some permissions
    Then user clicks on Groups and Permissions breadcrumb link
    When user clicks three dot menu next to group name
    And user clicks Add Edit Users in Group
    And user selects User names
    Then user clicks Add Users
    When user clicks on User name
    Then user is able to delete User from Group
    And user closes modal
    When user clicks three dot menu next to group name
    And user clicks Rename Group Name
    And user renames Group
    Then user saves changes
    When user clicks three dot menu next to group name
    And user clicks Delete Group
    Then user is able to delete Group













