package com.steps.cucumber;

import com.microsoft.playwright.*;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import utility.Constant;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.lang.ThreadLocal.withInitial;

public enum CucumberTestContext {
    CONTEXT;
    final String currUser = System.getProperty("user.name");
    private static final String PAYLOAD = "PAYLOAD";
    private static final String REQUEST = "REQUEST";
    private static final String RESPONSE = "RESPONSE";
    private static final String SCENARIO = "SCENARIO";
    private final ThreadLocal<Map<String, Object>> threadLocal = withInitial(HashMap::new);

    private Map<String, Object> testContextMap() {
        return threadLocal.get();
    }

    public void set(String key, Object value) {
        testContextMap().put(key, value);
    }

    public Object get(String key) {
        return testContextMap().get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(testContextMap().get(key));
    }

    public void setPayload(Object value) {
        set(PAYLOAD, value);
    }

    public Object getPayload() {
        return testContextMap().get(PAYLOAD);
    }

    public void openBrowser() {
        Browser browser;
        Playwright playwright = Playwright.create();
        if (Constant.browserName.equals("chrome")) {
            browser = playwright.chromium().launch(getLaunchOptions());
        } else if (Constant.browserName.equals("firefox")) {
            browser = playwright.firefox().launch(getLaunchOptions());
        } else {
            browser = playwright.chromium().launch(getLaunchOptions());
        }

        Page page;
        BrowserContext context;
        boolean headlessBrowser = getIsHeadLessBrowser();
        if (!headlessBrowser) {
            // Create a new incognito browser context
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) screenSize.getWidth();
            int height = (int) screenSize.getHeight();
            context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
        } else {
            context = browser.newContext();
        }


        if (Constant.CUSTOM_LOGIN == true) {
            context = browser.newContext(
                    new Browser.NewContextOptions()
                            .setStorageStatePath(Paths.get("logindetails.json")).setViewportSize(1920, 1080));
            context.grantPermissions(Arrays.asList("camera", "microphone"));
             page = context.newPage();
        } else {
             page = context.newPage();
        }



        page.setDefaultTimeout(60000);
        String frontBaseUrl = getProperty("front_base_url");
        page.navigate(frontBaseUrl);


        set("BROWSER", browser);
        set("PLAYWRIGHT", playwright);
        set("PAGE", page);
        getScenarioLogger().log(Constant.browserName + " browser is opened.with Permission granted");
    }

    public void setScenarioLogger(Scenario scenario) {
        set(SCENARIO, scenario);
    }

    public Scenario getScenarioLogger() {
        return get(SCENARIO, Scenario.class);
    }

    public Playwright getPlaywright() {
        return (Playwright) testContextMap().get("PLAYWRIGHT");
    }

    public Browser getBrowser() {
        return (Browser) testContextMap().get("BROWSER");
    }

    public Page getBrowserPage() {
        return (Page) testContextMap().get("PAGE");
    }

    public void reset() {
        testContextMap().clear();
    }

    public BrowserType.LaunchOptions getLaunchOptions() {
        String currUser = System.getProperty("user.name");
        boolean headlessBrowser = currUser.contains("jenkins");
        return new BrowserType.LaunchOptions()
                .setChannel("chrome")
                .setHeadless(headlessBrowser)
                .setSlowMo(1000);
    }

    public void setResponse(Response response) {
        set(RESPONSE, response);
    }

    public Response getResponse() {
        return get(RESPONSE, Response.class);
    }

    private String getProperty(String key) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config/QA.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load Live.properties file", e);
        }
        return properties.getProperty(key);
    }
    public boolean getIsHeadLessBrowser() {
        String headLessBrowserFromCommandLine = System.getProperty("headLessBrowser");
        if (currUser.contains("jenkins") || (headLessBrowserFromCommandLine != null && headLessBrowserFromCommandLine.equals("true"))) {
            return true;
        } else {
            return false;
        }
    }
}
