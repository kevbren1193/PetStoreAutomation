Feature: User API Testing

  # Positive Scenarios
  Scenario: Create a new user with valid data
    Given the user endpoint is running in pet store API
    When I send a POST request to user endpoint "/user" with the following user details:
      | id     | username | firstName | lastName | email                | password | phone    | userStatus |
      | 965745 | kbrenes  | Kevin     | Brenes   | kbrenes@testmail.com | pass123  | 12345678 | 1          |
    Then I should receive a 200 status code from user endpoint
    And the response should include the user details for "kbrenes"
