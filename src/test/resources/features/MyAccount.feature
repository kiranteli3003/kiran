@My-Account
Feature: MyAccount on Platform

  As a user,
  I want to access the MyAccount page,
  So that I can update my profile data, connect my social accounts, and update the comment keyword functionality.

  @positive
  Scenario: Verify MyAccount Functionality
  Given User login with valid credentials "kiran.teli+009@brainvire.com" and password "Test@123"
    When MyAccount: User navigates to the MyAccount page
    Then MyAccount: User updates the first name, last name, and mobile number
    Then  Message should be visible as "Updated user successfully"
    And MyAccount: The saved data should match the updated data
   Then MyAccount: User updates the comment keyword
    Then  Message should be visible as "Comment keyword updated successfully"