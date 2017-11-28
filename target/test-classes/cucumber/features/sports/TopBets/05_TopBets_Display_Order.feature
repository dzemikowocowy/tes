@Top-Bets
Feature: 05 TopBets Display Order

  @manual
  Scenario: Here we setup our Hardcoded event
    Given we set our Hardcoded Top Bet to Default values


  @manual
  Scenario: Change Display Order - Home Page
    Given the user navigates to WilliamHill homepage
    Then get the current List of Top Bets elements
    Then verify the order of Top Bets elements on BackOffice
    And modify the Manual Weight of one Top Bet to '10'
    Then verify that display order was modified
    And modify the Manual Weight of one Top Bet to '800'


  @manual
  Scenario: Verify correct Order of the Top Bets on Sports Page - Bets from that sport should be displayed first
    Given the user navigates to WilliamHill homepage
    And the user selects 'Horses' from sports menu
    Then verify that the first Top Bets displayed are from that sport


  @manual
  Scenario: Change Display Order - Sports Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Football' from sports menu
    Then get the current List of Top Bets elements
    Then verify the order of Top Bets elements on BackOffice
    And modify the Manual Weight of one Top Bet to '10'
    Then verify that display order was modified
    And modify the Manual Weight of one Top Bet to '800'


  @manual
  Scenario: Change Display Order - Competitions Page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' competitions page
    Then get the current List of Top Bets elements
    Then verify the order of Top Bets elements on BackOffice
    And modify the Manual Weight of one Top Bet to '10'
    Then verify that display order was modified
    And modify the Manual Weight of one Top Bet to '800'


  @manual
  Scenario: Change Display Order - Football Daily List Page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' daily list page
    Then get the current List of Top Bets elements
    Then verify the order of Top Bets elements on BackOffice
    And modify the Manual Weight of one Top Bet to '10'
    Then verify that display order was modified
    And modify the Manual Weight of one Top Bet to '800'


  @manual
  Scenario: Change Display Order - Horse Racing Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Horses' from sports menu
    Then get the current List of Top Bets elements
    Then verify the order of Top Bets elements on BackOffice
    And modify the Manual Weight of one Top Bet to '10'
    Then verify that display order was modified
    And modify the Manual Weight of one Top Bet to '800'


  @manual
  Scenario: Change Display Order - Greyhounds Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Greyhounds' from sports menu
    Then get the current List of Top Bets elements
    Then verify the order of Top Bets elements on BackOffice
    And modify the Manual Weight of one Top Bet to '10'
    Then verify that display order was modified
    And modify the Manual Weight of one Top Bet to '800'