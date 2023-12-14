@home
Feature: Home Page Functionality Validation
  Background:
    Given user navigates to FETCH Homepage

  Scenario: User should be able to validate Homepage icons and tabs
    When user looks at the header
    Then the logo is displayed
    And the hamburger menu is clickable
    And the search bar is visible
    And the login button is visible
    And Scanned Bar Codes field is visible
    And the side navigation menu is visible


    Scenario: User should be able to validate side navigation tabs on Homepage
      Then user verifies side navigation tabs on Homepage

      |tabname      |
      |Accession    |
      |Verification |
      |Shelving     |
      |Request      |
      |Refile       |





