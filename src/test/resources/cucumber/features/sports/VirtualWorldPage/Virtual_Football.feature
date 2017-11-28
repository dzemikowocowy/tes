@virtualWorld
@mobile-only
Feature: Virtual Football

  Scenario: On this scenario we verify the components of this section.
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the user select 'Football' from the Virtual World Carousel
    And user verify that the section tile is 'Markets'
    And verify all the Football components


  Scenario: On this scenario we verify that the logged in user can see the correct event video.
    Given the user navigates to WilliamHill homepage
    And the user has logged into sportsbook
    When the user selects 'virtual world' from sports menu
    Then the user select 'Football' from the Virtual World Carousel
    And the user will be able to see only the football event video


  Scenario: On this scenario we verify that non logged in user can see the demo video.
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the user select 'Football' from the Virtual World Carousel
    And the user will be able to see only the Demo video on Football




