Feature: Checking if Reports are generated
#This is how background can be used to eliminate duplicate steps 

Background: User navigates to Camping Menu
   Given I am on Camping Menu


   
 #Scenario Outline with AND 
Scenario Outline: Successful Generation of Reports (examples)
   When I enter username as "<username>"
   And I enter start date as "<entrydate>"
   And I enter end date as "<exitdate>"
   And Clicks on submit
   Then Report Gets Generated 
   
   Examples:
   	| username  | entrydate  | exitdate   |
	| SR01 		| 01/2019/20 | 10/2019/23 |
	| ALL 		| 05/2019/15 | 09/2019/29 |
	| SR99 		| 01/2019/13 | 12/2019/20 |
	
	
	
 