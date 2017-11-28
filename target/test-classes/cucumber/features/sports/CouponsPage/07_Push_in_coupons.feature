@coupons
Feature: 07 Push in coupons
  AS A Customer
  I WANT to ensure that changes are pushed in the events in coupons
  SO THAT I can see updated info in the coupons
  # AJAX-639
  # https://williamhill.jira.com/wiki/pages/viewpage.action?spaceKey=AJAX&title=Coupons

  @manual
  Scenario: Price change in a coupon
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    When the user selects the coupon
    Then the selection price changed


  @manual
  Scenario: Market suspended in a coupon
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    When the user selects the coupon
    Then the market is suspended


  @manual
  Scenario: Handicap change in a coupon
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    When the user selects the coupon
    Then the value of the handicap selection is increased


  @manual
  Scenario: New event in a coupon
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    And the user selects the coupon
    When a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    Then add the event in the coupon