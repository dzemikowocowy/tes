@MarketTemplateInCompetitionsPage

Feature: 06 Handicap inplay market template in Competition page

  Scenario: Match Handicap inplay market in Competition page 3 columns
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type           | selection_Type | handicap |
      | \Match Handicap Live\ | Home/Away/Line | 5        |
    And the event and market are in-play
    And the user navigates to 'football' competitions page
    When the user expanded competition for created event
    Then the correct event name is displayed on competition list
    And the correct market name is displayed on competition list
    And the correct selections displayed with correct order
    And the selections labels displayed


#  Scenario: Handicap in-play market in Competition page colums
#    Given a 'Euroleague' event with following markets
#      |market_type              |  selection_Type           | handicap  |
#      |\Spread\                 |     Home/Away              |    5      |
#    And the event and market are in-play
#    And the user navigates to 'basketball' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#
#
#  Scenario: Over Under in-play market in Competition page 2 columns handicap Total Runs
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type              |  selection_Type           | handicap  |
#      |\Half Over/Under Goals\  |    High/Low               |    5      |
#   And the event and market are in-play
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed




