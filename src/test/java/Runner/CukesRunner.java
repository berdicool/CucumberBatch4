package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src\\test\\resources\\com.Etsy\\ScenarioOutlineEtsy.feature"},
        glue = "StepDefinitions",
        monochrome = false,
        dryRun = false,
        tags = "@etsyOutline"
//        dryRun = true shows unimplemeted steps
)
public class CukesRunner { }
