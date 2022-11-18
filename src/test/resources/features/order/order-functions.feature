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