package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/testFeatures",
        glue = "stepDefinitions",
        tags = {"@positive, @negative"},
        dryRun = false
)

public class Runner {
}
