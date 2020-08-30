Feature: WebOrder Login page Test
  @smoke
  Scenario: Login Page Positive Test
    Given the demoUser enters username "Tester"
    When the demoUser enters password "test"
    And the demoUser validate home page

  Scenario: Login Page Negative Test
    Given the demoUser enters username "techtorial"
    When the demoUser enters password "test"
    Then the demoUser validate "Invalid Login or Password."

  @negative @smoke @conditional
  Scenario: Login Page Negative Test 1
    Given the demoUser enters username "Tester"
    When the demoUser enters password "tttt"
    Then the demoUser validate "Invalid Login or Password."

    @negative @smoke
  Scenario: Login Page Negative Test 2
    Given the demoUser enters username "techtorial"
    When the demoUser enters password "tttt"
    Then the demoUser validate "Invalid Login or Password."