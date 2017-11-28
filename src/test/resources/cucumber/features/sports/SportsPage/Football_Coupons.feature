@footbalPage
@mobile-only
Feature: Football Coupons


  Correct components in Football Coupons
  All Sports In-Play button
  Correct events available in Football In-Play
  Event details in Football In-Play
  Bet placement in Football In-Play


#    ------------------------------------Bet placement in Football In-Play ALL


  Scenario:Bet placement in Football In-Play
    Given the user is on the football Couppons page
    Then Football Couppons components displayed

    #    -------------------------------------- Event details in Football Daily List
@12312312
  Scenario:1 Event details in Football In-Play
    Given a 'English League 1' event with following markets
      |market_type           |  selection_Type |
      |\Match Betting Live\  |  Home/Draw/Away   |
    And the event and market are in-play
    When the user is on the In Play 'football' page
    Then the event details are correct
    And the customer click on the event link
    And the user is on the selected event page

#----------------------------------------Correct events available in Football In-Play


  Scenario:1 Active football event with starting time in the past and Off flag to Yes
    Given a 'English League 1' event with following markets
      |market_type           |  selection_Type |
      |\Match Betting Live\  |  Home/Draw/Away   |
    And the event and market are in-play
    When the user is on the In Play 'football' page
    Then the event displayed on the page

  Scenario:2 Active football event with starting time in the past and Off flag to N A
    Given a 'English League 1' event with following markets
      |market_type           |  selection_Type |
      |\Match Betting Live\  |  Home/Draw/Away   |
    And the event and market are in-play
    And the event off flag set to n/a
    When the user is on the In Play 'football' page
    Then the event displayed on the page
  @test
  Scenario:3 Active football event with starting time in the past and Off flag to No
    Given a 'English League 1' event with following markets
      |market_type           |  selection_Type |
      |\Match Betting Live\  |  Home/Draw/Away   |
    And the event and market are in-play
    And the event off flag set to no
    When the user is on the In Play 'football' page
    Then the event is not displayed on the page


  Scenario:4 Active football event with starting time in the future and Off flag to Yes
    Given a 'English League 1' event with following markets
      |market_type           |  selection_Type |
      |\Match Betting Live\  |  Home/Draw/Away   |
    And the event off flag set to yes
    And the event start time is set up for '10' hours from now
    When the user is on the In Play 'football' page
    Then the event displayed on the page

  @test
  Scenario:5 football event with starting time in the future and Off flag to No
    Given a 'English League 1' event with following markets
      |market_type           |  selection_Type |
      |\Match Betting Live\  |  Home/Draw/Away   |
    And the event and market are in-play
    And the event start time is set up for '10' hours from now
    And the event off flag set to no
    When the user is on the In Play 'football' page
    Then the event is not displayed on the page


  Scenario:6 football event with starting time in the future and Off flag to  NA
    Given a 'English League 1' event with following markets
      |market_type           |  selection_Type |
      |\Match Betting Live\  |  Home/Draw/Away   |
    And the event and market are in-play
    And the event start time is set up for '10' hours from now
    And the event off flag set to n/a
    When the user is on the In Play 'football' page
    Then the event is not displayed on the page

  Scenario:7 football event with starting time in the future and Off flag to  NA
    Given a 'English League 1' event with following markets
      |market_type           |  selection_Type |
      |\Match Betting Live\  |  Home/Draw/Away   |
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
