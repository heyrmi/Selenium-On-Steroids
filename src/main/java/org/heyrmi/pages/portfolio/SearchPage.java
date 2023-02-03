package org.heyrmi.pages.portfolio;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage {

    /* Locators */
    private static final SelenideElement SEARCH_TEXT = $(By.xpath("//header[@class='page-header']/h1"));
    private static final SelenideElement SEARCH = $(By.id("searchInput"));
    private static final ElementsCollection SEARCH_RESULTS = $$(By.xpath("//li[@class='post-entry']/header"));

    /* Methods */
    public HomePage testSearchPage() {
        SEARCH_TEXT.shouldBe(visible, partialText("Search"));
        SEARCH.shouldBe(visible, enabled).setValue("Java");
        SEARCH_RESULTS.first().shouldBe(visible, partialText("Why Java is still relevant"));
        return new HomePage();
    }
}
