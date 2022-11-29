@createorder
Feature: Order

  Background:
    Given The User is logged in

  # BR-3 --> TC-#
  Scenario: Create Order
    Given the user is on the order page
    When the user selects test automation premade option
    And the user adds a name
    And the user selects tools options
    And the user selects education options
    And the user enters "32564" into salary field
    Then the user clicks on order and a modal is opened
    Then the user clicks on place order and a modal is closed
    And the user is on the userprofile page
 #TC-17
  Scenario: Create Order, select premade and change selections
    Given the user is on the order page
    When the user selects test automation premade option
    And the user selects all of the languages
    And the user selects tools options
    And the user selects education options
    And the user enters "23432" into salary field
    Then the user clicks on order and a modal is opened
    Then the user clicks on place order and a modal is closed
    And the user is on the userprofile page
  #TC-18
  Scenario: Create Order with all options selected
    Given the user is on the order page
    When the user selects all of the languages
    And the user adds a name
    And the user selects all of the tools
    And the user selects education options
    And the user enters "23222" into salary field
    Then the user clicks on order and a modal is opened
    Then the user clicks on place order and a modal is closed
    And the user is on the userprofile page
 #TC-19
  Scenario: Check to see if the order is showing up on the order page
    Given the user is on the order page
    When the user selects a language
    And the user adds a name
    And the user selects a tool
    And the user selects education options
    And the user enters "78987" into salary field
    Then the user clicks on order and a modal is opened
    Then the user clicks on place order and a modal is closed
    And the user is on the userprofile page

  Scenario: Languages are being tallied
    Given the user is on the order page
    When the user selects all of the languages
    Then the tally equals the same number of languages

  Scenario: Tools are being tallied
    Given the user is on the order page
    When the user selects all of the tools
    Then the tally equals the same number of tools

  Scenario: All Tools and all languages are being tallied
    Given the user is on the order page
    When the user selects all of the tools
    And the user selects all of the languages
    Then the tally equals the total number of inputs

  # Negative testing
  # TC-48
  Scenario: Create order without salary
    Given the user is on the order page
    When the user selects a language
    And the user adds a name
    And the user selects a tool
    And the user selects education options
    And the user enters "" into salary field
    Then the user clicks on order and a modal is opened
    Then the user clicks on place order and a modal is closed
    And the user is on the userprofile page
  #TC-49
  Scenario: Create order with only salary
    Given the user is on the order page
    When the user enters "24234" into salary field
    Then the user clicks on order and a modal is opened
    Then the user clicks on place order and a modal is closed
    And the user is on the userprofile page