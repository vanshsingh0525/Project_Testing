package com.ecomtesting.utils;

import com.ecomtesting.factory.DriverFactory;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class WaitUtils {

    private static final Duration DEFAULT_TIMEOUT =
            Duration.ofSeconds(Long.parseLong(DriverFactory.getConfig("explicit.wait.seconds", "20")));

    private WaitUtils() {
    }

    public static WebDriverWait waitFor(WebDriver driver) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    public static WebElement visible(WebDriver driver, By locator) {
        return waitFor(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static List<WebElement> allVisible(WebDriver driver, By locator) {
        return waitFor(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static WebElement clickable(WebDriver driver, By locator) {
        return waitFor(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void pageReady(WebDriver driver) {
        waitFor(driver).until(webDriver -> "complete".equals(
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState")));
    }

    public static void textPresent(WebDriver driver, By locator, String text) {
        waitFor(driver).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static void invisibility(WebDriver driver, By locator) {
        waitFor(driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void refreshedList(WebDriver driver, Function<WebDriver, Boolean> condition) {
        waitFor(driver).until(condition::apply);
    }
}
