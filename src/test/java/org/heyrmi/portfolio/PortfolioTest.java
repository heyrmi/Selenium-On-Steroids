package org.heyrmi.portfolio;

import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import org.heyrmi.RemoteExecution;
import org.heyrmi.pages.portfolio.HomePage;
import org.heyrmi.urls.URL;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;

@ExtendWith({ TextReportExtension.class, RemoteExecution.class })
public class PortfolioTest {

    @BeforeEach
    void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
    }

    @Test
    @DisplayName("Regression Test of Portfolio Website")
    @Tag("Regression")
    void regressionTestPortfolioWebsite() {
        open(URL.getPORTFOLIO_WEBSITE_URL());

        HomePage.getHomepageInstance()
                .verifyHomePage()
                .goToBlogPage()
                .testBlogPage()
                .goToWorkPage()
                .testWorkPage()
                .goToExperiencePage()
                .testExperiencePage()
                .goToSearchPage()
                .testSearchPage();

    }

    @Test
    @DisplayName("Smoke Test of Portfolio Website")
    @Tag("Smoke")
    // @Disabled
    void smokeTestPortfolioWebsite() {
        open(URL.getPORTFOLIO_WEBSITE_URL());

        HomePage.getHomepageInstance()
                .goToBlogPage()
                .goToWorkPage()
                .goToExperiencePage()
                .goToSearchPage()
                .testSearchPage();
    }

}
