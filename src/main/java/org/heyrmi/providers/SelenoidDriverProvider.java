package org.heyrmi.providers;

import com.codeborne.selenide.WebDriverProvider;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.URL;

import static org.heyrmi.config.ConfigurationManager.configuration;

public class SelenoidDriverProvider implements WebDriverProvider {
  @SneakyThrows
  @Nonnull
  @Override
  public WebDriver createDriver(@Nonnull Capabilities capabilities) {
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability("browserName", "chrome");
    desiredCapabilities.setCapability("enableVNC", false);
    desiredCapabilities.setCapability("enableVideo", false);
    desiredCapabilities.setCapability("enableLog", false);
    return new RemoteWebDriver(new URL(configuration().girdURL()), desiredCapabilities);
  }
}