package org.petstore.automation.definitions;

import com.google.gson.Gson;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.petstore.automation.utils.ApiUtils;
import org.petstore.automation.utils.AssertUtils;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreStepDefinitions {



    @When("I send a POST request to {string} with the following order details:")
    public void iSendAPostRequestToWithTheFollowingDetails(String endpoint, DataTable table) {
        // Convert DataTable to List
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        //Get first row of data
        Map<String, String> orderDetails = data.get(0);

        // Build payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", Integer.parseInt(orderDetails.get("id")));
        payload.put("petId", Integer.parseInt(orderDetails.get("petId")));
        payload.put("quantity", Integer.parseInt(orderDetails.get("quantity")));
        payload.put("shipDate", orderDetails.get("shipDate"));
        payload.put("status", orderDetails.get("status"));
        payload.put("complete", Boolean.parseBoolean(orderDetails.get("complete")));

        // Convert payload to JSON string
        Gson gson = new Gson();
        String jsonPayload = gson.toJson(payload);


        // Send POST request
        ApiUtils.setResponse(ApiUtils.requestSpecification()
                .body(jsonPayload)
                .post(endpoint));
    }

    @Then("the response should include the details for order {int}")
    public void theResponseShouldIncludeTheOrderDetails(int id) {
        AssertUtils.assertResponseBodyContains(ApiUtils.getResponse(), "id", id);
    }


}