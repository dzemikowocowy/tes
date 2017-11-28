package cucumber_runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber/cucumber-html-report",
                "json:target/cucumber/cucumber-json-report.json"
        },
        monochrome=true,
        // tags = "@google_search",
        features = "src/test/resources/cucumber/features",
        // features = "src/test/resources/cucumber/features",
        glue = {
                "stepdefs.sports",
                "stepdefs.shared"
        })
@RunWith(Cucumber.class)
public class ScenarioOutlineRunnerTest {
}
