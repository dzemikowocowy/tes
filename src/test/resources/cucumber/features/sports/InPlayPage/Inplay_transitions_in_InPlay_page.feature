@inPlay

Feature: Inplay transitions in InPlay page



    Scenario:1a Display OFF one event  - propagation of the transitions
    Given a 'English League 1' event with following markets
      |market_type|  selection_Type |
      |\Draw No Bet\  |   Home/Away'    |
      |\90 Minutes\      |  Home/Draw/Away     |
      |\Match Betting Live\  |   Home/Draw/Away   |
#    Given a 'English League 1' event with following markets
#      |market_type            |  selection_Type |
#      |\Match Betting Live\  |   Home/Draw/Away   |
#      |\Draw No Bet\  |   Home/Away   |
#      |\Match Betting Live\  |   Home/Draw/Away   |
#      |\Match Betting\ | Home/Away      |
#    And  the event and market are in-play
    And the user has navigated to the InPlay page
#    And the user selects 'football' button from the In-Play Betting menu
#    When the first event display flag is set to 'no'
#    Then the first event is not longer visible


  Scenario:1c Display YES all events for one competition- propagation of the transitions
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type|  selection_Type |
      |\Draw No Bet\  | Home/Away    |
    And a 'English League 1' event with following markets
      |market_type            |  selection_Type |
      |\Match Betting Live\  |   Home/Draw/Away   |
    And  the event and market are in-play
    And the first event display flag is set to 'no'
    And the user has navigated to the InPlay page
    And the user selects 'football' button from the In-Play Betting menu
    When the first event display flag is set to 'yes'
    Then the first event is displayed on the page