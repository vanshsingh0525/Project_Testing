package com.ecomtesting.stepdefinitions.member1;


import org.junit.Assert;

import com.ecomtesting.config.ConfigReader;
import com.ecomtesting.factory.DriverManager;
import com.ecomtesting.pages.common.HomePage;
import com.ecomtesting.pages.member1.BagPage;
import com.ecomtesting.pages.member1.ProductDetailsPage;
import com.ecomtesting.pages.member1.SearchResultsPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyntraStepDefinitions {

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private ProductDetailsPage productDetailsPage;
    private BagPage bagPage;

    private HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(DriverManager.getDriver());
        }
        return homePage;
    }

    private SearchResultsPage getSearchResultsPage() {
        if (searchResultsPage == null) {
            searchResultsPage = new SearchResultsPage(DriverManager.getDriver());
        }
        return searchResultsPage;
    }

    private ProductDetailsPage getProductDetailsPage() {
        if (productDetailsPage == null) {
            productDetailsPage = new ProductDetailsPage(DriverManager.getDriver());
        }
        return productDetailsPage;
    }

    private BagPage getBagPage() {
        if (bagPage == null) {
            bagPage = new BagPage(DriverManager.getDriver());
        }
        return bagPage;
    }

    @Given("the user opens the Myntra website")
    public void theUserOpensTheMyntraWebsite() {
        getHomePage().open(ConfigReader.get("base.url"));
        Assert.assertTrue("Search field is not visible on homepage", getHomePage().isSearchFieldVisible());
    }

    @Then("the search bar should be visible on the homepage")
    public void theSearchBarShouldBeVisibleOnTheHomepage() {
        Assert.assertTrue("Search bar is not visible on the homepage", getHomePage().isSearchFieldVisible());
    }

    @When("the user clicks on the search bar")
    public void theUserClicksOnTheSearchBar() {
        getHomePage().clickSearchField();
    }

    @When("the user enters {string}")
    public void theUserEnters(String searchText) {
        getHomePage().enterSearchText(searchText);
    }

    @When("the user presses the Enter key")
    public void theUserPressesTheEnterKey() {
        getHomePage().pressEnterOnSearch();
    }

    @Then("the search results should be displayed")
    public void theSearchResultsShouldBeDisplayed() {
        Assert.assertTrue("Search results are not displayed", getSearchResultsPage().isSearchResultsDisplayed());
    }

    @When("the user selects the product {string}")
    public void theUserSelectsTheProduct(String productTitle) {
        getSearchResultsPage().openProductByTitle(productTitle);
    }

    @Then("the product details page should be displayed")
    public void theProductDetailsPageShouldBeDisplayed() {
        Assert.assertTrue("Product details page is not displayed", getProductDetailsPage().isProductDetailsPageDisplayed());
    }

    @Then("size {string} should be available")
    public void sizeShouldBeAvailable(String size) {
        Assert.assertTrue("Expected size is not available: " + size, getProductDetailsPage().isSizeDisplayed(size));
    }

    @When("the user selects size {string}")
    public void theUserSelectsSize(String size) {
        Assert.assertTrue("Expected size is not visible: " + size, getProductDetailsPage().isSizeDisplayed(size));
        getProductDetailsPage().selectSize(size);
    }

    @When("the user clicks on ADD TO BAG")
    public void theUserClicksOnAddToBag() {
        getProductDetailsPage().clickAddToBag();
    }

    @When("the user opens the shopping bag")
    public void theUserOpensTheShoppingBag() {
        getProductDetailsPage().openBagFromProductPageOrHeader();
        Assert.assertTrue("Bag page is not visible", getBagPage().isBagPageVisible());
    }

    @Then("the bag page should be displayed")
    public void theBagPageShouldBeDisplayed() {
        Assert.assertTrue("Bag page is not displayed", getBagPage().isBagPageVisible());
    }

    @When("the user changes the quantity to {string}")
    public void theUserChangesTheQuantityTo(String quantity) {
        getBagPage().openQuantitySelector();
        getBagPage().selectQuantity(quantity);
        getBagPage().clickDone();
    }

    @Then("the bag quantity should be displayed as {string}")
    public void theBagQuantityShouldBeDisplayedAs(String quantity) {
        Assert.assertTrue("Quantity update not visible for quantity " + quantity, getBagPage().isQuantityApplied(quantity));
    }

    @Then("the size selection error message should be displayed")
    public void theSizeSelectionErrorMessageShouldBeDisplayed() {
        Assert.assertTrue("Size selection error message is not displayed", getProductDetailsPage().isSizeSelectionErrorDisplayed());
    }
}
