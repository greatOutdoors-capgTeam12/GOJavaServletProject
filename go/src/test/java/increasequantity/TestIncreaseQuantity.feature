Feature: Increase Product Feature

  #This is how background can be used to eliminate duplicate steps
  Background: User navigates to Great Outdoors All Products Page
    Given I am on Increase Product Quantity in Great Outdoors Product page

  Scenario Outline: User enters a valid Product Id, Product Quantity and Submits the form
    When I enter product id as "<productId>"
    And I enter quantity as "<productQuantity>"
    And I click on Increase Quantity button
    Then "<productId>" and "<productQuantity>" Increase Product Quantity Success Message popped up

    Examples: 
      | productId | productQuantity |
      | prod07    |               1 |
      | prod06    |               2 |

  Scenario Outline: User enters a valid Product Id, Product Quantity and Submits the form
    When I enter product id as "<productId>"
    And I enter quantity as "<productQuantity>"
    And I click on Increase Quantity button
    Then Increase Product Quantity Failure Message popped up

    Examples: 
      | productId | productQuantity |
      | prod258   |               2 |
