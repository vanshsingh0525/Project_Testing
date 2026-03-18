package com.ecomtesting.pages.common;

import com.ecomtesting.factory.DriverFactory;
import com.ecomtesting.utils.WaitUtils;
import org.openqa.selenium.WebDriver;

public class HomePage {

    protected final WebDriver driver;

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void navigateTo(String url) {
        driver.get(url);
        WaitUtils.pageReady(driver);
    }
}
