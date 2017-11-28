@MarketTemplateEventPage

Feature: 07 Handicap market template in Event page prematch and inplay
  AS A WH Customer
  I WANT markets using handicap market template in event page
  SO THAT they are correctly displayed


  Scenario: Match Handicap pre-match market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type | selection_Type | handicap |
      | \Handicap\  | Home/Away/Line | -5       |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And Handicap value displayed correctly
    And the user can collapse and expand market


  Scenario: Match Handicap in-play market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type | selection_Type | handicap |
      | \Handicap\  | Home/Away/Line | 5        |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And Handicap value displayed correctly
    And the user can collapse and expand market


  Scenario: Handicap pre-match market in Event page
    Given a 'Euroleague' event with following markets
    | market_type | selection_Type | handicap |
    | \Spread\    | Home/Away      | -5       |
    When the user has navigated to the 'basketball' page for created event
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market


  Scenario: Handicap in-play market in Event page
    Given a 'Euroleague' event with following markets
      | market_type | selection_Type | handicap |
      | \Spread\    | Home/Away      | 5        |
    And the event and market are in-play
    When the user has navigated to the 'basketball' page for created event
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And Handicap value displayed correctly
    And the user can collapse and expand market


  Scenario: OverUnder pre-match market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type             | selection_Type | handicap |
      | \Half Over/Under Goals\ | High/Low       | 5        |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And Handicap value displayed correctly
    And the user can collapse and expand market


  Scenario: OverUnder in-play market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type             | selection_Type | handicap |
      | \Half Over/Under Goals\ | High/Low       | 5        |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And Handicap value displayed correctly
    And the user can collapse and expand market

