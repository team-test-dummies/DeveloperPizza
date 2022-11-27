@profile
Feature: Profile

  Background:
    Given User is logged in
    And User is on the profile page

  # CHECK PROFILE CONTENT
  Scenario: Check profile content
    Then User should see their profile content
    Then User confirms profile content
