@Push

Feature: Push in InPlay page

  Scenario: Checking push by a change of price in play
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type          | selection_Type |
      | \Match Betting Live\ | Home/Draw/Away |
    And the event and market are in-play
    And the user has navigated to the InPlay page
    And the user selects 'football' button from the In-Play Betting menu
    And the first selection price 'increase'
    Then the selection price increased