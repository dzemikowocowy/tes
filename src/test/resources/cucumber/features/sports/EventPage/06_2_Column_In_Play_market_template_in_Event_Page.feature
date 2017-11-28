@MarketTemplateEventPage

Feature: 06 2 Column inplay market template in Event page
  AS A WH Customer
  I WANT markets using 2 Column market template in in-play event page
  SO THAT they are correctly displayed

  Scenario: WinWin in-play market in Event page
    Given a '|NHL|' event with following markets
      | market_type       | selection_Type |
      | \Money Line Live\ | Home/Away      |
    And the event and market are in-play
    When the user has navigated to the 'ice-hockey' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market


#  Scenario: First To Score in-play market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      | market_type      | selection_Type |
#      | \First to score\ | Home/Away      |
#    And the event and market are in-play
#    When the user has navigated to the 'Football' page for created event
#    And the user select correct market collection
#    Then the correct event name is displayed
#    And the correct market name is displayed
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#    And the user can collapse and expand market


  Scenario: Draw No Bet in-play market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type   | selection_Type |
      | \Draw No Bet\ | Home/Away      |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market


  Scenario: OddEven in-play market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type        | selection_Type |
      | \Odd/Even Corners\ | Odd/Even       |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market