Feature: Shelf Time Reports
#This is how background can be used to eliminate duplicate steps 

Background: User navigates to Great Outdoors home Page
	Given I am on Shelf Time Report Form in Great Outdoors home page

Scenario Outline: User enters a valid Retailer Id and a valid Date and Submits the form
	When I enter retialerId as "<retailerId>"
	And I enter reportType as "<reportType>"
	Then Report must be generated
    Examples:
    |    retailerId	|	reportType                       		|    
    |    RT01     	|   Product Delivery Time Report     		|       
    |    RT02       |   Outlier Item Level Delivery Time Report |
    |    RT01       |   Item level delivery time         		|   
    |    RT01       |   Product Delivery Time Report   			|   
    |    RT02       |   Outlier Item Level Delivery Time Report |        
    |    RT02       |   Item level delivery time        		|
    |    RT03       |   Product Delivery Time Report   			|       
    |    RT03       |   Outlier Item Level Delivery Time Report |
    |    RT03       |	Item level delivery time         		|
 	
