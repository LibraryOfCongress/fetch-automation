@regression @home

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

      | tabname      |
      | Accession    |
      | Verification |
      | Shelving     |
      | Request      |
      | Refile       |
      | Admin        |


  @FETCH-526 @FETCH-454 @active_links
  Scenario: User should tell which page/section of the webapp the user is currently at
    When user clicks Accession on side navigation menu
    Then verify that Accession navigation link on side menu is highlighted
    When user clicks Verification on side navigation menu
    Then verify that Verification navigation link on side menu is highlighted
    When user clicks Shelving on side navigation menu
    Then verify that Shelving navigation link on side menu is highlighted
    When user clicks Admin on side navigation menu
    Then verify that Admin navigation link on side menu is highlighted


  @FETCH-694 @FETCH-548 @smoke @login @positive @run
  Scenario: User should be able to verify successful login with valid credentials
    When user clicks Login icon on dashboard
    And user enters "username" username
    And user enters "password" password
    And user clicks login button
    Then user should be able to verify account name on user dashboard
    When user clicks Login icon on dashboard
    And user clicks logout button
    Then user verifies "You have successfully been logged out of FETCH." alert msg


  @FETCH-694 @FETCH-548 @login @negative @wip
  Scenario Outline: User should not be able to login with invalid credentials
    When user clicks Login icon on dashboard
    And user enters "<username>" username
    And user enters "<password>" password
    And user clicks login button
    Then user validates "<errorMessage>" error message

    Examples:
    | username            | password     | errorMessage              |
    | invalidUsername     | invalidPwd   | Invalid credentials       |
    |                     | invalidPwd   | Username cannot be empty  |
    | invalidUsername     |              | Password cannot be empty  |
    |                     |              | Username cannot be empty  |















