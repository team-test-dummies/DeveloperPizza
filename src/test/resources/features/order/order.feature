@order
Feature: Order

  Background:
    Given User is logged in
    And User clicks on the "Create Order" button
    And User is on the order page

  # CREATE ORDER
  Scenario Outline: Create an order
    When User enters name of order "<name>"
    And User selects a premade "<preOp>"
    And User selects a language(s) "<langOp>
    And User selects a tool(s) "<tool>"
    And User selects a eduction level "<edu>"
    And User sets a salary "<salary>"
    Then User clicks on the "Order" button

  Examples:
    | name | preOp | langOp | tool | edu | salary |
    |       |   |   |   |   |       |
    | oTest | 1 | 1 | 1 | 1 | 50000 |
    |       | 1 | 1 | 1 | 1 | 50000 |
    | oTest |   | 1 | 1 | 1 | 50000 |
    | oTest | 1 |   | 1 | 1 | 50000 |
    | oTest | 1 | 1 |   | 1 | 50000 |
    | oTest | 1 | 1 | 1 |   | 50000 |
    | oTest | 1 | 1 | 1 | 1 |       |