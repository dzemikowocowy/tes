@MarketTemplateInCompetitionsPage

Feature: 05 2 Column inplay market template in Competitions Page

  Scenario:WinWin inplay market in Competitions page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type        | selection_Type |
      | \Draw No Bet Live\ | Home/Away      |
    And the event and market are in-play
    And the user navigates to 'football' competitions page
    When the user expanded competition for created event
    Then the correct event name is displayed on competition list
    And the correct market name is displayed on competition list
    And the correct selections displayed with correct order
    And the selections labels displayed


#  Scenario: First To Score in-play market in Competitions page
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type       |  selection_Type |
#      |\First to score\  |   Home/Away     |
#    And the event and market are in-play
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#
#
#  Scenario: Draw No Bet in-play market in Competitions page
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type    |  selection_Type |
#      |\Draw No Bet\  |   Home/Away     |
#    And the event and market are in-play
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#
#
#  Scenario: Odd Even in-play market in Competitions page
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type    |  selection_Type |
#      |\Odd/Even Corners\  |   Odd/Even    |
#    And the event and market are in-play
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed