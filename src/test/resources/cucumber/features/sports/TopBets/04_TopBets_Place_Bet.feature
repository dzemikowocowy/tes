@Top-Bets
Feature: 04 TopBets Place Bet

  @manual
  Scenario: On this scenario we test placing a bet from Home Page
    Given the user navigates to WilliamHill homepage
    Then the user is able to add a Top Bet Selection to the Betslip
    And the user has logged into sportsbook
    And user open the Betslip
    Then set the stake to '0.05' for the just added bet
    And user click on Place Bet
    And the message Bets Placed is displayed


  @manual
  Scenario: On this scenario we test placing a bet from Football sports page
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the user is able to add a Top Bet Selection to the Betslip
    And the user has logged into sportsbook
    And user open the Betslip
    Then set the stake to '0.05' for the just added bet
    And user click on Place Bet
    And the message Bets Placed is displayed


  @manual
  Scenario: On this scenario we test placing a bet from Football Competitions page
    Given the user navigates to WilliamHill homepage
    When the user navigates to 'football' competitions page
    Then the user is able to add a Top Bet Selection to the Betslip
    And the user has logged into sportsbook
    And user open the Betslip
    Then set the stake to '0.05' for the just added bet
    And user click on Place Bet
    And the message Bets Placed is displayed


  @manual
  Scenario: On this scenario we test placing a bet from Football Daily List page
    Given the user navigates to WilliamHill homepage
    When the user navigates to 'football' daily list page
    Then the user is able to add a Top Bet Selection to the Betslip
    And the user has logged into sportsbook
    And user open the Betslip
    Then set the stake to '0.05' for the just added bet
    And user click on Place Bet
    And the message Bets Placed is displayed


  @manual
  Scenario: On this scenario we test placing a bet from Horse Racing page
    Given the user navigates to WilliamHill homepage
    When the user selects 'Horses' from sports menu
    Then the user is able to add a Top Bet Selection to the Betslip
    And the user has logged into sportsbook
    And user open the Betslip
    Then set the stake to '0.05' for the just added bet
    And user click on Place Bet
    And the message Bets Placed is displayed


  @manual
  Scenario: On this scenario we test placing a bet from Greyhounds page
    Given the user navigates to WilliamHill homepage
    When the user selects 'greyhound' from sports menu
    Then the user is able to add a Top Bet Selection to the Betslip
    And the user has logged into sportsbook
    And user open the Betslip
    Then set the stake to '0.05' for the just added bet
    And user click on Place Bet
    And the message Bets Placed is displayed