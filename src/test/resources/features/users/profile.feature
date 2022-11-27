@profile
Feature: Profile

  Background:
    Given User is logged in
    And User is on the profile page

    # CHECK PROFILE CONTENT
  Scenario: Check profile content
    Then User should see their profile content
    Then User confirms profile content

    # DELETE PROFILE POSITIVE
  Scenario: User deletes profile
    When User clicks the profile delete button
    And A popup appears stating to input credentials to delete profile
    And User inputs their username
    And User inputs their password
    And User clicks the delete button
    Then User sees an alert stating the account was successfully deleted
    And User is navigated to the login page
    And User attempts to login with valid credentials
    And Users sees an error message stating invalid username or password

  Scenario: User cancels delete profile
    When User clicks the profile delete button
    And A popup appears stating to input credentials to delete profile
    And User clicks the cancel button
    Then The popup is closed

    # DELETE PROFILE NEGATIVE
  Scenario: User deletes profile with invalid username
    When User clicks the profile delete button
    And A popup appears stating to input credentials to delete profile
    And User inputs an invalid username
    And User inputs their password
    And User clicks the delete button
    Then User sees an alert stating username or password is incorrect

  Scenario: User deletes profile without username
    When User clicks the profile delete button
    And A popup appears stating to input credentials to delete profile
    And User inputs their password
    And User clicks the delete button
    Then User sees an alert stating username or password is required

  Scenario: User deletes profile with invalid password
    When User clicks the profile delete button
    And A popup appears stating to input credentials to delete profile
    And User inputs their username
    And User inputs an invalid password
    And User clicks the delete button
    Then User sees an alert stating username or password is incorrect

  Scenario: User deletes profile without password
    When User clicks the profile delete button
    And A popup appears stating to input credentials to delete profile
    And User inputs their username
    And User clicks the delete button
    Then User sees an alert stating username or password is required