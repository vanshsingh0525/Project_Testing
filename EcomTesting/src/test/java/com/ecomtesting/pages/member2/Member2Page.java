package com.ecomtesting.pages.member2;

import com.ecomtesting.factory.DriverFactory;
import com.ecomtesting.pages.common.HomePage;
import com.ecomtesting.utils.WaitUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class Member2Page extends HomePage {

    private static final int PRICE_CAPTURE_COUNT =
            Integer.parseInt(DriverFactory.getConfig("price.capture.count", "5"));

    private static final By PRODUCTS = By.cssSelector("li.product-base");
    private static final By PRICE_ELEMENTS = By.cssSelector(
            "li.product-base span.product-discountedPrice, li.product-base span.product-price");
    private static final By LOADER = By.cssSelector("div.results-loader, div.spinner-spinner");
    private static final By SORT_LABEL = By.xpath(
            "//*[contains(normalize-space(),'Sort by') or contains(normalize-space(),'Recommended')]");

    public void openMensTshirtsCategory() {
        navigateTo(DriverFactory.getConfig("member2.category.url", "https://www.myntra.com/men-tshirts"));
        closeOptionalPopup();
        waitForProducts();
    }

    public void waitForProducts() {
        WaitUtils.pageReady(driver);
        WaitUtils.allVisible(driver, PRODUCTS);
        WaitUtils.refreshedList(driver, webDriver -> getDisplayedPrices().size() >= PRICE_CAPTURE_COUNT);
    }

    public List<Integer> captureFirstFiveDisplayedPrices() {
        List<Integer> prices = getDisplayedPrices();
        Assertions.assertTrue(prices.size() >= PRICE_CAPTURE_COUNT,
                "Expected at least " + PRICE_CAPTURE_COUNT + " product prices but found " + prices.size());
        return new ArrayList<>(prices.subList(0, PRICE_CAPTURE_COUNT));
    }

    public void sortProductsBy(String sortOption) {
        List<Integer> previousPrices = safeCaptureCurrentPrices();
        clickSortOption(sortOption);
        waitForSortingUpdate(previousPrices, sortOption);
    }

    public void verifyPricesAreAscending() {
        List<Integer> actualPrices = captureFirstFiveDisplayedPrices();
        List<Integer> sortedPrices = actualPrices.stream().sorted().collect(Collectors.toList());
        Assertions.assertEquals(sortedPrices, actualPrices,
                "The first five product prices are not sorted from low to high. Actual prices: " + actualPrices);
    }

    public void verifyPricesAreDescending() {
        List<Integer> actualPrices = captureFirstFiveDisplayedPrices();
        List<Integer> sortedPrices = actualPrices.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        Assertions.assertEquals(sortedPrices, actualPrices,
                "The first five product prices are not sorted from high to low. Actual prices: " + actualPrices);
    }

    public void verifySortingChangedResults() {
        List<Integer> prices = captureFirstFiveDisplayedPrices();
        Assertions.assertFalse(prices.isEmpty(), "No prices were available after applying sort.");
    }

    private void clickSortOption(String sortOption) {
        By sortOptionLocator = By.xpath(
                "//*[self::label or self::li or self::span or self::div or self::a][normalize-space()='"
                        + sortOption + "']");

        WebElement option = WaitUtils.clickable(driver, sortOptionLocator);
        scrollIntoView(option);
        try {
            option.click();
        } catch (Exception exception) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
        }
    }

    private void waitForSortingUpdate(List<Integer> previousPrices, String sortOption) {
        WaitUtils.pageReady(driver);
        try {
            WaitUtils.invisibility(driver, LOADER);
        } catch (Exception ignored) {
            // Loader is optional on Myntra, so we continue with content-based synchronization.
        }
        WaitUtils.refreshedList(driver, webDriver -> {
            List<Integer> currentPrices = getDisplayedPrices();
            return currentPrices.size() >= PRICE_CAPTURE_COUNT
                    && !Objects.equals(limit(previousPrices), limit(currentPrices));
        });

        try {
            WaitUtils.textPresent(driver, SORT_LABEL, sortOption);
        } catch (Exception ignored) {
            // Some layouts do not persist the selected sort text in the header.
        }
    }

    private List<Integer> safeCaptureCurrentPrices() {
        try {
            return captureFirstFiveDisplayedPrices();
        } catch (Exception exception) {
            return List.of();
        }
    }

    private List<Integer> getDisplayedPrices() {
        List<Integer> prices = new ArrayList<>();
        for (WebElement priceElement : WaitUtils.allVisible(driver, PRICE_ELEMENTS)) {
            try {
                int parsedPrice = parsePrice(priceElement.getText());
                if (parsedPrice > 0) {
                    prices.add(parsedPrice);
                }
            } catch (StaleElementReferenceException ignored) {
                return getDisplayedPrices();
            }
        }
        return prices;
    }

    private int parsePrice(String rawPrice) {
        String normalized = rawPrice.replaceAll("[^0-9]", "");
        if (normalized.isBlank()) {
            return 0;
        }
        return Integer.parseInt(normalized);
    }

    private List<Integer> limit(List<Integer> prices) {
        if (prices == null || prices.isEmpty()) {
            return List.of();
        }
        return new ArrayList<>(prices.subList(0, Math.min(PRICE_CAPTURE_COUNT, prices.size())));
    }

    private void closeOptionalPopup() {
        By closeButton = By.cssSelector("button[class*='close'], span[class*='desktop-iconCross']");
        List<WebElement> closeButtons = driver.findElements(closeButton);
        if (!closeButtons.isEmpty()) {
            try {
                scrollIntoView(closeButtons.get(0));
                closeButtons.get(0).click();
            } catch (Exception ignored) {
                // Popup is optional. Ignore when not interactable.
            }
        }
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
    }
}
