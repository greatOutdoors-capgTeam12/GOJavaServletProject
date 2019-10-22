Feature: Delete Product Feature

  #This is how background can be used to eliminate duplicate steps
  Background: User navigates to Great Outdoors All Products Page
    Given I am on Delete Product Form in Great Outdoors Product page

  Scenario Outline: User enters a valid Product Id and Submits the form
    When I enter product id as "<productId>"
    And I click on delete button
    And I accept the alert
    Then "<productId>" Delete Product Message Success popped up

    Examples: 
      | productId |
      | prod22    |

  Scenario Outline: User enters a valid Product Id and Submits the form
    When I enter product id as "<productId>"
    And I click on delete button
    And I accept the alert
    Then Delete Product Message Failure popped up

    Examples: 
      | productId |
      | prod258Q   |
      | prod444   |
