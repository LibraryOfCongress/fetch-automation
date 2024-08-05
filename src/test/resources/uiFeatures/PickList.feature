@picklist

Feature: Pick List Page Functionality Validation

  Background:
    Given user navigates to the Pick List Page

  @regression
  Scenario: User should be able to verify Front-End Layout of Pick List Dashboard
    Then user verifies the Pick List table column names

      | name              |
      | Job Number #      |
      | # of Items in Job |
      | Status            |
      | Assigned User     |
      | Date Added        |
      | Date Completed    |

    And Rearrange dropdown is clickable
    When user clicks on Pick List Job
    Then user verifies Pick List number is displayed
    When user clicks three dot menu next to Pick List Number
    Then Edit button is displayed
    And user verifies the Items in Job table column names

      | name          |
      | Barcode       |
      | Owner         |
      | Size Class    |
      | Item Location |

    And Rearrange dropdown is clickable





