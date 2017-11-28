@BackButton
@mobile-only

Feature:We need to ensure that back button works as expected in mobile view


  Scenario:Back button functionality in In-Play page
  Given the user is on the William Hill Home page
    And the user selects 'In-Play' from sports menu
    And the user selects first sport in In-Play page
    And the user selects first event
    When the user clicks on back button
    Then the user is the In-Play sport page
    And the user clicks on back button
    And home page is displayed