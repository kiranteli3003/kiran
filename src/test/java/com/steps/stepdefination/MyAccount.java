package com.steps.stepdefination;

import com.steps.cucumber.AbstractSteps;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.CommonMethods;
import utility.CommonStaticStrings;
import utility.locators.MyAccountLocators;



public class MyAccount extends AbstractSteps implements MyAccountLocators {

    private final Page page = testContext().getBrowserPage();
    CommonMethods commonMethods = new CommonMethods();

    @When("MyAccount: User navigates to the MyAccount page")
    public void userNavigatesToTheMyAccountPage() throws InterruptedException {
        page.locator(MyAccountLocator).click();
        page.locator(MyProfileLocator).click();
        Thread.sleep(3000);
    }

    @Then("MyAccount: User updates the first name, last name, and mobile number")
    public void userUpdatesTheFirstNameLastNameAndMobileNumber() throws InterruptedException {
        Thread.sleep(3000);
        String firstName = commonMethods.generateFirstName();
        String lastName = commonMethods.generateLastName();
        String mobileNumber = commonMethods.generateNumber(10);

        testContext().set(CommonStaticStrings.FIRST_NAME, firstName);
        testContext().set(CommonStaticStrings.LAST_NAME, lastName);
        testContext().set(CommonStaticStrings.MOBILE_NUMBER, mobileNumber);


        testContext().getScenarioLogger().log("firstName = " + firstName);
        testContext().getScenarioLogger().log("lastName = " + lastName);
        testContext().getScenarioLogger().log("mobileNumber = " + mobileNumber);

        page.locator(MyProfileFirstnameFieldLocator).clear();
        page.locator(MyProfileFirstnameFieldLocator).fill(firstName);
        page.locator(MyProfileLastnameFieldLocator).clear();
         page.locator(MyProfileLastnameFieldLocator).fill(lastName);
        page.locator(MyProfileMobileFieldLocator).clear();
         page.locator(MyProfileMobileFieldLocator).fill(mobileNumber);
         page.locator("//button[text() ='Update'][1]").first().click();
    }

    @And("MyAccount: The saved data should match the updated data")
    public void theSavedDataShouldMatchTheUpdatedData() throws InterruptedException {
        Thread.sleep(3000);
        String expectedFirstName = (String) testContext().get(CommonStaticStrings.FIRST_NAME);
        String expectedLastName = (String) testContext().get(CommonStaticStrings.LAST_NAME);
        String expectedMobileNumber = (String) testContext().get(CommonStaticStrings.MOBILE_NUMBER);

        String actualFirstName = page.locator("//input[@id='firstName']").inputValue();
        String actualLastName = page.locator("//input[@id='lastName']").inputValue();
        String actualMobileNumber = page.locator("//input[@id='phoneNumber']").inputValue();

        testContext().getScenarioLogger().log("Expected First Name: " + expectedFirstName + ", Actual First Name: " + actualFirstName);
        testContext().getScenarioLogger().log("Expected Last Name: " + expectedLastName + ", Actual Last Name: " + actualLastName);
        testContext().getScenarioLogger().log("Expected Mobile Number: " + expectedMobileNumber + ", Actual Mobile Number: " + actualMobileNumber);

        assert expectedFirstName.equals(actualFirstName) : "First Name does not match!";
        assert expectedLastName.equals(actualLastName) : "Last Name does not match!";
        assert expectedMobileNumber.equals(actualMobileNumber) : "Mobile Number does not match!";
    }


    @Then("MyAccount: User updates the comment keyword")
    public void userUpdatesTheCommentKeyword() {
        page.locator("//input[@placeholder='Enter Keywords']").clear();
        page.locator("//input[@placeholder='Enter Keywords']").fill("Auto");
        page.locator("(//button[@class='btn btn-primary'][text()='Update'])[2]").click();
    }



}
