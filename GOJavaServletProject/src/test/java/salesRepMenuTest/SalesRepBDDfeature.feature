Feature: Checking if Reports are generated
#This is how background can be used to eliminate duplicate steps 

Background: User navigates to SalesRepMenu 
   Given I am on SalesRepMenu 


   
 #Scenario Outline with AND 
Scenario Outline: Successful Generation of Reports (examples)
   When I enter username as "<username>"
   And Clicks on submit
   Then Report Gets Generated 
   
   Examples:
   	| username  | 
	| SR01 | 
	| SR99 |
	
	
	
 