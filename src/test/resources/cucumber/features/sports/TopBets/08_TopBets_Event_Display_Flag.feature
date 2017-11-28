@Top-Bets
Feature: 08 TopBets Event Display Flag

#  Scenario: Here we setup our Hardcoded event
#    Given we set our Hardcoded Top Bet to Default values


  @manual
  Scenario: verification of the Event Display Flag - Home Page
    Given the user navigates to WilliamHill homepage
    Then user set a Top Bet Event to display flag 'no'
    And the Top Bet Event 'is not' displayed
    Then user set a Top Bet Event to display flag 'yes'
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: verification of the Event Display Flag - Football Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Football' from sports menu
    Then user set a Top Bet Event to display flag 'no'
    And the Top Bet Event 'is not' displayed
    Then user set a Top Bet Event to display flag 'yes'
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: verification of the Event Display Flag - Football Competition Page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' competitions page
    Then user set a Top Bet Event to display flag 'no'
    And the Top Bet Event 'is not' displayed
    Then user set a Top Bet Event to display flag 'yes'
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: verification of the Event Display Flag - Football Daily List Page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' daily list page
    Then user set a Top Bet Event to display flag 'no'
    And the Top Bet Event 'is not' displayed
    Then user set a Top Bet Event to display flag 'yes'
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: verification of the Event Display Flag - Horse Racing Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Horses' from sports menu
    Then user set a Top Bet Event to display flag 'no'
    And the Top Bet Event 'is not' displayed
    Then user set a Top Bet Event to display flag 'yes'
    And the Top Bet Event 'is' displayed


  @manual
  Scenario: verification of the Event Display Flag - Greyhounds Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Greyhounds' from sports menu
    And we set our Hardcoded Top Bet to Default values
    Then user set a Top Bet Event to display flag 'no'
    And the Top Bet Event 'is not' displayed
    Then user set a Top Bet Event to display flag 'yes'
    And the Top Bet Event 'is' displayed