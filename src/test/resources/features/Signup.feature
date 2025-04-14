@signup
Feature: Signup Functionality

  New register with valida data
  As a valid customer details
  Send email verification

  @positive
  Scenario: Sign Up: Verify that user is able to sign up with valid data
    Given User go to Sign Up page
    When Sign Up: Enters all valid details
     And Message should be visible as "Influencer Register Successfully"
    Then Signup: User should be registered successfully

  @negative
  Scenario: Sign Up: Verify that invalid password format message is visible for invalid password
    Given User go to Sign Up page
    When Sign Up: User enters invalid password format as "aaaaa"
    And Message should be visible as "Password Format is wrong"

  @negative
  Scenario: Signup: Verify that validation message is visible for already registered email
    Given User go to Sign Up page
    When Sign Up: Enters already registered email address as "kiran.teli+007@brainvire.com"
#    And Message should be visible as "Email already exists"
    And Message should be visible as "Please verify captcha!"



