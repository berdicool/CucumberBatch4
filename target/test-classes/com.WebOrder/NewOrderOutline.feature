Feature: Validation of New Order using Scenario Outline

  Scenario Outline: New Order validation
    Given the demoUser enters username "Tester"
    When the demoUser enters password "test"
    Then the user enters product info "<productName>" and "<quantity>"
    And the user enter address info "<name>", "<address>", "<city>", "<state>", "<zipCode>"
    * the user enter payment info "<cardType>", "<cardNum>", "<expDate>"
    Then the user validate success message
    And the user validate title new order details "<productName>","<quantity>","<name>", "<address>", "<city>", "<state>", "<zipCode>","<cardType>", "<cardNum>", "<expDate>"
    Examples:
      | productName | quantity | name    | address      | city        | state      | zipCode | cardType         | cardNum          | expDate |
      | MyMoney     | 10       | David   | 2200 E Devon | Des Plaines | Illinois   | 60018   | Visa             | 4444333322221111 | 05/24   |
      | FamilyAlbum | 5        | John    | 2222 E Devon | Chicago     | Arizona    | 60645   | MasterCard       | 4444333322226666 | 06/25   |
      | ScreenSaver | 3        | Jessica | 3445 E River | Cumberland  | California | 45678   | American Express | 4444333322228888 | 07/23   |