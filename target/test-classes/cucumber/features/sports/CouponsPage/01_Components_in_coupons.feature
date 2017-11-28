@coupons
Feature: 01 Components in coupons
  AS A WH Customer
  I WANT to check that coupons page is available, and it contains expected components
  SO THAT I can place bets on it

  @manual
  Scenario: Correct components in Coupons page
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    When the user navigates to the coupons page
    Then the coupon is displayed on the list
    And correct components are displayed in the coupons page


  @manual
  Scenario: Correct components in a coupon
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    When the user selects the coupon
    Then correct components are displayed in the coupon


  @manual
  Scenario: Event details in a coupon
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    When the user selects the coupon
    Then correct event details in the coupon


  @manual
  Scenario: Bet placement in a coupon
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    When the user selects the coupon
    Then the user places a bet