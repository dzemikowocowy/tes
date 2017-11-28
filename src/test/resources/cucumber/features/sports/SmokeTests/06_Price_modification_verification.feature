@smoke
@non-live
Feature: 06 Price modification verification


  @desktop-only
  Scenario: User is able to Decrease the price of a Selection on Sports Page - Desktop
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then user decrease the price of the first selection displayed and verify it


  @desktop-only
  Scenario: User is able to Increase the price of a Selection on In Play Page - Desktop
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then user increase the price of the first selection displayed and verify it

  @mobile-only
  Scenario: User is able to Decrease the price of a Selection on Sports Page - Mobile
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the user clicks on 'In-Play' from the Sports Page Carousel
    Then user decrease the price of the first selection displayed and verify it

  @mobile-only
  Scenario: User is able to Increase the price of a Selection on In Play Page - Mobile
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the user clicks on 'In-Play' from the Sports Page Carousel
    Then user increase the price of the first selection displayed and verify it


