@refile

Feature: Refile Page Functionality Validation

  Background:
    Given user navigates to FETCH Homepage
    And user logs in as a tester1


  @FETCH-834 @FETCH-763 @regression
  Scenario: User should be able to verify Front-End Layout of Refile Dashboard
    When user navigates to the Refile Page
    Then user verifies Refile Job table is displayed
    And user verifies Refile Job table column names

      | name                   |
      | Job ID #               |
      | # of Items             |
      | # of Items Shelved     |
      | Assigned User          |
      | Date Created           |
      | Last Updated           |

    When user clicks Refile Queue
    Then user verifies Refile Queue table is displayed
    And user verifies Refile Queue table column names

      | name                   |
      | Item Location          |
      | Container Type         |
      | Media Type             |
      | Item Barcode           |
      | Owner                  |
      | Container Size         |

    When user clicks Create button
    Then user verifies the dropdown options

      | option                    |
      | Add Item to Queue         |
      | Add Item to Refile Job    |
      | Create Refile Job         |