@Go-live
Feature: Go Live on Platform
  As a user, I want to go live on the platform by providing a title, description, selecting a product, enabling Facebook, and starting the live session.

  Scenario Outline: Successfully start a live session
    Given User login with valid credentials "kiran.teli+009@brainvire.com" and password "Test@123"
    Then Login: User should be logged in
    Given User go to the Go Live page
    When Go Live > Your Live Information: User enters all valid data
    And Go live: User click the Next button after entering the title
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
