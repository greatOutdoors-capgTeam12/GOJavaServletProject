@tag1
Feature: SalesRep wants to  initiate a return order request by retailer


    
    

 
  @tag2
  Scenario Outline: The sales representative enters required details to return order
    Given The sales representative is in go's website
    And clicks the return order button
    When It enters the UserId as "<userid>" and OrderId as "<orderid>" and Reason as "<reason>" 
    And It clicks submit button
    Then "<output>" is displayed

 

    Examples: 
      | userid 	| orderid		| reason			| output |
      | SR02 	| OR14444 		| defective			| User not linked with the order |
      | SR01 	| OR789 		| defective			| Unable to process request |
      | SR01 	| OR123 		| wrong product		| User not linked with the order |
      | SR02 	| OR123 		| wrong product		| return order accepted|