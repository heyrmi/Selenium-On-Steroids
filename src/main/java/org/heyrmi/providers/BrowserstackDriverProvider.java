package org.heyrmi.providers;

import com.codeborne.selenide.WebDriverProvider;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.URL;
import java.util.HashMap;

public class BrowserstackDriverProvider implements WebDriverProvider {

  private static final String BROWSERSTACK_USERNAME = System.getenv("BROWSERSTACK_USERNAME");
  private static final String BROWSERSTACK_ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");

  @SneakyThrows
  @Nonnull
  @Override
  public WebDriver createDriver(@Nonnull Capabilities capabilities) {
    MutableCapabilities mutableCapabilities = new MutableCapabilities();
    mutableCapabilities.setCapability("browserName", "Chrome");
    mutableCapabilities.setCapability("browserVersion", "latest");
    HashMap<String, Object> browserstackOptions = new HashMap<>();
    browserstackOptions.put("os", "Windows");
    browserstackOptions.put("osVersion", "10");
    browserstackOptions.put("projectName", "Selenium Automation Project");
    browserstackOptions.put("buildName", "Smoke");
    browserstackOptions.put("sessionName", "Menu Check");
    browserstackOptions.put("local", "false");
    mutableCapabilities.setCapability("bstack:options", browserstackOptions);
    return new RemoteWebDriver(
        new URL("https://" + BROWSERSTACK_USERNAME + ":" + BROWSERSTACK_ACCESS_KEY
                    + "@hub-cloud.browserstack.com/wd/hub"),
        mutableCapabilities);
  }
}