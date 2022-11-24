@register
Feature: Create Account

      # REGISTER POSITIVE
    Scenario: Register with valid inputs
      Given User is on the register page
      When User enters a full name
      And User enters a username
      And User enters a password
      And User enters phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees a popup for successful registration
      And User clicks Ok
      And User is redirected to the login page

      # REGISTER NEGATIVE
    Scenario: Register without full name
      Given User is on the register page
      And User enters a username
      And User enters a password
      And User enters phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid full name

    Scenario Outline: Register with invalid username
      Given User is on the register page
      When User enters a full name
      And User enters <username> as their username
      And User enters a password
      And User enters a phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid username

      Examples: |username         |
                |null             |
                |special character|
                |short username   |
                |long username    |

    Scenario Outline: Register with invalid password
      Given User is on the register page
      When User enters a full name
      And User enters a username
      And User enters <password> as their pass
      And User enters a phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid password

      Examples: |password             |
                |null                   |
                |pass with one number   |
                |pass with one uppercase|
                |short password         |
                |long password          |


    Scenario Outline: Register with invalid phone number
      When User enters a full name
      And User enters username
      And User enters password
      And User enters <phone> as their phone number
      And User enters an email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid phone number

      Examples: |phone          |
                |null           |
                |NaN            |
                |too many digits|


    Scenario Outline: Register with invalid email
      When User enters a full name
      And User enters a username
      And User enters a password
      And User enters a phone number
      And User enters <email> as their email
      And User enters a location
      And User clicks the sign up button
      Then User sees an error message for invalid email

      Examples: |email       |
                |null        |
                |only at sign|
                |no at sign  |
                |extra period|

    Scenario: Register without location
      Given User is on the register page
      When User enters a full name
      And User enters a username
      And User enters a password
      And User enters phone number
      And User enters an email
      And User clicks the sign up button
      Then User sees an error message for invalid location

