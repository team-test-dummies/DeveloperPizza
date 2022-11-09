@devfunction
Feature: DevFunction

  Background:
    Given The developer is logged
    Given The developer is on the home page

  # BR-6 --> TC-#
  Scenario Outline: Developer edit profile
    When Developer clicks "Account" button
    Then Developer should be on the account page
    When Developer clicks "Account Settings" Button
    Then A display box of the acount popsup
    Then The fields should be uneditable
    When Developer clicks "Edit" button within the display box
    Then The fields should be editable
    When Devloper types "<skillset>" into textarea
    When Devloper types "<location>" into textarea
    When Devloper types "<availability>" into textarea
    When Devloper types "<salary>" into textarea
    When Devloper types "<experience>" into textarea
    When Devloper types "<certifications>" into textarea
    When Devloper types "<languages>" into textarea
    When Devloper types "<frameworks>" into textarea
    When Devloper types "<databases>" into textarea
    When Devloper types "<operatingsystem>" into textarea
    When Devloper types "<tools>" into textarea
    When Devloper types "<hobbies>" into textarea
    And Developer clicks "Save" Button
    Then A confirmation prompt appears "Account Updated"
    And Developer clicks the "Ok" button
    And Developer clicks "Done"
    Then Developer is sent back to account page

    Examples:
      | skillset | location | availability | salary | experience | certifications | languages | frameworks | databases | operatingsystem | tools | hobbies |
