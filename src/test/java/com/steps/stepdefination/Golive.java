package com.steps.stepdefination;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.steps.cucumber.AbstractSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utility.CommonMethods;
import utility.CommonStaticStrings;
import utility.locators.GoLiveLocators;


public class Golive extends AbstractSteps implements GoLiveLocators {
    private final Page page = testContext().getBrowserPage(); // Playwright Page object

    CommonMethods commonMethods = new CommonMethods();
    //CommonSteps commonSteps = new CommonSteps();
    String GoLiveTitle = commonMethods.genrategolivetitle(); // Fixed typo here
    String GoLiveDescription = commonMethods.genrategolivedescription(); // Fixed typo here

    @Given("User go to the Go Live page")
    public void userGoToTheGoLivePage() {
        page.locator(GoLivePageLocator).click(); // Playwright equivalent of clicking a link
    }

    @When("Go Live > Your Live Information: User enters all valid data")
    public void GoLiveYourLiveInformationUserentersallvaliddata() {
        testContext().set(CommonStaticStrings.GO_LIVE_TITLE, GoLiveTitle);
        testContext().set(CommonStaticStrings.GO_LIVE_DESCRIPTION, GoLiveDescription);

        page.locator(GoLiveTitleLocator).fill(GoLiveTitle); // Filling in the title
        page.locator(GoLiveDescriptionLocator).fill(GoLiveDescription); // Filling in the description
    }

    @And("Go live: User click the Next button after entering the title")
    public void goLiveUserClickTheNextButtonAfterEnteringTheTitle() {
        page.locator(TitleNextButtonLocator).click(); // Clicking the next button
    }

    @And("Go live: User select a any one product click the Next button")
    public void goLiveUserSelectAAnyOneProductClickTheNextButton() throws InterruptedException {
        Thread.sleep(4000);
        String selectedProductName = page.locator(ProductSelectionLocator).textContent().trim();

        testContext().getScenarioLogger().log("Selected Product Name: " + selectedProductName);
        page.locator(ProductNextButtonLocator).click();
    }

    @And("Go live > Connect to Broadcast Channels: User enable social media {string} toggle")
    public void goLiveConnectToBroadcastChannelsUserEnableSocialMediaToggle(String socialMedia) throws InterruptedException {
        Thread.sleep(3000);
        enableBroadcastChannel(socialMedia, "enable");
    }


    @And("Go live: User click the Next button after social media selection")
    public void goLiveUserClickTheNextButtonAfterSocialMediaSelection() {
        page.locator(SocialNextButtonLocator).click();
        testContext().getScenarioLogger().log("Social Media page is enabled");
    }

    @And("Go live: user click the Go Live button")
    public void goLiveUserClickTheGoLiveButton(){
        page.locator(GoLiveButtonLocator).click(); // Click the "Go Live" button
        page.waitForSelector(GoLiveProcessLocator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
        String GoLive= page.locator(LiveLocator).textContent();
        testContext().getScenarioLogger().log("Session is successfully started  " + GoLive);
    }

    @And("Go live: session Ends successfully")
    public void goLiveSessionEndsSuccessfully() {
        page.waitForTimeout(120000);
        page.locator(EndSessionButtonLocator).click();
        page.locator(ConfirmEndButtonLocator).click();
        testContext().getScenarioLogger().log("Session is successfully Ended ");
    }


    private void enableBroadcastChannel(String channelName, String toggleEvent) throws InterruptedException {

        String enableDisableLocator = null;
        if (toggleEvent.equals("enable")) {
            enableDisableLocator = "label[@class='toggle-yes']/..";
        } else if (toggleEvent.equals("disable")) {
            enableDisableLocator = "label[@class='toggle-no']/..";
        }
        Thread.sleep(3000);
        String toggleLocator = "//h3[contains(.,'" + channelName + "')]/../../div/div/" + enableDisableLocator;
        page.locator(toggleLocator).click();
    }

    @And("Go Live: Update the Product")
    public void goLiveUpdateTheProduct() throws InterruptedException {
        page.locator(GoLiveUpdateProductLocator).click();
        Thread.sleep(6000);
    }

    @And("Go live: Publish the Comment")
    public void goLivePublishThaComment() throws InterruptedException {
        Thread.sleep(2000);
        page.locator(GoliveCommentFieldLocator).fill("test the comment");
        page.locator(PublishCommentButtonLocator).click();
        Thread.sleep(4000);
    }
}
