@profile
Feature: Profile

  Background:
    Given User is logged in
    And User is on the profile page

  # CHECK PROFILE CONTENT
  Scenario: Check profile content
    Then User should see their profile content
    And User confirms username is displayed
    And User confirms fullname is displayed
    And User confirms phonenumber is displayed
    And User confirms email is displayed
    And User confirms location is displayed

  # CHECK ORDER(S)
  Scenario: Check order list
  Then User should see their order list
  And User confirms order list size

  # EDIT ORDER(S)
  Scenario Outline: Edit order
      When User clicks "edit" button
      Then User should see the edit order page
      And User changes "<element>"

  Examples:
    | element         |
    | orderID         |
    | Name            |
    | Languages       |
    | Tools           |
    | Education Level |
    | Salary          |

  # DELETE ORDER(S)
  Scenario: Delete order
    When User clicks "delete" button
    Then User should see the delete order page
    And User confirms order list size