@removePost

Feature: Remove Post

 Scenario: Remove a job post
    Given Employer is logged in on the order page
    When Employer selects an existing job post
    And Employer can see the job status is Filled
    And Employer clicks the Edit button
    And Employer clicks the Delete button
    Then A confirmation prompt appears stating "This post will be permanently removed"
    And Employer clicks the Ok button
    And The job post is removed from the order page