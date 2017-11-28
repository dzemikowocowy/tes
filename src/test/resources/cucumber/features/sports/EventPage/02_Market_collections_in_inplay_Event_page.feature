@MarketCollections

Feature: 02 Market collections in inplay Event page

  Scenario: No scroller menu when there is only one collection in in-play event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type          | selection_Type |
      | \Match Betting Live\ | Home/Draw/Away |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    Then the collection scroller is not displayed


  Scenario: No scroller menu when all the markets are unassigned to a collection in in-play event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type           | selection_Type     |
      | \Total Bookings Live\ | One/Two/Three/Four |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    Then the collection scroller is not displayed


  Scenario: Scroller menu when there are some collections in in-play event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type           | selection_Type             |
      | \Match Betting Live\  | Home/Draw/Away             |
      | \Draw No Bet Live\    | Home/Away                  |
      | \1st Half Goals Live\ | None/One/Two/Three or more |
      | \2nd Half Goals Live\ | None/One/Two/Three or more |
    And the event and market are in-play
    When the user has navigated to the 'Football' page for created event
    Then the following markets displayed in correct Market Collections
      | market_type           | MarketCollections |
      | \Match Betting Live\  | Popular Markets   |
      | \Draw No Bet Live\    | Popular Markets   |
      | \1st Half Goals Live\ | Half Betting      |
      | \2nd Half Goals Live\ | Half Betting      |


  @manual
  Scenario: Collection disappears in in-play event page when collection is selected
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type           | selection_Type             |
      | \Match Betting Live\  | Home/Draw/Away             |
      | \1st Half Goals Live\ | None/One/Two/Three or more |
      | \Half Corners Live\   | Over/Middle/Under          |
    And the event and market are in-play
    And the user has navigated to the 'Football' page for created event
    And the user selects the market collection 'Half Betting'
    When market '1st Half Goals Live' is settled
    Then market '1st Half Goals Live' is not displayed
    And the message "no markets in this collection available" is displayed
    And the user selects the market collection 'Popular Markets'
    And the market collection 'Half Betting' is not displayed
    And the market collection 'Corners' is displayed


  @manual
  Scenario: Collection disappears in in-play event page when collection is not selected
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type           | selection_Type             |
      | \Match Betting Live\  | Home/Draw/Away             |
      | \1st Half Goals Live\ | None/One/Two/Three or more |
      | \Half Corners Live\   | Over/Middle/Under          |
    And the event and market are in-play
    And the user has navigated to the 'Football' page for created event
    And the user selects the market collection 'Popular Markets'
    When market '1st Half Goals Live' is settled
    Then the market collection 'Half Betting' is not displayed
    And the market collection 'Popular Markets' is displayed
    And the market collection 'Corners' is displayed


  Scenario: New collection appears in in-play event
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type              | selection_Type               |
      | \Match Betting Live\     | Home/Draw/Away               |
      | \Corners Match Bet Live\ | None/1-10/11-20/More than 21 |
    And the event and market are in-play
    And the user has navigated to the 'Football' page for created event
    And the user selects the market collection 'Popular Markets'
    When add new in-play market '|1st Half Goals Live|' with selections type 'None/One/Two/Three or more' to created event
    And the market collection 'Half Betting' is displayed


  Scenario: Scroller appears in in-play event page when a market of a new collection is displayed
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type          | selection_Type |
      | \Match Betting Live\ | Home/Draw/Away |
    And the event and market are in-play
    And the user has navigated to the 'Football' page for created event
    And the collection scroller is not displayed
    When add new in-play market '|1st Half Goals Live|' with selections type 'None/One/Two/Three or more' to created event
    Then the collection scroller is displayed


  Scenario: Scroller appears in in-play event page when a market of an unassigned collection is displayed
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type          | selection_Type |
      | \Match Betting Live\ | Home/Draw/Away |
    And the event and market are in-play
    And the user has navigated to the 'Football' page for created event
    And the collection scroller is not displayed
    When add new in-play market '|Total Bookings Live|' with selections type 'One/Two/Three/Four' to created event
    Then the collection scroller is displayed


  Scenario: Scroller disappears in pre-match event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type               | selection_Type               |
      | \Match Betting Live\      | Home/Draw/Away               |
      | \Corners Match Bet Live\  | None/1-10/11-20/More than 21 |
    And the event and market are in-play
    And the user has navigated to the 'Football' page for created event
    And the user selects the market collection 'Popular Markets'
    When market 'Corners Match Bet Live' is settled
    Then market 'Corners Match Bet Live' is not displayed
    And the collection scroller is not displayed

  @FURY-1268
  Scenario:Transition to in-play in in-play event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type          | selection_Type    | BIR |
      | \90 Minutes\         | Home/Draw/Away    | no  |
      | \1st Half Betting\   | Home/Draw/Away    | no  |
      | \Match Betting Live\ | Home/Draw/Away    | yes |
      | \Half Corners Live\  | Over/Middle/Under | yes |
    And the user has navigated to the 'Football' page for created event
    And the following markets displayed in correct Market Collections
      | market_type        | MarketCollections |
      | \90 Minutes\       | Popular Markets  |
      | \1st Half Betting\ | Half Betting     |
    When the event is in-play
    Then the following markets displayed in correct Market Collections
      | market_type          | MarketCollections |
      | \Match Betting Live\ | Popular Markets   |
      | \Half Corners Live\  | Corners           |
    And the market collection 'Popular Markets' is not displayed
    And the market collection 'Half Betting' is not displayed