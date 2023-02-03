package org.heyrmi.pages.portfolio;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BlogPage extends HomePage {

    /* Locators */

    private static final SelenideElement BLOG_TEXT = $(By.xpath("//h1[normalize-space()='Blogs']"));
    private static final SelenideElement JAVA_BLOG = $(By.xpath("//header[@class='entry-header']/h2"));

    /* Methods */

    public HomePage testBlogPage() {
        BLOG_TEXT.shouldBe(visible);
        JAVA_BLOG.shouldBe(visible, text("How to develop E2E Applications"));
        return new HomePage();
    }

    public WorkPage goToWorkPage() {
        WORK.click();
        return new WorkPage();
    }

}
