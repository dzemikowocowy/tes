@marketTemplatehomePage

Feature: Inplay market template in Sport Homepage

  Scenario: WinDrawWin inplay market in Sport Homepage
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type          | selection_Type |
      | \Match Betting Live\ | Home/Draw/Away |
    And the event is in-play in Football page
    And the user navigates to 'football' Sport page
    Then the event is displayed on the football page
    And the correct event name is displayed in in-play section on the football page
    And markets and selections are displayed correctly in in-play section


  Scenario: WinWin inplay market in Sport Homepage
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type        | selection_Type |
      | \Draw No Bet Live\ | Home/Away      |
    And the event is in-play in Football page
    And the user navigates to 'football' Sport page
    Then the event is displayed on the football page
    And the correct event name is displayed in in-play section on the football page
    And markets and selections are displayed correctly in in-play section


  Scenario: Match Handicap in-play market in Sport Homepage
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type           | selection_Type | handicap |
      | \Match Handicap Live\ | Home/Away/Line | 5        |
    And the event is in-play in Football page
    And the user navigates to 'football' Sport page
    Then the event is displayed on the football page
    And the correct event name is displayed in in-play section on the football page
    And markets and selections are displayed correctly in in-play section
    And Handicap value displayed correctly


#  Scenario:  Handicap in-play market in Sport Homepage
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type |  selection_Type           | handicap  |
#      |\Standard handicap\  |    Home/Away/Line         |    5      |
#    And the event is in-play in Football page
#    And the user navigates to 'football' Sport page
#    Then the event is displayed on the football page
#    And the correct event name is displayed in in-play section on the football page
#    And markets and selections are displayed correctly in in-play section
#    And Handicap value displayed correctly