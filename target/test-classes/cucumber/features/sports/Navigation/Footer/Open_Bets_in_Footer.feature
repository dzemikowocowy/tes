@footer
@mobile-only
Feature: Open Bets in Footer
#//Issue FURY-1582 - Navigation Footer - mobile
  Scenario: On this scenario we test that the Cash in my bet message is displayed when user is logged in
    Given the user is on the William Hill Home page
    When the user selects 'Football' from sports menu
    Then the user has logged into sportsbook
    And user click on Open Bet from the Footer Menu
    And the 'Cash in my bet' message is displayed


  Scenario: Please log in to see your open bets message is displayed when user is not logged in
    Given the user is on the William Hill Home page
    When the user selects 'Football' from sports menu
    And user click on Open Bet from the Footer Menu
    And the 'Please log in to see your open bets' message is displayed