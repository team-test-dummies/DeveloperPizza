@login
Feature: Login

  Background:
    Given User is on the login page

  # LOGIN POSITIVE
  #BR-1 / TC- <--Employer login
  Scenario Outline: Login with valid credentials
    When User enters "<username>" into username input
    And User enters "<password>" into password input
    And User clicks on the login button
    Then User should be logged in successfully
  Examples:
    |username|password|
    |rickmonald|guest|
    |halffoods|guest|

  # LOGIN NEGATIVE
  #BR-1 / TC-
  Scenario: Login with correct username and incorrect password
    When User enters "rickmonald" into username input
    And User enters "password" into password input
    And User clicks on the login button
    Then An alert should be displayed with the message "Invalid username or password"

  Scenario: Login with incorrect username and correct password
    When User enters "username" into username input
    And User enters "guest" into password input
    And User clicks on the login button
    Then An alert should be displayed with the message "Invalid username or password"

  Scenario: Login with invalid credentials
    When User enters "username" into username input
    And User enters "password" into password input
    And User clicks on the login button
    Then An alert should be displayed with the message "Invalid username or password"

  Scenario: Login fields are empty
    When User enters "" into username input
    And User enters "" into password input
    And User clicks on the login button
    Then An alert should be displayed with the message "Fields cannot be empty"

  Scenario: User can logout
    When User enters "rickmonald" into username input
    And User enters "guest" into password input
    And User clicks on the login button
    Then User should be logged in successfully
    When User clicks on the logout button
    Then User should be logged out successfully