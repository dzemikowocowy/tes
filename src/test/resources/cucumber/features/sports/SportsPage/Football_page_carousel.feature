@footbalPage
@mobile-only


Feature: Football page carousel
  AS  user
  I WANT  see correct working Crousel on mobile
  SO THAT  user could navigate between options


  Scenario Outline: Options in Football Carousel redirect to correct pages.
    Given the user is on the William Hill Home page
    When the user selects 'Football' from sports menu
    Then the user select '<Carouseloption>' from the footbal Carousel

  Examples:
  |Carouseloption |
  | Daily List    |
  | In-Play       |
  | Coupons       |
  | Competitions  |








