Feature: New User Registration Feature for great outdoors 

@tag1
Scenario Outline: Successful Registration of a new user 
	Given User is on GO homepage 
	When User Clicks on Login Tab 
	And User Clicks on New Registration Button 
	And User enters "<userName>" , "<userId>" , "<userMail>" , "<password>" , "<userNumber>" , "<userCategory>" 
	And User clicks on the submit button 
	Then "<userId>" Successfully Registered 
	
	Examples: 
		|  userName  |  userId  |  userMail      |  password    |userNumber   |userCategory|
		|   Vani     |  RT1239  |  vani@gmail.com|  @Qwert123   |3579510246   |  3		   |
		|   Pushkar  |  RT1240  |  pusd@gmail.com|  @Abcde123   |4862193708    |  2		   |
		
@tag2		
Scenario Outline: Registration with an existing userId 
	Given User is on GO homepage 
	When User Clicks on Login Tab 
	And User Clicks on New Registration Button 
	And User enters "<userName>" , "<userId>" , "<userMail>" , "<password>" , "<userNumber>" , "<userCategory>" 
	And User clicks on the submit button 
	Then Message UserId exists pops-up 
	
	Examples: 
		|  userName  |  userId  |  userMail      |  password    |userNumber   |userCategory|
		|   Vani     |  RT1239  | vanik@gmail.com|  @Qwert123   |3579510624   |  3		   |
