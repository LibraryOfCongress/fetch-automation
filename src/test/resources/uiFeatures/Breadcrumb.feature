Feature: Breadcrumb Functionality Validation

  Background:
    Given user navigates to FETCH Homepage

  @FETCH-533 @FETCH-472
  Scenario: User should be able to validate Breadcrumb Navigation
    When user clicks Verification on side navigation menu
    Then user should see the corresponding breadcrumbs

      | breadcrumb   |
      | Home         |
      | Verification |

    And user selects a Verification Job
    Then user should see the corresponding breadcrumbs

      | breadcrumb   |
      | Home         |
      | Verification |
      | 1234567891   |

    When user clicks on Verification breadcrumb link
    Then user should navigate to the Verification page
    When user clicks on Home breadcrumb link
    And user clicks Later on the banner
    Then user should navigate to the Home page
    When user clicks Admin on side navigation menu
    And user selects Building
    Then user should see the corresponding breadcrumbs

      | breadcrumb   |
      | Home         |
      | Admin        |
      | Cabin Branch |

    When user clicks on Admin breadcrumb link
    Then user should navigate to the Admin page
    When user clicks on Home breadcrumb link
    Then user should navigate to the Home page


