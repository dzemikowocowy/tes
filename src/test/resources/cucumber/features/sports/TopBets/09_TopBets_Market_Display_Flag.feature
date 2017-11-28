@Top-Bets
Feature: 09 TopBets Market Display Flag

#  Scenario: Here we setup our Hardcoded event
#    Given we set our Hardcoded Top Bet to Default values


  @manual
  Scenario: Verification of the Market Display Flag - Home Page
    Given the user navigates to WilliamHill homepage
    Then user set a Bet Market to display flag 'no'
    And the Top Bet Event 'is not' displayed
    Then user set a Bet Market to display flag 'yes'
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: Verification of the Market Display Flag - Sports Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Football' from sports menu
    Then user set a Bet Market to display flag 'no'
    And the Top Bet Event 'is not' displayed
    Then user set a Bet Market to display flag 'yes'
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: Verification of the Market Display Flag - Competitions Page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' competitions page
    Then user set a Bet Market to display flag 'no'
    And the Top Bet Event 'is not' displayed
    Then user set a Bet Market to display flag 'yes'
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: Verification of the Market Display Flag - Daily List Page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' daily list page
    Then user set a Bet Market to display flag 'no'
    And the Top Bet Event 'is not' displayed
    Then user set a Bet Market to display flag 'yes'
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: Verification of the Market Display Flag - Horse Racing Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Horses' from sports menu
    Then user set a Bet Market to display flag 'no'
    And the Top Bet Event 'is not' displayed
    Then user set a Bet Market to display flag 'yes'
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: Verification of the Market Display Flag - Greyhounds Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Greyhounds' from sports menu
    Then user set a Bet Market to display flag 'no'
    And the Top Bet Event 'is not' displayed
    Then user set a Bet Market to display flag 'yes'
    And the Top Bet Event 'is' displayed