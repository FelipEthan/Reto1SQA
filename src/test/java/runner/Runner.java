package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "summary",
                "html:target/report.html",
                "json:target/report.json",
                "junit:target/.xml",
                "timeline:target/cucumber-timeline",
        },
        features = {"src/test/resources/features"},
        glue = "steps"
)
public class Runner {

}