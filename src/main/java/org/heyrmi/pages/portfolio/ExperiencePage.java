package org.heyrmi.pages.portfolio;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ExperiencePage extends HomePage {

    /* Locators */

    private static final SelenideElement EXPERIENCE_TEXT = $(By.xpath("//h1[normalize-space()='Experiences']"));
    private static final SelenideElement DRIP_EXPERIENCE = $(By.xpath("(//div[@class='entry-content']/p)[1]"));
    private static final SelenideElement AU_EXPERIENCE = $(By.xpath("(//div[@class='entry-content']/p)[2]"));

    /* Methods */

    public HomePage testExperiencePage() {
        EXPERIENCE_TEXT.shouldBe(visible);
        DRIP_EXPERIENCE.shouldBe(visible, text("DripCapital (Mumbai, India)"));
        AU_EXPERIENCE.shouldBe(visible, text("AU Small Finance Bank (Jaipur, India)"));
        return new HomePage();
    }

    public SearchPage goToSearchPage() {
        SEARCH.click();
        return new SearchPage();
    }
}
