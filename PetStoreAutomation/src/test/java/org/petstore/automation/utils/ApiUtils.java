package org.petstore.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiUtils {

    private static final ThreadLocal<Response> response = new ThreadLocal<>();
    public static void setResponse(Response res) {
        response.set(res);
    }

    public static Response getResponse() {
        return response.get();
    }

    public static void ensureApiIsRunning() {
        RestAssured.baseURI = EnvConfig.getBaseUrl();
        System.out.println("Pet store API base URL: " + RestAssured.baseURI);
    }

    public static RequestSpecification requestSpecification() {
        return RestAssured.given()
                .header("Content-Type", "application/json");
    }
}
