Feature: Validation of search functionality on Rocketmiles

  Background:
    Given User is on Rocketmiles website

  @positive
  Scenario: As a user I want to Search for a hotel at July 30, 2019 - July 31, 2019 with one guest in Chicago, IL
    When User search hotels in "Chicago, IL" for July 30, 2019 - July 31, 2019 with one guest
    And User choose "Amazon.com Gift Card" reward program
    Then User get available hotels at given date range "Jul 30 - Jul 31" in "Chicago, IL, USA" with "Amazon.com Gift Card" reward

  @positive
  Scenario: As a user I want to Search for a hotel at July 30, 2019 - July 31, 2019 with one guest in Chicago, IL
    When User search hotels in "Chicago, IL" for July 30, 2019 - July 31, 2019 with one guest
    Then All listed hotels are in Chicago, IL area

  @negative
  Scenario: Search with empty destination hotel
    When User search hotels but with hotel box empty
    Then User will get city box message

  @negative
  Scenario: Search with empty reward program
    When User search hotels in "Chicago, IL" but with reward box empty
    Then User will get reward box message

  @negative
  Scenario: Search with empty reward program and empty destination hotel
    When User search with empty hotel and reward program
    Then User will get hotel box message


















