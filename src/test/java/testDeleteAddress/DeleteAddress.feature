@tag 
Feature: User wants to delete Address to their address list 

@tag2 
Scenario Outline: User enters required details to update address 
	Given User is in go's website 
	And User clicks the Delete Address button 
	When User enters the addressId as "<address_ID>" 
	And User clicks on Submit Button 
	Then "<message>" is displayed 
	
	Examples: 
	
		| address_ID | message                   |
		| SR01ADDOO6 | address deleted beroooooo |
		| SR01ADDOO1 | address not deleted berooo|