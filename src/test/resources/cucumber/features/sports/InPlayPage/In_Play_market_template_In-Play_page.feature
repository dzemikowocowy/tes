@marketTemplateInPlayPage

Feature: Market templates in InPlay page

  Scenario: WinDrawWin in-play market in In-Play page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type          | selection_Type |
      | \Match Betting Live\ | Home/Draw/Away |
    And the event and market are in-play
    And the user has navigated to the InPlay page
    When the user selects 'football' button from the In-Play Betting menu
    Then the correct event name is displayed on in play list
    And the correct market name is displayed on In Play list
    And the correct selections displayed with correct order
    And the selections labels displayed


  Scenario: WinWin in-play market in In-Play page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type        | selection_Type |
      | \Draw No Bet Live\ | Home/Away      |
    And the event and market are in-play
    And the user has navigated to the InPlay page
    When the user selects 'football' button from the In-Play Betting menu
    Then the correct event name is displayed on in play list
    And the correct market name is displayed on In Play list
    And the correct selections displayed with correct order
    And the selections labels displayed


  Scenario: Match Handicap in-play market in In-Play
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type |  selection_Type           | handicap  |
      |\Handicap\  |    Home/Away/Line         |    -5      |
    And the event and market are in-play
    And the user has navigated to the InPlay page
    And the user selects 'football' button from the In-Play Betting menu
    Then the correct event name is displayed on in play list
    And the correct market name is displayed on In Play list
    And the correct selections displayed with correct order
    And the selections labels displayed


  Scenario: Handicap in-play market in In-Play
    Given a 'Euroleague' event with following markets
      |market_type |  selection_Type           | handicap  |
      |\Spread\  |   Home/Away        |    5      |
    And the event and market are in-play
    And the user has navigated to the InPlay page
    When the user selects 'basketball' button from the In-Play Betting menu
    Then the correct event name is displayed on in play list
    And the correct market name is displayed on In Play list
    And the correct selections displayed with correct order
    And the selections labels displayed