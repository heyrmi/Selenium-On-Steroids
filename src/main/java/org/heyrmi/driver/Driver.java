package org.heyrmi.driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import lombok.SneakyThrows;

import static org.heyrmi.config.ConfigurationManager.configuration;

import java.net.URL;
import java.util.HashMap;

public class Driver {

    private static final String browserstack_username = System.getenv("BROWSERSTACK_USERNAME");
    private static final String browserstack_access_key = System.getenv("BROWSERSTACK_ACCESS_KEY");

    @SneakyThrows
    public static WebDriver getRemoteWebDriver() {

        WebDriver driver = null;

        String runmode = configuration().runmode();

        if (runmode.equalsIgnoreCase("browserstack")) {
            driver = browserStackDriver();
        } else if (runmode.equalsIgnoreCase("selenoid")) {
            driver = selenoidDriver();
        }
        return driver;
    }

    @SneakyThrows
    private static WebDriver browserStackDriver() {

        WebDriver brwoserstackdriver = null;

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "latest");
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "10");
        browserstackOptions.put("projectName", "Selenium Automation Project");
        browserstackOptions.put("buildName", "Smoke");
        browserstackOptions.put("sessionName", "Menu Check");
        browserstackOptions.put("local", "false");
        capabilities.setCapability("bstack:options", browserstackOptions);
        brwoserstackdriver = new RemoteWebDriver(
                new URL("https://" + browserstack_username + ":" + browserstack_access_key
                        + "@hub-cloud.browserstack.com/wd/hub"),
                capabilities);

        return brwoserstackdriver;

    }

    @SneakyThrows
    private static WebDriver selenoidDriver() {

        WebDriver selenoidDriver;

        // Capabilities to run test in GitHub Runners
        // If you have Selenoid UI enable: 'enableVideo', 'enableVNC', 'enableLog'
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("enableVNC", false);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("enableLog", false);

        selenoidDriver = new RemoteWebDriver(new URL(configuration().girdURL()), capabilities);
        return selenoidDriver;
    }
}