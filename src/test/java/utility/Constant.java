package utility;

import utility.enums.TEST_ENV;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constant {
    public static final TEST_ENV DEFAULT_TEST_ENV = TEST_ENV.QA;
    public static final String DEFAULT_BROWSER = "chrome";
    public static String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : DEFAULT_BROWSER;
    public static TEST_ENV testEnv;
    public static String frontBaseUrl;
    public static Properties TestDataProperties;
    public static Boolean CUSTOM_LOGIN = true;

    public static TEST_ENV getTestEnv() {
        String testPropertyValue = System.getProperty("testEnv");
        String testEnvValue = System.getenv("testEnv");

        // First priority will be from the command line then environment value
        TEST_ENV testEnv;
        if (testPropertyValue != null) {
            testEnv = TEST_ENV.valueOf(testPropertyValue);
        } else if (testEnvValue != null) {
            testEnv = TEST_ENV.valueOf(testEnvValue);
        } else {
            testEnv = DEFAULT_TEST_ENV;
        }
        return testEnv;
    }

    public static void setUpTestEnvData() throws IOException {
        testEnv = getTestEnv();
        String filePath = "src/test/resources/config/";
        TestDataProperties = new Properties();
        File envFile = new File(filePath + testEnv + ".properties");
        FileInputStream fileInputStream = new FileInputStream(envFile);
        TestDataProperties.load(fileInputStream);
        fileInputStream.close();

        frontBaseUrl = TestDataProperties.getProperty("front_base_url");

    }

}
