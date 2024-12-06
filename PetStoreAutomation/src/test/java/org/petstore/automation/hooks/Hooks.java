package org.petstore.automation.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.time.LocalTime;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("[" + LocalTime.now() + "] Thread: " + Thread.currentThread().getName() +
                " - Starting scenario: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("[" + LocalTime.now() + "] Thread: " + Thread.currentThread().getName() +
                " - Finished scenario: " + scenario.getName());
    }
}
