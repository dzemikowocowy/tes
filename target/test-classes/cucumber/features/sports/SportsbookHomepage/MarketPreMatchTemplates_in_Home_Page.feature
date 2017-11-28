@marketTemplatehomePage

Feature:  Prematch market template in Homepage

  Scenario: WinDrawWin prematch market in  Homepage
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    And event is enabled in Squiz for Highlights and sport 'Football'
    And the user navigates to WilliamHill homepage
    Then the event is displayed on the homepage
    And the correct event name is displayed in highlights section
    And markets and selections are displayed correctly in highlights section

  Scenario: WinWin prematch market in  Homepage
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type   | selection_Type |
      | \Draw No Bet\ | Home/Away      |
    And event is enabled in Squiz for Highlights and sport 'Football'
    And the user navigates to WilliamHill homepage
    Then the event is displayed on the homepage
    And the correct event name is displayed in highlights section
    And markets and selections are displayed correctly in highlights section

  Scenario: Match Handicap prematch market in  Homepage
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type | selection_Type | handicap |
      | \Handicap\  | Home/Away/Line | -5       |
    And event is enabled in Squiz for Highlights and sport 'Football'
    And the user navigates to WilliamHill homepage
    Then the event is displayed on the homepage
    And the correct event name is displayed in highlights section
    And markets and selections are displayed correctly in highlights section

#  Scenario: Handicap pre-match market in  Homepage
#    Given a 'Spanish La Liga Primera' event with following markets
#      |market_type          |  selection_Type           | handicap  |
#      |\Standard handicap\  |    Home/Away/Line         |    5      |
#    And event is enabled in Squiz for Highlights and sport 'Football'
#    And the user navigates to WilliamHill homepage
#    Then the event is displayed on the homepage
#    And the correct event name is displayed in highlights section
#    And markets and selections are displayed correctly in highlights section
#    And Handicap value displayed correctly