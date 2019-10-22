#Author: anshu.f.kumar@capgemini.com
@tag
Feature: Checking target status by sales representative

  @tag2
  Scenario Outline: The sales representative enters required details to check target status
    Given The sales representative is logged into the go's website
    And clicks the check bonus button
    When It enters the UserId as "<userId>"
    And It clicks submit button
    Then The "<message>" is displayed

    Examples: 
      | userId | message                                                 |
      | AM01   | Wrong user id                                           |
      | SR01   | Your target sales is 2000 and target status is not met  |
      | SR02   | Your target sales is 2000 and target status is exceeded |
