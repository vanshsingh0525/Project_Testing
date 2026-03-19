package com.ecomtesting.pages.member1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {

    private static final By GO_TO_BAG_BUTTON = By.xpath("//span[contains(normalize-space(),'GO TO BAG')]/ancestor::a | //div[contains(normalize-space(),'GO TO BAG')]");
    private static final By PRODUCT_NAME = By.cssSelector("h1.pdp-name, h1.pdp-title");
    private static final By SIZE_ERROR = By.xpath("//*[contains(normalize-space(),'Please select a size')]");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void selectSize(String sizeValue) {
        By sizeLocator = By.xpath("//p[normalize-space()='" + sizeValue + "']/parent::button | //button[normalize-space()='" + sizeValue + "']");
        click(sizeLocator);
    }

    public void clickAddToBag() {
        By addToBag = By.xpath("//div[contains(normalize-space(),'ADD TO BAG')] | //button[contains(normalize-space(),'ADD TO BAG')]");
        click(addToBag);
    }

    public void openBagFromProductPageOrHeader() {
        if (isDisplayed(GO_TO_BAG_BUTTON)) {
            click(GO_TO_BAG_BUTTON);
            return;
        }

        By bagIcon = By.cssSelector(".myntraweb-sprite.desktop-iconBag.sprites-headerBag");
        click(bagIcon);
    }

    public boolean isSizeDisplayed(String sizeValue) {
        By sizeLocator = By.xpath("//p[normalize-space()='" + sizeValue + "']/parent::button | //button[normalize-space()='" + sizeValue + "']");
        return isDisplayed(sizeLocator);
    }

    public boolean isProductDetailsPageDisplayed() {
        return isDisplayed(PRODUCT_NAME);
    }

    public boolean isSizeSelectionErrorDisplayed() {
        return isDisplayed(SIZE_ERROR);
    }
}
