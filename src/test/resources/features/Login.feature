@login
Feature: Login Functionality

  In order to do try tro login with invalid cred
  As a valid customer details
  I want to login validation message

  @positive
  Scenario: Login with valid credentials
    Given User go to login page
    When Login: Enters valid credentials as email "kiran.teli+007@brainvire.com" and password "Test@123"
    Then Login: User should be logged in


  Scenario: Login with invalid credentials
    Given User go to login page
    When I enter invalid credentials as email "kdjsdjs@abc.com" and password "WrongPassword@1"
    And Message should be visible as "Invalid credentials "
   # Then Login: Validation message should be visible for invalid credentials

  Scenario:Login flow store the data in the json file
    Given User login with valid credentials "kiran.teli+01@brainvire.com" and password "Test@123"
# when need to auto login change the cucumbertext context script >if (Constant.CUSTOM_LOGIN == true) {
 # constant under the utility - public static Boolean CUSTOM_LOGIN = true;
  #and change the commonsteps script Constant.CUSTOM_LOGIN == false


