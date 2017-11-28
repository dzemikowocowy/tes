@smoke-translation
Feature: 04 Bet Placement verification


  Scenario: User can Bet from the Home Page
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user has logged into sportsbook
    And the user clicks on the 'Live' tab
    Then the user adds the first available selection to the Betslip
    And user open the Betslip
    Then set the stake to '0.05' for the just added bet
    And user click on Place Bet
    And the message Bets Placed is displayed


  @desktop-only
  Scenario: User can Bet from the Sports Page
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    And the user has logged into sportsbook
    And the user adds the first available selection to the Betslip
    And user open the Betslip
    Then set the stake to '0.05' for the just added bet
    And user click on Place Bet
    And the message Bets Placed is displayed


  @mobile-only
  Scenario: User can Bet from the Sports Page
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    And the user has logged into sportsbook
    Then the user clicks on the 'Live' tab
    And the user adds the first available selection to the Betslip
    And user open the Betslip
    Then set the stake to '0.05' for the just added bet
    And user click on Place Bet
    And the message Bets Placed is displayed


  @desktop-only
  Scenario: User can Bet from the In Play page - Desktop
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    And the user has logged into sportsbook
    Then the user click on 'Live' from the left hand menu
    And the user adds the first available selection to the Betslip
    And user open the Betslip
    Then set the stake to '0.05' for the just added bet
    And user click on Place Bet
    And the message Bets Placed is displayed

  @mobile-only
  Scenario: User can Bet from the In Play page - Mobile
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    And the user has logged into sportsbook
    Then the user clicks on 'Live' from the Sports Page Carousel
    And the user adds the first available selection to the Betslip
    And user open the Betslip
    Then set the stake to '0.05' for the just added bet
    And user click on Place Bet
    And the message Bets Placed is displayed


  Scenario: User can Bet from the Horse Racing page
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Pferderennen' from sports menu
    And the user has logged into sportsbook
    And the user adds the first available selection to the Betslip
    And user open the Betslip
    Then set the stake to '0.05' for the just added bet
    And user click on Place Bet
    And the message Bets Placed is displayed


  Scenario: User can Bet from the Event page
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    And the user has logged into sportsbook
    Then the user clicks on the first event with active Selection
    And the user adds the first available selection to the Betslip
    And user open the Betslip
    Then set the stake to '0.05' for the just added bet
    And user click on Place Bet
    And the message Bets Placed is displayed


  @desktop-only
  Scenario: User can Bet on a Double Bet - Desktop
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    And the user has logged into sportsbook
    And the user click on 'Live' from the left hand menu
    Then the user adds '2' selections to the Betslip from different markets
    And user open the Betslip
    Then user set the stake for the Double bet added
    And user click on Place Bet
    And the message Bets Placed is displayed

  @mobile-only
  Scenario: User can Bet on a Double Bet - Mobile
    Given the user navigates to WilliamHill homepage
    And the user changes the language to 'Deutsch' from the footer
    When the user selects 'Fußball' from sports menu
    And the user has logged into sportsbook
    Then the user clicks on 'Live' from the Sports Page Carousel
    And the user adds '2' selections to the Betslip from different markets
    And user open the Betslip
    And user set the stake for the Double bet added
    And user click on Place Bet
    And the message Bets Placed is displayed





