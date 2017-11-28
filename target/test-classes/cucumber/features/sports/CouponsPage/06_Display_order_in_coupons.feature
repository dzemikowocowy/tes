@coupons
Feature: 06 Display order in coupons
  AS A Customer
  I WANT to have the coupons ordered by display order value
  SO THAT the coupons are displayed in the list in correct order


  @manual
  Scenario: First coupon in the list
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    When the user navigates to the coupons page
    Then change the coupon to be displayed first in the list


  @manual
  Scenario: Last coupon in the list
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    When the user navigates to the coupons page
    Then change the coupon to be displayed last in the list


  @manual
  Scenario: Change order in coupons list
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Draw/Away |
    And add new coupon in cricket with order 1 and title First Automation Test Coupon
    And add new coupon in cricket with order 2 and title Second Automation Test Coupon
    And add new coupon in cricket with order 3 and title Third Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    When the coupons are displayed on the list with correct order
    Then change the order in the coupons
    And the coupons are displayed on the list with correct order