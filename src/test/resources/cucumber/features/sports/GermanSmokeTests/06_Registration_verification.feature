@smoke-translation
Feature: 06 Registration verification

  Scenario: User is able to Register a new account from the Home Page
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user register a new account on Sportsbook
    Then the user verify that was successfully logged in