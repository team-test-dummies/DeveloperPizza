@account
Feature: Create Account

  # BR-7 --> TC-#
  Scenario Outline: Create an Account with valid input
    Given Actor is on the login page
    Given Actor clicks "Create Account" button
    And Actor is on the "Create Account" page
    When Actor selects "<accounttype> "
    And Actor enters valid "<username>"
    And Actor enters valid "<password>"
    And Actor enters valid "<email>"
    And Actor clicks "Create Account" button
    Then A confirmation prompt appears "Account Created"
    And Actor clicks the "Ok" button
    Then Actor is sent back to login page

    Examples:
      | accounttype | username | password | email |

  # BR-7 --> TC-#
  Scenario Outline: Create an Account with invalid username
    Given Actor is on the login page
    Given Actor clicks "Create Account" button
    And Actor is on the create account page
    When Actor selects "<accounttype> "
    And Actor enters valid "<username>"
    And Actor enters valid "<password>"
    And Actor enters valid "<email>"
    And Actor clicks "Create Account" button
    Then An alert prompt appears "not a valid username"
    Then Actor is sent back to create account page

    Examples:
      | accounttype | username | password | email |

  # BR-7 --> TC-#
  Scenario Outline: Create an Account with invalid password
    Given Actor is on the login page
    Given Actor clicks "Create Account" button
    And Actor is on the "Create Account" page
    When Actor selects "<accounttype> "
    And Actor enters valid "<username>"
    And Actor enters valid "<password>"
    And Actor enters valid "<email>"
    And Actor clicks "Create Account" button
    Then An alert prompt appears "not a valid password"
    Then Actor is sent back to create account page

    Examples:
      | accounttype | username | password | email |

  # BR-7 --> TC-#
  Scenario Outline: Create an Account with invalid email
    Given Actor is on the login page
    Given Actor clicks "Create Account" button
    And Actor is on the "Create Account" page
    When Actor selects "<accounttype> "
    And Actor enters valid "<username>"
    And Actor enters valid "<password>"
    And Actor enters valid "<email>"
    And Actor clicks "Create Account" button
    Then An alert prompt appears "not a valid email"
    Then Actor is sent back to create account page

    Examples:
      | accounttype | username | password | email |

  # BR-7 --> TC-#
  Scenario Outline: Create an Account with no input
    Given Actor is on the login page
    Given Actor clicks "Create Account" button
    And Actor is on the "Create Account" page
    When Actor selects "<accounttype> "
    And Actor enters valid "<username>"
    And Actor enters valid "<password>"
    And Actor enters valid "<email>"
    And Actor clicks "Create Account" button
    Then An alert prompt appears "feilds left empty"
    Then Actor is sent back to create account page

    Examples:
      | accounttype | username | password | email |