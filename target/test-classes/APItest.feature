@api
Feature: API test for petstore website
  Scenario: Creating and Asserting a pet
    When user creates a pet with id, name, status
    Then the status code is OK
    And pet with id, name, status is created