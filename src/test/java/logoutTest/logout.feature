@tag1
Feature: Log-Out Feature for great outdoors

#with Examples Keyword
@tag2
Scenario Outline: Successful Logout as a authenticated user
    Given User is on GO homepage
    When User Clicks on Login Tab
    And User Clicks on Logout Button
    And User enters "<UserId>"
    And User clicks on the submit button
    Then "<UserId>" Successfully Logs-Out
Examples:
	| UserId |
	|  RT03  |
	
@tag3
Scenario Outline: Unsuccessful Logout of a already logged-out Authenticated user
    Given User is on GO homepage
    When User Clicks on Login Tab
    And User Clicks on Logout Button
    And User enters "<UserId>"
    And User clicks on the submit button
    Then User already logged out Message displayed

Examples:
	| UserId |
	|  RT03  |