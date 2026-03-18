package com.ecomtesting.pages.member1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final By SEARCH_FIELD = By.cssSelector("input[placeholder='Search for products, brands and more']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void clickSearchField() {
        click(SEARCH_FIELD);
    }

    public void enterSearchText(String searchText) {
        type(SEARCH_FIELD, searchText);
    }

    public void pressEnterOnSearch() {
        pressEnter(SEARCH_FIELD);
    }

    public boolean isSearchFieldVisible() {
        return isDisplayed(SEARCH_FIELD);
    }
}
