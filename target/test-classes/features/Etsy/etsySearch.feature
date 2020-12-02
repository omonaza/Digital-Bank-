Feature: Etsy Search

  @search
  Scenario: Etsy search verification
    Given got to etsy.com
    When Enter any search term "Book"
    And click on search button
    And click on allFilters button
    Then verify shop location anywhere is checked
