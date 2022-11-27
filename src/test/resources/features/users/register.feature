@register
Feature: Create Account

  Background:
    Given User is on the register page

      # REGISTER POSITIVE

    Scenario: Register with valid inputs
      When User enters a full name
      And User enters a username
      And User enters a password
      And User enters a phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees a popup for successful registration
      And User clicks Ok
      And User is redirected to the login page

      # REGISTER NEGATIVE FULL NAME

    Scenario: Register without full name
      And User enters a username
      And User enters a password
      And User enters a phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid full name

      # REGISTER NEGATIVE USERNAME

    Scenario: Register without username
      When User enters a full name
      And User enters a password
      And User enters a phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid username

    Scenario: Register with special characters for username
      When User enters a full name
      And User enters special characters in their username
      And User enters a password
      And User enters a phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid username

    Scenario: Register short username
      When User enters a full name
      And User enters a short username
      And User enters a password
      And User enters a phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid username

    Scenario: Register long username
      When User enters a full name
      And User enters a long username
      And User enters a password
      And User enters a phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid username

      # REGISTER NEGATIVE PASSWORD

    Scenario: Register without password
      When User enters a full name
      And User enters a username
      And User enters a phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid password

    Scenario: Register with one number in password
      When User enters a full name
      And User enters a username
      And User enters one number in password
      And User enters a phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid password

    Scenario: Register with one uppercase letter in password
      When User enters a full name
      And User enters a username
      And User enters one uppercase letter in password
      And User enters a phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid password

    Scenario: Register with short password
      When User enters a full name
      And User enters a username
      And User enters a short password
      And User enters a phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid password

    Scenario: Register with long password
      When User enters a full name
      And User enters a username
      And User enters a long password
      And User enters a phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid password

      # REGISTER NEGATIVE PHONE NUMBER

    Scenario: Register without phone number
      When User enters a full name
      And User enters a username
      And User enters a password
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid phone number

    Scenario: Register NaN for phone number
      When User enters a full name
      And User enters a username
      And User enters a password
      And User enters NaN as their phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid phone number

    Scenario: Register too many digits for phone number
      When User enters a full name
      And User enters a username
      And User enters a password
      And User enters too many digits as their phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid phone number

      # REGISTER NEGATIVE EMAIL

    Scenario: Register without email
      When User enters a full name
      And User enters a username
      And User enters a password
      And User enters a phone number
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid email

    Scenario: Register email with only at sign
      When User enters a full name
      And User enters a username
      And User enters a password
      And User enters a phone number
      And User enters email with at sign
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid email

    Scenario: Register email without at sign
      When User enters a full name
      And User enters a username
      And User enters a password
      And User enters a phone number
      And User enters email without at sign
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid email

    Scenario: Register email with extra periods
      When User enters a full name
      And User enters a username
      And User enters a password
      And User enters a phone number
      And User enters extra periods in their email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid email

      # REGISTER NEGATIVE LOCATION
    Scenario: Register without location
      When User enters a full name
      And User enters a username
      And User enters a password
      And User enters a phone number
      And User enters an email
      And User clicks the sign up button
      Then User sees an error message for invalid location

