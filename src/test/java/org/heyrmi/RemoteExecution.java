package org.heyrmi;

import com.codeborne.selenide.WebDriverRunner;

import org.heyrmi.driver.Driver;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class RemoteExecution implements BeforeEachCallback, AfterEachCallback {

    public WebDriver driver;

    @Override
    public void beforeEach(ExtensionContext context) {
        // Get driver based on execution
        driver = Driver.getRemoteWebDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        WebDriverRunner.closeWebDriver();
    }
}
