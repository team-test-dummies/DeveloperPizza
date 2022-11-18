@account
Feature: Create Account

  Background:
    Given User is on the login page
    And User clicks on the register button

  # BR-7 --> TC-#
  Scenario: Create an Account with valid data
    When User enters "full name" into full name field
    And User enters "username" into username field
    And User enters "password" into password field
    And User enters "phone number" into phone number field
    And User enters "email" into email field
    And User enters "location" into location field
    Then User clicks the sign up button
