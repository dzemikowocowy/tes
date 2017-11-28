@virtualWorld
@mobile-only
Feature: Virtual Horse Racing National Hunt

  Scenario: On this scenario we verify the components of this section.
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the user select 'horseracing-nationalhunt' from the Virtual World Carousel
    And user verify that the section tile is 'Next Off Races'
    And user verify that the section tile is 'All Meetings'
    And meetings are display with races on them
    And the Virtual Banner is displayed at the bottom


  Scenario: On this scenario we verify the components of the Event page.
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the user select 'horseracing-nationalhunt' from the Virtual World Carousel
    And the user click on a meeting
    And user verify that the section tile is 'Virtual Horse Racing - National Hunt'
    And the user will be able to see only the Demo video
    And the event title is the same as the name of the event clicked
    And the navigation arrows for the selections are displayed
    And the selections are order by their odd values


  Scenario: On this scenario we verify that the non logged in user will be able to see the Demo video
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the user select 'horseracing-nationalhunt' from the Virtual World Carousel
    And user click on Next Off Races bar
    And the user will be able to see only the Demo video
    And the three next events are displayed


  Scenario: On this scenario we verify that the logged in user will be able to see the real video
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    And the user has logged into sportsbook
    Then the user select 'horseracing-nationalhunt' from the Virtual World Carousel
    And user click on Next Off Races bar
    And the three next events are displayed
    And the user will be able to see only the event video


  Scenario: On this scenario we verify on Features page
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the user select 'horseracing-nationalhunt' from the Virtual World Carousel
    And the user verify the Horse Racing National Hunt event title on the Featured page
    And the selections are order by their odd values
    And the user view the full event
    And user verify that the section tile is 'Virtual Horse Racing - National Hunt'


  Scenario: Journey 1
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the user select 'horseracing-nationalhunt' from the Virtual World Carousel
    And the user will click on Load more until it becomes Show less
    And the user will click on Show less until it becomes Load more