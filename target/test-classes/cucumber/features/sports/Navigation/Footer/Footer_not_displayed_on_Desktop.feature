@footer
@desktop-only
Feature: Footer not displayed in desktop


  Scenario: Mobile footer not displayed on Desktop view - Home Page
    Given the user is on the William Hill Home page
    Then the Mobile Footer is not being displayed


  Scenario: Mobile footer not displayed on Desktop view - Sports Page
    Given the user is on the William Hill Home page
    When the user selects 'Football' from sports menu
    Then the Mobile Footer is not being displayed


  Scenario: Mobile footer not displayed on Desktop view - Competitions Page
    Given the user is on the William Hill Home page
    When the user navigates to 'football' competitions page
    Then the Mobile Footer is not being displayed


  Scenario: Mobile footer not displayed on Desktop view - Daily List Page
    Given the user is on the William Hill Home page
    When the user is on the 'Football' Daily List page
    Then the Mobile Footer is not being displayed


  Scenario: Mobile footer not displayed on Desktop view - Horse Racing Page
    Given the user is on the William Hill Home page
    When the user selects 'Horses' from sports menu
    Then the Mobile Footer is not being displayed


  Scenario: Mobile footer not displayed on Desktop view - Greyhounds Page
    Given the user is on the William Hill Home page
    When the user selects 'Greyhounds' from sports menu
    Then the Mobile Footer is not being displayed