package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources"},
        glue = "StepDefinitions",
        monochrome = false,
        dryRun = false,
        tags = "@pet",
        plugin = {"pretty","html:target/cucumber-html-report",
        "json:target/cucumber.json","junit:target/cucumber.xml"}
)
public class APIRunner {
}
