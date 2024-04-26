@regression @shelving
Feature: Shelving Page Functionality Validation

  Background:
    Given user navigates to Shelving Page

  @FETCH-313 @FETCH-165
  Scenario: User should be able to validate Shelf Management icons and tabs
    When user is on Shelving page
    Then the hamburger menu is clickable
    And the search bar is visible
    And the login button is visible
    And Filter icon is clickable
    And Rearrange dropdown is clickable
    And Create Shelving Job button is clickable


  Scenario: User should be able to select multiple options from Rearrange dropdown
    When user clicks on Rearrange dropdown
    Then user verifies dropdown options

      | optionname             |
      | Job Number             |
      | # of Containers in Job |
      | Status                 |
      | Assigned User          |
      | Date Added             |

    And user verifies dropdown checkboxes are clickable
    And user verifies dropdown options match shelf table column options


  Scenario: User should be able to select multiple options from Rearrange Dropdown
    When user clicks on Rearrange dropdown
    Then user verifies all options are selected
    And user is able to deselect all the options
    And user selects options A, B and C from the dropdown
    Then selected options are displayed on the page


  @newShelf
  Scenario: User should be able to validate fields of Create Shelving Job modal
    When user clicks on Create Shelving Job button
    And user selects From Verification Job option
    And user selects Yes
    Then a new modal with shelving location options along with the verification job selection is displayed
    And user verifies Create Shelving Job modal sections

      | section                              |
      | Assign Shelving Location?            |
      | Verification Job List:               |
      | Please Select Shelving Location:     |

    And user verifies Create Shelving Job modal dropdown fields

      | field                                |
      | Please Select Verification Job(s)    |
      | Building                             |
      | Module                               |
      | Aisle                                |
      | Side                                 |
      | Ladder                               |

    And cancel button is clickable
    And submit button is enabled and clickable


  @FETCH-360 @FETCH-336 @wip
  Scenario: User should be able to rearrange tables columns to their preferred order
    When user clicks on Rearrange dropdown
    And user clicks Rearrange Columns toggle switch
    And user drags each menu item to their preferred order
    And user switches off the Rearrange Columns toggle
    Then user verifies the Shelf Table column names

  @wip
  Scenario: User should be able to Create new Shelf
    When user clicks on Create Shelving Job button
    And user selects an Owner
    And user enters a Shelf Number
    And user enters a Shelf Width
    And user enters a Shelf Height
    And user enters a Shelf Depth
    And user selects a Container Type
    And user clicks Create Shelf button


  @FETCH-648 @FETCH-380
  Scenario: User should be able to create a Shelving Job from Verification Jobs
    When user clicks on Create Shelving Job button
    And user selects From Verification Job option
    And user selects No
    Then user is able to select a Verification Job from the Verification Job(s) List
    And user clicks Submit
    Then user verifies the Shelving Job is created
    And user is navigated to the shelving detail page
    And user clicks Shelving on side navigation menu
    When user clicks on Create Shelving Job button
    And user selects From Verification Job option
    And user selects Yes
    Then a new modal with shelving location options along with the verification job selection is displayed


  @FETCH-625 @FETCH-382
  Scenario:  User should be able to change Shelving Address within a Shelving Job
    When user navigates to Shelving Job
    And user clicks three dot menu next to a container that has shelving location information
    Then user should see Edit Location option
    And user clicks Edit Location button
    Then Edit Shelving Location modal is displayed
    When user clicks three dot menu next to a container that does not have a shelving location information
    Then user should see Assign Location option
    And user clicks Assign Location button
    Then Assign Shelving Location modal is displayed




















