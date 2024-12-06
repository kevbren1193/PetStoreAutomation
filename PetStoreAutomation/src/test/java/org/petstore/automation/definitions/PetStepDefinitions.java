package org.petstore.automation.definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.petstore.automation.utils.AssertUtils;
import org.petstore.automation.utils.EnvConfig;
import org.petstore.automation.utils.ApiUtils;

import java.util.List;
import java.util.Map;

public class PetStepDefinitions {

    @When("I send a POST request to pet endpoint {string} with the following details:")
    public void iSendAPostRequestToWithTheFollowingDetails(String endpoint, DataTable table) {
        // Convert DataTable to List
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        //Get first row of data
        Map<String, String> petDetails = data.get(0);

        // Build Category
        JSONObject category = new JSONObject();
        category.put("id", Integer.parseInt(petDetails.get("category.id")));
        category.put("name", petDetails.get("category.name"));

        // Build Tags
        JSONArray tags = new JSONArray();
        JSONObject tag = new JSONObject();
        tag.put("id", Integer.parseInt(petDetails.get("tags.id")));
        tag.put("name", petDetails.get("tags.name"));
        tags.put(tag);

        // Build PhotoUrls
        JSONArray photoUrls = new JSONArray();
        for(String url: petDetails.get("photoUrls").split(",")){
            photoUrls.put(url);
        }

        // Build Pet Object
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", Integer.parseInt(petDetails.get("id")));
        requestBody.put("name", petDetails.get("name"));
        requestBody.put("category", category);
        requestBody.put("photoUrls", photoUrls);
        requestBody.put("tags", tags);
        requestBody.put("status", petDetails.get("status"));

        ApiUtils.setResponse(ApiUtils.requestSpecification()
                .body(requestBody.toString())
                .post(endpoint));
    }


    @Then("the response should include the pet details for {string}")
    public void theResponseShouldIncludeThePetDetails(String petName) {
       AssertUtils.assertResponseContains("pet name", petName, ApiUtils.getResponse().asString());
    }

    @When("I send a GET request to pet endpoint {string} with status {string}")
    public void iSendAGetRequestToWithStatus(String endpoint, String status) {
        ApiUtils.setResponse(ApiUtils.requestSpecification()
                .queryParam("status", status)
                .get(endpoint));
    }

    @Then("the response should include pets with the status {string}")
    public void theResponseShouldIncludePetsWithTheStatus(String expectedStatus) {
        String responseBody = ApiUtils.getResponse().getBody().asString();
        JSONArray pets = new JSONArray(responseBody);

        // Validate each pet in the response
        for (int i = 0; i < pets.length(); i++) {
            JSONObject pet = pets.getJSONObject(i);
            String actualStatus = pet.getString("status");
            AssertUtils.assertEquals(
                    "Pet status does not match the expected status",
                    expectedStatus,
                    actualStatus
            );
        }
    }

    @When("I send a POST request to pet endpoint {string} with the following incomplete details:")
    public void iSendAPostRequestToWithTheFollowingIncompleteDetails(String endpoint, DataTable table) {
        // Convert DataTable to List
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        // Get the first row of data
        Map<String, String> petDetails = data.get(0);

        // Build JSON Object
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", Integer.parseInt(petDetails.get("id")));
        requestBody.put("status", petDetails.get("status"));

        // Send POST request
        ApiUtils.setResponse(ApiUtils.requestSpecification()
                .body(requestBody.toString())
                .post(endpoint));
    }


}