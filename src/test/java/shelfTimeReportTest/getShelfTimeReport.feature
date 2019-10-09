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
    |	RT01		|	Monthly Shelf Time Report		|	10/04/2019	|
 	|	RT02		|	Quaterly Shelf Time Report      |	09/21/2018	|
 	|	RT01		|	Quarterly Shelf Time Report		|	02/15/2017	|
 	|	RT01		|	Monthly Shelf Time Report		|	12/05/2019	|
 	|	RT02		|	Quarterly Shelf Time Report		|	10/21/2018	|
 	|	RT02		|	Yearly Shelf Time Report		|	12/10/2018	|
 	|	RT03		|	Yearly Shelf Time Report		|	05/07/2017	|
 	|	RT03		|	Quarterly Shelf Time Report		|	06/10/2017	|
 	|	RT03		|	Monthly Shelf Time Report		|	07/12/2017	|
 	