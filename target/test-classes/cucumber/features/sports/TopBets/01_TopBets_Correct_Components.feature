@Top-Bets
Feature: 01 TopBets Correct Components

  Scenario: Correct components in Top Bets widget in homepage
    Given the user navigates to WilliamHill homepage
    Then the user verifies that Top Bets title is being displayed only for desktop
    And all bets components on Top Bets widget are being displayed
    And the user is able to add a Top Bet Selection to the Betslip
    And the user navigates to the event page by clicking on the Top Bet event


  Scenario: Correct components in Top Bets widget in sport homepage
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the user verifies that Top Bets title is being displayed only for desktop
    And all bets components on Top Bets widget are being displayed
    And the user is able to add a Top Bet Selection to the Betslip
    And the user navigates to the event page by clicking on the Top Bet event


  Scenario: Correct components in Top Bets widget in sport Competitions page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' competitions page
    Then the user verifies that Top Bets title is being displayed only for desktop
    And all bets components on Top Bets widget are being displayed
    And the user is able to add a Top Bet Selection to the Betslip
    And the user navigates to the event page by clicking on the Top Bet event


  Scenario: Correct components in Top Bets widget in sport Daily List page
    Given the user navigates to WilliamHill homepage
    And the user navigates to 'football' daily list page
    Then the user verifies that Top Bets title is being displayed only for desktop
    And all bets components on Top Bets widget are being displayed
    And the user is able to add a Top Bet Selection to the Betslip
    And the user navigates to the event page by clicking on the Top Bet event

@defect
  Scenario: User verify that all the Top Bets components are being displayed correctly on Horse Racing Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Horse Racing' from sports menu
    Then the user verifies that Top Bets title is being displayed only for desktop
    And all bets components on Top Bets widget are being displayed
    And the user is able to add a Top Bet Selection to the Betslip
    And the user navigates to the event page by clicking on the Top Bet event

@defect
  Scenario: User verify that all the Top Bets components are being displayed correctly on Grey Hounds Racing Page
    Given the user navigates to WilliamHill homepage
    And the user selects 'Greyhounds' from sports menu
    Then the user verifies that Top Bets title is being displayed only for desktop
    And all bets components on Top Bets widget are being displayed
    And the user is able to add a Top Bet Selection to the Betslip
    And the user navigates to the event page by clicking on the Top Bet event