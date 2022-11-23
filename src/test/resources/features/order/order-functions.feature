@order
Feature: Order

  Background:
    Given The User is logged in
    And The User is on the order page

  # BR-3 --> TC-#
  Scenario: Create Order
    And The User selects premades options
    And The User selects languages options
    And The User selects tools options
    And The User selects education options
    And The User enters "location" into location field
    And The User enters "salary" into salary field
    Then The User clicks on order button

  Scenario Outline: Create Order with premade
    When The User selects <premade> option
    And the User enters "location" into location field
    And the User enters "salary" into salary field
    Then the User clicks on order and a modal is opened
    Then the User clicks on place order and a modal is closed
    And the user is on the userprofile page
    Examples:
    | premade |
    |Back End Developer|
    |Cybersecurity Specialist|
    |DevOps Engineer|
    |Frontend Developer|
    |Full sStack Developer|
    |Test Automation Engineer|


  Scenario: Create Order with all options selected
    Given the user is on the order page
    When the user selects all of the languages
    And the user selects all of the tools
    Then the User clicks on order and a modal is opened
    Then the User clicks on place order and a modal is closed
    And the user is on the userprofile page