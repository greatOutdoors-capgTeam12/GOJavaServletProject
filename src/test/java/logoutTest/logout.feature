Feature: Log-Out Feature for great outdoors

#with Examples Keyword

Scenario Outline:Successful Logout as a authenticated user
    Given User is on GO homepage
    When User Clicks on Login Tab
    And User Clicks on Logout Button
    And User enters "<UserId>"
    And User clicks on the submit button
    Then User Successfully Logs-Out

Examples:
	| UserId |
	|  RT03  |
	