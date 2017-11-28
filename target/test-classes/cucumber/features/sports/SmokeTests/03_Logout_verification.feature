@smoke
@live-safe
Feature: 03 Logout verification

  Scenario: User is able to Logout from the Home Page
    Given the user navigates to WilliamHill homepage
    When the user has logged into sportsbook
    Then the user logs out from Sportsbook
    And the user verify that was successfully logged out

