@MarketTemplateInCompetitionsPage

Feature: 01 3 Column prematch market template in Competitions Page

  Scenario: WinDrawWin prematch market in Competitions page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    And the user navigates to 'football' competitions page
    When the user expanded competition for created event
    Then the correct event name is displayed on competition list
    And the correct market name is displayed on competition list
    And the correct selections displayed with correct order
    And the selections labels displayed


#  Scenario: 2nd Half Result pre-match market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type            |   selection_Type    |
#      |\2nd Half Result\      |  Home/Draw/Away     |
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#
#
#  Scenario: Last Team To Score pre-match market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type               |   selection_Type    |
#      |\Last Team To Score\      |  Home/Draw/Away     |
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#
#
#  Scenario: Half Time pre-match market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type      |   selection_Type    |
#      |\Half Time\      |  Home/Draw/Away     |
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#
#
#  Scenario: Double Chance pre-match market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type          |   selection_Type    |
#      |\Double Chance\      |  HomeDraw/DrawAway/HomeAway    |
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#
#
#  Scenario: Goals 3-Band pre-match market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type                               |  selection_Type           |
#      |\Total Goals\ - \Under\/\Exactly\/\Over\  |   Over/Middle/Under       |
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#
#
#  Scenario: Corners 3-Band pre-match market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type      |  selection_Type           |
#      |\Total Corners\  |   Over/Middle/Under       |
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed
#
#
#  Scenario: Bookings 3-Band pre-match market in Event page
#    Given a 'Spanish La Liga Primera' event with following markets
#      | market_type      | selection_Type    |
#      | \Total Bookings\ | Over/Middle/Under |
#    And the user navigates to 'football' competitions page
#    When the user expanded competition for created event
#    Then the correct event name is displayed on competition list
#    And the correct market name is displayed on competition list
#    And the correct selections displayed with correct order
#    And the selections labels displayed
