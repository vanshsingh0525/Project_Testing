package com.ecomtesting.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ecomtesting.factory.DriverFactory;
import com.ecomtesting.pages.member1.BasePage;
import com.ecomtesting.utils.WaitUtils;

public class HomePage extends BasePage {

    private static final By SEARCH_FIELD = By.cssSelector("input[placeholder='Search for products, brands and more']");

    public HomePage() {
        this(DriverFactory.getDriver());
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
        WaitUtils.pageReady(driver);
    }

    public void navigateTo(String url) {
        open(url);
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
