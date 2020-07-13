Feature: All products

  Scenario: Validation of all products
    Given the demoUser enters username "Tester"
    When the demoUser enters password "test"
    Then the user clicks the all products button
    And the user validate the product details
      | Product name | Price | Discount|
      | MyMoney      | $100  | 8%      |
      | FamilyAlbum  | $80   | 15%     |
      | ScreenSaver  | $20   | 10%     |

