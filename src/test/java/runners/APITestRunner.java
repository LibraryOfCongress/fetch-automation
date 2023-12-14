package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-html-report",
                "json:target/cucumber.json"},
        features="src/test/resources/apiFeatures",
        glue="api_automation.stepDefinitions",
        tags="@run",
        dryRun = false,
        monochrome = true
)

public class APITestRunner {
}
