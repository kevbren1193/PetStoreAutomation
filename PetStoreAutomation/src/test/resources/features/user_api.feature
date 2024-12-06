Feature: User API Testing

  # Positive Scenarios
  Scenario: Create a new user with valid data
    Given the pet store API is running
    When I send a POST request to "/user" with the following user details:
      | id     | username | firstName | lastName | email                | password | phone    | userStatus |
      | 965745 | kbrenes  | Kevin     | Brenes   | kbrenes@testmail.com | pass123  | 12345678 | 1          |
    Then I should receive a 200 status code
    And the response should include the user details for "kbrenes"

  Scenario: Log in with valid credentials
    Given the pet store API is running
    When I send a GET request to "/user/login" with username "testUser" and password "testPass"
    Then the response headers should contain:
      | X-Rate-Limit   |
      | X-Expires-After|
