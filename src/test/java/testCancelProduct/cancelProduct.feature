#Author: anshu.f.kumar@capgemini.com
@tag
Feature: Cancelling Product by sales representative

  @tag2
  Scenario Outline: The sales representative enters required details to cancel product
    Given The sales representative is logged into the go's website
    And clicks the cancel product button
    When It enters the "<userId>", "<orderId>", "<productId>" and "<quantity>"
    And It clicks the submit button
    Then The "<message>" is displayed

    Examples: 
      | userId | orderId | productId | quantity | message                                                |
      | AM02   | OR157   | PROD06    |        2 | Wrong user id                                          |
      | SR02   | OR1234  | PROD06    |        2 | The order id is invalid                                |
      | SR02   | OR157   | PROD06    |        5 | The product quantity is more than the quantity ordered |
      | SR02   | OR157   | PROD06    |        2 | The given product is cancelled                         |
      | SR02   | OR157   | PROD06    |        3 | The product quantity is more than the quantity ordered |
