package org.heyrmi;

import com.codeborne.selenide.Configuration;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static org.heyrmi.config.ConfigurationManager.configuration;

public class RemoteExecution implements BeforeAllCallback {

    private static final String browserstack_username = System.getenv("BROWSERSTACK_USERNAME");
    private static final String browserstack_access_key = System.getenv("BROWSERSTACK_ACCESS_KEY");

    @Override
    @SneakyThrows
    public void beforeAll(ExtensionContext extensionContext) {

        // Inputs on Execution: Browserstack or Selenoid

        if (configuration().runmode() == "selenoid") {
            Configuration.remote = configuration().girdURL();

            //Capabilities can be changed later
            Map<String, Boolean> options = new HashMap<>();
            options.put("enableVNC", false);
            options.put("enableVideo", false);

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setBrowserVersion("105.0");
            Configuration.browserCapabilities = chromeOptions;
            Configuration.browserCapabilities.setCapability("selenoid:options", options);

        }

        else if (configuration().runmode() == "browserstack") {

            //Still to be configured.
            Configuration.remote = "https://" + browserstack_username + ":" + browserstack_access_key
                    + "@hub-cloud.browserstack.com/wd/hub";

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setBrowserVersion("109.0");
            HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
            browserstackOptions.put("os", "Windows");
            browserstackOptions.put("osVersion", "11");
            browserstackOptions.put("browserVersion", "latest");
            browserstackOptions.put("projectName", "Selenide");
            browserstackOptions.put("local", "false");
            browserstackOptions.put("seleniumVersion", "4.8.0");
            Configuration.browserCapabilities = chromeOptions;
            Configuration.browserCapabilities.setCapability("bstack:options", browserstackOptions);

            /*
            RemoteWebDriver driver = new RemoteWebDriver(
                    new URL("https://" + browserstack_username + ":" + browserstack_access_key
                            + "@hub-cloud.browserstack.com/wd/hub"),
                    capabilities);

            WebDriverRunner.setWebDriver(driver);
            */
        }
    }
}