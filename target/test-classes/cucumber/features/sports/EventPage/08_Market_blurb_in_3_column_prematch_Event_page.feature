@MarketBlur


Feature: 08 Market blurb in 3 column prematch market Event page
  As a user i want to verify that  market blurb in 3 column pre-match market is properly displayed on following pages
  Event page Homepage highlights Sport page highlights Daily list page Competitions page Coupons page


  Scenario: WinDrawWin market blurb on event page
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    And set a blurb message 'test blurb message' for market
    When the user has navigated to the 'Football' page for created event
    And the user select correct market collection
    Then the correct blurb message displayed