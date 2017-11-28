@MarketTemplateEventPage

Feature: 03 3 Column prematch market template in Event page
  AS A WH Customer
  I WANT markets using 3 Column market template in pre-match event page
  SO THAT they are correctly displayed

  Scenario: WinDrawWin prematch market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market

##2nd Half Result it is not created by default after DB refresh
#  Scenario: 2nd Half Result prematch market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      | market_type       | selection_Type |
#      | \2nd Half Result\ | Home/Draw/Away |
#    When the user has navigated to the 'Football' page for created event
#    And the user select correct market collection
#    Then the correct event name is displayed
#    And the correct market name is displayed
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#    And the user can collapse and expand market
#
##Last Team To Score it is not created by default after DB refresh
#  Scenario: Last Team To Score prematch market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      | market_type          | selection_Type |
#      | \Last Team To Score\ | Home/Draw/Away |
#    When the user has navigated to the 'Football' page for created event
#    And the user select correct market collection
#    Then the correct event name is displayed
#    And the correct market name is displayed
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#    And the user can collapse and expand market


  Scenario: Half Time prematch market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type | selection_Type |
      | \Half Time\ | Home/Draw/Away |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market


  Scenario: Double Chance prematch market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type     | selection_Type             |
      | \Double Chance\ | HomeDraw/DrawAway/HomeAway |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market


  Scenario: Goals 3Band prematch market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type                              | selection_Type    |
      | \Total Goals\ - \Under\/\Exactly\/\Over\ | Over/Middle/Under |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market


  Scenario: Corners 3Band prematch market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type     | selection_Type    |
      | \Total Corners\ | Over/Middle/Under |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market


  Scenario: Bookings 3Band prematch market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type      | selection_Type    |
      | \Total Bookings\ | Over/Middle/Under |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market