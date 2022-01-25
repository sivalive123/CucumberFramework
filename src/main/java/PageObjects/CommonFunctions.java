package PageObjects;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CommonFunctions {

	private final WebDriver driver;

	public CommonFunctions(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 15), this);
	}

	public void closeFirstTab() {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		driver.close();
		driver.switchTo().window(tabs.get(1));
	}

}
