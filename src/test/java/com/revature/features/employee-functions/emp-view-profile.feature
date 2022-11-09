@empfunction
Feature: empFunction

  Background:
    Given The Employer is logged
    Given The Employer is on the home page

  # BR-1 --> TC-#
  Scenario: Employee view profile
    When Employer clicks "Account" button
    Then Employer should be on the account page
    When Employer clicks "Account Settings" Button
    Then A display box of the acount popsup
    Then The fields should be uneditable
    And Employer clicks "Done"
    Then Employer is sent back to account page