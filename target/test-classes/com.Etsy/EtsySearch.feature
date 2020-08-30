Feature: Validate Search in Etsy

  Background: It will run before each scenario
    Given the user goes to Etsy
    #We need to run three scenarios and before every scenario
    # we have to navigate to etsy except second scenario
  @etsy @tt
  Scenario: Validation of Search in Etsy 1
    When the user search in Etsy with "winter hat"
    Then the user validate title "Winter hat | Etsy" and url "winter"
  @etsy
  Scenario: Validation of Search in Etsy 2
    When the user search in Etsy with "hat"
    Then the user validate title "Hat | Etsy" and url "hat"
  @etsy @tt
  Scenario: Validation of Search in Etsy 3
    When the user search in Etsy with "winter soldier"
    Then the user validate title "Winter soldier | Etsy" and url "soldier"
