@empfunction
Feature: empFunction

  Background:
    Given The Employer is logged
    Given The Employer is on the home page

    # BR-1 --> TC-#
    Scenario Outline:
      When Employer clicks "Account" button
      Then Employer should be on the account page
      When Employer clicks "Account Settings" Button
      Then A display box of the acount popsup
      Then The fields should be uneditable
      When Employer clicks "Edit" button within the display box
      Then The fields should be editable
      When Employer types "<firstname>" into textarea
      When Employer types "<lastname>" into textarea
      And Employer clicks "Save" Button
      Then A confirmation prompt appears "Account Updated"
      And Employer clicks the "Ok" button
      And Employer clicks "Done"
      Then Employer is sent back to account page

    Examples:
      | firstname | lastname |