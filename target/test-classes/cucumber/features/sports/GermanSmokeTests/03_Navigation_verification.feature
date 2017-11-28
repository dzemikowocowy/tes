@smoke-translation
Feature: 03 Navigation verification

  Scenario: User is able to Navigate to the William Hill Homepage
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer


  Scenario: User is able to navigate to Sports page
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu


  @desktop-only
  Scenario: User is able to navigate to In Play page - Desktop
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    Then the user click on 'Live' from the left hand menu
    And the url contains 'live'


  @mobile-only
  Scenario: User is able to navigate to In Play page - Mobile
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    Then the user clicks on 'Live' from the Sports Page Carousel
    And the url contains 'live'


  Scenario: User is able to navigate to Horse Racing page
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Pferderennen' from sports menu
    And the url contains 'pferderennen'


  @desktop-only
  Scenario: User is able to navigate to Coupons Page - Desktop
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    Then the user click on 'Coupons' from the left hand menu
    And the url contains 'coupons'


  @mobile-only
  Scenario: User is able to navigate to Coupons Page - Mobile
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    Then the user clicks on 'Coupons' from the Sports Page Carousel
    And the url contains 'coupons'


  @desktop-only
  Scenario: User is able to navigate to Daily List Page - Desktop
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    Then the user click on 'Tägliche Liste' from the left hand menu
    And the url contains 'begegnungen'


  @mobile-only
  Scenario: User is able to navigate to Daily List Page - Mobile
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    Then the user clicks on 'Tägliche Liste' from the Sports Page Carousel
    And the url contains 'begegnungen'


  @desktop-only
  Scenario: User is able to navigate to Competitions Page - Desktop
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    Then the user click on 'Wettbewerbe' from the left hand menu
    And the url contains 'wettbewerbe'


  @mobile-only
  Scenario: User is able to navigate to Competitions Page - Mobile
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    Then the user clicks on 'Wettbewerbe' from the Sports Page Carousel
    And the url contains 'wettbewerbe'


  Scenario: User is able to navigate to Event page
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user clicks on the 'Live' tab
    Then the user clicks on the first event
    And the url contains 'OB_EV'
    And the event page has the correct Title
    And the event page has a Market block