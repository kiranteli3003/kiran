package utility.locators;

public interface ReplayHistoryLocators {

    String ReplayHistoryLocator = "//a[text() = 'Replay History']";
    String ReplayHistoryTableLocator = "(//table/tbody/tr)";
    String ReplayHistorySearchFieldLocator = "//input[@placeholder='Search Video']";
    String ReplayHistoryFirstRowLocator = "//tbody/tr[1]";
    String ReplayHistoryInsightButtonLocator = "//tbody/tr[1]/td//button";
    String InsightPageTitleLocator = "//h1[@class = 'page-title']";
    String InsightSessionNameLocator = "//p[@class ='greyText']";
    String InsightSessionDateTimeLocator =  "//p[@class='gap-4' and strong[text()='Date & Time']]/following-sibling::p";
    String InsightSessionDurationLocator = "//p[@class='gap-4' and strong[text()='Duration']]/following-sibling::p";
    String InsightBackButtonLocator =   "button[type='button']";
    String ReplayHistoryTitleLocator = "//h1[text()='Replay History']";
    String SortOldestLocator = "//a[text()='Oldest']";
    String SortDurationLocator = " //a[text()='Duration']";
    String SortNewestLocator = "//a[text()='Newest']";

}
