@virtualWorld
@mobile-only
Feature: General Navigation in Virtual World page

  Scenario: Correct options available in Virtual World Carousel
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the mobile 'virtual world' Carousel displayed with correct order
    And Default option in virtual world Carousel is Featured


  Scenario: Options in Virtual World Carousel redirect to correct pages and that these have selections
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the user navigates thru all the elements on the Virtual World Carousel


  Scenario: Correct options available in Virtual World Carousel Countdown clock
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then Countdown clock and in play event is available for all the options in carousel


  Scenario: On this Scenario we verify that one of the countdown becomes In Play in the carousel
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then verify that the section with the countdown becomes In Play icon when countdown finishes


  Scenario:TV icon in an event in Virtual World homepage
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the TV icon is displayed for each event with correct colour


  Scenario:TV icon in an event in Virtual World homepage redirects to event page
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    And user click on  first avaibale tv icon

  @QAPD-75
  Scenario:Non-football Next Off events in Virtual World homepage sports title
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then correct Virtual sport name is displayed on the top

  @QAPD-75
  Scenario: Virtual World homepage View Full Event button redirects user to event page
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then click on the first View Full Event on Virtual World home page

  @QAPD-75
  Scenario: Virtual World homepage Football title is displayed above Match betting markets
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then click on the first View Full Event on Virtual World home page

  @QAPD-76
  Scenario: Virtual World homepage Football title is displayed above Match betting markets
    Given the user navigates to WilliamHill homepage
    And  the user selects 'virtual world' from sports menu
    When the user click on the event from virtual footbal area
    Then the user is redirected to the event page

  @QAPD-76 @deffererd
  Scenario: Virtual World homepage Total amount of markets is displayed on the right of event name
    Given the user navigates to WilliamHill homepage
    And  the user selects 'virtual world' from sports menu
    Then Total amount of markets is displayed on the right of event name

  @QAPD-76 @deffererd
  Scenario: Virtual World homepage Total amount of markets is displayed on the right of event name
    Given the user navigates to WilliamHill homepage
    And  the user selects 'virtual world' from sports menu
    Then data title  selection  and mareket for the virtual footbal event are correct