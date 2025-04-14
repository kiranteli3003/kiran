package com.steps.cucumber;

import com.microsoft.playwright.Page;
import io.cucumber.java.*;
import utility.Constant;
import utility.DateTimeUtility;
import utility.enums.TEST_ENV;

import java.io.*;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class Hooks extends AbstractSteps {

    private static String starTimeString;
    private static final String ddMmmYyyyDateFormat = "dd MMM yyyy, hh:mm:ss a";
    private static final String FILE_PATH = "src/test/resources/report.properties";
    static TEST_ENV currentTestEnv = Constant.getTestEnv();

    @BeforeAll()
    public static void beforeAll() {
        starTimeString = DateTimeUtility.getCurrentDateTimeInFormat(ddMmmYyyyDateFormat);
        writeProperty("Start_Time", starTimeString);
        writeProperty("Test_Env", currentTestEnv.toString());
    }

    @Before()
    public void setUpFrontEndBrowser(Scenario scenario) throws IOException {
        testContext().setScenarioLogger(scenario);
        testContext().getScenarioLogger().log("SETUP SCENARIO: " + scenario.getName());
        Constant.setUpTestEnvData();
        testContext().openBrowser();
    }

    @After()
    public void tearDown(Scenario scenario) {
        testContext().getScenarioLogger().log("GENERIC TEARDOWN: " + scenario.getName());
        Page page = testContext().getBrowserPage();

        if (scenario.isFailed()) {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("target/tmp", "screenshot.png")));
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
        page.close();
        testContext().getBrowser().close();
        testContext().getPlaywright().close();

        testContext().reset();
    }
    @AfterAll()
    public static void afterAll() {
        String endTimeString = DateTimeUtility.getCurrentDateTimeInFormat(ddMmmYyyyDateFormat);
        writeProperty("End_Time", endTimeString);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ddMmmYyyyDateFormat);

        LocalDateTime startTime = LocalDateTime.parse(starTimeString, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endTimeString, formatter);
        Duration duration = Duration.between(startTime, endTime);

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        String formattedDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        writeProperty("Duration", formattedDuration);
    }

    private static void writeProperty(String key, String value) {
        Map<String, String> orderedProperties = new LinkedHashMap<>();

        // Load existing properties file while maintaining order
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("#") && line.contains("=")) {  // Ignore comments
                    String[] parts = line.split("=", 2);
                    orderedProperties.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Properties file not found, creating a new one.");
        }

        // Update or add new key-value
        orderedProperties.put(key, value);

        // Write updated properties back without timestamp or escaping
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<String, String> entry : orderedProperties.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Exception while updating properties file.");
        }
    }
}
