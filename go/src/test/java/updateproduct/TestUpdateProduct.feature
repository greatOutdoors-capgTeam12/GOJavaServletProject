Feature: Update Product Feature

  #This is how background can be used to eliminate duplicate steps
  Background: User navigates to Great Outdoors All Products Page
    Given I am on Update Product Form in Great Outdoors Product page

  Scenario Outline: User enters a valid Product Id, Product Name, Product Brand, Product Dimension, Product Specification, Price, Category, Colour and Submits the form
    When I enter product id as "<productId>"
    And I enter product name as "<productName>"
    And I enter product brand as "<productBrand>"
    And I enter product dimension as "<productDimension>"
    And I enter product Specification as "<productSpecification>"
    And I enter price as "<productPrice>"
    And I  select Category as "<category>"
    And I enter color as "<productColor>"
    And I click on submit button
    Then "<productId>" Update Product Success Message Popped Up

    Examples: 
     | productId | productName | productBrand | productDimension | productSpecification | productPrice | category | productColor |
     | prod07    |             |   Samsung    |                  |                      |        2000  |          |       336699 |
     | prod06    |             |              |                  |                      |        3000  |          |       226699 |

  Scenario Outline: User enters a valid Product Id, Product Name, Product Brand, Product Dimension, Product Specification, Quantity, Price, Category, Colour and Submits the form
    When I enter product id as "<productId>"
    And I enter product name as "<productName>"
    And I enter product brand as "<productBrand>"
    And I enter product dimension as "<productDimension>"
    And I enter product Specification as "<productSpecification>"
    And I enter price as "<productPrice>"
    And I  select Category as "<category>"
    And I enter color as "<productColor>"
    And I click on submit button
    Then Update Product Failure Message Popped Up

    Examples: 
      | productId | productName | productBrand | productDimension | productSpecification | productQuantity | productPrice | category | productColor |
      | prod77Q    | Gloves      | Adidas       | Xl               | leather              |               2 |          200 | Golf     |       336699 |
			| prodX6   	 |  Jeans      | le           | 32 waist         | denim                |               1 |          300 | personal |       226699 |
			