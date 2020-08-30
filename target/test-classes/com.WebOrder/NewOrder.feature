Feature: New order validation

  Scenario: Validate new order in WebOrder page
    Given the demoUser enters username "Tester"
    When the demoUser enters password "test"
    Then the user enters product info "MyMoney" and "10"
    And the user enter address info "David", "2200 E Devon", "Des Plaines", "Illinois", "60018"
    * the user enter payment info "Visa", "4444333322221111", "05/24"
    Then the user validate success message
