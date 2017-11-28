@smoke-translation
Feature: 01 Login verification

  Scenario: We login from the Home Page.
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user has logged into sportsbook
    Then the user verify that was successfully logged in