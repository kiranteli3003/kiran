@replay-history
Feature: Replay History Functionality


  As a user,
  I want to access the Replay History page,
  So that I can search and verify past session details.

  @positive
  Scenario: Verify Replay History Redirection
    Given User login with valid credentials "kiran.teli+009@brainvire.com" and password "Test@123"
    When ReplayHistory: user navigates to the Replay History page
    Then ReplayHistory: list should display all available data
    When ReplayHistory: user enters search data and performs a search
    Then ReplayHistory: search results should be displayed correctly
    And ReplayHistory: Clear the search field
    When ReplayHistory: user clicks on a insight button
    Then ReplayHistory: user should be redirected to the Replay History to Insight page
   And ReplayHistory: list data should match the details on the Insight page
   When ReplayHistory: user clicks the back button on the Insight detail page
    Then ReplayHistory: user should be redirected back to the Replay History list page
    And ReplyHistory: User Click on the Sorting options

