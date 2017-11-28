@summaryScores

Feature: Football summary scores



  Scenario: Football match score is correct
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type                   |  selection_Type     |
      |\Match Betting Live\          |    Home/Draw/Away   |
    And the event and market are in-play
    And the user has navigated to the football InPlay page
    And click on the first selection to the betslip
    When update goal score
    Then the score should be updated
    And the user has navigated to the InPlay page
    And the user selects 'football' button from the In-Play Betting menu
    And click on the first selection to the betslip
    And the score should be updated
    And the user is on the 'Football' Daily List page
  And the score should be updated on Daily list


  Scenario:Football starting time is correct
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type                   |  selection_Type     |
      |\Match Betting Live\          |    Home/Draw/Away   |
    And the event and market are in-play
    And the user has navigated to the football InPlay page
    And click on the first selection to the betslip
    When update goal score
    Then the starting Time is correct
    And the user has navigated to the InPlay page
    And the user selects 'football' button from the In-Play Betting menu
    And the starting Time is correct
    And the user is on the 'Football' Daily List page
#    And the starting Time is correcton Daily list