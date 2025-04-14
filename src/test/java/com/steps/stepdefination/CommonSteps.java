package com.steps.stepdefination;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.options.AriaRole;
import com.steps.cucumber.AbstractSteps;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import utility.Constant;

import java.nio.file.Paths;


import static org.assertj.core.api.Assertions.assertThat;

public class CommonSteps extends AbstractSteps {
    private final Page page = testContext().getBrowserPage();

    @Given("User go to Sign Up page")
    public void userGoToSignUpPage() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login / Register")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("SignUp")).click();
    }

    @And("Message should be visible as {string}")
    public void messageShouldBeVisibleAs(String expectedMessage) {
        page.locator("(//div[@class='Toastify']/div/div/div/div)[2]").waitFor();
        String actualMessage = page.locator("(//div[@class='Toastify']/div/div/div/div)[2]").textContent();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Given("User go to login page")
    public void userGoToLoginPage() {
        page.locator("button.btn.btn-primary.btn-sm").click();
    }


    @Given("User login with valid credentials {string} and password {string}")
    public void userLoginWithValidCredentialsAndPassword(String email, String password) throws InterruptedException {

        if (Constant.CUSTOM_LOGIN == false) {
            page.locator("button.btn.btn-primary.btn-sm").click();
            page.locator("#user-email").fill(email);
            page.locator("#user-password").fill(password);
            Thread.sleep(60000);
            page.locator("//button[text()='Login']").click();
            Thread.sleep(20000);
            page.context().storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("logindetails.json")));
        }
    }


}
