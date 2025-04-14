package utility.locators;

public interface LoginLocators {

    String EmailFieldLocator = "//input[@id='user-email']";
    String PasswordFieldLocator = "//input[@id='user-password']";
    String LoginButtonLocator = "//button[@class='btn btn-primary login-btn']";
    String SignoutLocator = "//a[text()='Sign out']";
    String LoginValidationLocator = "//div[@class='Toastify']/div/div/div[1]/div[2]";

}
