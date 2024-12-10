package org.petstore.automation.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.Map;
import java.util.Set;

public class AssertUtils {

    public static void assertEquals(String message, Object expected, Object actual) {
        Assert.assertEquals(message, expected, actual);
    }

    public static void assertStatusCodeEqual(int expected, int actual) {
        Assert.assertEquals("Unexpected status code returned,", expected, actual);
    }

    public static void assertContains(String message, String expectedSubstring, String actual) {
        if (!StringUtils.containsIgnoreCase(actual, expectedSubstring)) {
            throw new AssertionError(message + ", expected: <[" + expectedSubstring + "]> not found in: " + actual);
        }
    }

    public static void assertResponseContains(String field, String expectedSubstring, String actual) {
        assertContains("The response does not include the expected " + field, expectedSubstring, actual);
    }


    public static void assertResponseBodyContains(Response response, String field, Object expectedValue) {
            Object actualValue = response.jsonPath().get(field);
            Assert.assertNotNull("Field '" + field + "' is missing in the response body.", actualValue);
            Assert.assertEquals(
                    "Value for field '" + field + "' does not match.", expectedValue, actualValue
            );
    }

    public static void validateSchema(Response response, String schema) {
        try {
            response.then()
                    .assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(String.format("schemas/%s", schema)));
        } catch (AssertionError e){
            throw new AssertionError(e.getMessage());
        }
    }

}
