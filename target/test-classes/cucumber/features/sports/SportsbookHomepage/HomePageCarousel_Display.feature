@homePage
Feature: Availability of Homepage carousel

  @desktop-only
  Scenario: For Desktop on the Home Page should not be visible
    Given the user is on the William Hill Home page
    Then the Home Page Carousel 'is not' being displayed

  @mobile-only
  Scenario: For Mobile should be visible on the Home Page
    Given the user is on the William Hill Home page
    Then the Home Page Carousel 'is' being displayed


  Scenario: On this scenario we verify for Mobile or Desktop that is not displayed on the Football Page
    Given the user is on the William Hill Home page
    When the user selects 'Football' from sports menu
    Then the Home Page Carousel 'is not' being displayed


  Scenario: On this scenario we verify for Mobile or Desktop that is not displayed on the Football Competition Page
    Given the user is on the William Hill Home page
    When the user navigates to 'football' competitions page
    Then the Home Page Carousel 'is not' being displayed


  Scenario: On this scenario we verify for Mobile or Desktop that is not displayed on the Football Daily List Page
    Given the user is on the William Hill Home page
    When the user navigates to 'football' daily list page
    Then the Home Page Carousel 'is not' being displayed


  Scenario: On this scenario we verify for Mobile or Desktop that is not displayed on the Horse Racing Page
    Given the user is on the William Hill Home page
    When the user selects 'Horses' from sports menu
    Then the Home Page Carousel 'is not' being displayed


  Scenario: On this scenario we verify for Mobile or Desktop that is not displayed on the Greyhounds Page
    Given the user is on the William Hill Home page
    When the user selects 'Greyhounds' from sports menu
    Then the Home Page Carousel 'is not' being displayed














