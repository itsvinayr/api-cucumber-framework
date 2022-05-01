package cucumber.options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features",
        plugin = "json:target/jsonReports/cucumber-report.json",
        glue = "stepDefinitions")
public class TestNGRunner extends AbstractTestNGCucumberTests {
}
