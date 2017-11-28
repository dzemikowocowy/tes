@ScrollerInRacecard

Feature: Scroller in greyhounds racecard

  Scenario: No scroller menu in greyhounds racing racecard one market
    Given a 'Oxford' race with 6 greyhounds and the following markets:
      |market_group              |SP    |LP  |each_way	|each_way_places|each_way_places_at|early_prices|Reduction|
      |\Win\                     |  yes | yes |yes	    |3             |1/2                |    yes     |   20    |
    When the user has navigated to the 'greyhounds' page for created event
    Then scroller menu does not appear on greyhounds racing racecard


  Scenario: Scroller menu in greyhound racecard
    Given a 'Oxford' race with 6 greyhounds and the following markets:
      |market_group              |SP    |LP  |each_way	|each_way_places|each_way_places_at|early_prices|Reduction|
      |\Win\                     |  yes | yes |yes	    |3             |1/2                |    yes     |   20    |
      |\Win (Trap)\                |  yes | yes |yes	    |2             |1/1                |    yes     |   10    |
    When the user has navigated to the 'greyhounds' page for created event
    Then scroller menu appears on greyhounds racing racecard
    And after click correct content display for following scroller options
    |scroller_options  |
    | Named Dog        |
    | Trap   |


  Scenario: No scroller menu in greyhound racing racecard rece is resulted
    Given a 'Oxford' race with 6 greyhounds and the following markets:
      |market_group              |SP    |LP  |each_way	|each_way_places|each_way_places_at|early_prices|Reduction|
      |\Win\                     |  yes | yes |yes	    |3             |1/2                |    yes     |   20    |
      |\Win (Trap)\              |  yes | yes |yes	    |2             |1/1                |    yes     |   10    |
    When the user has navigated to the 'greyhounds' page for created event
    Then scroller menu appears on horse racing racecard
    When the all races are resulted
    And the races are settled with forecast and tricast dividends
    Then the 'results' icon is displayed next to the race link
    Then scroller menu does not appear on greyhounds racing racecard

