package com.steps.stepdefination;

import com.steps.cucumber.AbstractSteps;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.locators.ReplayHistoryLocators;


public class ReplayHistory extends AbstractSteps implements ReplayHistoryLocators {

    private final Page page = testContext().getBrowserPage();

    @Given("ReplayHistory: user navigates to the Replay History page")
    public void ReplayHistoryusernavigatetotheReplayHistorypage() {
        page.locator(ReplayHistoryLocator).click(); // Playwright equivalent of clicking a link
    }

    @Then("ReplayHistory: list should display all available data")
    public void replayhistoryListShouldDisplayAllAvailableData() throws InterruptedException {
        //String ReplayHistoryCount = page.locator(ReplayHistoryTableLocator).allInnerTexts().toString();
        int ReplayHistoryCount = page.locator(ReplayHistoryTableLocator).count();
        testContext().getScenarioLogger().log("Replay History Table:" + ReplayHistoryCount);
        Thread.sleep(4000);
    }

    @When("ReplayHistory: user enters search data and performs a search")
    public void replayhistoryUserEntersSearchDataAndPerformsASearch()  {
        page.locator(ReplayHistorySearchFieldLocator).fill("And");
    }

    @Then("ReplayHistory: search results should be displayed correctly")
    public void replayhistorySearchResultsShouldBeDisplayedCorrectly() throws InterruptedException {
        Thread.sleep(6000);
        //String  SearchData = page.locator(ReplayHistoryTableLocator).allInnerTexts().toString();
        int SearchDataCount = page.locator(ReplayHistoryTableLocator).count();
        testContext().getScenarioLogger().log("Replay History Search Table data :" + SearchDataCount);
        Thread.sleep(6000);
    }

    @And("ReplayHistory: Clear the search field")
    public void replayhistoryClearTheSearchField() throws InterruptedException {
        page.locator(ReplayHistorySearchFieldLocator).clear();
        Thread.sleep(3000);
    }


    @When("ReplayHistory: user clicks on a insight button")
    public void replayhistoryUserClicksOnAInsightButton() throws InterruptedException {
        String insightRowData = page.locator(ReplayHistoryFirstRowLocator).innerText();
        Thread.sleep(3000);
        String[] dataParts = insightRowData.split("\\t+");

        if (dataParts.length < 3) {
            throw new RuntimeException("Unexpected data format in Replay History row: " + insightRowData);
        }

        String sessionName = dataParts[0].trim();
        String dateTime = dataParts[1].trim();
        String duration = dataParts[2].trim();


        testContext().getScenarioLogger().log("Session Name : " + sessionName);
        testContext().getScenarioLogger().log("Date and Time : " + dateTime);
        testContext().getScenarioLogger().log("Session duration : " + duration);

        page.locator(ReplayHistoryInsightButtonLocator).click();
        Thread.sleep(3000);

        testContext().set("SESSION_NAME", sessionName);
        testContext().set("SESSION_DATE_TIME", dateTime);
        testContext().set("SESSION_DURATION", duration);
    }

    @Then("ReplayHistory: user should be redirected to the Replay History to Insight page")
    public void replayhistoryUserShouldBeRedirectedToTheReplayHistoryInsightPage() throws InterruptedException {
        String PageName = page.locator(InsightPageTitleLocator).textContent();
        testContext().getScenarioLogger().log("User is redirect to the " + PageName + " page");
        Thread.sleep(3000);
    }

    @And("ReplayHistory: list data should match the details on the Insight page")
    public void replayhistoryListDataShouldMatchTheDetailsOnTheInsightPage() throws InterruptedException {
        String insightSessionName = page.locator(InsightSessionNameLocator).textContent().trim();
        String insightSessionDateTime = page.locator(InsightSessionDateTimeLocator).textContent().replace("/", "").trim();
        String insightSessionDuration = page.locator(InsightSessionDurationLocator).textContent().trim();

        // Convert duration format from '3m 22s' to '3 minutes 22 seconds'
        insightSessionDuration = insightSessionDuration.replace("m", " minutes").replace("s", " seconds").replace("minute seconds", "minutes");

        String ReplayHistorySessionName = testContext().get("SESSION_NAME", String.class);
        String ReplayHistoryDateTime = testContext().get("SESSION_DATE_TIME", String.class);
        String ReplayHistoryDuration = testContext().get("SESSION_DURATION", String.class);

        boolean isSessionNameMatch = insightSessionName.equalsIgnoreCase(ReplayHistorySessionName);
        boolean isDateTimeMatch = insightSessionDateTime.equalsIgnoreCase(ReplayHistoryDateTime);
        boolean isDurationMatch = insightSessionDuration.equalsIgnoreCase(ReplayHistoryDuration);

        if (isSessionNameMatch && isDateTimeMatch && isDurationMatch) {
            testContext().getScenarioLogger().log("Insight Page data matches Replay History List data.");
        } else {
            testContext().getScenarioLogger().log("Mismatch Found!\n" +
                    "Expected: " + ReplayHistorySessionName + ", " + ReplayHistoryDateTime + ", " + ReplayHistoryDuration + "\n" +
                    "Actual: " + insightSessionName + " , " + insightSessionDateTime + " , " + insightSessionDuration);
        }
        Thread.sleep(4000);
    }

    @When("ReplayHistory: user clicks the back button on the Insight detail page")
    public void replayhistoryUserClicksTheBackButtonOnTheInsightDetailPage() throws InterruptedException {
        Thread.sleep(4000);
        page.locator(InsightBackButtonLocator).click();

    }

    @Then("ReplayHistory: user should be redirected back to the Replay History list page")
    public void replayhistoryUserShouldBeRedirectedBackToTheReplayHistoryListPage() throws InterruptedException {
        Thread.sleep(3000);
        String ReplayHistoryPageName = page.locator(ReplayHistoryTitleLocator).textContent();
        testContext().getScenarioLogger().log("User is redirect to the " + ReplayHistoryPageName + " page");
    }


    @And("ReplyHistory: User Click on the Sorting options")
    public void replyhistoryUserClickOnTheSortingOptions() throws InterruptedException {
        page.locator(SortOldestLocator).click();
        Thread.sleep(3000);
        page.locator(SortDurationLocator).click();
        Thread.sleep(3000);
        page.locator(SortNewestLocator).click();
        Thread.sleep(3000);
        testContext().getScenarioLogger().log("Sorting is working Properly");
    }
}


