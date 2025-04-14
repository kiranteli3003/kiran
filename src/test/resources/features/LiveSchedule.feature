@live-schedule
Feature: Live Schedule  on Platform
  As a user, I want to go live on the platform by providing a title, Description acreate the single hosting live schedule after the Seassion time is ongoing user is able to the  starting the live session.

  Scenario: Create a live schedule session for the single hosting
    Given User login with valid credentials "kiran.teli+009@brainvire.com" and password "Test@123"
    Then Login: User should be logged in
    When User go to the live schedule list screen
    And Live schedule> Create Live schedule: Click on the live schedule button
    And Live schedule> Create Schedule : Your Live Information :Enter all Valid data
    And Live schedule: Set up your Stream Sources> SingleHosting create the live schedule
    And  Message should be visible as "Session scheduled successfully."
    Then Live schedule: User Redirect to the live schedule session  list screen
    Then Live schedule: Open the view live schedule screen
    And Live schedule: Open the edit live schedule screen
    And live schedule: Edit : Update the valid data and submit
    Then  Message should be visible as "Streaming scheduled updated successfully"
    And Live schedule: Delete : Delete the created live schedule session
    Then  Message should be visible as "Scheduled streaming deleted successfully."

  Scenario: Create a live schedule session for the  Multihosting
    Given User login with valid credentials "kiran.teli+009@brainvire.com" and password "Test@123"
    Then Login: User should be logged in
    When User go to the live schedule list screen
    And Live schedule> Create Live schedule: Click on the live schedule button
    And Live schedule> Create Schedule : Your Live Information :Enter all Valid data
    And Live schedule: Set up your Stream Sources> Multihosting create the live schedule
    And  Message should be visible as "Session scheduled successfully."
    Then Live schedule: User Redirect to the live schedule session  list screen
    Then Live schedule: Open the view live schedule screen
    And Live schedule: Open the edit live schedule screen
    And live schedule: Edit : Update the valid data and submit
    Then  Message should be visible as "Streaming scheduled updated successfully"
    And Live schedule: Delete : Delete the created live schedule session
    Then  Message should be visible as "Scheduled streaming deleted successfully."

  Scenario Outline: Live schedule: Go live schedule session for the single hosting
    Given User login with valid credentials "kiran.teli+009@brainvire.com" and password "Test@123"
    Then Login: User should be logged in
    When User go to the live schedule list screen
    And Live schedule> Create Live schedule: Click on the live schedule button
    And Live schedule> Create Schedule : Your Live Information :Enter all Valid data
    And Live schedule: Set up your Stream Sources> SingleHosting create the live schedule
    And  Message should be visible as "Session scheduled successfully."
    Then Live schedule: User Redirect to the live schedule session  list screen
    Then Live Schedule : User Click on the Go live Button
    And Go live: User select a any one product click the Next button
    And Go live > Connect to Broadcast Channels: User enable social media "<socialMedia>" toggle
    And Go live: User click the Next button after social media selection
    And Go live: user click the Go Live button
    And Go Live: Update the Product
    Then Message should be visible as "Streaming product updated successfully."
    And Go live: Publish the Comment
    And  Go live: session Ends successfully
    Then Message should be visible as "Streaming ended successfully."

    Examples:
      | socialMedia |
      | Facebook    |
      #| Youtube     |

  Scenario Outline: LiveSchedule :Go live schedule session for the Multi hosting
    Given User login with valid credentials "kiran.teli+009@brainvire.com" and password "Test@123"
    Then Login: User should be logged in
    When User go to the live schedule list screen
    And Live schedule> Create Live schedule: Click on the live schedule button
    And Live schedule> Create Schedule : Your Live Information :Enter all Valid data
    And Live schedule: Set up your Stream Sources> Multihosting create the live schedule
    And  Message should be visible as "Session scheduled successfully."
    Then Live schedule: User Redirect to the live schedule session  list screen
    Then Live Schedule : User Click on the Go live Button
    And Go live: User select a any one product click the Next button
    And Go live > Connect to Broadcast Channels: User enable social media "<socialMedia>" toggle
    And Go live: User click the Next button after social media selection
    And Go live: user click the Go Live button
    And Go Live: Update the Product
    Then Message should be visible as "Streaming product updated successfully."
    And Go live: Publish the Comment
    And  Go live: session Ends successfully
    Then Message should be visible as "Streaming ended successfully."

    Examples:
      | socialMedia |
      | Facebook    |
      #| Youtube     |
