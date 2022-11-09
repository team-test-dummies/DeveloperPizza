@login

Feature: Login

  # LOGIN POSITIVE
  Scenario Outline: Login with valid credentials
    Given <user> is on the login page
    When <user> enters a valid username
    And <user> enters a valid password
    And <user> clicks the Login button
    Then <user> is navigated to the homepage
    And <user> can see their <item> displayed

    Examples:
    | user      | item            |
    | employer  | job posts       |
    | developer | available orders|

    # LOGIN NEGATIVE
  Scenario: Login with valid username and invalid password
    Given User is on the login page
    When User enters a valid username
    And User enters a valid password
    And User clicks the Login button
    Then An alert appears stating "Incorrect username/password"

  Scenario: Login with invalid username and valid password
    Given User is on the login page
    When User enters a valid username
    And User enters a valid password
    And User clicks the Login button
    Then An alert appears stating "Incorrect username/password"

  Scenario: Login with invalid credentials
    Given User is on the login page
    When User enters a invalid username
    And User enters a invalid password
    And User clicks the Login button
    Then An alert appears stating "Incorrect username/password"