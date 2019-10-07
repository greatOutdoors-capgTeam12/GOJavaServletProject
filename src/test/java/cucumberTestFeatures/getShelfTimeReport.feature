Feature: Shelf Time Reports
#This is how background can be used to eliminate duplicate steps 

Background: User navigates to GoAdmin Shelf Time Reports  
   Given I am on Great Outdoors home page 

#Scenario with AND 
Scenario: Failed Login for wrong credentials
   When I enter username as "TOM"
   And I enter password as "JERRY" 
   Then Login should fail 

#Scenario with BUT 
Scenario: Failed Login with relogin option for wrong credentials
   When I enter username as "TOM" 
   And I enter password as "JERRY" 
   Then Login should fail 
   But Relogin option should be available
   
 #Scenario Outline with AND 
Scenario Outline: Failed Login for wrong credentials (examples)
   When I enter username as "<username>"
   And I enter password as "<password>" 
   Then Login should fail 
   
   Examples:
   	| username  | password  | 
	| TOM | JERRY | 
	| RITESH | PRASAD |
	| JP | NARAYAN |
	
	
 