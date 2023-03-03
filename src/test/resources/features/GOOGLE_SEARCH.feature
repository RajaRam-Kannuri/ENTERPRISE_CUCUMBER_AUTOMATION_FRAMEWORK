@RunGoogle
Feature: Google Search Feature Validation

  Background: 
    Given Launch the URL "URL"

  @googlesearchforAviva
  Scenario Outline: Search with Aviva and verify results links
    When Enter the Text "<KEYWORD>"
    And click on button "SEARCH"
    Then application should display links returned in resultspage and verify "<LinkCount>"
    And user should display "<link>" link

    Examples: 
      | KEYWORD | LinkCount | link |
      | Aviva   |         6 |    5 |

  @PositiveScenario
  Scenario Outline: Verify Title in Aviva Home Page
    When Enter the Text "<KEYWORD>"
    And click on button "SEARCH"
    Then click on Aviva Login Link in the Google Search Page
    And verify Title of the "<Access>" of the Aviva Home Page

    Examples: 
      | KEYWORD | Access               |
      | Aviva   | Access without Login |

  @NegativeScenario
  Scenario Outline: Verify Error Message in Aviva Login Page
    When Enter the Text "<KEYWORD>"
    And click on button "SEARCH"
    Then click on Aviva Login Link in the Google Search Page and click on Login
    And verify the Error Message in the Aviva Login Page"<ERROR_MSG>"

    Examples: 
      | KEYWORD | ERROR_MSG                       |
      | Aviva   | Please enter login credentials. |
