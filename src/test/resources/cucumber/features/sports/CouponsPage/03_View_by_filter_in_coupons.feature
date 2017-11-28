@coupons
Feature: 03 View by filter in coupons
  AS A WH Customer
  I WANT to be able to change the display view of the coupon
  SO THAT I can view the coupon with the events listed by competition or time
  # AJAX-251
  # https://williamhill.jira.com/wiki/pages/viewpage.action?spaceKey=AJAX&title=Coupons


  @manual
  Scenario: Default option in View by filter
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    When the user selects the coupon
    Then the coupon is ordered using the default option


  @manual
  Scenario: View by Competition
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    When the user selects the coupon
    Then the coupon is Ordered by competition


  @manual
  Scenario: View by Time
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    When the user selects the coupon
    Then the coupon is Ordered by time