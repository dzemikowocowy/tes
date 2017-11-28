@smoke
@live-safe
Feature: 02 Login verification

  Scenario: We login from the Home Page
    Given the user navigates to WilliamHill homepage
    When the user has logged into sportsbook
    Then the user verify that was successfully logged in