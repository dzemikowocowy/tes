@MarketBlurb

Feature: Market blurb in 3 column prematch in Homepage
  As a user i want to verify that  market blurb in 3 column pre-match market is properly displayed on following pages
  Event page Homepage highlights Sport page highlights Daily list page Competitions page Coupons page

  Scenario: WinDrawWin market blurb homepage highlights
    Given a 'Spanish La Liga Primera' event with following markets
      | market_type  | selection_Type |
      | \90 Minutes\ | Home/Draw/Away |
    And set a blurb message 'test blurb message' for market
    And event is enabled in Squiz for Highlights and sport 'Football'
    And the user navigates to WilliamHill homepage
    When the event is displayed on the homepage
    Then On Home Page the correct blurb message displayed