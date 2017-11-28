@homePage
@mobile-only
Feature: None cross sell games in Homepage carousel

  Scenario: On this scenario we prove that the user is redirected to the Football Page
    Given the user is on the William Hill Home page
    Then the user click on 'Football' from the Carousel menu
    And the url contains 'football'
    And the second Header Title is 'Football Betting Highlights'


  Scenario: On this scenario we prove that the user is redirected to the Horse Racing Page
    Given the user is on the William Hill Home page
    Then the user click on 'Horses' from the Carousel menu
    And the url contains 'horse-racing'
    And the second Header Title is 'Horse Racing'


  Scenario: On this scenario we prove that the user is redirected to the Tennis Page
    Given the user is on the William Hill Home page
    Then the user click on 'Tennis' from the Carousel menu
    And the url contains 'tennis'
    And the second Header Title is 'Tennis Betting Highlights'


  Scenario: On this scenario we prove that the user is redirected to the Golf Page
    Given the user is on the William Hill Home page
    Then the user click on 'Golf' from the Carousel menu
    And the url contains 'golf'
    And the second Header Title is 'Golf Betting Highlights'


  Scenario: On this scenario we prove that the user is redirected to the Cricket Page
    Given the user is on the William Hill Home page
    Then the user click on 'Cricket' from the Carousel menu
    And the url contains 'cricket'
    And the second Header Title is 'Cricket Betting Highlights'


  Scenario: On this scenario we prove that the user is redirected to the Greyhounds Page
    Given the user is on the William Hill Home page
    Then the user click on 'Greyhounds' from the Carousel menu
    And the url contains 'greyhounds'
    And the second Header Title is 'Greyhounds Racing'


  Scenario: On this scenario we prove that the user is redirected to the Baseball Page
    Given the user is on the William Hill Home page
    Then the user click on 'Baseball' from the Carousel menu
    And the url contains 'baseball'
    And the second Header Title is 'Baseball Betting Highlights'


  Scenario: On this scenario we prove that the user is redirected to the Darts Page
    Given the user is on the William Hill Home page
    Then the user click on 'Darts' from the Carousel menu
    And the url contains 'darts'
    And the second Header Title is 'Darts Betting Highlights'


  Scenario: On this scenario we prove that the user is redirected to the Boxing Page
    Given the user is on the William Hill Home page
    Then the user click on 'Boxing' from the Carousel menu
    And the url contains 'boxing'
    And the second Header Title is 'Boxing Betting Highlights'


  Scenario: On this scenario we prove that the user is redirected to the Rugby League Page
    Given the user is on the William Hill Home page
    Then the user click on 'R League' from the Carousel menu
    And the url contains 'rugby-league'
    And the second Header Title is 'Rugby-league Betting Highlights'







