@storeAPI
Feature: Store API Testing

  # Positive Scenarios
  Scenario: Place an order with valid data
    Given the pet store API is running
    When I send a POST request to "/store/order" with the following order details:
      | id | petId | quantity | shipDate             | status   | complete |
      | 10 | 10    | 10       | 2024-12-08T10:00:00Z | approved | true     |
    Then I should receive a 200 status code
    And the response schema should match schema "order-schema.json"
    And the response should include the details for order 10

  Scenario: Retrieve an order with valid id
    Given the pet store API is running
    When I send a GET request to "/store/order/{id}" with ID "10"
    Then I should receive a 200 status code
    And the response schema should match schema "order-schema.json"
    And the response should include the details for order 10

  Scenario: Place and order with invalid pet id field
    Given the pet store API is running
    When I send a POST request to "/store/order" with the following order details:
      | id | petId   | quantity | shipDate             | status   | complete |
      | 10 | invalid | 10       | 2024-12-08T10:00:00Z | approved | true     |
    Then I should receive a 400 status code
    And the response schema should match schema "error-schema.json"