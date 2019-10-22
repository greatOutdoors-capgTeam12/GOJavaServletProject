Feature: SalesRep wants to  initiate a return product request by retailer

Scenario Outline: The sales representative enters required details to return product
    Given The sales representative is in go's website
    And clicks the return product button
    When It enters the UserId as "<userid>" and  OrderId as "<orderid>" and ProductId as "<productid>" and Quantity as "<quantity>" and Reason as "<reason>"
    And It clicks submit button
    Then "<output>" is displayed

 

    Examples: 
      | userid 	| orderid 	| productid	  |quantity	  |reason	  | output	 			    |
      | SR02	|	OR234	| prod02	  | 2	  	  |defective  |return order accepted    |
      | SR02	|	OR234	| prod02	  | 2	  	  |defective  |Unable to process request|