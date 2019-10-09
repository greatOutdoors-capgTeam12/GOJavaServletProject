Feature: Login Feature for great outdoors

#with Examples Keyword

Scenario Outline:Successful Login as a authenticated user
    Given User is on GO homepage
    When User Clicks on Login Tab
    And User Clicks on Login Button
    When User enters "<UserId>" and "<password>"
    And User clicks on the submit button
    Then "<UserId>" Successfully Logs-In

Examples:
	| UserId | password |
	|  RT03  | @manRaj1 |
	