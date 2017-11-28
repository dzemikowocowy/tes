@smoke
@live-safe
Feature: 04 Navigation verification

  Scenario: User is able to Navigate to the William Hill Homepage
    Given the user navigates to WilliamHill homepage


  Scenario: User is able to navigate to Sports page
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu


  @desktop-only
  Scenario: User is able to navigate to In Play page - Desktop
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the user click on 'In-Play' from the left hand menu
    And verify In-Play redirection


  @mobile-only
  Scenario: User is able to navigate to In Play page - Mobile
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the user clicks on 'In-Play' from the Sports Page Carousel
    And verify In-Play redirection


  Scenario: User is able to navigate to Horse Racing page
    Given the user navigates to WilliamHill homepage
    When the user selects 'Horses' from sports menu
    Then verify Horse Racing redirection


  @desktop-only
  Scenario: User is able to navigate to Coupons Page - Desktop
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the user click on 'Coupons' from the left hand menu
    And verify Coupons redirection


  @mobile-only
  Scenario: User is able to navigate to Coupons Page - Mobile
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the user clicks on 'Coupons' from the Sports Page Carousel
    And verify Coupons redirection


  @desktop-only
  Scenario: User is able to navigate to Daily List Page - Desktop
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the user click on 'Daily List' from the left hand menu
    And the url contains 'football/matches'

  @mobile-only
  Scenario: User is able to navigate to Daily List Page - Mobile
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the user clicks on 'Daily List' from the Sports Page Carousel
    And the url contains 'football/matches'


  @desktop-only
  Scenario: User is able to navigate to Competitions Page - Desktop
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the user click on 'Competitions' from the left hand menu
    And the url contains 'football/competitions'

  @mobile-only
  Scenario: User is able to navigate to Competitions Page - Mobile
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the user clicks on 'Competitions' from the Sports Page Carousel
    And the url contains 'football/competitions'


  Scenario: User is able to navigate to Event page
    Given the user navigates to WilliamHill homepage
    When the user clicks on the 'In-Play' tab
    Then the user clicks on the first event
    And the url contains 'football/OB_EV'
    And the event page has the correct Title
    And the event page has a Market block