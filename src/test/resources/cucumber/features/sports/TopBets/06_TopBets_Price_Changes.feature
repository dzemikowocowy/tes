@Top-Bets
Feature: 06 TopBets Price Changes

  @manual
  Scenario: Here we setup our Hardcoded event
    Given we set our Hardcoded Top Bet to Default values


  @manual
  Scenario: Price changes odd value is updated and animation is displayed - Home page
    Given the user navigates to WilliamHill homepage
    When increase the price of a Top Bet and the Increase animation appears
    And the price has 'increased' for the modified selection
    When decrease the price of a Top Bet and the Decrease animation appears
    And the price has 'decreased' for the modified selection


  @manual
  Scenario: Price changes odd value is updated and animation is displayed - Football page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Football' from sports menu
    When increase the price of a Top Bet and the Increase animation appears
    And the price has 'increased' for the modified selection
    When decrease the price of a Top Bet and the Decrease animation appears
    And the price has 'decreased' for the modified selection


  @manual
  Scenario: Price changes odd value is updated and animation is displayed - Competition page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' competitions page
    When increase the price of a Top Bet and the Increase animation appears
    And the price has 'increased' for the modified selection
    When decrease the price of a Top Bet and the Decrease animation appears
    And the price has 'decreased' for the modified selection


  @manual
  Scenario: Price changes odd value is updated and animation is displayed - Daily List page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' daily list page
    When increase the price of a Top Bet and the Increase animation appears
    And the price has 'increased' for the modified selection
    When decrease the price of a Top Bet and the Decrease animation appears
    And the price has 'decreased' for the modified selection


  @manual
  Scenario: Price changes odd value is updated and animation is displayed - Horse Racing page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Horses' from sports menu
    When increase the price of a Top Bet
    Then the 'increase' animation for the modified selection is displayed
    And the price has 'increased' for the modified selection
    When decrease the price of the On-focus Top Bet
    Then the 'decrease' animation for the modified selection is displayed
    And the price has 'decreased' for the modified selection


  @manual
  Scenario: Price changes odd value is updated and animation is displayed - Greyhounds page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Greyhounds' from sports menu
    When increase the price of a Top Bet and the Increase animation appears
    And the price has 'increased' for the modified selection
    When decrease the price of a Top Bet and the Decrease animation appears
    And the price has 'decreased' for the modified selection

