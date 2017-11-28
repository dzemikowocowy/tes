@Defects

Feature:defects


  Scenario: Correct options are available in Football Daily List bar
    Given the user is on the 'Football' Daily List page
    Then  Correct options are available in Football Daily List bar


  @FURY-1268
  Scenario:Transition to in-play in in-play event page
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type   |BIR   |
      |\Match Betting Live\  |   Home/Draw/Away  | yes  |
      |\90 Minutes\         |   Home/Draw/Away   | yes  |
      |\Draw No Bet\         |   Home/Away       | yes  |
      |\Win/Win\             |   Home/Away       |  no  |
      |\Outright\            |  Home/Draw/Away   |  no  |
    And the user has navigated to the 'Football' page for created event
    And the following markets displayed in correct Market Collections
      |market_type           |  MarketColections  |
      |\Win/Win\             |   Others           |
      |\Outright\            |  Outright          |
    When the event is in-play
    Then the following markets displayed in correct Market Collections
      |market_type           |  MarketColections  |
      |\Match Betting Live\  |   Popular Markets |
      |\90 Minutes\          |   Popular Markets   |
      |\Draw No Bet\         |  Popular Markets   |
    And the market collection 'Outright' is not displayed
    And the market collection 'Others ' is not displayed

