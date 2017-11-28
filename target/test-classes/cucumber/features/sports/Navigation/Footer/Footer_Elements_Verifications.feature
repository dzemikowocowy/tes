@footer
@mobile-only
Feature: Footer elements verification

  Scenario: Verification of the Footer display on Home Page
    Given the user is on the William Hill Home page
    Then the A to Z Sports section is being displayed correctly on the Footer
    And the Open Bets on footer is being displayed correctly
    And the Betslip on footer is being displayed correctly
    And the Search on footer is being displayed correctly
    And the Roulette on footer is being displayed correctly


  Scenario: Verification of the Footer display on Football Sports Page
    Given the user is on the William Hill Home page
    When the user selects 'Football' from sports menu
    Then the A to Z Sports section is being displayed correctly on the Footer
    And the Open Bets on footer is being displayed correctly
    And the Betslip on footer is being displayed correctly
    And the Search on footer is being displayed correctly
    And the Roulette on footer is being displayed correctly


  Scenario: Verification of the Footer display on Football Competition Page
    Given the user is on the William Hill Home page
    When the user navigates to 'football' competitions page
    Then the A to Z Sports section is being displayed correctly on the Footer
    And the Open Bets on footer is being displayed correctly
    And the Betslip on footer is being displayed correctly
    And the Search on footer is being displayed correctly
    And the Roulette on footer is being displayed correctly


  Scenario: Verification of the Footer display on Football Daily List Page
    Given the user is on the William Hill Home page
    When the user is on the 'Football' Daily List page
    Then the A to Z Sports section is being displayed correctly on the Footer
    And the Open Bets on footer is being displayed correctly
    And the Betslip on footer is being displayed correctly
    And the Search on footer is being displayed correctly
    And the Roulette on footer is being displayed correctly


  Scenario: Verification of the Footer display on Horse Racing Page
    Given the user is on the William Hill Home page
    When the user selects 'Horses' from sports menu
    Then the A to Z Sports section is being displayed correctly on the Footer
    And the Open Bets on footer is being displayed correctly
    And the Betslip on footer is being displayed correctly
    And the Search on footer is being displayed correctly
    And the Roulette on footer is being displayed correctly


  Scenario: Verification of the Footer display on Greyhounds Page
    Given the user is on the William Hill Home page
    When the user selects 'Greyhounds' from sports menu
    Then the A to Z Sports section is being displayed correctly on the Footer
    And the Open Bets on footer is being displayed correctly
    And the Betslip on footer is being displayed correctly
    And the Search on footer is being displayed correctly
    And the Roulette on footer is being displayed correctly