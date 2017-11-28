@Top-Bets
Feature: 02 TopBets Page Verification

#  Scenario: Here we setup our Hardcoded event
#    Given we set our Hardcoded Top Bet to Default values

  @manual
  Scenario: User verify that the link click to see more  (Top Bets page) is working as expected from the home page.
    Given the user navigates to WilliamHill homepage
    Then the user clicks on Top Bets Show more link
    And all bets components on Top Bets page are being displayed


  @manual
  Scenario: User verify that the link click to see more  (Top Bets page) is working as expected from the Sports page.
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the user clicks on Top Bets Show more link
    And all bets components on Top Bets page are being displayed


  @manual
  Scenario: User verify that the link click to see more  (Top Bets page) is working as expected from the Competitions page.
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' competitions page
    Then the user clicks on Top Bets Show more link
    And all bets components on Top Bets page are being displayed


  @manual
  Scenario: User verify that the link click to see more  (Top Bets page) is working as expected from the Daily List page.
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' daily list page
    Then the user clicks on Top Bets Show more link
    And all bets components on Top Bets page are being displayed


  @manual
  Scenario: User verify that the link click to see more  (Top Bets page) is working as expected from the Horse Racing page.
    Given the user navigates to WilliamHill homepage
    And the user selects 'Horses' from sports menu
    Then the user clicks on Top Bets Show more link
    And all bets components on Top Bets page are being displayed


  @manual
  Scenario: User verify that the link click to see more  (Top Bets page) is working as expected from the Greyhounds page.
    Given the user navigates to WilliamHill homepage
    And the user selects 'Greyhounds' from sports menu
    Then the user clicks on Top Bets Show more link
    And all bets components on Top Bets page are being displayed