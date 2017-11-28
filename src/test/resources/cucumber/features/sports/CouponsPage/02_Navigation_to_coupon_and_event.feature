@coupons
Feature: 02 Navigation to coupon and event
  AS A WH Customer
  I WANT to navigate to an event in a coupon from the sport page
  SO THAT I can see all other markets in the event
  # AJAX-239
  # https://williamhill.jira.com/wiki/pages/viewpage.action?spaceKey=AJAX&title=Coupons


  @manual
  Scenario: Navigation to a coupon from Sport highlights page
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    When the user navigates to 'cricket' Sport page
    Then the coupons section is available in Sport page
    And the coupon is displayed on the list
    And the user selects the coupon


  @manual
  Scenario: Navigation to a coupon from Coupons page
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to 'cricket' Sport page
    When the user navigates to the coupons page
    Then the coupon is displayed on the list
    And the user selects the coupon


  @manual
  Scenario: Navigation to event page from a coupon
    Given a 'Indian Premier League' event with following markets
      | market_type     | selection_Type |
      | \Match Betting\ | Home/Away      |
    And add new coupon in cricket with title Automation Test Coupon
    And set up event market for coupon in cricket
    And the user navigates to 'cricket' Sport page
    And the user navigates to the coupons page
    And the coupon is displayed on the list
    And the user selects the coupon
    When the customer click on the event link
    Then the user is on the selected event page