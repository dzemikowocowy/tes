@footer
@mobile-only
Feature: Footer disappears during scrolling down


  Scenario: Footer disappears while scrolling - Home Page
    Given the user is on the William Hill Home page
    Then scroll down and verify that Footer disappears for a brief time


  Scenario: Footer disappears while scrolling - Sports Page
    Given the user is on the William Hill Home page
    When the user selects 'Football' from sports menu
    Then scroll down and verify that Footer disappears for a brief time


  Scenario: Footer disappears while scrolling - Competition Page
    Given the user is on the William Hill Home page
    When the user navigates to 'football' competitions page
    Then scroll down and verify that Footer disappears for a brief time


  Scenario: Footer disappears while scrolling - Daily List Page
    Given the user is on the William Hill Home page
    When the user is on the 'Football' Daily List page
    Then scroll down and verify that Footer disappears for a brief time


  Scenario: Footer disappears while scrolling - Horse Racing Page
    Given the user is on the William Hill Home page
    When the user selects 'Horses' from sports menu
    Then scroll down and verify that Footer disappears for a brief time


  Scenario: Footer disappears while scrolling - Greyhounds Page
    Given the user is on the William Hill Home page
    When the user selects 'Greyhounds' from sports menu
    Then scroll down and verify that Footer disappears for a brief time

