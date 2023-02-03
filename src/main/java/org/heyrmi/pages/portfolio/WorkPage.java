package org.heyrmi.pages.portfolio;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class WorkPage extends HomePage{

    /* Locators */
    private static final SelenideElement WORK_TEXT = $(By.xpath("//h1[normalize-space()='Works']"));
    private static final SelenideElement JAVA_ARTICLE = $(By.xpath("//header[@class='entry-header']/h2"));

    /* Methods */
    public HomePage testWorkPage() {
        WORK_TEXT.shouldBe(visible);
        JAVA_ARTICLE.shouldBe(visible, text("Why Java is still relevant"));
        return new HomePage();
    }

    public ExperiencePage goToExperiencePage() {
        EXPERIENCE.click();
        return new ExperiencePage();
    }

}
