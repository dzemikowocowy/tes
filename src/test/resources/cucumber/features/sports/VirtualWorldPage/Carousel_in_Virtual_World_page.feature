@virtualWorld
@mobile-only

Feature: Carousel in Virtual World page
  As a mobile user
  I want a Featured icon at the start of the Virtual carousel
  So that I land on the Featured Sports in the Virtual Homepage
  As a mobile user
  I want the Virtual Carousel icons to have countdown clocks for Next Off events and a TV for in progress events
  So that I can see how long until the Next Off event for each Sport and which Sport has an event in progress


  1. Correct options available in Virtual World Carousel.
  2. Options in Virtual World Carousel redirect to correct pages.
  3. Default option in Virtual World Carousel is Featured.
  4. Countdown clock in virtual sport in Virtual World Carousel.
  5. WHTV icon in virtual sport in Virtual World Carousel.

  Scenario:Correct options available in Virtual World Carousel
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the mobile 'virtual world' Carousel displayed with correct order
    And Default option in virtual world Carousel is Featured


  Scenario: Options in Virtual World Carousel redirect to correct pages
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the user navigates thru all the elements on the Virtual World Carousel


  Scenario:Correct options available in Virtual World Carousel Countdown clock
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then Countdown clock and in play event is available for all the options in carousel


  Scenario: On this Scenario we verify that one of the countdown becomes In Play in the carousel
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then verify that the section with the countdown becomes In Play icon when countdown finishes