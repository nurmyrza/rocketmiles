package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumberHTML-report",
                "json:target/cucumber.json"},
        features = "src/test/resources/testFeatures",
        glue = "stepDefinitions",
        tags = {"@search"},
        dryRun = false
)

public class Runner {
}
