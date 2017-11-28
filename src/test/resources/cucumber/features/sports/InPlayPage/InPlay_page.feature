@inPlay

Feature: InPlay page


  Scenario: navigate In play page
    Given the user navigates to WilliamHill homepage
    When the user selects 'In-Play' from sports menu
    Then the user is on the In Play All Page

  Scenario:3 Click in “All Sports” button from left betting menu
    Given the user has navigated to the InPlay page
    When the user is on the In Play All Page
    Then the number next to All Sports link is the sum of all events listed below

  Scenario:Add Selection to the bet slip, check the betslip is counter
    Given a 'English League 1' event with following markets
      |market_type            |  selection_Type |
      |\Match Betting Live\  |   Home/Draw/Away   |
    And the event and market are in-play
    And the user has navigated to the InPlay page
    And the user selects 'football' button from the In-Play Betting menu
    When add selection to the betslip
    And the user opens their bet slip
    Then the betslip counter is increased

  Scenario: Checking push- by a change of price in play
    Given a 'English League 1' event with following markets
      |market_type            |  selection_Type |
      |\Match Betting Live\  |   Home/Draw/Away   |
    And the event and market are in-play
    And the user has navigated to the InPlay page
    And the user selects 'football' button from the In-Play Betting menu
    And the first selection price 'increase'
    Then the selection price increased


  Scenario: Navigation to event from In-Play page
    Given a 'English League 1' event with following markets
      |market_type            |  selection_Type |
      |\Match Betting Live\  |   Home/Draw/Away   |
    And the event and market are in-play
    And the user has navigated to the InPlay page
    And the user selects 'football' button from the In-Play Betting menu
    When the customer click on the event link
    Then the user is on the selected event page


  Scenario: Win Win in-play market in Event page European ice-hockey
    Given a '|NHL|' event with following markets
      |market_type       |  selection_Type |
      |\Money Line Live\  |  Home/Away   |
    And the event and market are in-play
    And the user has navigated to the InPlay page
    And the user selects 'ice-hockey' button from the In-Play Betting menu
    Then the correct event name is displayed on in play list
    And the correct market name is displayed on In Play list
    And the correct selections displayed with correct order
    And the selections labels displayed




