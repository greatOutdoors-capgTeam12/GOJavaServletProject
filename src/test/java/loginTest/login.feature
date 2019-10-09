Feature: Login Feature for great outdoors

#with Examples Keyword
@tag1
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
	
@tag2
Scenario Outline:Login as an Unauthenticated user
    Given User is on GO homepage
    When User Clicks on Login Tab
    And User Clicks on Login Button
    When User enters "<UserId>" and "<password>"
    And User clicks on the submit button
    Then Message UserId doesn't exist pops

Examples:
	| UserId | password |
	|  assx  | @manRaj1 |
	
@tag3
Scenario Outline:Login with a wrong password
    Given User is on GO homepage
    When User Clicks on Login Tab
    And User Clicks on Login Button
    When User enters "<UserId>" and "<password>"
    And User clicks on the submit button
    Then Message password doesnot match

Examples:
	| UserId | password |
	|  USER01| sds55fsd |