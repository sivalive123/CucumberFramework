Feature: Cart tests
  As a Customer, I want to view cart menu and add product to cart

  Scenario: Login after access cart without authorizations
    Given Home page without authorizations
    When Go to cart page without authorizations
    And This is for invalid locators