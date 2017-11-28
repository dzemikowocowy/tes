@footer
@mobile-only

Feature: Sports menu components

  Scenario: Quick Links - Click on Home
    Given the user is on the William Hill Home page
    When the user selects 'Football' from sports menu
    Then the user click on 'Home' from the Quick Links
    And verify Home Page redirection


  Scenario: Quick Links - Click on In-Play
    Given the user is on the William Hill Home page
    When the user click on 'In-Play' from the Quick Links
    Then verify In-Play redirection


  Scenario: Quick Links - Click on Football
    Given the user is on the William Hill Home page
    When the user click on 'Football' from the Quick Links
    Then verify Football redirection


  Scenario: Quick Links - Click on Horse Racing
    Given the user is on the William Hill Home page
    When the user click on 'Horses' from the Quick Links
    Then verify Horse Racing redirection

  Scenario: Quick Links - Click on Cricket
    Given the user is on the William Hill Home page
    When the user click on 'Cricket' from the Quick Links
    Then verify Cricket redirection


  Scenario: Quick Links - Click on Greyhounds
    Given the user is on the William Hill Home page
    When the user click on 'Greyhounds' from the Quick Links
    Then verify Greyhounds redirection


  Scenario: Quick Links - Click on Virtual World
    Given the user is on the William Hill Home page
    When the user click on 'Virtual World' from the Quick Links
    Then verify Virtual World redirection


  Scenario: Quick Links - Click on Roulette
    Given the user is on the William Hill Home page
    When the user has logged into sportsbook
    Then the user click on 'Roulette' from the Quick Links


  Scenario: Quick Links - Click on Blackjack
    Given the user is on the William Hill Home page
    When the user click on 'Blackjack' from the Quick Links
    Then verify Blackjack redirection


  Scenario: Quick Links - Click on Wish JP
    Given the user is on the William Hill Home page
    Then the user click on 'Wish JP' from the Quick Links



  Scenario: Quick Links - Click on Search
    Given the user is on the William Hill Home page
   When the user click on 'Search' from the Quick Links
    Then verify Search redirection


  Scenario: A - Z Sports - Click on One Sport
    Given the user is on the William Hill Home page
    When user click on A - Z Sports from Footer
    Then verify A to Z Sports redirection


  Scenario: Extras - Click on Top Bets
    Given the user is on the William Hill Home page
    When the user click on 'Top Bets' from the Extras
    Then verify Top Bets redirection


  Scenario: Extras - Click on Promotions
    Given the user is on the William Hill Home page
    When the user click on 'Promotions' from the Extras


  Scenario: Extras - Click on Schedule
    Given the user is on the William Hill Home page
    When the user click on 'Schedule' from the Extras
    Then verify Schedule redirection