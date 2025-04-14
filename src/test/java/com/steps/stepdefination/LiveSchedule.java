package com.steps.stepdefination;

import com.microsoft.playwright.Page;
import com.steps.cucumber.AbstractSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utility.CommonMethods;
import utility.CommonStaticStrings;
import utility.locators.LiveScheduleLocators;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Locale;

public class LiveSchedule extends AbstractSteps implements LiveScheduleLocators {
    private final Page page = testContext().getBrowserPage();

    CommonMethods commonMethods = new CommonMethods();
    CommonSteps commonSteps = new CommonSteps();
    String GoLiveTitle = commonMethods.genrategolivetitle(); // Fixed typo here
    String GoLiveDescription = commonMethods.genrategolivedescription();

    @When("User go to the live schedule list screen")
    public void userGoToTheLiveScheduleListScreen() throws InterruptedException {
        Thread.sleep(3000);
        page.locator(LiveSchedulePageLocator).click();
    }

    @And("Live schedule> Create Live schedule: Click on the live schedule button")
    public void liveScheduleCreateLiveScheduleClickOnTheLiveScheduleButton() throws InterruptedException {
        Thread.sleep(3000);

        page.locator(CreateLiveScheduleLocator).click();

    }


    @And("Live schedule> Create Schedule : Your Live Information :Enter all Valid data")
    public void liveScheduleCreateScheduleYourLiveInformationEnterAllValidData() throws InterruptedException {
        Thread.sleep(3000);

        testContext().set(CommonStaticStrings.GO_LIVE_TITLE, GoLiveTitle);
        testContext().set(CommonStaticStrings.GO_LIVE_DESCRIPTION, GoLiveDescription);
        LocalTime futureTime = LocalTime.now().plusMinutes(2);
        String formattedTime = futureTime.format(DateTimeFormatter.ofPattern("h:mm"));
        String hoursToSelect = formattedTime.split(":")[0];
        System.out.println("hoursToSelect = " + hoursToSelect);
        String minuteToSelect = formattedTime.split(":")[1];
        System.out.println("minuteToSelect = " + minuteToSelect);

        Thread.sleep(3000);
        page.locator(LiveScheduleDatePickerLocator).click();
        page.locator(TodayDatePickerLocator).click();
        Thread.sleep(10000);
        page.locator(LiveScheduleTimePickerLocator).click();
        page.locator("(//div[@class='time-picker-body time-picker-roll']/div/div[text()='" + hoursToSelect + "'])[1]").click();
        page.locator("(//div[@class='time-picker-body time-picker-roll']/div/div[text()='" + minuteToSelect + "'])[1]").click();
        page.locator(TimepickerselectionLocator).click();
        page.locator(LiveScheduleTitleLocator).fill(GoLiveTitle); // Filling in the title
        page.locator(LiveScheduleDescriptionLocator).fill(GoLiveDescription);
        Thread.sleep(3000);
        page.locator(LiveScheduleTitleNextLocator).click();

    }

    @And("Live schedule: Set up your Stream Sources> SingleHosting create the live schedule")
    public void LivescheduleSetupyourStreamSourcesSingleHostingcreatetheliveschedule() throws InterruptedException {
        Thread.sleep(3000);
        //page.locator("//li[@aria-label='Single Hosting']").click();
        page.locator(SingleHostSubmitButtonLocator).click();
    }

    @And("Live schedule: Set up your Stream Sources> Multihosting create the live schedule")
    public void liveScheduleSetUpYourStreamSourcesMultihostingCreateTheLiveSchedule() throws InterruptedException {

        Thread.sleep(3000);
        page.locator(MultiHostSelectionLocator).click();
        page.locator(InvitationEmailFieldLocator).fill("kiran.teli@brainvire.com");
        page.locator(MultiHostSubmitButtonLocator).click();
    }

    @Then("Live schedule: User Redirect to the live schedule session  list screen")
    public void liveScheduleUserRedirectToTheLiveScheduleSessionListScreen() throws InterruptedException {
        Thread.sleep(3000);
        String ActualGoLiveTitle = page.locator("//table/tbody/tr[1]/td[text()= '" + GoLiveTitle + "']").textContent();
        Assert.assertEquals("Go Live Title does not match!", GoLiveTitle, ActualGoLiveTitle);
        Thread.sleep(6000);
    }
    @Then("Live Schedule : User Click on the Go live Button")
    public void liveScheduleUserClickOnTheGoLiveButton() throws InterruptedException {
        String scheduledTimeText = page.locator("//td[text()='" + GoLiveTitle + "']/following-sibling::td/span[@class='time']").textContent().trim();
        testContext().getScenarioLogger().log("Extracted Scheduled Time: " + scheduledTimeText);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
        LocalTime scheduledTime = LocalTime.parse(scheduledTimeText, timeFormatter);

        while (true) {
            LocalTime currentTime = LocalTime.now();
            String formattedCurrentTime = currentTime.format(timeFormatter);
            if (formattedCurrentTime.equals(scheduledTime.format(timeFormatter))) {
                testContext().getScenarioLogger().log("Time Matched! Refreshing the page...");
                page.reload();  // Reload the page to update status
                Thread.sleep(3000);  // Wait for the page to load
                page.locator("//td[text()='" + GoLiveTitle + "']/following-sibling::td//button[contains(text(),'Go Live')]").click();
                testContext().getScenarioLogger().log("Go Live button clicked!");
                break;
            }
        }
    }

    @Then("Live schedule: Open the view live schedule screen")
    public void liveScheduleOpenTheViewLiveScheduleScreen() throws InterruptedException {
        Thread.sleep(3000);
        page.locator("//td[text()='" + GoLiveTitle + "']/following-sibling::td//a[@aria-label='eye link']").click();
        page.locator(ViewcloseLocator).click();

    }

    @And("Live schedule: Open the edit live schedule screen")
    public void liveScheduleOpenTheEditLiveScheduleScreen() throws InterruptedException {
        Thread.sleep(3000);
        page.locator("//td[text()='" + GoLiveTitle + "']/following-sibling::td//a/span[@class='icon-pen']").click();
        Thread.sleep(3000);
        LocalDate futureDate = LocalDate.now().plusDays(1);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // Get current time and add 2 hours to it
        LocalTime futureTime = LocalTime.now().plusHours(2);
        String formattedTime = futureTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        Thread.sleep(3000);

        page.locator(LiveScheduleDatePickerLocator).click();
        page.locator(LiveScheduleDatePickerLocator).fill(formattedDate);
        page.locator(LiveScheduleTimePickerLocator).click();
        page.locator(LiveScheduleTimePickerLocator).fill(formattedTime);
        page.locator(TimepickerselectionLocator).click();
        page.locator(LiveScheduleTitleLocator).fill(GoLiveTitle + "test"); // Filling in the title
        page.locator(LiveScheduleDescriptionLocator).fill(GoLiveDescription + "test description");

    }

    @And("live schedule: Edit : Update the valid data and submit")
    public void liveScheduleEditUpdateTheValidDataAndSubmit() throws InterruptedException {
        Thread.sleep(3000);
        page.locator(UpdateScheduleButtonLocator).click();

    }

    @And("Live schedule: Delete : Delete the created live schedule session")
    public void liveScheduleDeleteDeleteTheCreatedLiveScheduleSession() throws InterruptedException {
        Thread.sleep(5000);
        page.locator("//td[text()='" + GoLiveTitle + "test" + "']/following-sibling::td/div[@class='action-row']//span[@class='icon-trash']").click();
        page.locator(DeleteScheduleLocator).click();

    }



}
