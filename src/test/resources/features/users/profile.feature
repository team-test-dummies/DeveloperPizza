@profile
Feature: Profile

  Background:
    Given User on the login page
    Given User clicks on the profile button

    # Account Functionality

    # VIEW PROFILE
    Scenario: User can see their account
    Then User should see their account details

    # VIEW ORDERS
    Scenario: User can see their orders
    Then User should see their order history

    # EDIT
    Scenario: User can edit their order
    Then User should be able to edit their order

    # DELETE
    Scenario: User can delete their order
    Then User should be able to delete their order