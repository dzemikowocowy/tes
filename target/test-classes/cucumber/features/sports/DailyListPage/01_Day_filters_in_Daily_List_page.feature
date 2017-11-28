@Day_filters

Feature: 01 Day filters in Daily List page

  Scenario: Correct options in day filter of football Daily List
    Given the user navigates to 'football' daily list page
    When  Correct options are available in Football Daily List bar
    Then the option 'Today' in day filter is highlighted


  Scenario: Correct options in View by filter in football Daily List
    Given the user navigates to 'football' daily list page
    When View By Menu contains two options, Time and Competition
    Then Default option is Competition


  Scenario: Navigation through day filter in football Daily List
    Given the user navigates to 'football' daily list page
    When Correct options are available in Football Daily List bar
    Then user can navigate trough all daily list options


  Scenario: Navigation and View by filter in football Daily List
    Given the user navigates to 'football' daily list page
    When Correct options are available in Football Daily List bar
    Then View by filter working correctly on each daily list option


  Scenario: Correct options in day filters of non-football Daily List
    Given the user navigates to 'basketball' daily list page
    When  Correct options are available in Basketball Daily List bar
    Then the option 'Today' in day filter is highlighted


  Scenario: Correct options in View by filter in non-football Daily List
    Given the user navigates to 'basketball' daily list page
    When View By Menu contains two options, Time and Competition
    Then Default option is Competition


  Scenario: Navigation and View by filter in non-football Daily List
    Given the user navigates to 'basketball' daily list page
    And  Correct options are available in Basketball Daily List bar
    And the option 'Today' in day filter is highlighted
    When the user changes view filter to 'time'
    Then the option 'Today' in day filter is highlighted
    And the user changes view filter to 'competition'
    And the option 'Today' in day filter is highlighted
    And the user selects future from day filter
    And the option 'Future' in day filter is highlighted
    And the user changes view filter to 'time'
    And the option 'Future' in day filter is highlighted
    And the user changes view filter to 'competition'
    And the option 'Future' in day filter is highlighted