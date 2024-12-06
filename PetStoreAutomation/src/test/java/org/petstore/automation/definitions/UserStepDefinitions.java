package org.petstore.automation.definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.petstore.automation.utils.AssertUtils;
import org.petstore.automation.utils.EnvConfig;
import org.petstore.automation.utils.ApiUtils;

import java.util.List;
import java.util.Map;

public class UserStepDefinitions {

    @When("I send a POST request to {string} with the following user details:")
    public void iSendAPostRequestToWithTheFollowingUserDetail(String endpoint, DataTable table) {
        // Convert DataTable to Map
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        // Get the first row of data
        Map<String, String> userDetails = data.get(0);

        // Build the User JSON Object
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", Integer.parseInt(userDetails.get("id")));
        requestBody.put("username", userDetails.get("username"));
        requestBody.put("firstName", userDetails.get("firstName"));
        requestBody.put("lastName", userDetails.get("lastName"));
        requestBody.put("email", userDetails.get("email"));
        requestBody.put("password", userDetails.get("password"));
        requestBody.put("phone", userDetails.get("phone"));
        requestBody.put("userStatus", Integer.parseInt(userDetails.get("userStatus")));

        // Send POST request
        ApiUtils.setResponse(ApiUtils.requestSpecification()
                .body(requestBody.toString())
                .post(endpoint));
    }

    @Then("the response should include the user details for {string}")
    public void theResponseShouldIncludeTheUserDetails(String username) {
        AssertUtils.assertResponseContains("username", username, ApiUtils.getResponse().asString());
    }

    @When("I send a GET request to {string} with username {string} and password {string}")
    public void iSendAGetRequestToWithUsernameAndPassword(String endpoint, String username, String password) {
        ApiUtils.setResponse(ApiUtils.requestSpecification()
                .queryParam("username", username)
                .queryParam("password", password)
                .get(endpoint));
    }

}