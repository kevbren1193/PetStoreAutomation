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

    @Then("I should receive a {int} status code")
    public void iShouldReceiveAStatusCode(int statusCode) {
        AssertUtils.assertStatusCodeEqual(statusCode, ApiUtils.getResponse().getStatusCode());
    }

    @When("I send a GET request to {string} with status {string}")
    public void iSendAGetRequestToWithStatus(String endpoint, String status) {
        ApiUtils.setResponse((ApiUtils.requestSpecification()
                .queryParam("status", status)
                .get(endpoint)));
    }
}

