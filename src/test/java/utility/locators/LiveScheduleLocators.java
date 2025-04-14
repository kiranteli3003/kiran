package utility.locators;

public interface LiveScheduleLocators {

    String LiveSchedulePageLocator = "//a[text()='Live Schedule']";
    String CreateLiveScheduleLocator = "//button[text()='Create Live Schedule']";
    String TodayDatePickerLocator = "//td[@class='calendar-cell current today']";
    String LiveScheduleDatePickerLocator ="//input[@class ='date-picker-input']";
    String LiveScheduleTimePickerLocator = "//input[@class ='time-picker-input']";
    String TimepickerselectionLocator =  "//button[text()='OK']";
    String LiveScheduleTitleLocator = "//input[@id='streamTitle']";
    String LiveScheduleDescriptionLocator= "//textarea[@id='streamDescription']";
    String LiveScheduleTitleNextLocator = "//button[text()='Next']";
    String SingleHostSubmitButtonLocator  ="(//button[@class='btn btn-primary'][text()='Schedule'])[1]";
    String MultiHostSelectionLocator = "//li[@aria-label='Multiple Hosting']";
    String InvitationEmailFieldLocator = "//input[@id='email2']";
    String MultiHostSubmitButtonLocator = "//button[@type='submit'][text()='Schedule']";
    String UpdateScheduleButtonLocator ="//button[text()='Update']";
    String ViewcloseLocator = "//i[@class= 'icon-close']";
    String DeleteScheduleLocator  = "//button[text()='YES']";
}
