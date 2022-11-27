@profile
Feature: Profile

  Background:
    Given User is logged in
    And User is on the profile page

  # CHECK PROFILE CONTENT
  Scenario: Check profile content
    Then User should see their profile content
    Then User confirms profile content

  # CHECK ORDER(S)
  Scenario: Check order list
  Then User should see their order list

  # EDIT ORDER(S)
  Scenario: Edit order
      When User clicks edit button
      Then User should see the edit order popup
      And User edits order name
      And User edits language option
      And User edits tool option
      And User edits education option
      And User edits salary amount
      And User clicks confirm button
      Then User should see edited order

  # DELETE ORDER(S)
  Scenario: Delete order
    When User clicks delete button