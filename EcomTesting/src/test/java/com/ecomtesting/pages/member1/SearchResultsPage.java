package com.ecomtesting.pages.member1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends BasePage {

    private static final By SEARCH_RESULTS = By.cssSelector("ul.results-base");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSearchResultsDisplayed() {
        return isDisplayed(SEARCH_RESULTS);
    }

    public void openProductByTitle(String productTitle) {
        By productLocator = By.xpath("//img[@title='" + productTitle + "']");
        click(productLocator);
        switchToNewestWindow();
    }
}
