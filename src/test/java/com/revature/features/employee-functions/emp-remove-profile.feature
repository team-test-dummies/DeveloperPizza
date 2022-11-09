@empfunction
Feature: empFunction

  Background:
    Given The Employer is logged
    Given The Employer is on the home page

  # BR-1 --> TC-#
  Scenario: Employee remove profile
    When Employer clicks "Account" button
    Then Employer should be on the account page
    When Employer clicks "Account Settings" Button
    Then A display box of the acount popsup
    Then The fields should be uneditable
    And Employer clicks "Remove Account"
    Then A confirmation prompt appears stating "This account will be permanently removed"
    And Employer clicks the "Ok" button
    Then Employer is sent back to login page