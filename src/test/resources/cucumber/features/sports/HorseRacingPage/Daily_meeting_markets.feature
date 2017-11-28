@Daily_Meeting_Markets_Scroller

Feature: Scroller in Daily Meeting


  Scenario: Daily  Meeting Markets Scroller
    Given Data Meeting Markets
    When the user has navigated to the 'horse-racing' created meeting market event
    Then scroller menu appears on horse racing meeting market event
    And after click correct content display for following meeting market scroller options
      |scroller_options   |
      | All Markets       |
      | Jockey Markets    |
      | Trainer Markets   |
      | Horse Markets     |
      | Forecast Markets  |
      | Others            |