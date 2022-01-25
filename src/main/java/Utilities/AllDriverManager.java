package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AllDriverManager {

	private WebDriver driver;

	private WebDriver createLocalDriver(String browser) {
		WebDriver driver = null;
		if (browser.toLowerCase().contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			// chromeOptions.setExperimentalOption("debuggerAddress", "localhost:53177");
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			driver = new ChromeDriver(chromeOptions);

		} else if (browser.toLowerCase().contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			driver = new FirefoxDriver(firefoxOptions);
		}
		return driver;
	}

	private WebDriver createRemoteDriver() {
		throw new RuntimeException("Remote web driver is not yet implemented");
	}

	private WebDriver createDriver() {
		PropertiesFileReader propertiesFileReader = new PropertiesFileReader(FilePaths.CONFIG_FILEPATH);
		String browser = propertiesFileReader.properties.getProperty("browser");
		String environment = propertiesFileReader.properties.getProperty("environment");
		if (environment.toLowerCase().contains("local")) {
			driver = createLocalDriver(browser);

		} else if (browser.toLowerCase().contains("firefox")) {
			driver = createRemoteDriver();
		}
		return driver;
	}

	public WebDriver getDriver() {
		if (driver == null)
			driver = createDriver();
		return driver;
	}

	public void closeDriver() {
		driver.close();
		driver.quit();
	}

	public static void main(String[] args) {

		System.out.println(FilePaths.CONFIG_FILEPATH);

	}
}
