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

    private static final ThreadLocal<Response> response = new ThreadLocal<>();

    @Given("the user endpoint is running in pet store API")
    public void thePetStoreApiIsRunning() {
        RestAssured.baseURI = EnvConfig.getBaseUrl();
    }

    @When("I send a POST request to user endpoint {string} with the following user details:")
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
        response.set(ApiUtils.requestSpecification()
                .body(requestBody.toString())
                .post(endpoint));
    }

    @Then("the response should include the user details for {string}")
    public void theResponseShouldIncludeTheUserDetails(String username) {
        AssertUtils.assertResponseContains("username", username, response.get().asString());
    }

    @Then("I should receive a {int} status code from user endpoint")
    public void iShouldReceiveAStatusCode(int statusCode) {
        AssertUtils.assertStatusCodeEqual(statusCode, response.get().getStatusCode());
    }

}