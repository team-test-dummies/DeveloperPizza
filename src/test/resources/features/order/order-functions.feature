@order
Feature: Order

  Background:
    Given The Employer is logged
    Given The Employer is on the order page

  # BR-3 --> TC-#
  Scenario: View Order
    When Employer selects existing order
    And Employer clicks "View" button
    Then A display box of the order popsup
    Then The fields should be uneditable
    When Employer clicks "Done"
    Then Employer is sent back to order page

  # BR-3 --> TC-#
  Scenario Outline: Edit Order
    When Employer selects existing order
    And Employer clicks "View" button
    Then A display box of the order popsup
    Then The fields should be uneditable
    When Employer clicks "Edit" button within the display box
    Then The fields should be editable
    And Employer edits order
    When Employer types "<skillset>" into textarea
    When Employer types "<location>" into textarea
    When Employer types "<availability>" into textarea
    When Employer types "<salary>" into textarea
    When Employer types "<experience>" into textarea
    When Employer types "<certifications>" into textarea
    When Employer types "<languages>" into textarea
    When Employer types "<frameworks>" into textarea
    When Employer types "<databases>" into textarea
    When Employer types "<operatingsystem>" into textarea
    When Employer types "<tools>" into textarea
    When Employer types "<hobbies>" into textarea
    And Employer clicks "Save" Button
    Then A confirmation prompt appears "Order Updated"
    And Employer clicks the "Ok" button
    And Employer clicks "Done"
    Then Employer is sent back to order page

    Examples:
      | skillset | location | availability | salary | experience | certifications | languages | frameworks | databases | operatingsystem | tools | hobbies |


  # BR-3 --> TC-#
  Scenario: Filter Order
    When Employer clicks "Filter" button
    And Employer selects filter options
    And Employer clicks "Search" button
    Then Orders containing the filter are listed
    When Employer selects filtered order
    And Employer clicks "View" button
    Then A display box of the order popsup
    Then The fields should be uneditable
    And Employer should see filtered results
    When Employer clicks "Done"
    Then Employer is sent back to order page