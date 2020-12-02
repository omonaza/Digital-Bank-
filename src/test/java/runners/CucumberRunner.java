package runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features"},
        glue = {"step_definitions"},
      //  plugin = {"json:target/cucumber.json","pretty","html:target/reports"},
        plugin = {"json:target/cucumber.json"},
        dryRun = false,
       tags = {"@search"}


)

public class CucumberRunner {
}
