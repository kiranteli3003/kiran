package utility.locators;

public interface GoLiveLocators {

    String GoLivePageLocator = "//a[text()='Go live']";
    String GoLiveTitleLocator = "//input[@id='go_live_title']";
    String GoLiveDescriptionLocator =  "//textarea[@id='live_description']";
    String TitleNextButtonLocator = "//button[@aria-label='next-button']";
    String ProductSelectionLocator= "//li[@class='select-in-grid-list-item active']//p";
    String ProductNextButtonLocator = "(//button[contains(@type,'submit')][normalize-space()='Next'])[1]";
    String SocialNextButtonLocator ="(//button[contains(@type,'submit')])[2]";
    String GoLiveButtonLocator = "//button[text()='Go Live']";
    String GoLiveProcessLocator ="//div[@class='loader-spin']";
    String LiveLocator = "//span[normalize-space()='Live']";
    String GoLiveUpdateProductLocator = "(//div[@aria-label='Product Select'])[3]";
    String GoliveCommentFieldLocator = "//textarea[@placeholder='Enter Your Comment']";
    String PublishCommentButtonLocator = "//button[text()='Publish comment']";
    String EndSessionButtonLocator = "//span[text() ='End Live']";
    String ConfirmEndButtonLocator = "//button[text()='YES']";

}
