Feature: Myntra Women Sneaker Add-to-Bag Flow

  Background:
    Given the user opens the Myntra website

  @smoke
  Scenario: TC-01 Verify homepage loads with search bar visible
    Then the search bar should be visible on the homepage

  @smoke
  Scenario: TC-02 Search for women sneakers and verify results
    When the user clicks on the search bar
    And the user enters "women's sneakers"
    And the user presses the Enter key
    Then the search results should be displayed

  @regression
  Scenario: TC-03 Open a product from search results
    When the user clicks on the search bar
    And the user enters "women's sneakers"
    And the user presses the Enter key
    And the user selects the product "Lotto Women Mavin Court Light Blue Sneakers"
    Then the product details page should be displayed

  @regression
  Scenario: TC-04 Verify size availability on product details page
    When the user clicks on the search bar
    And the user enters "women's sneakers"
    And the user presses the Enter key
    And the user selects the product "Lotto Women Mavin Court Light Blue Sneakers"
    Then size "5" should be available

  @smoke @regression
  Scenario: TC-05 Add product to bag after selecting size
    When the user clicks on the search bar
    And the user enters "women's sneakers"
    And the user presses the Enter key
    And the user selects the product "Lotto Women Mavin Court Light Blue Sneakers"
    And the user selects size "5"
    And the user clicks on ADD TO BAG
    And the user opens the shopping bag
    Then the bag page should be displayed

  @smoke @regression
  Scenario: TC-06 Add product to bag and update quantity to 10
    When the user clicks on the search bar
    And the user enters "women's sneakers"
    And the user presses the Enter key
    And the user selects the product "Lotto Women Mavin Court Light Blue Sneakers"
    And the user selects size "5"
    And the user clicks on ADD TO BAG
    And the user opens the shopping bag
    And the user changes the quantity to "10"
    Then the bag quantity should be displayed as "10"

  @regression
  Scenario Outline: TC-07 Update bag quantity to different values
    When the user clicks on the search bar
    And the user enters "women's sneakers"
    And the user presses the Enter key
    And the user selects the product "Lotto Women Mavin Court Light Blue Sneakers"
    And the user selects size "5"
    And the user clicks on ADD TO BAG
    And the user opens the shopping bag
    And the user changes the quantity to "<quantity>"
    Then the bag quantity should be displayed as "<quantity>"

    Examples:
      | quantity |
      | 2        |
      | 5        |
      | 10       |

  @regression
  Scenario Outline: TC-08 Add product with different sizes
    When the user clicks on the search bar
    And the user enters "women's sneakers"
    And the user presses the Enter key
    And the user selects the product "Lotto Women Mavin Court Light Blue Sneakers"
    And the user selects size "<size>"
    And the user clicks on ADD TO BAG
    And the user opens the shopping bag
    Then the bag page should be displayed

    Examples:
      | size |
      | 4    |
      | 5    |
      | 6    |
      | 7    |

  @negative
  Scenario: TC-09 Attempt to add product to bag without selecting size
    When the user clicks on the search bar
    And the user enters "women's sneakers"
    And the user presses the Enter key
    And the user selects the product "Lotto Women Mavin Court Light Blue Sneakers"
    And the user clicks on ADD TO BAG
    Then the size selection error message should be displayed

  @smoke
  Scenario: TC-10 Search with a different search term
    When the user clicks on the search bar
    And the user enters "men's running shoes"
    And the user presses the Enter key
    Then the search results should be displayed
