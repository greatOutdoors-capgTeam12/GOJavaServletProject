#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Placing order of the products present in the cart by retailer
  @tag1
 Scenario Outline: The retailer enters required details to place order
    Given retailer is logged into the go's website
    And clicks the Buy Now button
    When It enters the UserId as "<retid>" and AddressId as "<addid>"
    And It clicks BuyNow button
    Then "<message>" is displayed


    Examples: 
      | retid | addid	 | 	 message         |
      | SR01  |	AR234	 |   Order is placed |
      | RT02  | hhflk	 |   Wrong Address   |
      |hfabd | SR02AD2   |  Wrong Retailer id|
     