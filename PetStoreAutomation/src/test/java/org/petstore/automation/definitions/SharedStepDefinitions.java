package org.petstore.automation.definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.petstore.automation.utils.ApiUtils;
import org.petstore.automation.utils.AssertUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Then("the response should include message {string}")
    public void theResponseShouldIncludeAnMessage(String expectedErrorMessage) {
        String responseBody = ApiUtils.getResponse().getBody().asString();
        AssertUtils.assertResponseContains("message", expectedErrorMessage, responseBody);
    }

    @Then("the response headers should contain:")
    public void theResponseHeadersShouldContain(DataTable dataTable) {
        // Get the response headers
        Response response = ApiUtils.getResponse();

        Map<String, String> headers = new HashMap<>();

        // Iterate through the list of headers and populate the map
        for (Header header : response.getHeaders()) {
            headers.put(header.getName(), header.getValue());
        }

        // Validate each expected header
        List<String> expectedHeaders = dataTable.asList(String.class);
        for (String headerName : expectedHeaders) {
            AssertUtils.assertEquals("The response header does not include the expected " + headerName + " header", true, headers.containsKey(headerName));
        }
    }

}

