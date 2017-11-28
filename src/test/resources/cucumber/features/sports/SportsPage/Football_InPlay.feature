@footbalPage
@mobile-only
Feature: Football InPlay


  Correct components in Football In-Play
  All Sports In-Play button
  Correct events available in Football In-Play
  Event details in Football In-Play
  Bet placement in Football In-Play


#------------------------------------------correct components in football In-Play

  Scenario: correct components in football In-Play
#    Given the user is on the In Play 'football' page
#    Then Football In Play components displayed
    Given virtualfrom squiz
#    ------------------------------------Bet placement in Football In-Play ALL

    Scenario:Bet placement in Football In-Play
    Given the user is on the In Play 'football' page
    And the user has logged into sportsbook
    When the user clicks the first active selection
    And the betslip is open
    And the user adds £0.05 to the "Double" stake input
    And the user clicks on Place Bet
    Then the user balance is updated on refresh


    #    -------------------------------------- Event details in Football Daily List

  Scenario:1 Event details in Football In-Play
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Result\  |  Home/Draw/Away   |
    And  the event and market are in-play
    When the user is on the In Play 'football' page
    Then the event details are correct
    And the customer click on the event link
    And the user is on the selected event page

#----------------------------------------Correct events available in Football In-Play


  Scenario:1 Active football event with starting time in the past and Off flag to Yes
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Result\  |  Home/Draw/Away   |
    And the event and market are in-play
    When the user is on the In Play 'football' page
    Then the event displayed on the page

  Scenario:2 Active football event with starting time in the past and Off flag to N/A
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Result\  |  Home/Draw/Away   |
    And the event and market are in-play
    And the event off flag set to n/a
    When the user is on the In Play 'football' page
    Then the event displayed on the page

  Scenario:3 Active football event with starting time in the past and Off flag to No
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Result\  |  Home/Draw/Away   |
    And the event and market are in-play
    And the event off flag set to no
    When the user is on the In Play 'football' page
    Then the event is not displayed on the page


  Scenario:4 Active football event with starting time in the future and Off flag to Yes
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Result\  |  Home/Draw/Away   |
    And the event off flag set to yes
    And the event start time is set up for '10' hours from now
    When the user is on the In Play 'football' page
    Then the event displayed on the page


  Scenario:5 football event with starting time in the future and Off flag to No
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Result\  |  Home/Draw/Away   |
    And the event and market are in-play
    And the event start time is set up for '10' hours from now
    And the event off flag set to no
    When the user is on the In Play 'football' page
    Then the event is not displayed on the page


  Scenario:6 football event with starting time in the future and Off flag to  N/A
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Result\  |  Home/Draw/Away   |
    And the event and market are in-play
    And the event start time is set up for '10' hours from now
    And the event off flag set to n/a
    When the user is on the In Play 'football' page
    Then the event is not displayed on the page

  Scenario:7 football event with starting time in the future and Off flag to  N/A
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Result\  |  Home/Draw/Away   |
    And the event and market are in-play
    And the first event market is settled and Event off flag is set to 'yes'
    And the event off flag set to n/a
    When the user is on the In Play 'football' page
    Then the event is not displayed on the page

#------------------------------------------All Sports In-Play button
     Scenario: All Sports In-Play button
      Given the user is on the William Hill Home page
      When user click in All In-Play button on carousel
      Then the user is on the In Play All Page
