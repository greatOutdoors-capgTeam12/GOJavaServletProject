Feature: Admin wants to suggest the product to their user
    
Background: Admin navigates to Great Outdoors home Page
	Given Admin is on "home" page 

Scenario Outline: Admin enters a valid Retailer Id and Submits the form
	When I enter retialerId as "<retailerId>"
	And clicks on "submit" button
	Then Product must be displayed to their screen
	Examples:
    |	retailerId	|
    |	SR01		|	
 	|	SR02		|	