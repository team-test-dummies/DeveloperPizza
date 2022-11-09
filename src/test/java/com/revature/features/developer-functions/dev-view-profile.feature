@devfunction
Feature: DevFunction

  Background:
    Given The developer is logged
    Given The developer is on the home page

  # BR-6 --> TC-#
  Scenario: Developer view profile
    When Developer clicks "Account" button
    Then Developer should be on the account page
    When Developer clicks "Account Settings" Button
    Then A display box of the acount popsup
    Then The fields should be uneditable
    And Developer clicks "Done"
    Then Developer is sent back to account page