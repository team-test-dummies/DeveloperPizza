@order
Feature: Order

  Background:
    Given The User is logged in
    And The User is on the order page

  # BR-3 --> TC-#
  Scenario: Create Order
    When The User selects premades options
    And The User selects languages options
    And The User selects tools options
    And The User selects education options
    And The User enters "location" into location field
    And The User enters "salary" into salary field
    Then The User clicks on order button

  Scenario: Create Order with premade
    When The User selects Test Automation premade option
    And the User enters "location" into location field
    And the User enters "salary" into salary field
    Then the User clicks on "Order" and a modal is opened
    Then the User clicks on "Place Order" and a modal is closed
    And the user is on the userprofile page

  Scenario: Create Order with all options selected
    Given the user is on the order page
    When they select all the options
    Then the User clicks on "Order" and a modal is opened
    Then the User clicks on "Place Order" and a modal is closed
    And the user is on the userprofile page