@MarketBlurb

Feature: 07 Market blurb in 3 column prematch market in Competitions page
  As a user i want to verify that  market blurb in 3 column pre-match market is properly displayed on following pages
  Event page Homepage highlights Sport page highlights Daily list page Competitions page Coupons page

  Scenario: WinDrawWin market blurb on Competition page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type          | selection_Type |
      | \Match Betting Live\ | Home/Draw/Away |
    And set a blurb message 'test blurb message' for market
    And  the user navigates to 'football' competitions page
    When the user expanded competition for created event
    Then the correct event name is displayed on competition list
    And  the correct blurb message displayed