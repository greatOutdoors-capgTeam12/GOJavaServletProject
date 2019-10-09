@tag  
Feature: Adding Item to cart by retailer
  
  @tag1
  Scenario Outline: The retailer enters required details to add item to cart
    Given retailer is logged into the go's website
    When User clicks the Add to Cart button
    And It enters the "<retid>" ,"<productId>" ,"<quantity>"
    And It clicks Add to Cart button
    Then "<message>" is displayed


    Examples: 
      | retid   | productId | quantity  | message				   |
      | SR01 	| prod07    |  	 2      |product is added to cart  |
      


