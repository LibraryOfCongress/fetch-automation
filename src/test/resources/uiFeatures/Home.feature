@home

Feature: Home Page Functionality Validation

  Background:
    Given user navigates to FETCH Homepage


  @regression
  Scenario: User should be able to validate Homepage icons and tabs
    When user looks at the header
    Then the hamburger menu is clickable
    And the search bar is visible
    And the login button is clickable
    And Scanned Bar Codes field is visible


  @regression @smoke
  Scenario: User should be able to validate side navigation tabs on Homepage
    Then user verifies side navigation tabs on Homepage

      | tabname      |
      | Accession    |
      | Verification |
      | Shelving     |
      | Request      |
      | Pick List    |
      | Refile       |
      | Withdrawal   |
      | Reports      |
      | Admin        |


  @FETCH-526 @FETCH-454 @active_links @regression @smoke
  Scenario: User should tell which Page/Section of the WebApp they are currently at
    When user clicks Accession on side navigation menu
    Then verify that Accession navigation link on side menu is highlighted
    When user clicks Verification on side navigation menu
    Then verify that Verification navigation link on side menu is highlighted
    When user clicks Shelving on side navigation menu
    Then verify that Shelving navigation link on side menu is highlighted
    When user clicks Request on side navigation menu
    Then verify that Request navigation link on side menu is highlighted
    When user clicks Pick List on side navigation menu
    Then verify that Pick List navigation link on side menu is highlighted
    When user clicks Refile on side navigation menu
    Then verify that Refile navigation link on side menu is highlighted
    When user clicks Withdrawal on side navigation menu
    Then verify that Withdrawal navigation link on side menu is highlighted
    When user clicks Reports on side navigation menu
    Then verify that Reports navigation link on side menu is highlighted


  @FETCH-694 @FETCH-548 @smoke
  Scenario: User should be able to verify successful login with valid credentials
    When user logs in as a tester
    Then user should be able to verify account name on user dashboard


  @FETCH-694 @FETCH-548 @negative
  Scenario: User should see an Error Message when logs in with invalid credentials
    When user logs in with invalid email
    Then user verifies "User not found for test@loctest.gov" alert msg


  @FETCH-765 @FETCH-670 @scanning @regression
  Scenario: User should be able to use Toggle Scanning Function
    When user logs in as a tester
    And user switches on Toggle Barcode Scan
    Then verify barcode scanning is enabled
    When user enters barcode by scanning
    Then user verifies the scanned barcode is displayed

















