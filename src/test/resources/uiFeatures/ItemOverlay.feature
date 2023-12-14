@regression
@FETCH-298 @FETCH-180 @overlay
Feature: Overlay slide Validation
  Background:
    Given user navigates to Item Management Page

Scenario: User should be able to validate Overlay slide with Items in Tray details
    When user clicks on any item in the table of items
    Then the overlay slide is visible
    And user verifies item in tray details on Overlay Slide

     |labelname                 |
     |Barcode:                  |
     |Media Type:               |
     |Size Class:               |
     |Volume:                   |
     |Container Type:           |
     |Subcollection:            |
     |Dimensions:               |
     |Condition:                |
     |Accession Date:           |
     |Withdrawal Date:          |
     |Arrival Date:             |

Scenario: User should be able to close Overlay Slide using X button
   When user clicks on any item in the table of items
   Then the overlay slide is visible
   And the x button is clickable

Scenario: User should be able to close Overlay Slide by clicking outside of the overlay
   When user clicks on any item in the table of items
   Then the overlay slide is visible
   And user clicks outside of overlay
   Then the overlay slide is not visible