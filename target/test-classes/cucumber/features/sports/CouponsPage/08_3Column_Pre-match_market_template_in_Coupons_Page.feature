@coupons
Feature: 08 3 Column Pre-match market template in Coupons Page

  @manual
  Scenario: WIN DRAW WIN 90 Minutes in European Major Leagues
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type       |   selection_Type    |
      |\90 Minutes\      |  Home/Draw/Away     |
    And add new coupon with title 'Automation Test Coupon'
    And set up event market for coupon
    When the user has navigated to the 'Football' coupon page for created event
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed
    And the user can collapse and expand market


  @manual
  Scenario: 2nd Half Result pre-match market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type            |   selection_Type    |
      |\2nd Half Result\      |  Home/Draw/Away     |
    And add new coupon with title 'Automation Test Coupon'
    And set up event market for coupon
    When the user has navigated to the 'Football' coupon page for created event
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed


  @manual
  Scenario: Last Team To Score pre-match market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type               |   selection_Type    |
      |\Last Team To Score\      |  Home/Draw/Away     |
    And add new coupon with title 'Automation Test Coupon'
    And set up event market for coupon
    When the user has navigated to the 'Football' coupon page for created event
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed


  @manual
  Scenario: Half Time pre-match market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type      |   selection_Type    |
      |\Half Time\      |  Home/Draw/Away     |
    And add new coupon with title 'Automation Test Coupon'
    And set up event market for coupon
    When the user has navigated to the 'Football' coupon page for created event
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed


  @manual
  Scenario: Double Chance pre-match market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type          |   selection_Type    |
      |\Double Chance\      |  HomeDraw/DrawAway/HomeAway    |
    And add new coupon with title 'Automation Test Coupon'
    And set up event market for coupon
    When the user has navigated to the 'Football' coupon page for created event
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed



  @manual
  Scenario: Goals 3-Band pre-match market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type                               |  selection_Type           |
      |\Total Goals\ - \Under\/\Exactly\/\Over\  |   Over/Middle/Under       |
    And add new coupon with title 'Automation Test Coupon'
    And set up event market for coupon
    When the user has navigated to the 'Football' coupon page for created event
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed


  @manual
  Scenario: Corners 3-Band pre-match market in Event page
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type      |  selection_Type           |
      |\Total Corners\  |   Over/Middle/Under       |
    And add new coupon with title 'Automation Test Coupon'
    And set up event market for coupon
    When the user has navigated to the 'Football' coupon page for created event
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed


  @manual
  Scenario: Bookings 3-Band pre-match market in Event pag
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type       |  selection_Type           |
      |\Total Bookings\  |   Over/Middle/Under       |
    And add new coupon with title 'Automation Test Coupon'
    And set up event market for coupon
    When the user has navigated to the 'Football' coupon page for created event
    Then the correct event name is displayed
    And the correct market name is displayed
    And the correct selections displayed with correct order
    And the selections labels displayed