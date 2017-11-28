@marketTemplateDailyList

Feature: 03 Inplay market template in Daily List page

  Scenario: WinDrawWin inplay market in Daily List
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type          | selection_Type |
      | \Match Betting Live\ | Home/Draw/Away |
    And the event and market are in-play
    And the user navigates to 'football' daily list page
    Then the correct event name is displayed on the daily list
    And the correct market name is displayed on daily list
    And the correct selections displayed with correct order on daily list

  Scenario: WinWin inplay market in Daily List
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type        | selection_Type |
      | \Draw No Bet Live\ | Home/Away      |
    And the event and market are in-play
    And the user navigates to 'football' daily list page
    Then the correct event name is displayed on the daily list
    And the correct market name is displayed on daily list
    And the correct selections displayed with correct order on daily list


  Scenario: Match Handicap inplay market in Daily List
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type           | selection_Type | handicap |
      | \Match Handicap Live\ | Home/Away/Line | 5        |
    And the event and market are in-play
    And the user navigates to 'football' daily list page
    Then the correct event name is displayed on the daily list
    And the correct market name is displayed on daily list
    And the correct selections displayed with correct order on daily list


#  Scenario:  Handicap inplay market in Daily List
#    Given a 'Spanish La Liga Primera' event with following markets
#      | market_type         | selection_Type | handicap |
#      | \Standard handicap\ | Home/Away      | 5        |
#    And the event and market are in-play
#    And the user navigates to 'football' daily list page
#    Then the correct event name is displayed on the daily list
#    And the correct market name is displayed on daily list
#    And the correct selections displayed with correct order on daily list