package StepDefinitions;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;

import Utilities.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	TestContext testContext;
	WebDriver webDriver;

	public Hooks(TestContext context) {
		testContext = context;
	}

	@Before
	public void setUp() {
		webDriver = testContext.getDriverManager().getDriver();
		Capabilities cap = ((RemoteWebDriver) webDriver).getCapabilities();
		String address = cap.getCapability("goog:chromeOptions").toString();
		System.out.println("Debugg" + address);
	}

	@After
	public void tearDown(Scenario scenario) {

		if (scenario.isFailed()) {
			try {
				byte[] screenshot = ((TakesScreenshot) testContext.getDriverManager().getDriver())
						.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "screenshot");
			} catch (WebDriverException noSupportScreenshot) {
				System.err.println(noSupportScreenshot.getMessage());
			}
		}
		testContext.getDriverManager().closeDriver();
	}
}
