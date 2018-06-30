package cucumberTest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(/**
		 * 
		 * @author Administrator
		 *
		 */
		features = "Feature"
		,glue = {"stepDefinition"}
//		,junit = {"html:target/cucumber-html-report"}
				)
	

public class TestRunner {

}
