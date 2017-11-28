@homePage
@desktop-only
Feature: Homepage in desktop

  As a William Hill customer
  I want to be able check the home page functionality and navigation


  Scenario Outline:  Go to home page from different pages
    Given the user navigates to WilliamHill homepage
    And home page is displayed
    When the user selects '<navigationoption>' from sports menu
    And the user selects 'home' from sports menu
    Then home page is displayed
    Examples:
      |navigationoption|
      | in-play       |
      | football      |


  Scenario Outline:  Go to home page from different pages through main icon
    Given the user navigates to WilliamHill homepage
    And home page is displayed
    When the user selects '<navigationoption>' from sports menu
    And the user clicks on William Hill icon in the header
    Then home page is displayed
    Examples:
      |navigationoption|
      | in-play       |
      | football      |


  Scenario: Navigate To home page  from event  page
    Given the user navigates to WilliamHill homepage
    And the user clicks on the first event
    And the user is on the selected event page
    When the user selects 'home' from sports menu
    Then home page is displayed


  Scenario: Navigate To home page  from event  page through main icon
    Given the user navigates to WilliamHill homepage
    And the user clicks on the first event
    And the user is on the selected event page
    When the user clicks on William Hill icon in the header
    Then home page is displayed


  Scenario: Add selection to the bestlip from  home page
    Given the user navigates to WilliamHill homepage
    When  the user clicks the first active selection
    Then  the betslip counter is increased


  Scenario: Navigate To show more
    Given the user navigates to WilliamHill homepage
    Then the user clicks on the show more link and the page is loaded

  Scenario Outline: Customer selects the different odd formats from matches
    Given the user navigates to WilliamHill homepage
    And the price format is set to <odd_format>
    Then the selection prices are displayed in <odd_format>
    Examples:
      |odd_format |
      |Fraction   |
      |Decimal    |
      |American   |
