Feature: Shelf Time Reports
#This is how background can be used to eliminate duplicate steps 

Background: User navigates to Great Outdoors home Page
	Given I am on Shelf Time Report Form in Great Outdoors home page

Scenario Outline: User enters a valid Retailer Id and a valid Date and Submits the form
	When I enter retialerId as "<retailerId>"
	And I enter reportType as "<reportType>"
	And I enter date as "<date>"
	Then Report must be generated 
	Examples:
    |	retailerId	|	reportType						|	date		|
    |	RT01		|	Yearly Shelf Time Report		|	10/04/2019	|
    

#Scenario with BUT 
#Scenario: Failed Login with relogin option for wrong credentials
#   When I enter username as "TOM" 
#   And I enter password as "JERRY" 
#   Then Login should fail 
#   But Relogin option should be available
#    