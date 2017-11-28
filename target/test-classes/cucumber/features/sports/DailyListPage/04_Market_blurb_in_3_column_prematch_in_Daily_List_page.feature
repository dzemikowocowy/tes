@MarketBlurb

Feature: 04 Market blurb in 3 column prematch in Daily List page
  As a user i want to verify that  market blurb in 3 column pre-match market is properly displayed on following pages
  Event page Homepage highlights Sport page highlights Daily list page Competitions page Coupons page

  Scenario: WinDrawWin market blurb on Daily list
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\Match Betting Live\  |   Home/Draw/Away    |
    And set a blurb message 'test blurb message' for market
    When  the user is on the 'Football' Daily List page
    Then the correct event name is displayed on the daily list
    And  the correct blurb message displayed