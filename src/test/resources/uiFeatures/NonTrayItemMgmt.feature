@nontray

Feature: Non-Trayed Item Management Validation

  Background:
    Given user navigates to Non-Trayed Item Management Page


  @FETCH-311 @FETCH-182 @regression
  Scenario: User should be able to validate Non-Trayed Item Page icons and tabs
    When user looks at the header
    Then the name of shelf is displayed
    And the hamburger menu is clickable
    And the search bar is visible
    And the login button is clickable

  @regression
  Scenario: User should be able to validate Page Labels
    Then user verifies shelf labels on Non-Trayed Items Management Page

      |labelname            |
      |Facility             |
      |Shelf Location       |
      |Media Type           |
      |Container Type       |
      |Accession Date       |
      |Item Count           |
      |# of Items Out       |
      |Delete Count         |

  @regression
  Scenario: User should be able to validate Non-Trayed Items labels
    Then user verifies non-trayed items labels on Non-Trayed Items Management Page

      |labelname               |
      |Barcode                 |
      |Media Type              |
      |Shelf Location          |
      |Shelf Position          |
      |Accession Date          |
      |Subcollection           |
      |Container Type          |

  @regression
  Scenario: User should be able to validate Overlay Slide with Item's details
    When user clicks on any item in the table of items
    Then the overlay slide is visible
    And user verifies item details on Overlay Slide

      |labelname                 |
      |Barcode:                  |
      |Media Type:               |
      |Container Type:           |
      |Subcollection:            |
      |Dimensions:               |
      |Accession Date:           |










