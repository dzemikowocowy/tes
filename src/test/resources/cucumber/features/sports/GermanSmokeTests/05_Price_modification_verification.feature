@smoke-translation
@non-live
Feature: 05 Price modification verification


  @desktop-only
  Scenario: User is able to modify the price of a Selection on Sports Page - Desktop
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    Then user decrease the price of the first selection displayed and verify it


  @desktop-only
  Scenario: User is able to modify the price of a Selection on In Play Page - Desktop
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    Then user increase the price of the first selection displayed and verify it


  @mobile-only
  Scenario: User is able to modify the price of a Selection on Sports Page - Mobile
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    Then the user clicks on 'Live' from the Sports Page Carousel
    Then user decrease the price of the first selection displayed and verify it


  @mobile-only
  Scenario: User is able to modify the price of a Selection on In Play Page - Mobile
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    Then the user clicks on 'Live' from the Sports Page Carousel
    Then user increase the price of the first selection displayed and verify it