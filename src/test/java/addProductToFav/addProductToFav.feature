Feature: User wants to add the product to their favourites
    
Background: User navigates to Great Outdoors home Page
	Given User is on "home" page 

Scenario Outline: User enters a valid Product Id and Retailer Id and Submits the form
	When I enter retialerId as "<retailerId>"
	And I enter productId as "<productId>"
	And clicks on "submit" button
	Then Product must be added to their wishlist 
	Examples:
    |	retailerId	|	productId	|	
    |	SR01		|	prod06		|	
 	|	SR01		|	prod07		|
 	|	SR01		|	prod06		|