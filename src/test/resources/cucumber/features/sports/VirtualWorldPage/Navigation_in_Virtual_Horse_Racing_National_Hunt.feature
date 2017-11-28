@virtualWorld
@mobile-only
Feature: Navigation in Virtual Horse Racing National Hunt

  Scenario: Journey 1
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the user select 'horseracing-nationalhunt' from the Virtual World Carousel
    And the user verify that 'Horse Racing - National Hunt' is highlighted on the carousel
    And the user place a single bet from a meeting and verify that betslip is updated
    And the user click on back button
    And the user click on back button
    And the user verify that 'Featured' is highlighted on the carousel

  @virtualWorld1
  Scenario: Journey 2
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    And user click on View All Horse Racing National Hunt Events from Featured page
    And the user click on back button
    And the user verify that 'Featured' is highlighted on the carousel


  Scenario: Journey 3
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the user select 'horseracing-nationalhunt' from the Virtual World Carousel
    And the user verify that 'Horse Racing - National Hunt' is highlighted on the carousel
    And user click on Next Off Races bar
    And user click on a View Full Card button
    And the user place a single bet from a meeting and verify that betslip is updated
    And the user click on back button
    And the user verify that 'Horse Racing - National Hunt' is highlighted on the carousel
    And the user click on back button
    And the user verify that 'Featured' is highlighted on the carousel


  Scenario: Journey 4
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the user select 'horseracing-nationalhunt' from the Virtual World Carousel
    And the user verify that 'Horse Racing - National Hunt' is highlighted on the carousel
    And the user click on the Next 8 button
    And the user place a single bet from a meeting and verify that betslip is updated
    And the user click on back button
    And the user verify that 'Horse Racing - National Hunt' is highlighted on the carousel
    And the user click on back button
    And the user verify that 'Featured' is highlighted on the carousel










