Feature: Amazon Search

  Background:
    Given go to amazon.com
    When enter search term "Selenium book"
    And click search button

  Scenario: Amazon search result verification
    Then verify that a result with "Selenium Testing Tools Cookbook" is  displayed anywhere in the results
    When enter search term "java OCA book"
    And click search button
    Then verify that a result with "Selenium Testing Tools Cookbook" is not displayed anywhere in the results


  Scenario: Amazon Search Result verification
    Then verify all result contains the word "Selenium"

  Scenario: Amazon Search Result verification
    And verify free shipping check box is not selected
    And check the free shipping box
    Then verify that top five results are still "Selenium"







