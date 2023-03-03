package org.heyrmi;

import com.codeborne.selenide.WebDriverRunner;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static org.heyrmi.config.ConfigurationManager.configuration;

@Slf4j
public class RemoteExecution implements BeforeEachCallback, AfterEachCallback {

    public RemoteWebDriver driver;
    private static final String browserstack_username = System.getenv("BROWSERSTACK_USERNAME");
    private static final String browserstack_access_key = System.getenv("BROWSERSTACK_ACCESS_KEY");

    @Override
    public void beforeEach(ExtensionContext context) throws MalformedURLException {

        // Inputs on Execution: Browserstack or Selenoid

        if (configuration().runmode() == "selenoid") {

            log.info("Runmode:" + configuration().runmode());

            // Capabilities to run test in GitHub Runners
            // If you have Selenoid UI enable: 'enableVideo', 'enableVNC', 'enableLog'
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", "chrome");
            capabilities.setCapability("enableVNC", false);
            capabilities.setCapability("enableVideo", false);
            capabilities.setCapability("enableLog", false);

            driver = new RemoteWebDriver(new URL(configuration().girdURL()), capabilities);
            WebDriverRunner.setWebDriver(driver);
        }

        else if (configuration().runmode() == "browserstack") {

            MutableCapabilities capabilities = new MutableCapabilities();
            capabilities.setCapability("browserName", "Chrome");
            HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
            browserstackOptions.put("os", "Windows");
            browserstackOptions.put("osVersion", "10");
            browserstackOptions.put("browserVersion", "latest");
            browserstackOptions.put("projectName", "Selenium-On-Steroids");
            browserstackOptions.put("buildName", "SampleBuild");
            browserstackOptions.put("local", "false");
            browserstackOptions.put("seleniumVersion", "4.8.0");
            capabilities.setCapability("bstack:options", browserstackOptions);

            driver = new RemoteWebDriver(new URL("https://" + browserstack_username + ":" + browserstack_access_key
                    + "@hub-cloud.browserstack.com/wd/hub"),
                    capabilities);

            WebDriverRunner.setWebDriver(driver);
        }
    }

    @Override
    public void afterEach(ExtensionContext context) {
        WebDriverRunner.closeWebDriver();
    }
}