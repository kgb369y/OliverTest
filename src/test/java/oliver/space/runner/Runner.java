package oliver.space.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@CucumberOptions(plugin = { "html:target/cucumber-html-report" }, glue = { "oliver.space" }, features = {
		"src/test/resources/features" }, tags = { "@EditCart" })
@RunWith(Cucumber.class)
public class Runner {

}
