@Top-Bets
Feature: 10 TopBets InPlay transition

#  Scenario: Here we setup our Hardcoded event
#    Given we set our Hardcoded Top Bet to Default values


  @manual
  Scenario: In-play transition bet disappears if event changes to in-play - Home Page
    Given the user navigates to WilliamHill homepage
    And specific Top Bet is displayed
    Then the event is in-play
    And the Top Bet Event 'is not' displayed


  @manual
  Scenario: In-play transition bet appears if event changes to pre-match - Home Page
    Given the user navigates to WilliamHill homepage
    And specific Top Bet is set on Scenario Context
    Then the event is pre-match
    And set back the Top Bet Manual Weight
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: In-play transition bet disappears if event changes to in-play - Sports Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Football' from sports menu
    And specific Top Bet is displayed
    Then the event is in-play
    And the Top Bet Event 'is not' displayed


  @manual
  Scenario: In-play transition bet appears if event changes to pre-match - Sports Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Football' from sports menu
    And specific Top Bet is set on Scenario Context
    Then the event is pre-match
    And set back the Top Bet Manual Weight
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: In-play transition bet disappears if event changes to in-play - Competition Page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' competitions page
    And specific Top Bet is displayed
    Then the event is in-play
    And the Top Bet Event 'is not' displayed


  @manual
  Scenario: In-play transition bet appears if event changes to pre-match - Competition Page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' competitions page
    And specific Top Bet is set on Scenario Context
    Then the event is pre-match
    And set back the Top Bet Manual Weight
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: In-play transition bet disappears if event changes to in-play - Daily List Page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' daily list page
    And specific Top Bet is displayed
    Then the event is in-play
    And the Top Bet Event 'is not' displayed


  @manual
  Scenario: In-play transition bet appears if event changes to pre-match - Daily List Page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' daily list page
    And specific Top Bet is set on Scenario Context
    Then the event is pre-match
    And set back the Top Bet Manual Weight
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: In-play transition bet disappears if event changes to in-play - Horse Racing Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Horses' from sports menu
    And specific Top Bet is displayed
    Then the event is in-play
    And the Top Bet Event 'is not' displayed


  @manual
  Scenario: In-play transition bet appears if event changes to pre-match - Horse Racing Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Horses' from sports menu
    And specific Top Bet is set on Scenario Context
    Then the event is pre-match
    And set back the Top Bet Manual Weight
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: In-play transition bet disappears if event changes to in-play - Greyhounds Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Greyhounds' from sports menu
    And specific Top Bet is displayed
    Then the event is in-play
    And the Top Bet Event 'is not' displayed


  @manual
  Scenario: In-play transition bet appears if event changes to pre-match - Greyhounds Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Greyhounds' from sports menu
    And specific Top Bet is set on Scenario Context
    Then the event is pre-match
    And set back the Top Bet Manual Weight
    And the Top Bet Event 'is' displayed