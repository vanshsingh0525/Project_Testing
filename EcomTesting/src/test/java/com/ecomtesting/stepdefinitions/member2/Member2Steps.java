package com.ecomtesting.stepdefinitions.member2;

import com.ecomtesting.pages.member2.Member2Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import org.junit.jupiter.api.Assertions;

public class Member2Steps {

    private final Member2Page member2Page = new Member2Page();
    private List<Integer> initialPrices;

    @Given("Member 2 opens the Myntra men's t-shirts category")
    public void member2OpensTheMyntraMensTshirtsCategory() {
        member2Page.openMensTshirtsCategory();
    }

    @When("Member 2 captures the first five displayed product prices")
    public void member2CapturesTheFirstFiveDisplayedProductPrices() {
        initialPrices = member2Page.captureFirstFiveDisplayedPrices();
    }

    @When("Member 2 sorts the products by {string}")
    public void member2SortsTheProductsBy(String sortOption) {
        member2Page.sortProductsBy(sortOption);
    }

    @Then("Member 2 verifies the first five product prices are sorted in ascending order")
    public void member2VerifiesTheFirstFiveProductPricesAreSortedInAscendingOrder() {
        member2Page.verifyPricesAreAscending();
    }

    @Then("Member 2 verifies the first five product prices are sorted in descending order")
    public void member2VerifiesTheFirstFiveProductPricesAreSortedInDescendingOrder() {
        member2Page.verifyPricesAreDescending();
    }

    @Then("Member 2 verifies the sorted results are refreshed successfully")
    public void member2VerifiesTheSortedResultsAreRefreshedSuccessfully() {
        Assertions.assertNotNull(initialPrices, "Initial prices should be captured before sorting.");
        Assertions.assertEquals(5, initialPrices.size(), "Exactly five prices should be captured before sorting.");
        member2Page.verifySortingChangedResults();
    }
}
