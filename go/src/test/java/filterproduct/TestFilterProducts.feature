Feature: Filter Product Feature

  #This is how background can be used to eliminate duplicate steps
  Background: User navigates to Great Outdoors All Products Page
    Given I am on Filter Product Form in Great Outdoors Product page

  Scenario Outline: User enters a valid Product Name, Product Brand,Low Price Range,High Price Range,Category and Submits the form
    When I enter product name as "<productName>"
    And I enter product brand as "<productBrand>"
    And I enter low price range as "<lowPrice>"
    And I enter high price as "<highPrice>"
    And I select category as "<category>"
    And I click on submit button
    Then Filtered Product page shown up

    Examples: 
      | productName | productBrand | lowPrice | highPrice | category        |
      | ear 				|	bose				 | 0        | 100000		| Select Category |
