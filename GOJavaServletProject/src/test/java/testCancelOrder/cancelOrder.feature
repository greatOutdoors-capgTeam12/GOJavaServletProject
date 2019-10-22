#Author: anshu.f.kumar@capgemini.com
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#@ (Tags/Labels):To group Scenarios
@tag
Feature: Cancelling Order by sales representative

  @tag2
  Scenario Outline: The sales representative enters required details to cancel order
    Given The sales representative is logged into the go's website
    And clicks the cancel order button
    When It enters the UserId as "<userId>" and OrderId as "<orderId>"
    And It clicks submit button
    Then The "<message>" is displayed

    Examples: 
      | userId | orderId | message                            |
      | AM01   | OR157   | Wrong user id                      |
      | SR02   | OR123   | Order already dispatched           |
      | SR02   | OR1324  | Order id not present               |
      | SR02   | OR100   | Order get cancelled                |
      | SR02   | OR100   | Products are not mapped with order |
