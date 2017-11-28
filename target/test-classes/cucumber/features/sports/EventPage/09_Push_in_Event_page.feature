@Push

Feature: 09 Push in Event page
@defect
  Scenario: Market updates in 3 column market
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type          | selection_Type | BIR |
      | \90 Minutes\         | Home/Draw/Away | no  |
      | \Draw No Bet\        | Home/Away      | no  |
      | \Match Betting Live\ | Home/Draw/Away | yes |
    When the user has navigated to the 'Football' page for created event
    And market '90 Minutes' is displayed
    And market 'Match Betting Live' is not displayed
    Then the correct event name is displayed
    And the correct market name is displayed
    And the first selection price 'increase'
    And selection 'increase' animation displayed
    And the market is suspended
    And the market is disabled
    Then the selection is inactive
    And the market is active
    And the selection is active
    And market '90 Minutes' display flag is set to 'no'
    And market '90 Minutes' is not displayed
    And market '90 Minutes' display flag is set to 'yes'
    And market '90 Minutes' is displayed
    When the event is in-play
    And market 'Match Betting Live' is displayed
    And market '90 Minutes' is not displayed
    And market 'Match Betting Live' is settled
    And market 'Match Betting Live' is not displayed

@defect
  Scenario: Market updates in 2 column market
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type        | selection_Type | BIR |
      | \90 Minutes\       | Home/Draw/Away | no  |
      | \Draw No Bet\      | Home/Away      | no  |
      | \Draw No Bet Live\ | Home/Away      | yes |
    When the user has navigated to the 'Football' page for created event
    And market 'Draw No Bet' is displayed
    And market 'Money Line Live' is not displayed
    Then the correct event name is displayed
    And the correct market name is displayed
    And the first selection price 'increase'
    And selection 'increase' animation displayed
    And the market is suspended
    And the market is disabled
    Then the selection is inactive
    And the market is active
    And the selection is active
    And market 'Draw No Bet' display flag is set to 'no'
    And market 'Draw No Bet' is not displayed
    And market 'Draw No Bet' display flag is set to 'yes'
    And market 'Draw No Bet' is displayed
    When the event is in-play
    And market 'Draw No Bet Live' is displayed
    And market 'Draw No Bet' is not displayed
    And market 'Draw No Bet Live' is settled
    And market 'Draw No Bet Live' is not displayed

@defect
  Scenario: Market updates in 3 column handicap market
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type           | selection_Type | handicap | BIR |
      | \90 Minutes\          | Home/Draw/Away |          | no  |
      | \Handicap\            | Home/Away/Line | -5       | no  |
      | \Match Handicap Live\ | Home/Away/Line | 5        | yes |
    When the user has navigated to the 'Football' page for created event
    Then the correct event name is displayed
    And market 'Handicap' is displayed
    And market 'Match Handicap Live' is not displayed
    And handicap value displayed correctly for 'Handicap' market
    And the first selection price 'increase'
    And selection 'increase' animation displayed
    And the market is suspended
    And the market is disabled
    Then the selection is inactive
    And the market is active
    And the selection is active
    And the user updated handicap value to '4' for 'Handicap' market
    And handicap value displayed correctly for 'Handicap' market
    And market 'Handicap' display flag is set to 'no'
    And market 'Handicap' is not displayed
    And market 'Handicap' display flag is set to 'yes'
    And market 'Handicap' is displayed
    When the event is in-play
    And market 'Match Handicap Live' is displayed
    And market 'Handicap' is not displayed
    And market 'Match Handicap Live' is settled
    And market 'Match Handicap Live' is not displayed


#  Scenario: Market updates in 2 column handicap market
#    Given a 'Spanish La Liga Primera' event with following markets
#      | market_type          | selection_Type    | handicap | BIR |
#      | \Team Over/Under Goals\ | Over/Under      | 5        | no |
#      | \Team Over/Under Goals Live\           | Over/Under    | -5       | yes  |
#    When the user has navigated to the 'Football' page for created event
#    Then the correct event name is displayed
#    And market 'Team Over/Under Goals' is displayed
#    And market 'Team Over/Under Goals Live' is not displayed
#    And handicap value displayed correctly for 'Team Over/Under Goals' market
#    And the first selection price 'increase'
#    And selection 'increase' animation displayed
#    And the market is suspended
#    And the market is disabled
#    Then the selection is inactive
#    And the market is active
#    And the selection is active
#    And the user updated handicap value to '4' for 'Handicap' market
#    And handicap value displayed correctly for 'Handicap' market
#    And market 'Team Over/Under Goals' display flag is set to 'no'
#    And market 'Team Over/Under Goals' is not displayed
#    And market 'Team Over/Under Goals' display flag is set to 'yes'
#    And market 'Team Over/Under Goals' is displayed
#    When the event is in-play
#    And market 'Team Over/Under Goals Live' is displayed
#    And market 'Team Over/Under Goals' is not displayed
#    And market 'Team Over/Under Goals Live' is settled
#    And market 'Team Over/Under Goals Live' is not displayed