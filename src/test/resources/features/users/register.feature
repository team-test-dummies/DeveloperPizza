@register
Feature: Create Account

  Background:
    Given User is on the login page
    When User clicks on the create profile link
    Then User redirects to the register page

      # REGISTER POSITIVE
    Scenario: Register with valid inputs
      When User enters 'Jane Doe' into full name field
      And User enters 'jane123' into username field
      And User enters 'Password1' into password field
      And User enters '555-555-5555' into phone number field
      And User enters 'jane@gmail.com' into email field
      And User enters 'Georgia' into location field
      And User clicks the sign up button
      Then User sees a popup for successful registration
      And User clicks Ok
      And User is redirected to the login page

      # REGISTER NEGATIVE
    Scenario: Register without full name
      When User enters 'jane123' into username field
      And User enters 'Password1' into password field
      And User enters '555-555-5555' into phone number field
      And User enters 'jane@gmail.com' into email field
      And User enters 'Georgia' into location field
      And User clicks the sign up button
      Then User sees an error message for invalid full name

    Scenario Outline: Register with invalid username
      When User enters 'Jane Doe' into full name field
      And User enters <username> into username field
      And User enters 'Password1' into password field
      And User enters '555-555-5555' into phone number field
      And User enters 'jane@gmail.com' into email field
      And User enters 'Georgia' into location field
      And User clicks the sign up button
      Then User sees an error message for invalid username

      Examples: |username         |
                |null             |
                |jane             |
                |jane$%&          |
                |averylongusername|

    Scenario Outline: Register with invalid password
      When User enters 'Jane Doe' into full name field
      And User enters 'jane123' into username field
      And User enters <password> into password field
      And User enters '555-555-5555' into phone number field
      And User enters 'jane@gmail.com' into email field
      And User enters 'Georgia' into location field
      And User clicks the sign up button
      Then User sees an error message for invalid password

      Examples: |password         |
                |null             |
                |pass             |
                |password1        |
                |averylongpassword|


    Scenario Outline: Register with invalid phone number
      When User enters 'Jane Doe' into full name field
      And User enters 'jane123' into username field
      And User enters 'Password1' into password field
      And User enters <phone> into phone number field
      And User enters 'jane@gmail.com' into email field
      And User enters 'Georgia' into location field
      And User clicks the sign up button
      Then User sees an error message for invalid phone number

      Examples: |phone         |
                |null          |
                |phonenumber   |
                |555-55555555  |


    Scenario Outline: Register with invalid email
      When User enters 'Jane Doe' into full name field
      And User enters 'jane123' into username field
      And User enters 'Password1' into password field
      And User enters '555-555-5555' into phone number field
      And User enters <email> into email field
      And User enters 'Georgia' into location field
      And User clicks the sign up button
      Then User sees an error message for invalid email

      Examples: |email          |
                |null           |
                |jane@          |
                |janegmail.com  |
                |jane@.gmail.com|

    Scenario: Register without location
      When User enters 'Jane Doe' into full name field
      And User enters 'jane123' into username field
      And User enters 'Password1' into password field
      And User enters '555-555-5555' into phone number field
      And User enters 'jane@gmail.com' into email field
      And User clicks the sign up button
      Then User sees an error message for invalid location

