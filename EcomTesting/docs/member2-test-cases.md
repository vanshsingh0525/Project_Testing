# Member 2 Test Cases: Myntra Product Sorting by Price

## Positive Test Cases

1. Verify Men's T-shirts category page loads successfully and displays at least 5 products.
2. Verify the first 5 displayed discounted prices can be captured before any sorting action.
3. Verify selecting `Price: Low to High` sorts the first 5 displayed product prices in ascending order.
4. Verify selecting `Price: High to Low` sorts the first 5 displayed product prices in descending order.
5. Verify sorting still works correctly after page refresh by reapplying the selected sort option.

## Negative Test Cases

1. Verify the test fails with a clear assertion message if fewer than 5 products are displayed.
2. Verify the test fails if the sort control is not visible or not clickable.
3. Verify the test handles products without a valid discounted price and ignores blank or malformed price text.
4. Verify the test fails if `Price: Low to High` does not update the product list after the click.
5. Verify the test fails if `Price: High to Low` returns prices in any order other than descending.
