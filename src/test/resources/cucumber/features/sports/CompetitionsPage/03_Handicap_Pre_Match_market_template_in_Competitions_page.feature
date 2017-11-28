@MarketTemplateInCompetitionsPage

Feature: 03 Match Handicap prematch market template in Competitions page

  Scenario: Match Handicap prematch market in Competitions page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type | selection_Type | handicap |
      | \Handicap\  | Home/Away/Line | -5       |
    And the user navigates to 'football' competitions page
    When the user expanded competition for created event
    Then the correct event name is displayed on competition list
    And the correct market name is displayed on competition list
    And the correct selections displayed with correct order
    And the selections labels displayed


#  Scenario: Handicap pre-match market in Competition page 2 columns
#    Given a 'Euroleague' event with following markets
#      |market_type              |  selection_Type           | handicap  |
#      |\Spread\                 |    High/Low               |   -5      |
#    And the user navigates to 'basketball' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed



# Scenario: Over Under pre-match market in Competition page 2 columns handicap
#   Given a 'Spanish La Liga Primera' event with following markets
#     |market_type              |  selection_Type           | handicap  |
#     |\Half Over/Under Goals\  |    High/Low               |    5      |
#   And the user navigates to 'football' competitions page
#   When the user expanded competition for created event
#   Then the correct event name is displayed on competition list
#   And the correct market name is displayed on competition list
#   And the correct selections displayed with correct order
#   And the selections labels displayed






