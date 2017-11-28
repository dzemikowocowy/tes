@MarketTemplateEventPage

Feature: 05 3 Column inplay market template in Event page
  AS A WH Customer
  I WANT markets using 3 Column market template in in-play event page
  SO THAT they are correctly displayed

  Scenario: WinDrawWin in-play market in Event page
    Given a 'English League 1' event with following markets
      | market_type          | selection_Type |
      | \Match Betting Live\ | Home/Draw/Away |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market


#  Scenario: 2nd Half Result in-play market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      | market_type       | selection_Type |
#      | \2nd Half Result\ | Home/Draw/Away |
#    And the event and market are in-play
#    When the user has navigated to the 'Football' page for created event
#    And the user select correct market collection
#    Then the correct event name is displayed
#    And the correct market name is displayed
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#    And the user can collapse and expand market
#
#
#  Scenario: Last Team To Score in-play market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      | market_type          | selection_Type |
#      | \Last Team To Score\ | Home/Draw/Away |
#    And the event and market are in-play
#    When the user has navigated to the 'Football' page for created event
#    And the user select correct market collection
#    Then the correct event name is displayed
#    And the correct market name is displayed
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#    And the user can collapse and expand market


  Scenario: Half Time in-play market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type | selection_Type |
      | \Half Time\ | Home/Draw/Away |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market


  Scenario: Double Chance in-play market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type     | selection_Type             |
      | \Double Chance\ | HomeDraw/DrawAway/HomeAway |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market


  Scenario: Goals 3-Band in-play market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type                              | selection_Type    |
      | \Total Goals\ - \Under\/\Exactly\/\Over\ | Over/Middle/Under |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market


  Scenario: Corners 3-Band in-play market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type     | selection_Type    |
      | \Total Corners\ | Over/Middle/Under |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market

#2nd Half Result it is not created by default after DB refresh
  Scenario: Bookings 3-Band in-play market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type      | selection_Type    |
      | \Total Bookings\ | Over/Middle/Under |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market