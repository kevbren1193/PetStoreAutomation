package org.petstore.automation.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.petstore.automation",
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber-reports.json"},
        monochrome = true,
        tags = "@all"
)public class TestRunner {
}
