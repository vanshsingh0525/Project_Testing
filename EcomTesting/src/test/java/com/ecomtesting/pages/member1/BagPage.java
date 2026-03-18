package com.ecomtesting.pages.member1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BagPage extends BasePage {

    private static final By BAG_PAGE_IDENTIFIER = By.xpath("//h1[contains(normalize-space(),'Bag')] | //div[contains(normalize-space(),'ITEMS SELECTED')]");
    private static final By DONE_BUTTON = By.xpath("//div[contains(normalize-space(),'DONE')] | //button[contains(normalize-space(),'DONE')]");

    public BagPage(WebDriver driver) {
        super(driver);
    }

    public boolean isBagPageVisible() {
        return isDisplayed(BAG_PAGE_IDENTIFIER);
    }

    public void openQuantitySelector() {
        By quantityOpen = By.xpath("//div[contains(@class,'itemComponents-base-quantity')]//div[contains(@class,'itemComponents-base-dropDown')] | //div[@class='itemComponents-base-quantity']//div[contains(normalize-space(),'Qty')]");
        click(quantityOpen);
    }

    public void selectQuantity(String quantity) {
        By quantityById = By.id(quantity);
        click(quantityById);
    }

    public void clickDone() {
        click(DONE_BUTTON);
    }

    public boolean isQuantityApplied(String quantity) {
        By selectedQuantity = By.xpath("//div[contains(@class,'itemComponents-base-quantity')]//*[contains(normalize-space(),'Qty: " + quantity + "')] | //div[contains(normalize-space(),'Qty: " + quantity + "')]");
        return isDisplayed(selectedQuantity);
    }
}
