@tag 
Feature: User wants to update Address to their address list 

@tag2 
Scenario Outline: User enters required details to update address 
	Given User is in go's website 
	And User clicks the Update Address button 
	When User enters the addressId as "<address_ID>" and retailerId as "<Retailer_ID>" and Building Number as "<Building_Number>" and  City as "<City>"  and  state as "<State>" and Zip Code as "<Zip Code>" and country as "<Country>" 
	And User clicks on Submit Button 
	Then "<message>" is displayed 
	
	
	Examples: 
		|address_ID     |Retailer_ID    |	Building_Number  | City    | State     | Zip Code | Country | message                    |
		|SR01AD13		|SR01           |	11               | PUNE    | AFRICA    | 234156   | INDIA   | ADDRESS ADDED SUCCESSFULLY |
		|SR01AD12		|SR01           |	23               | NAGPUR  | CHINA     | 241456   | INsDIA  | ADDRESS NOT ADDED          |
		|AR123			|SR02           |	69               | BIHAR   | AMERICA   | 900800   | INDIA   | ADDRESS ADDED SUCCESSFULLY |
		
