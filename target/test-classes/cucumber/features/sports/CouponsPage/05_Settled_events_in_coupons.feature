@coupons
Feature: 05 Settled events in coupons
  AS A WH Customer
  I WANT to ensure that events are correctly settled
  SO THAT settled events are not displayed in coupons
  # AJAX-646
  # https://williamhill.jira.com/wiki/pages/viewpage.action?spaceKey=AJAX&title=Coupons


  @manual
  Scenario: Event disappears from coupon when it is settled
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
    When the first event is settled
    Then the first event is not longer visible
    And the second event is still visible under the coupon


  @manual
  Scenario: Coupon disappears when last event is settled
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Draw/Away |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    And the user selects the coupon
    When the first event is settled
    Then the coupon is no longer displayed on the list


  @manual
  Scenario: Event disappears from coupon when it is not displayed
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
    When the first event display flag is set to no
    Then the first event is not longer visible
    And the second event is still visible under the coupon


  @manual
  Scenario: Coupon disappears when last event is not displayed
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Draw/Away |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    And the user selects the coupon
    When the first event display flag is set to no
    Then the coupon is no longer displayed on the list


  @manual
  Scenario: Market group disappears when last event is not displayed
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Draw/Away |
    And a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Most Runs Out\ | Home/Tie/Away  |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    And the user selects the coupon
    When the first event display flag is set to no
    Then the first event is not longer visible
    And the market group Most Runs Out is not longer displayed on the coupon
    And the second event is still visible under the coupon


  @manual
  Scenario: Competition disappears when last event is not displayed
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Draw/Away |
    And a 'Bangladesh Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Tie/Away  |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to cricket Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    And the user selects the coupon
    When the first event display flag is set to no
    Then the first event is not longer visible
    And the competition Indian Premier League is not longer displayed on the coupon
    And the second event is still visible under the coupon