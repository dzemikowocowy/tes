@secondary-header
@mobile-only
Feature: Secondary Header on a Depth 1 scenarios

  Scenario: On this scenario we verify that Title and Back functionality is working correctly on Football page.
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the second Header Title is 'football Betting Competitions'
    And the user clicks on Second Header Back Button '1' times
    And the second Header it 'is not' displayed


  Scenario: On this scenario we verify that Title and Back functionality is working correctly on Greyhounds page.
    Given the user navigates to WilliamHill homepage
    When the user selects 'Greyhounds' from sports menu
    Then the second Header Title is 'Greyhounds Racing'
    And the user clicks on Second Header Back Button '1' times
    And the second Header it 'is not' displayed


  Scenario: On this scenario we verify that Title and Back functionality is working correctly on Horse Racing page.
    Given the user navigates to WilliamHill homepage
    When the user selects 'Horses' from sports menu
    Then the second Header Title is 'Horse Racing'
    And the user clicks on Second Header Back Button '1' times
    And the second Header it 'is not' displayed
