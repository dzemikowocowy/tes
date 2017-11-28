@BackButton
@mobile-only

Feature:We need to ensure that back button works as expected in mobile view


  Scenario:Back button functionality in Daily List page
    Given the user is on the William Hill Home page
    When the user selects 'football' from sports menu
    And the user select 'Daily List' from the football Carousel
    And the user navigates to any '3' option in the day filter
    And the user changes view filter to 'competition'
    And the user navigates to any '1' option in the day filter
    When the user clicks on back button
    Then the user is in football page


