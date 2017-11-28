@footbalPage
@mobile-only
Feature: Football Daily List
  As a Product Owner
  I want the filter icons in Daily Lists to be more descriptive for when the user switches between Time and Competition views
  So that the users navigation experience is improved

  Correct components in Football Daily List
  Correct options are available in Football Daily List bar
  Correct events available for Today in Football Daily List
  Correct events available for Tomorrow in Football Daily List
  Correct events available for any other day in Football Daily List
  View by menu in Football Daily List works as expected
  Event details in Football Daily List
  Bet placement in Football Daily List


#-------------------------- Correct components in Football Daily List


  Scenario: Correct components in Football Daily List
    Given the user is on the 'Football' Daily List page
    Then Football Daily List components displayed

# ------------------------   Correct options are available in Football Daily List bar

  Scenario: Correct options are available in Football Daily List bar
    Given the user is on the 'Football' Daily List page
    Then  Correct options are available in Football Daily List bar

#---------------------- Correct events available for Tomorrow in Football Daily List


  Scenario:Correct events available for Tomorrow in Football Daily List
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Result\  |  Home/Draw/Away   |
    And the event start time is set up for '24' hours from now
    When the user is on the 'Football' Daily List page
    Then the event is available on the 'Tomorrow' tab but It is not available for the other tabs



  Scenario:Correct events available for Tomorrow in Football Daily List off flag yes
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Result\  |  Home/Draw/Away   |
    And the event off flag set to yes
    And the event start time is set up for '24' hours from now
    When the user is on the 'Football' Daily List page
    Then the event does not appear in all tabs of Football Daily List


#--------------------------------- Correct events available for Today in Football Daily List

  Scenario:Correct events available for Today in Football Daily List
    # All active events with starting time for today or any point in the past are available in Today tab
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Result\  |  Home/Draw/Away   |
    When the user is on the 'Football' Daily List page
    Then the event is available on the 'Today' tab but It is not available for the other tabs



  Scenario:Correct events available for Today in Football Daily List future for today and Off flag to No
#  Active football event with starting time in the future for today and Off flag to No
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Result\  |  Home/Draw/Away   |
    And the event start time is set up for '2' hours from now
    When the user is on the 'Football' Daily List page
    Then the event is available on the 'Today' tab but It is not available for the other tabs

#    ------------------------------------View by menu in Football Daily List works as expected

  Scenario: View by menu in Football Daily List works as expected
      Given the user is on the 'Football' Daily List page
      When the user click on the View By button
      Then View By Menu is expanded
      And View By Menu contains two options, Time and Competition
      And Default option is Competition



  Scenario: Events are ordered by time and grouped by compeition when Competition is selected

    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\Outright\  |  Home/Draw/Away   |
    And the event start time is set up for '2' hours from now
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\Outright\  |  Home/Draw/Away   |
    And the event start time is set up for '3' hours from now
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\Outright\  |  Home/Draw/Away   |
    And the event start time is set up for '4' hours from now
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\Outright\  |  Home/Draw/Away   |
    And the event start time is set up for '5' hours from now
    When the user is on the 'Football' Daily List page
    Then Competition is selected in View By Menu
    And the events are grouped by Competition


  Scenario: Events are ordered by time without being grouped by competition when Time is selected
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\Outright\  |  Home/Draw/Away   |
    And the event start time is set up for '2' hours from now
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\Outright\  |  Home/Draw/Away   |
    And the event start time is set up for '3' hours from now
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\Outright\  |  Home/Draw/Away   |
    And the event start time is set up for '4' hours from now
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\Outright\  |  Home/Draw/Away   |
    And the event start time is set up for '5' hours from now
    When the user is on the 'Football' Daily List page
    Then Time is selected in View By Menu
    And the events are grouped by time

    #    ------------------------------------Bet placement in Football Daily List


  Scenario: Add Selection to the bet slip, open betslip
    Given the user is on the 'Football' Daily List page
    And the user has logged into sportsbook
    When the user clicks the first active selection
    And the betslip is open
    Then the betslip counter is increased

#   ----------------------------------------Correct events available for any other day in Football Daily List

  Scenario:Correct events available for any other day in Football Daily List
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Result\  |  Home/Draw/Away   |
    And the event start time is set up for '48' hours from now
    When the user navigates to 'football' daily list page
    Then the event is available on the 'third' tab but It is not available for the other tabs



#    -------------------------------------- Event details in Football Daily List

  Scenario:1 Event details in Football Daily List
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Result\  |  Home/Draw/Away   |
    And the event and market are in-play
    When the user navigates to 'football' daily list page
    Then the event details are correct
    And the customer click on the event link
    And the user is on the selected event page

  Scenario:2 Event details in Football Daily List
    Given a 'Spanish La Liga Primera' event with following markets
      |market_type           |  selection_Type |
      |\2nd Half Resutl\  |  Home/Draw/Away   |
    When the user navigates to 'football' daily list page
    Then the event details are correct
    And the customer click on the event link
    And the user is on the selected event page

