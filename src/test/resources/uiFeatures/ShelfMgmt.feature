
@FETCH-313 @FETCH-165 @shelf
Feature: Shelf Management Page Functionality Validation
  Background:
    Given user navigates to Shelving Page


  Scenario: User should be able to validate Shelf Management icons and tabs
    When user is on Shelving page
    Then the hamburger menu is clickable
    And the search bar is visible
    And the login button is visible
    And Aisle field is clickable
    And Side field is clickable
    And Ladder field is clickable
    And Filter dropdown is clickable
    And Add shelf button is clickable


    Scenario: User should be able to select an option from upper dropdowns
      When user selects an option from Aisle dropdown
      And user selects an option from Side dropdown
      And user selects an option from Ladder dropdown
      Then the selected options should be displayed


   Scenario: User should be able to select multiple options from Filter dropdown
     When user clicks on filter dropdown
     Then user verifies filter dropdown options

       |optionname                    |
       |Shelf Width                   |
       |Shelf Height                  |
       |Shelf Depth                   |
       |Vacancy                       |
       |Max Capacity                  |
       |Current Capacity              |
       |Available Capacity            |
       |Size Class                    |
       |Shelf Barcode                 |

     And user verifies filter dropdown checkboxes are clickable
     And user verifies filter options match shelf table column options


     Scenario: User should be able to select multiple options from Filter Dropdown
       When user clicks on filter dropdown
       Then user verifies all options are selected
       And user is able to deselect all the options
       And user selects options A, B and C from the dropdown
       Then selected options are displayed on the page


      Scenario: User should be able to create a new Shelf
        When user clicks on add shelf button
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
         When user clicks on filter dropdown
         And user clicks Rearrange Columns toggle switch
         And user drags each menu item to their preferred order
         And user switches off the Rearrange Columns toggle
         Then user verifies the Shelf Table column names






