@MarketTemplateEventPage

Feature: 04 2 Column prematch market template in Event page
  AS A WH Customer
  I WANT markets using 2 Column market template in pre-match event page
  SO THAT they are correctly displayed

  Scenario: WinWin pre-match market in Event page
    Given a '|NHL|' event with following markets
      | market_type  | selection_Type |
      | \Money Line\ | Home/Away      |
    When the user has navigated to the 'ice-hockey' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market

##First Team To Score it is not created by default after DB refresh
#  Scenario: First To Score pre-match market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      | market_type      | selection_Type |
#      | \First to score\ | Home/Away      |
#    When the user has navigated to the 'Football' page for created event
#    And the user select correct market collection
#    Then the correct event name is displayed
#    And the correct market name is displayed
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#    And the user can collapse and expand market


  Scenario: Draw No Bet pre-match market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type   | selection_Type |
      | \Draw No Bet\ | Home/Away      |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market


  Scenario: OddEven pre-match market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type        | selection_Type |
      | \Odd/Even Corners\ | Odd/Even       |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market

