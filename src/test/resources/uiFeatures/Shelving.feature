@regression
@FETCH-313 @FETCH-165 @shelving
Feature: Shelf Management Page Functionality Validation
  Background:
    Given user navigates to Shelving Page


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

       |optionname                  |
       |Job Number                  |
       |# of Containers in Job      |
       |Status                      |
       |Assigned User               |
       |Date Added                  |

     And user verifies dropdown checkboxes are clickable
     And user verifies dropdown options match shelf table column options


     Scenario: User should be able to select multiple options from Rearrange Dropdown
       When user clicks on Rearrange dropdown
       Then user verifies all options are selected
       And user is able to deselect all the options
       And user selects options A, B and C from the dropdown
       Then selected options are displayed on the page


      Scenario: User should be able to create a New Shelving Job
        When user clicks on Create Shelving Job button
        Then user verifies fields on Create New Shelf modal

        |fieldname                          |
        |Owner                              |
        |Shelf Number                       |
        |Shelf Width                        |
        |Shelf Height                       |
        |Shelf Depth                        |
        |Allowed Container Type             |

       And cancel button is clickable
       And create shelf button is enabled and clickable


       @FETCH-360 @FETCH-336
       Scenario: User should be able to rearrange tables columns to their preferred order
         When user clicks on Rearrange dropdown
         And user clicks Rearrange Columns toggle switch
         And user drags each menu item to their preferred order
         And user switches off the Rearrange Columns toggle
         Then user verifies the Shelf Table column names

       @newShelf
       Scenario: User should be able to Create new Shelf
         When user clicks on Create Shelving Job button
         And user selects an Owner
         And user enters a Shelf Number
         And user enters a Shelf Width
         And user enters a Shelf Height
         And user enters a Shelf Depth
         And user selects a Container Type
         And user clicks Create Shelf button











