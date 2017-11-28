@BackButton
@mobile-only

Feature:We need to ensure that back button works as expected in mobile view

  Scenario:Back button functionality in Horse racing page
    Given the user is on the William Hill Home page
    And the user selects 'Horse Racing' from sports menu
    And the  user click on Meetings
    And the user selects first available race
    And the user navigates through the races in the meeting
   When the user clicks on back button
    Then the user is the Horse Racing Meetings page