package org.petstore.automation.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.petstore.automation.utils.ApiUtils;
import org.petstore.automation.utils.AssertUtils;

public class SharedStepDefinitions {

    @Given("the pet store API is running")
    public void thePetStoreApiIsRunning() {
        ApiUtils.ensureApiIsRunning();
    }

    @When("I send a GET request to {string} with ID {string}")
    public void iSendAGetRequestToWithID(String endpoint, String id) {
        ApiUtils.setResponse(ApiUtils.requestSpecification()
                .pathParam("id", id)
                .get(endpoint));
    }

    @Then("I should receive a {int} status code")
    public void iShouldReceiveAStatusCode(int statusCode) {
        AssertUtils.assertStatusCodeEqual(statusCode, ApiUtils.getResponse().getStatusCode());
    }

    @Then("the response should include an error message {string}")
    public void theResponseShouldIncludeAnErrorMessage(String expectedErrorMessage) {
        String responseBody = ApiUtils.getResponse().getBody().asString();
        AssertUtils.assertResponseContains("error message", expectedErrorMessage, responseBody);
    }

}

