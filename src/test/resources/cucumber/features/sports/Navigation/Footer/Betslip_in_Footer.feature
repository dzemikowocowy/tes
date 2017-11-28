@footer
@mobile-only
Feature: Betslip in Footer


  Scenario: Verification of Adding and removing one by one the Selections
    Given the user is on the William Hill Home page
    When the user selects 'Football' from sports menu
    Then the user is able to add a Top Bet Selection to the Betslip
    And click on the Footer Betslip
    And verify that Bet is displayed on the Footer Betslip
    Then the user removes 1 selection using the "x" on the bet slip
    And all selections are removed from the bet slip


  Scenario: Verification of Adding and clicking on Clear Slip
    Given the user is on the William Hill Home page
    When the user selects 'Football' from sports menu
    Then the user is able to add a Top Bet Selection to the Betslip
    And click on the Footer Betslip
    And verify that Bet is displayed on the Footer Betslip
    Then the user clicks the Clear Slip link
    And all selections are removed from the bet slip