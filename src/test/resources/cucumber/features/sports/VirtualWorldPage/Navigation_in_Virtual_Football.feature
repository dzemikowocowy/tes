@virtualWorld
@mobile-only

Feature: Navigation in Virtual Football

  Scenario: Journey 1
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the user select 'Football' from the Virtual World Carousel
    And the user verify that 'Football' is highlighted on the carousel
    Then the user click on the Market: 'Correct Score'
    And the user click on back button
    And the user click on back button
    And the user verify that 'Featured' is highlighted on the carousel


  Scenario: Journey 2
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then user click on View All Football Events from Featured page
    And the user verify that 'Football' is highlighted on the carousel
    And the user click on '4' selections from Football
    And the user click on back button
    And the user verify that 'Featured' is highlighted on the carousel







