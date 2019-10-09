Feature: Filter Product Feature

  #This is how background can be used to eliminate duplicate steps
  Background: User navigates to Great Outdoors All Products Page
    Given I am on Filter Product Form in Great Outdoors Product page

  Scenario Outline: User enters a valid Product Id, Product Name, Product Brand, Product Dimension, Product Specification, Price, Category, Colour and Submits the form
    And I enter product name as "<productName>"
    And I enter product brand as "<productBrand>"
    And I enter low price range as "<lowPrice>"
    And I enter high price as "<highPrice>"
    And I select category as "<category>"
    And I click on submit button
    Then Filtered Product page shown up

    Examples: 
     | productName | productBrand | lowPrice | highPrice | category        | 
     | watch       | Titan        |  0       | 100000    | Select Category |
     |             | Bose         |  0       | 100000    | Select Category |
     |             | Bose         |  0       | 4000      | Select Category |                   | 
		 |    				 |							|	 10			 |	5000		 |	Golf					 |