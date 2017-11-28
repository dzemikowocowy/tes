@coupons
Feature: 04 Inplay transition in coupons
  AS A WH Customer
  I WANT ensure that In-Play transition is done in a coupon
  SO THAT in-play events are not displayed in coupons
  # AJAX-454
  # https://williamhill.jira.com/wiki/pages/viewpage.action?spaceKey=AJAX&title=Coupons

  @manual
  Scenario: Event disappears from a coupon when it is in-play
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Draw/Away |
    And a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Draw/Away |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    And the user selects the coupon
    When the event off flag set to yes
    Then the first event is not longer visible
    And the second event is still visible under the coupon


  @manual
  Scenario: Coupon disappears when last event is in-play
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Draw/Away |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    And the user selects the coupon
    When the event off flag set to yes
    Then the coupon is no longer displayed on the list