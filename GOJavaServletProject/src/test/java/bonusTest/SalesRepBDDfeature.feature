Feature: Checking if Reports are generated
#This is how background can be used to eliminate duplicate steps 

Background: User navigates to SalesRepMenu 
   Given I am on SalesRepMenu 


   
 #Scenario Outline with AND 
Scenario Outline: Successful Generation of Reports (examples)
   When I enter username as "<username>"
   And I enter bonus as "<Bonus>"
   And I click on set bonus
   Then Bonus gets Updated
   
   Examples:
   	| username  | Bonus |
	| SR01 | 	100		|
	| SR99 |	100		|
	
	
	
 