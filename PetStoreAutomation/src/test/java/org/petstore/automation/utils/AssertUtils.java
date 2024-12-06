package org.petstore.automation.utils;

public class AssertUtils {

    public static void assertEquals(String message, Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message + " | Expected: " + expected + ", Actual: " + actual);
        }
    }

    public static void assertStatusCodeEqual(int expected, int actual) {
        assertEquals("Unexpected status code returned", expected, actual);
    }

    public static void assertContains(String message, String expectedSubstring, String actual) {
        if (!actual.contains(expectedSubstring)) {
            throw new AssertionError(message + " | Expected : " + expectedSubstring + " not found in: " + actual);
        }
    }

    public static void assertResponseContains(String field, String expectedSubstring, String actual) {
        assertContains("The response does not include the expected " + field, expectedSubstring, actual);

    }
}
