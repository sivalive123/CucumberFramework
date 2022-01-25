package Runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/CartTests.feature", glue = "StepDefinitions", plugin = {
		"pretty", "html:target/cucumber-reports/cucumber-pretty",
		"json:target/cucumber-reports/CucumberTestReport.json",
		"timeline:target/test-output-thread/" }, dryRun = false, monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	// @BeforeSuite
	public void beforeSuite() {
		System.out.println("================ BEFORE SUITE ================");

	}

	// @AfterSuite
	public void afterSuite() {
		System.out.println("================ AFTER SUITE ================");
	}
}
