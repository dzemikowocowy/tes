@Animation

Feature: Animations in odd buttons

  Scenario: Price suspended animation in event level
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    When the user has navigated to the 'Football' page for created event
    And the event is suspended
    And the event is disabled
    Then the selection is inactive


  Scenario: Price suspended animation in market level
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    When the user has navigated to the 'Football' page for created event
    And the market is suspended
    And the market is disabled
    Then the selection is inactive


  Scenario: Price suspended animation in selection level
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    When the user has navigated to the 'Football' page for created event
    And the first selection is suspended
    Then the first Selection is inactive


  Scenario: Add to betslip animation
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    When the user has navigated to the 'Football' page for created event
    And click on the first selection to the betslip
    Then the selection is active
    And the selection button colour changes to blue


  Scenario: Remove from betslip animation
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    And click on the first selection to the betslip
    Then the selection is active
    And the selection button colour changes to blue
    And remove all selections from the betslip
    And the selection is active
    And the selection button colour changes to gray


  Scenario: Price increase animation
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    And the first selection price 'increase'
    Then selection 'increase' animation displayed


  Scenario:Price decrease animation
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    And the first selection price 'decrease'
    Then selection 'decrease' animation displayed


  Scenario:Price change animation when market is suspended
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    And the market is suspended
    And the first selection price 'decrease'
    Then selection 'decrease' animation displayed


  Scenario:Price change animation when selection is added to betslip
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    And click on the first selection to the betslip
    And the first selection price 'increase'
    Then selection 'increase' animation displayed


  Scenario:Price suspended animation when selection is added to betslip
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    And click on the first selection to the betslip
    And the first selection is suspended
    Then the first Selection added to betslip is inactive

@manual
  Scenario: Football Goal Score animation
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    And scoreboards displayed
    Then update goal score
    And Goal animation displayed

@manual
  Scenario: Goal animation when selection is added to betslip
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    And scoreboards displayed
    And the market is suspended
    Then update goal score
    And Goal animation displayed

@manual
  Scenario: Goal animation when selection is added to betslip
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    And scoreboards displayed
    And click on the first selection to the betslip
    Then update goal score
    And Goal animation displayed