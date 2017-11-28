@secondary-header
@mobile-only
Feature: Secondary Header on a Depth 2 scenarios

  Scenario: On this scenario we verify that Title and Back functionality is working correctly on Football Competitions page.
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    And the user clicks on 'Competitions' from the Sports Page Carousel
    Then the second Header Title is 'Football Betting Competitions'
    And the user clicks on Second Header Back Button '2' times
    And the second Header it 'is not' displayed


  Scenario: On this scenario we verify that Title and Back functionality is working correctly on Football Daily List page.
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    And the user clicks on 'Daily List' from the Sports Page Carousel
    Then the second Header Title is 'Daily List Football'
    And the user clicks on Second Header Back Button '2' times
    And the second Header it 'is not' displayed
