@virtualWorld
@mobile-only
Feature: Virtual World Featured
  As a mobile user
  I want a Featured icon at the start of the Virtual carousel
  So that I land on the Featured Sports in the Virtual Homepage
  As a mobile user
  I want the Virtual Carousel icons to have countdown clocks for Next Off events and a TV for in progress events
  So that I can see how long until the Next Off event for each Sport and which Sport has an event in progress


 1 Virtual World homepage components
 2 TV icon in an event in Virtual World homepage
 3 Non-football Next Off events in Virtual World homepage
 4 Football Next Off events in Virtual World homepage


  Scenario:Correct components in Virtual World homepage
    Given the user navigates to WilliamHill homepage
    And the user has logged into sportsbook
    When the user selects 'virtual world' from sports menu
    Then Virtual World homepage components displayed
    And Virtual World Notification Bar appears


  Scenario:TV icon in an event in Virtual World homepage
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then the TV icon is displayed for each event with correct colour


    Scenario:TV icon in an event in Virtual World homepage redirects to event page
      Given the user navigates to WilliamHill homepage
      When the user selects 'virtual world' from sports menu
      And user click on  first avaibale tv icon


  Scenario:Non-football Next Off events in Virtual World homepage sports title
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then correct Virtual sport name is displayed on the top


  Scenario: Virtual World homepage View Full Event button redirects user to event page
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then click on the first View Full Event on Virtual World home page


  Scenario: Virtual World homepage Football title is displayed above Match betting markets
    Given the user navigates to WilliamHill homepage
    When the user selects 'virtual world' from sports menu
    Then click on the first View Full Event on Virtual World home page

  Scenario: Virtual World homepage Football title is displayed above Match betting markets
    Given the user navigates to WilliamHill homepage
    And  the user selects 'virtual world' from sports menu
    When the user click on the event from virtual footbal area
    Then the user is redirected to the event page

  @deffererd
  Scenario: Virtual World homepage Total amount of markets is displayed on the right of event name
    Given the user navigates to WilliamHill homepage
    And  the user selects 'virtual world' from sports menu
    Then Total amount of markets is displayed on the right of event name


 @deffererd
  Scenario: Virtual World homepage Total amount of markets is displayed on the right of event name
    Given the user navigates to WilliamHill homepage
    And  the user selects 'virtual world' from sports menu
    Then data title  selection  and mareket for the virtual footbal event are correct
