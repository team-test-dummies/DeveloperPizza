@devfunction
Feature: DevFunction

  Background:
    Given The developer is logged
    Given The developer is on the order page

  # BR-5 --> TC-#
  Scenario Outline: Developer Orders
    When Developer clicks "New Order" button
    Then A display box of the order popsup
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
    Then A confirmation prompt appears "Order Complete"
    And Employer clicks the "Ok" button
    Then Developer is sent back to order page

 Examples:
    | skillset | location | availability | salary | experience | certifications | languages | frameworks | databases | operatingsystem | tools | hobbies |
