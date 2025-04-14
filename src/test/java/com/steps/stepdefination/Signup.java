package com.steps.stepdefination;

import com.steps.cucumber.AbstractSteps;
import com.microsoft.playwright.Page;
import utility.CommonMethods;
import utility.CommonStaticStrings;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.locators.SignUpLocators;

import static org.assertj.core.api.Assertions.assertThat;

public class Signup extends AbstractSteps implements SignUpLocators {
    private final Page page = testContext().getBrowserPage();
    CommonMethods commonMethods = new CommonMethods();

    @When("Sign Up: Enters all valid details")
    public void signUpEntersAllValidDetails() throws InterruptedException {
        fillSignUpForm("new", "Test@1234");
    }

    @Then("Signup: User should be registered successfully")
    public void signupUserShouldBeRegisteredSuccessfully() {
        boolean signOutButtonVisible = page.locator("//li/a[text()='Sign out']").isVisible();
        assertThat(signOutButtonVisible).as("User not logged in.").isTrue();
    }


    @When("Sign Up: User enters invalid password format as {string}")
    public void signUpUserEntersInvalidPasswordFormatAs(String invalidPassword) throws InterruptedException {
        fillSignUpForm("new", invalidPassword);
    }

    @Then("Signup: Validation message should be visible for invalid password format")
    public void signupValidationMessageShouldBeVisibleForInvalidPasswordFormat() {
        String actualMessage = page.locator("//input[@name='password']/following-sibling::small").textContent();
        assertThat(actualMessage).isEqualTo("Password must contain 1 upper case, 1 lower case, 1 number, 1 special character ( @ ! $ % ^ & #) & minimum 8 characters to maximum 40 characters");

    }

    @When("Sign Up: Enters already registered email address as {string}")
    public void signUpEntersAlreadyRegisteredEmailAddressAs(String email) throws InterruptedException {
        fillSignUpForm(email, "Test@1234");
    }


    private void fillSignUpForm(String email, String password) throws InterruptedException {
        String firstName = commonMethods.generateFirstName();
        String lastName = commonMethods.generateLastName();
        String mobileNumber = commonMethods.generateNumber(10);

        if (email.equals("new")) {
            email = firstName + "." + lastName + "@mailinator.com";
        }

        testContext().set(CommonStaticStrings.FIRST_NAME, firstName);
        testContext().set(CommonStaticStrings.LAST_NAME, lastName);
        testContext().set(CommonStaticStrings.EMAIL, email);
        testContext().set(CommonStaticStrings.MOBILE_NUMBER, mobileNumber);


        testContext().getScenarioLogger().log("firstName = " + firstName);
        testContext().getScenarioLogger().log("lastName = " + lastName);
        testContext().getScenarioLogger().log("email = " + email);
        testContext().getScenarioLogger().log("mobileNumber = " + mobileNumber);


        page.locator(firstNameLocator).fill(firstName);
        page.locator(lastNameLocator).fill(lastName);
        page.locator(NewEmailLocator).fill(email);
        page.locator(CountryDropDownLocator).click();
        page.locator(CountrySelectionLocator).click();
        page.locator(MobileNumberLocator).fill(mobileNumber);
        page.locator(GenderLocator).click();
        page.locator(GenderSelectionLocator).click();
        page.locator(NewPasswordLocator).fill(password);
        page.locator(ConfirmPasswordLocator).fill(password);
        page.locator(TandCLocator).click();
        page.locator(CreateAccountLocator).click();

    }
}
