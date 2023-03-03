@RunGooglewithExcel
Feature: Google Search Feature Validation with Excel Parameterization

  Background: 
    Given Launch the URL "URL"

  @googlesearchforAviva
  Scenario Outline: Search with Aviva and verify results links
    Given Load the Databank with Row"<SNO>" FileName"<FileName>" and SheetName"<Sheet_Name>"
    When Enter the Text "KEYWORD"
    And click on button "SEARCH"
    Then application should display links returned in resultspage and verify "LinkCount"
    And user should display "link" link

    Examples: 
      | SNO | FileName | Sheet_Name |
      |   1 | INPUT    | Data       |

  @PositiveScenario
  Scenario Outline: Verify Title in Aviva Home Page
    Given Load the Databank with Row"<SNO>" FileName"<FileName>" and SheetName"<Sheet_Name>"
    When Enter the Text "KEYWORD"
    And click on button "SEARCH"
    Then click on Aviva Login Link in the Google Search Page
    And verify Title of the "ACCESS" of the Aviva Home Page

    Examples: 
      | SNO | FileName | Sheet_Name |
      |   1 | INPUT    | Data       |

  @NegativeScenario
  Scenario Outline: Verify Error Message in Aviva Login Page
    Given Load the Databank with Row"<SNO>" FileName"<FileName>" and SheetName"<Sheet_Name>"
    When Enter the Text "KEYWORD"
    And click on button "SEARCH"
    Then click on Aviva Login Link in the Google Search Page and click on Login
    And verify the Error Message in the Aviva Login Page"ERROR_MSG"

    Examples: 
      | SNO | FileName | Sheet_Name |
      |   1 | INPUT    | Data       |
