@product
Feature: Add product Functionality

  In order to manage the catalog
  As a registered user
  I want to add a product after logging in

  @positive
  Scenario: Product Add Successful
    Given User login with valid credentials "kiran.teli+009@brainvire.com" and password "Test@123"
    And User go to the Catalog page
    And Product: User Go to the Add Product Screen
    When Product > Add Product: Enters all valid data
    Then Message should be visible as "Product created successfully"
    And Product > Product Listing: User clicks on edit button of added product
    Then Product > Edit Product: Verify that data shown are correct.
    And Product > Edit Product: User update the product data
    Then Message should be visible as "Product updated successfully"
    When Product: User delete the same product
    And Message should be visible as "Product deleted successfully"

  @positive
  Scenario: Product Add Successful using the Upload CSV file
    Given User login with valid credentials "kiran.teli+009@brainvire.com" and password "Test@123"
    And User go to the Catalog page
    And Product: User Download the CSV file Format
    Then Message should be visible as "All Product fully uploaded"