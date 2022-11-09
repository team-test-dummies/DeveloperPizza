@createPost

Feature: Create Post

  Background: Employer logs in to create a new job post
    Given Employer is logged in on the order page
    When Employer clicks Create Job button
    Then Employer sees a form to create a new job post

  Scenario: Create a job post with pre-made template
    Given Employer selects a pre-made job description from the template drop down
    When Employer adds a job title
    And Employer sets a location
    And Employer sets a salary range
    And Employer selects qualifications
    And Employer clicks the Order button
    Then The job is posted to the order page

  Scenario: Create a job post with customized template
    Given Employer selects a pre-made job description from the template drop down
    And Employer edits the input fields to fit their needs
    When Employer adds a job title
    And Employer sets a location
    And Employer sets a salary range
    And Employer selects qualifications
    And Employer clicks the Order button
    Then The job is posted to the order page

  Scenario: Create a job post without template
    Given Employer selects None from the template drop down
    And Employer creates their own job description
    And Employer edits the input fields to fit their needs
    When Employer adds a job title
    And Employer sets a location
    And Employer sets a salary range
    And Employer selects qualifications
    And Employer clicks the Order button
    Then The job is posted to the order page


