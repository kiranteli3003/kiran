package com.steps.stepdefination;

import com.microsoft.playwright.Page;
import com.steps.cucumber.AbstractSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.locators.LoginLocators;


public class LoginSteps extends AbstractSteps implements LoginLocators {
    private final Page page = testContext().getBrowserPage(); // Playwright Page object

    @When("Login: Enters valid credentials as email {string} and password {string}")
    public void loginEntersValidCredentialsAsEmailAndPassword(String email, String password) throws InterruptedException {
        Thread.sleep(2000);
        page.locator(EmailFieldLocator).fill(email); // Fill email
        page.locator(PasswordFieldLocator).fill(password); // Fill password
        page.locator(LoginButtonLocator).click(); // Click login button
    }

    @Then("Login: User should be logged in")
    public void loginUserShouldBeLoggedIn() throws InterruptedException {
        Thread.sleep(3000);
        String signOutText = page.locator(SignoutLocator).textContent(); // Get the Sign out text
        testContext().getScenarioLogger().log("User is successfully logged in ");
    }
    @When("I enter invalid credentials as email {string} and password {string}")
    public void iEnterInvalidCredentialsAsEmailAndPassword(String email, String password) throws InterruptedException {
        Thread.sleep(2000); // Replace with Playwright wait logic
        page.locator(EmailFieldLocator).fill(email); // Fill email
        page.locator(PasswordFieldLocator).fill(password); // Fill password
        page.locator(LoginButtonLocator).click(); // Click login button
    }

    @Then("Login: Validation message should be visible for invalid credentials")
    public void loginValidationMessageShouldBeVisibleForInvalidCredentials() {
        String validationMessage = page.locator(LoginValidationLocator).textContent(); // Get validation message
        testContext().getScenarioLogger().log("Get the message " +validationMessage);

    }
}