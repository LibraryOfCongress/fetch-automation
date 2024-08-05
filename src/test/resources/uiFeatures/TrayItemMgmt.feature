@tray

Feature: Trayed Item Page Validation

  Background:
    Given user navigates to Item Management Page

  @FETCH-287 @FETCH-170 @regression
  Scenario: User should be able to validate Tray Management icons and tabs
    When user looks at the tray header
    Then the name of tray is displayed
    And the hamburger menu is clickable
    And the search bar is visible
    And the login button is clickable
    And tray barcode is visible
    And filter columns dropdown is visible and clickable
    And user verifies filter column options

      | columnname         |
      | Barcode            |
      | Media Type         |
      | Size Class         |
      | Temporary Location |
      | Permanent Location |


  @FETCH-287 @FETCH-170 @regression
  Scenario: User should be able to validate Tray Labels
    Then user verifies tray labels on Items Management Page

      | labelname       |
      | Facility        |
      | Shelf Location  |
      | Media Type      |
      | Container Type  |
      | Accession Date  |
      | Shelved Date    |
      | Withdrawal Date |
      | Item Count      |
      | # of Items Out  |
      | Delete Count    |


  @FETCH-298 @FETCH-180 @regression
  Scenario: User should be able to validate Items in Tray labels
    Then user verifies items labels on Items Management Page

      | labelname          |
      | Barcode            |
      | Media Type         |
      | Size Class         |
      | Temporary Location |
      | Permanent Location |
      | Subcollection      |
      | Volume             |
      | Arrival Date       |
      | Accession Date     |
      | Withdrawal Date    |
      | Container Type     |


  @FETCH-298 @FETCH-180 @ @regression
  Scenario: User should be able to validate Overlay slide with Items in Tray details
    When user clicks on any item in the table of items
    Then the overlay slide is visible
    And user verifies item in tray details on Overlay Slide

      | labelname        |
      | Barcode:         |
      | Media Type:      |
      | Size Class:      |
      | Volume:          |
      | Container Type:  |
      | Subcollection:   |
      | Dimensions:      |
      | Condition:       |
      | Accession Date:  |
      | Withdrawal Date: |
      | Arrival Date:    |


  @FETCH-298 @FETCH-180 @overlay @regression
  Scenario: User should be able to close Overlay Slide using X button
    When user clicks on any item in the table of items
    Then the overlay slide is visible
    And the x button is clickable


  @FETCH-298 @FETCH-180 @overlay @regression
  Scenario: User should be able to close Overlay Slide by clicking outside of the overlay
    When user clicks on any item in the table of items
    Then the overlay slide is visible
    And user clicks outside of overlay
    Then the overlay slide is not visible