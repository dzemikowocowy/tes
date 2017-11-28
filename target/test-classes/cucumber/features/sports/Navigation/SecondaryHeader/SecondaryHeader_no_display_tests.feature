@secondary-header

Feature: Secondary Header not displayed

  Scenario: On this scenario we prove that the Secondary Header is not being display on Home Page
    Given the user navigates to WilliamHill homepage
    Then the second Header it 'is not' displayed

  @desktop-only
  Scenario: On this scenario we prove that the Secondary Header is not being display for Desktop on Football Page.
    Given the user navigates to WilliamHill homepage
    When the user selects 'Football' from sports menu
    Then the second Header it 'is not' displayed


  @desktop-only
  Scenario: On this scenario we prove that the Secondary Header is not being display for Desktop on Football Competition Page.
    Given the user navigates to WilliamHill homepage
    When the user navigates to 'football' competitions page
    Then the second Header it 'is not' displayed


  @desktop-only
  Scenario: On this scenario we prove that the Secondary Header is not being display for Desktop on Football Daily List Page.
    Given the user navigates to WilliamHill homepage
    When the user navigates to 'football' daily list page
    Then the second Header it 'is not' displayed


  @desktop-only
  Scenario: On this scenario we prove that the Secondary Header is not being display for Desktop on Horse Racing Page.
    Given the user navigates to WilliamHill homepage
    When the user selects 'Horses' from sports menu
    Then the second Header it 'is not' displayed


  @desktop-only
  Scenario: On this scenario we prove that the Secondary Header is not being display for Desktop on Greyhounds Page.
    Given the user navigates to WilliamHill homepage
    When the user selects 'Greyhounds' from sports menu
    Then the second Header it 'is not' displayed