@snapshot
Feature: image tour of the application

  Scenario: Tour
    Given User is on the register page
    Then Take a picture of register
    Given User is on the login page
    Then Take a picture of login
    Given User is logged in
    And User is on the profile page
    Then Take a picture of profile
    When User clicks on order edit button
    Then Take a picture of editOrder
    When User clicks on confirm
    And the user is on the order page
    Then Take a picture of order
