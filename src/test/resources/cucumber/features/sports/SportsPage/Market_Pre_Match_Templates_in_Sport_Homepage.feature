@marketTemplatehomePage

Feature: Prematch market template in Sport Homepage

  Scenario: WinDrawWin prematch market in Sport Homepage
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    And event is enabled in Squiz for Highlights and sport 'Football'
    And the user navigates to 'football' Sport page
    Then the event is displayed on the football page
    And the correct event name is displayed in highlights section on the football page
    And markets and selections are displayed correctly in highlights section


  Scenario: WinWin prematch market in Sport Homepage
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type   | selection_Type |
      | \Draw No Bet\ | Home/Away      |
    And event is enabled in Squiz for Highlights and sport 'Football'
    And the user navigates to 'football' Sport page
    Then the event is displayed on the football page
    And the correct event name is displayed in highlights section on the football page
    And markets and selections are displayed correctly in highlights section


  Scenario: Match Handicap pre-match market in Sport Homepage
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type | selection_Type | handicap |
      | \Handicap\  | Home/Away/Line | -5       |
    And event is enabled in Squiz for Highlights and sport 'Football'
    And the user navigates to 'football' Sport page
    Then the event is displayed on the football page
    And the correct event name is displayed in highlights section on the football page
    And markets and selections are displayed correctly in highlights section

#  Scenario: Handicap pre-match market in Sport Homepage
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type          |  selection_Type           | handicap  |
#      |\Standard handicap\  |    Home/Away/Line         |    5      |
#    And event is enabled in Squiz for Highlights and sport 'Football'
#    And the user navigates to 'football' Sport page
#    Then the event is displayed on the football page
#    And the correct event name is displayed in highlights section on the football page
#    And markets and selections are displayed correctly in highlights section
#    And Handicap value displayed correctly