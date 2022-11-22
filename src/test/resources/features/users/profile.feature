@profile
Feature: Profile

  Background:
    Given The User is logged in

    Scenario: User can see their account
    When The User is on the profile page
    Then The User should see their account details

    Scenario: User can see their orders
    When The User is on the profile page
    Then The User should see their order history
