@ScrollerInRacecard

Feature: Scroller in horse racecard


  Scenario: No scroller menu in horse racing racecard one market
    Given a 'Catterick' race with 4 horses and the following markets:
      |market_group     |SP |LP|
      |\Win\            |Yes|No|
    When the user has navigated to the 'horse-racing' page for created event
    Then scroller menu does not appear on horse racing racecard

  @ScrollerInRacecard1
  Scenario: No scroller menu in horse racing racecard
    Given a 'Catterick' race with 4 horses and the following markets:
      |market_group              |SP    |LP  |each_way	|each_way_places|each_way_places_at|early_prices|Reduction|
      |\Win\                     |  yes | yes |yes	    |3             |1/2                |    yes     |   20    |
      |\Place Only\ - 2 \Places\ |  yes | yes |yes	    |2             |1/1                |    yes     |   10    |
      |\Insure\ - 2 \Places\     |  yes | yes |yes	    |2             |1/4                |    no      |   10    |
    When the user has navigated to the 'horse-racing' page for created event
    Then scroller menu appears on horse racing racecard
    And after click correct content display for following scroller options
    |scroller_options  |
    | Racecard         |
    | Pro Racecard     |
    | Place Markets    |
    | Insurance Markets|
    | Betting Without  |


  Scenario: No scroller menu in horse racing racecard race resulted
    Given a 'Catterick' race with 4 horses and the following markets:
      |market_group              |SP    |LP  |each_way	|each_way_places|each_way_places_at|early_prices|Reduction|
      |\Win\                     |  yes | yes |yes	    |3             |1/2                |    yes     |   20    |
      |\Place Only\ - 2 \Places\ |  yes | yes |yes	    |2             |1/1                |    yes     |   10    |
      |\Insure\ - 2 \Places\     |  yes | yes |yes	    |2             |1/4                |    no      |   10    |
    And the user has navigated to the 'horse-racing' page for created event
    And scroller menu appears on horse racing racecard
    When the all races are resulted
    And the races are settled with forecast and tricast dividends
    Then the 'results' icon is displayed next to the race link
    Then scroller menu does not appear on horse racing racecard

