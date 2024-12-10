package org.petstore.automation.definitions;

import com.google.gson.Gson;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStepDefinitions {



    @When("I send a POST request to {string} with the following user details:")
    public void iSendAPostRequestToWithTheFollowingUserDetail(String endpoint, DataTable table) {
        // Convert DataTable to Map
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        // Get the first row of data
        Map<String, String> userDetails = data.get(0);

        // Build payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", Integer.parseInt(userDetails.get("id")));
        payload.put("username", userDetails.get("username"));
        payload.put("firstName", userDetails.get("firstName"));
        payload.put("lastName", userDetails.get("lastName"));
        payload.put("email", userDetails.get("email"));
        payload.put("password", userDetails.get("password"));
        payload.put("phone", userDetails.get("phone"));
        payload.put("userStatus", Integer.parseInt(userDetails.get("userStatus")));

        // Convert payload to JSON string
        Gson gson = new Gson();
        String jsonPayload = gson.toJson(payload);

        // Send POST request
        ApiUtils.setResponse(ApiUtils.requestSpecification()
                .body(jsonPayload.toString())
                .post(endpoint));
    }

    @Then("the response should include the user details for {string}")
    public void theResponseShouldIncludeTheUserDetails(String username) {
        AssertUtils.assertResponseBodyContains(ApiUtils.getResponse(), "username", username);
    }

    @When("I send a GET request to {string} with username {string} and password {string}")
    public void iSendAGetRequestToWithUsernameAndPassword(String endpoint, String username, String password) {
        ApiUtils.setResponse(ApiUtils.requestSpecification()
                .queryParam("username", username)
                .queryParam("password", password)
                .get(endpoint));
    }

    @When("I send a GET request to {string} with username {string}")
    public void iSendAGetRequestToWithUsername(String endpoint, String username) {
        ApiUtils.setResponse(ApiUtils.requestSpecification()
                .pathParam("username", username)
                .get(endpoint));
    }
}