Feature: Member 2 validates Myntra product sorting by price

  Background:
    Given Member 2 opens the Myntra men's t-shirts category

  @member2 @sorting
  Scenario: Verify price sorting from low to high and high to low
    When Member 2 captures the first five displayed product prices
    And Member 2 sorts the products by "Price: Low to High"
    Then Member 2 verifies the sorted results are refreshed successfully
    And Member 2 verifies the first five product prices are sorted in ascending order
    When Member 2 sorts the products by "Price: High to Low"
    Then Member 2 verifies the first five product prices are sorted in descending order
