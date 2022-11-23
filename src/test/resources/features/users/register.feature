@account
Feature: Create Account

  Background:
    Given User is on the login page
    Given User clicks on the create profile button

  Scenario Outline: Create an account
    When User enters "<full name>" into full name field
    And User enters "<username>" into username field
    And User enters "<password>" into password field
    And User enters "<phone number>" into phone number field
    And User enters "<email>" into email field
    And User enters "<location>" into location field
    When User clicks the sign up button
    Then User sees an alert message "<message>"

  Examples:
    | full name | username | password | phone number | email | location | message |
    |              |         |         |            |                    |        | Registration unsuccessful |
    |              | tester2 | test2   | 2222222222 | test2@email.com    | testl2 | Registration unsuccessful |
    | test3 tester |         | test3   | 3333333333 | test3@email.com    | testl3 | Registration unsuccessful |
    | test4 tester | tester4 |         | 4444444444 | test4@email.com    | testl4 | Registration unsuccessful |
    | test5 tester | tester5 | test5   |            | test5@email.com    | testl5 | Registration unsuccessful |
    | test6 tester | tester6 | test6   | 6666666666 |                    | testl6 | Registration unsuccessful |
    | test7 tester | tester7 | test7   | 7777777777 | test7@email.com    |        | Registration unsuccessful |
    | John Doe     | JDoe    | pass123 | 7271931002 | johndoe@domain.com | Ohio   | Log in to your new account|
