@marketTemplateDailyList

Feature: 02 Prematch market template in Daily List page

  Scenario: WinDrawWin pre-match market in Daily List
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    And the user navigates to 'football' daily list page
    Then the correct event name is displayed on the daily list
    And the correct market name is displayed on daily list
    And the correct selections displayed with correct order on daily list

  Scenario: WinWin prematch market in Daily List page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type   | selection_Type |
      | \Draw No Bet\ | Home/Away      |
    And the user navigates to 'football' daily list page
    Then the correct event name is displayed on the daily list
    And the correct market name is displayed on daily list
    And the correct selections displayed with correct order on daily list

  Scenario: Match Handicap prematch market in Daily List page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type | selection_Type | handicap |
      | \Handicap\  | Home/Away/Line | 5        |
    And the user navigates to 'football' daily list page
    Then the correct event name is displayed on the daily list
    And the correct market name is displayed on daily list
    And the correct selections displayed with correct order on daily list


#  Scenario: Handicap pre-match market in Daily List page
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type |  selection_Type           | handicap  |
#      |\Standard handicap\  |    Home/Away        |    5      |
#    And the user is on the 'Football' Daily List page
#    Then the correct event name is displayed on the daily list
#    And the correct market name is displayed on daily list
#    And the correct selections displayed with correct order on daily list