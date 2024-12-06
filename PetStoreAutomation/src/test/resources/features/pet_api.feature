Feature: Pet API Testing

  # Positive Scenarios
  Scenario: Add a new pet with valid data
    Given the pet endpoint is running in pet store API
    When I send a POST request to pet endpoint "/pet" with the following details:
      | id | name  | status    | category.id | category.name | photoUrls                                               | tags.id | tags.name |
      | 1  | Ramon | available | 10          | Dogs          | https://test.com/photo1.jpg,https://test.com/photo2.png | 20      | Small     |
    Then I should receive a 200 status code from pet endpoint
    And the response should include the pet details for "Ramon"

  Scenario: Get pets by a specific status
    Given the pet endpoint is running in pet store API
    When I send a GET request to pet endpoint "/pet/findByStatus" with status "available"
    Then I should receive a 200 status code from pet endpoint
    And the response should include pets with the status "available"

  Scenario: Retrieve a pet with a valid ID
    Given the pet endpoint is running in pet store API
    When I send a GET request to pet endpoint "/pet/{id}" with ID "10"
    Then I should receive a 200 status code from pet endpoint
    And the response should include the pet details for "doggie"

  # Negative Scenarios
  Scenario: Add a pet with missing required fields
    Given the pet endpoint is running in pet store API
    When I send a POST request to pet endpoint "/pet" with the following incomplete details:
      | id | name | status |
      | 2  |      |        |
    Then I should receive a 405 status code from pet endpoint
    And the response from pet endpoint should include an error message "Invalid input"

  Scenario: Get pets with an invalid status
    Given the pet endpoint is running in pet store API
    When I send a GET request to pet endpoint "/pet/findByStatus" with status "invalid_status"
    Then I should receive a 400 status code from pet endpoint
    And the response from pet endpoint should include an error message "Invalid status value"

  Scenario: Retrieve a pet with a non-existent ID
    Given the pet endpoint is running in pet store API
    When I send a GET request to pet endpoint "/pet/{id}" with ID "99999"
    Then I should receive a 404 status code from pet endpoint
    And the response from pet endpoint should include an error message "Pet not found"