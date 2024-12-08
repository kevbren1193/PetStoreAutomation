@userAPI
Feature: User API Testing

  # Positive Scenarios
  Scenario: Create a new user with valid data
    Given the pet store API is running
    When I send a POST request to "/user" with the following user details:
      | id     | username | firstName | lastName | email                | password | phone    | userStatus |
      | 965745 | kbrenes  | Kevin     | Brenes   | kbrenes@testmail.com | pass123  | 12345678 | 1          |
    Then I should receive a 200 status code
    And the response schema should match schema "user-schema.json"
    And the response should include the user details for "kbrenes"

  Scenario: Log in with valid credentials
    Given the pet store API is running
    When I send a GET request to "/user/login" with username "testUser" and password "testPass"
    Then I should receive a 200 status code
    And the response headers should contain:
      | X-Rate-Limit    |
      | X-Expires-After |

  Scenario: Retrieve details of a existent user
    Given the pet store API is running
    When I send a GET request to "/user/{username}" with username "theUser"
    And the response schema should match schema "user-schema.json"
    And the response should include the user details for "theUser"

  # Negative Scenarios
  Scenario: Retrieve details of a non-existent user
    Given the pet store API is running
    When I send a GET request to "/user/{username}" with username "invalid_user"
    Then I should receive a 404 status code
    And the response should include message "User not found"


  Scenario: Log in with invalid credentials
    Given the pet store API is running
    When I send a GET request to "/user/login" with username "invalid_user" and password "invalid_pass"
    Then I should receive a 400 status code
    And the response should include an error message "Invalid username/password supplied"