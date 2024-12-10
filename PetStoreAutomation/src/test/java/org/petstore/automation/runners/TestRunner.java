package org.petstore.automation.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.petstore.automation",
        plugin = {"pretty", "html:target/reports/cucumber-reports.html", "json:target/reports/cucumber-reports.json"},
        monochrome = true
)public class TestRunner {
}
