package com.ecomtesting.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/member1/Member1.feature",
        glue = { "com.ecomtesting.stepdefinitions.member1", "com.ecomtesting.hooks" },
        plugin = {
                "pretty",
                "summary",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        monochrome = true)
public class MyntraRunnerTest {
}
