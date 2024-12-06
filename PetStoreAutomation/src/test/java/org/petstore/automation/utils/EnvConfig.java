package org.petstore.automation.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Properties;

public class EnvConfig {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = EnvConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public static String getBaseUrl() {
        String baseUrl = properties.getProperty("base.url");
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalStateException("BASE_URL is not defined in application.properties");
        }
        return baseUrl;
    }
}
