#Author: anshu.f.kumar@capgemini.com
@tag
Feature: Checking bonus by sales representative

  @tag2
  Scenario Outline: The sales representative enters required details to check bonus
    Given The sales representative is logged into the go's website
    And clicks the check bonus button
    When It enters the UserId as "<userId>"
    And It clicks submit button
    Then The "<message>" is displayed

    Examples: 
      | userId | message             |
      | AM01   | Wrong user id       |
      | SR01   | Your bonus is 100.0 |
      | SR02   | Your bonus is 150.0 |
