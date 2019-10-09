@tag1
Feature: User wants to add Address to their address list
    
  @tag2
  Scenario Outline: User enters required details to add address
    Given User is in go's website 
    And User clicks the Add Address button
    When User enters the retailerId as "<Retailer_ID>" 	and  Building Number as "<Building_Number>" and  City as "<City>"  and  state as "<State>" and Zip Code as "<Zip Code>" and country as "<Country>"  
    And User clicks on Submit Button
    Then "<message>" is displayed

	
	Examples:
	|Retailer_ID    |	Building_Number  | City    | State          | Zip Code | Country | message                    |
	|SR01           |	23               | BOM     | MAHARASHTRA    | 234156   | INDIA   | ADDRESS ADDED SUCCESSFULLY |
	|SR01           |	20               | BOM     | MAHARASHTRA    | 241456   | INsDIA  | ADDRESS NOT ADDED          |
	|SR02           |	12               | INDORE  | MADHYA PRADESH | 900800   | INDIA   | ADDRESS ADDED SUCCESSFULLY |
	  