@MarketTemplateInCompetitionsPage

Feature: 02 2 Column prematch market template in Competitions page

  Scenario: WinWin prematch market in Competitions page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type   | selection_Type |
      | \Draw No Bet\ | Home/Away      |
    And the user navigates to 'football' competitions page
    When the user expanded competition for created event
    Then the correct event name is displayed on competition list
    And the correct market name is displayed on competition list
    And the correct selections displayed with correct order
    And the selections labels displayed


#  Scenario: Draw no Bet pre-match market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type        |  selection_Type    |
#      |\Draw No Bet\      |  Home/Away         |
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#
#
#  Scenario: First To Score Result pre-match market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type           |  selection_Type    |
#      |\First to score\      |  Home/Away         |
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#
#
#  Scenario: Draw no Bet pre-match market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type        |  selection_Type    |
#      |\Draw No Bet\      |  Home/Away         |
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#
#
#  Scenario: Odd Even pre-match market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type             |  selection_Type  |
#      |\Odd/Even Corners\      |  Odd/Even        |
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed