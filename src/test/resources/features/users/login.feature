@login
Feature: Login

  Background:
    Given User on the login page

  # LOGIN POSITIVE
  #BR-1 / TC- <--Employer login
  #BR-4 / TC- <--Developer login
  Scenario: Login with valid credentials
    When User enters "rickmonald" into username field
    And User enters "guest" into password field
    And User clicks on the login button
    Then User should be logged in successfully

  # LOGIN NEGATIVE
  #BR-1 / TC-
  Scenario: Login with correct username and incorrect password
    When User enters "rickmonald" into username field
    And User enters "password" into password field
    And User clicks on the login button
    Then An alert should be displayed with the message "Invalid username or password"

  Scenario: Login with incorrect username and correct password
    When User enters "username" into username field
    And User enters "guest" into password field
    And User clicks on the login button
    Then An alert should be displayed with the message "Invalid username or password"

  Scenario: Login with invalid credentials
    When User enters "username" into username field
    And User enters "password" into password field
    And User clicks on the login button
    Then An alert should be displayed with the message "Invalid username or password"

  Scenario: Login fields are empty
    When User enters "" into username field
    And User enters "" into password field
    And User clicks on the login button
    Then An alert should be displayed with the message "Invalid username or password"