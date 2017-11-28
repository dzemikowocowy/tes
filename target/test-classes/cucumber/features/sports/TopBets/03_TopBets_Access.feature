@Top-Bets
Feature: 03 TopBets Access

  @manual
  Scenario: Navigation to Top Bets page through Show more link
    Given the user navigates to WilliamHill homepage
    Then the user clicks on Top Bets Show more link
    Then all bets components on Top Bets page are being displayed


  @manual
  Scenario: Navigation to Top Bets page through sports menu
    Given the user navigates to WilliamHill homepage
    When the user selects 'top bets' from sports menu
    Then all bets components on Top Bets page are being displayed


  @manual
  Scenario: Navigation to  Top Bets page through carousel (mobile) or left menu (desktop)
    Given the user navigates to WilliamHill homepage
    When the user clicks on Top Bets from the Carousel or from the Left Hand menu
    Then all bets components on Top Bets page are being displayed