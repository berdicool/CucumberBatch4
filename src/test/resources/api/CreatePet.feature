@api
Feature: Pet Store
  Scenario: Create a pet
    When user creates a pet with id, name, status
    Then the status code is OK
    And pet with id, name, status is created