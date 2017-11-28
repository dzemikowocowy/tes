@MarketCollections
Feature: 01 Market collections in prematch Event page




  @dataprep
  Scenario: dataprep
    Given a '|NFL|' event with following markets
      | market_type        | selection_Type |
      | \Money Line\       | Home/Away|
      | \Match Spread\     | Home/Away      |
      | \Total Points\ | High/Low |
   And a 'Euroleague' event with following markets
      | market_type        | selection_Type |
      | \Money Line\       | Home/Away|
      | \Spread\     | Home/Away      |
      | \Total Points\ | High/Low |
    And a '|MLB|' event with following markets
      | market_type        | selection_Type |
      | \Money Line\       | Home/Away|
      | \Run Line\     | Home/Away      |
   And a '|NHL|' event with following markets
      | market_type            | selection_Type |
      | \60 Minutes Betting\   | Home/Draw/Away |
      | \Total Match Goals\     | High/Low     |
      | \Puck Line Handicap\ | Home/Away |
    When the user has navigated to the 'Football' page for created event



  @dataprep1
  Scenario: dataprep
    Given a 'English Premier League' event with following markets
      | market_type        | selection_Type |
      |   \90 Minutes\       | Home/Draw/Away |
      |\Match Result and BTTS\ | Home/Draw/Away |
      | \Both Teams To Score?\ | Home/Draw/Away |
      | \Draw No Bet\      | Home/Away      |
    When the user has navigated to the 'Football' page for created event


  Scenario: No scroller menu when there is only one collection in pre-match event page
    Given a 'English Premier League' event with following markets
      | market_type  | selection_Type |
      | \Both Teams To Score?\ | Home/Draw/Away |
      |   \90 Minutes\       | Home/Draw/Away |
      |\Match Result and BTTS\ | Home/Draw/Away |
    When the user has navigated to the 'Football' page for created event
    Then the collection scroller is not displayed


  Scenario: No scroller menu when all the markets are unassigned to a collection in pre-match event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type     | selection_Type     |
      | \Stats Trebles\ | One/Two/Three/Four |
    When the user has navigated to the 'Football' page for created event
    Then the collection scroller is not displayed


  Scenario: Scroller menu when there are some collections in pre-match event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type        | selection_Type |
      | \90 Minutes\       | Home/Draw/Away |
      | \Draw No Bet\      | Home/Away      |
      | \1st Half Betting\ | Home/Draw/Away |
      | \2nd Half Betting\ | Home/Away      |
    When the user has navigated to the 'Football' page for created event
    Then the following markets displayed in correct Market Collections
      | market_type        | MarketCollections |
      | \90 Minutes\       | Popular Markets   |
      | \Draw No Bet\      | Popular Markets   |
      | \1st Half Betting\ | Half Betting      |
      | \2nd Half Betting\ | Half Betting      |


  @defect
  Scenario: Collection disappears in pre-match event page when collection is selected
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type        | selection_Type     |
      | \90 Minutes\       | Home/Draw/Away     |
      | \1st Half Betting\ | Home/Draw/Away     |
      | \Total Team Cards\ | One/Two/Three/Four |
    And the user has navigated to the 'Football' page for created event
    And the user selects the market collection 'Half Betting'
    When market '1st Half Betting' display flag is set to 'no'
    Then market '1st Half Betting' is not displayed
    And the market collection 'Half Betting' is not displayed
    And the market collection 'Popular Markets' is selected
    And the market collection 'Cards' is displayed


  @defect
  Scenario: Collection disappears in pre-match event page when collection is not selected
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type        | selection_Type     |
      | \90 Minutes\       | Home/Draw/Away     |
      | \1st Half Betting\ | Home/Draw/Away     |
      | \Total Team Cards\ | One/Two/Three/Four |
    And the user has navigated to the 'Football' page for created event
    And the user selects the market collection 'Popular Markets'
    When market '1st Half Betting' display flag is set to 'no'
    When market '1st Half Betting' is not displayed
    Then the market collection 'Half Betting' is not displayed
    And the market collection 'Popular Markets' is selected
    And the market collection 'Cards' is displayed


  Scenario: New collection appears in pre-match event
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type        | selection_Type     |
      | \90 Minutes\       | Home/Draw/Away     |
      | \1st Half Betting\ | Home/Draw/Away     |
      | \Total Team Cards\ | One/Two/Three/Four |
    When market '1st Half Betting' display flag is set to 'no'
    And the user has navigated to the 'Football' page for created event
    Then the market collection 'Half Betting' is not displayed
    And the user selects the market collection 'Popular Markets'
    When market '1st Half Betting' display flag is set to 'yes'
    And the market collection 'Half Betting' is displayed


  @defect
  Scenario: Scroller appears in pre-match event page when a market of a new collection is displayed
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type        | selection_Type     |
      | \90 Minutes\       | Home/Draw/Away     |
      | \1st Half Betting\ | Home/Draw/Away     |
    When market '1st Half Betting' display flag is set to 'no'
    And the user has navigated to the 'Football' page for created event
    And the collection scroller is not displayed
    When market '1st Half Betting' display flag is set to 'yes'
    Then the collection scroller is displayed


  @defect
  Scenario: Scroller appears in pre-match event page when a market of an unassigned collection is displayed
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type     | selection_Type     |
      | \90 Minutes\    | Home/Draw/Away     |
      | \Stats Trebles\ | One/Two/Three/Four |
    And market 'Stats Trebles' display flag is set to 'no'
    And the user has navigated to the 'Football' page for created event
    And the collection scroller is not displayed
    And market 'Stats Trebles' display flag is set to 'yes'
    Then the collection scroller is displayed


  @defect
  Scenario: Scroller disappears in pre-match event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type        | selection_Type |
      | \90 Minutes\       | Home/Draw/Away |
      | \1st Half Betting\ | Home/Draw/Away |
    And the user has navigated to the 'Football' page for created event
    And the user selects the market collection 'Popular Markets'
    When market '1st Half Betting' display flag is set to 'no'
    Then market '1st Half Betting' is not displayed
    And the collection scroller is not displayed