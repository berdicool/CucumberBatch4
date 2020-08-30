Feature: Getting and deserializing pet from petstore
  @pet
  Scenario Outline: get pet
    Given contentType head is set to "application/json"
    When user executes "GET" request
    Then the status code is OK
    And contentType header is "application/json"
    And users verified <id> <name> <tags> size
    Examples:
      | id | name   | tags |
      | 10 | doggie | 1    |
