Feature: scenario outline test

  Background: Etsy navigation
    Given the user goes to Etsy

    #Scenario Outline: and Scenario Template: are same
    #Examples: and Scenarios: are same
  @etsyOutline
  Scenario Outline: Etsy search validation with outline
    When the user search in Etsy with "<searchValue>"
    Then the user validate title "<title>" and url "<etsyUrl>"
    Examples:
      | searchValue    | title                | etsyUrl |
      | winter hat     | Winter hat \| Etsy   | winter  |
      | hat            | Hat \| Etsy            | hat     |
      | winter soldier | Winter soldier \| Etsy | soldier |

    #to orgonise your code in IntellIJ you need to use ctrl+alt+l