@Top-Bets
@FURY-1217
Feature: 07 TopBets Handicap

#  Scenario: Here we setup our Hardcoded event
#    Given we set our Hardcoded Top Bet to Default values

  @manual
  Scenario: Handicap changes handicap value is updated on the Home page
    Given the user navigates to WilliamHill homepage
    When increase the value of the handicap on a selection on Top Bets
    Then the value of the Top Bet handicap selection is increased
    And revert the Top Bet handicap selection value


  @manual
  Scenario: Handicap changes handicap value is updated on the Football page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Football' from sports menu
    When increase the value of the handicap on a selection on Top Bets
    Then the value of the Top Bet handicap selection is increased
    And revert the Top Bet handicap selection value


  @manual
  Scenario: Handicap changes handicap value is updated on the Competitions Page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' competitions page
    When increase the value of the handicap on a selection on Top Bets
    Then the value of the Top Bet handicap selection is increased
    And revert the Top Bet handicap selection value


  @manual
  Scenario: Handicap changes handicap value is updated on the Football Daily List Page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' daily list page
    When increase the value of the handicap on a selection on Top Bets
    Then the value of the Top Bet handicap selection is increased
    And revert the Top Bet handicap selection value


  @manual
  Scenario: Handicap changes handicap value is updated on the Horse Racing Daily List Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Horses' from sports menu
    When increase the value of the handicap on a selection on Top Bets
    Then the value of the Top Bet handicap selection is increased
    And revert the Top Bet handicap selection value

  @manual
  Scenario: Handicap changes handicap value is updated on the Greyhounds Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Greyhounds' from sports menu
    When increase the value of the handicap on a selection on Top Bets
    Then the value of the Top Bet handicap selection is increased
    And revert the Top Bet handicap selection value