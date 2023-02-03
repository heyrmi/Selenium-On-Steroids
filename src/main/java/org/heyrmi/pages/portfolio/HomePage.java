package org.heyrmi.pages.portfolio;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    /* Locators */

    private static final SelenideElement TITLE = $(By.xpath("//div[@class='logo']/a"));
    private static final SelenideElement NAME = $(By.xpath("//div[@class='profile_inner']/h1"));
    private static final SelenideElement RESUME = $(By.xpath("//a[@class='button' and @title='Resume']"));
    protected static final SelenideElement HOME = $(By.xpath("//a[@title='Home']"));
    protected static final SelenideElement BLOG = $(By.xpath("//a[@title='Blog']"));
    protected static final SelenideElement WORK = $(By.xpath("//span[normalize-space()='Work']"));
    protected static final SelenideElement EXPERIENCE = $(By.xpath("//span[normalize-space()='Experience']"));
    protected static final SelenideElement SEARCH = $(By.xpath("//a[@title='Search (Alt + /)']"));

    /* Methods */
    public HomePage verifyHomePage() {
        TITLE.shouldHave(visible, text("Rahul Mishra"));
        NAME.shouldBe(visible).shouldHave(partialText("Rahul"));
        RESUME.shouldBe(visible, enabled);
        HOME.shouldBe(visible);
        BLOG.shouldBe(visible);
        WORK.shouldBe(visible);
        EXPERIENCE.shouldBe(visible);
        SEARCH.shouldBe(visible);
        return this;
    }

    public static HomePage getHomepageInstance() {
        return new HomePage();
    }

    public BlogPage goToBlogPage() {
        BLOG.click();
        return new BlogPage();
    }

    public WorkPage goToWorkPage() {
        WORK.click();
        return new WorkPage();
    }

    public ExperiencePage goToExperiencePage() {
        EXPERIENCE.click();
        return new ExperiencePage();
    }

    public SearchPage goToSearchPage() {
        SEARCH.click();
        return new SearchPage();
    }

}
