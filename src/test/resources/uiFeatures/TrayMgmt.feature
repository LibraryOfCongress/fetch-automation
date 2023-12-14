@regression
@FETCH-287 @FETCH-170 @tray
Feature: Trayed Item Page Validation
  Background:
    Given user navigates to Item Management Page

  Scenario: User should be able to validate Tray Management icons and tabs
    When user looks at the tray header
    Then the name of tray is displayed
    And the hamburger menu is clickable
    And the search bar is visible
    And the login button is visible
    And tray barcode is visible
    And filter columns dropdown is visible and clickable
    And user verifies filter column options

      |columnname          |
      |Barcode             |
      |Media Type          |
      |Size Class          |
      |Temporary Location  |
      |Permanent Location  |


  Scenario: User should be able to validate Tray Labels
    Then user verifies tray labels on Items Management Page

      |labelname            |
      |Facility             |
      |Shelf Location       |
      |Media Type           |
      |Container Type       |
      |Accession Date       |
      |Shelved Date         |
      |Withdrawal Date      |
      |Item Count           |
      |# of Items Out       |
      |Delete Count         |


  Scenario: User should be able to validate Items in Tray labels
    Then user verifies items labels on Items Management Page

      |labelname               |
      |Barcode                 |
      |Media Type              |
      |Size Class              |
      |Temporary Location      |
      |Permanent Location      |
      |Subcollection           |
      |Volume                  |
      |Arrival Date            |
      |Accession Date          |
      |Withdrawal Date         |
      |Container Type          |
