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


  @FETCH-694 @FETCH-548 @login
  Scenario: User should be able to verify successful login with valid credentials
    When user clicks Login icon on dashboard
    And user enters "Admin" username
    And user enters "password" password
    And user clicks login button
    When user clicks Login icon on dashboard
    Then user should be able to verify account name on user dashboard
    And user clicks logout button
    Then user verifies "You have successfully been logged out of FETCH." alert msg


#  @FETCH-694 @FETCH-548 @login @negative @wip
  Scenario Outline: User should not be able to login with invalid credentials
    When user clicks Login icon on dashboard
    And user enters "<username>" username
    And user enters "<password>" password
    Then login button is not enabled


    Examples:
      | username        | password   |
      |                 | invalidPwd |
      | invalidUsername |            |
      |                 |            |


  @FETCH-765 @FETCH-670
  Scenario: User should be able to use Toggle Scanning Function
    When user clicks Login icon on dashboard
    And user enters "Admin" username
    And user enters "password" password
    And user clicks login button
    And user clicks Login icon on dashboard
    When user switches on Toggle Barcode Scan
    Then verify barcode scanning is enabled
    When user enters barcode by scanning
    Then user verifies the scanned barcode is displayed
    When user clicks Accession on side navigation menu
    And user clicks Start Accession button
    And user selects Trayed Accession
    And user selects all required fields
    And user selects Media Type
    And user clicks submit button
    And user scans Barcode
    And user enters barcode by scanning
    Then verify the entered barcode is displayed under Scanned Items
    When user disables Toggle Barcode Scan
    Then Enter Barcode button is enabled
















