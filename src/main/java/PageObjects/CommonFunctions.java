package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunctions {

	static WebDriver driver;

	public CommonFunctions(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 15), this);
	}

	String convertElementToString(WebElement element) {

		String elementInString = element.toString();
		return elementInString;
	}

	public void closeFirstTab() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		driver.close();
		driver.switchTo().window(tabs.get(1));
	}

	public void highlightElementLocation(WebElement webElement) {
		String jsSyyle = "'3px solid red'";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			js.executeScript("arguments[0].style.border=" + jsSyyle, webElement);
		} catch (JavascriptException e) {
			System.out.println("Java scription Exception " + e.getMessage().toString());
		}
	}

	protected void sleepForSeconds(int seconds) {
		try {
			System.out.println("Kept Sleep for " + seconds + " seconds");
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void clickOnElement(WebElement element) {
		sleepForSeconds(1);
		highlightElementLocation(element);
		element.click();
		sleepForSeconds(1);
	}

	public void clickOnElementUsingJavaScript(WebElement element) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	protected void waitForElement(int maxSeconds, WebElement element) {

		for (int i = 0; i < maxSeconds; i++) {
			boolean elementDisplayed = checkElementIsDisplayed(element);
			boolean elementEnabled = checkElementIsEnabled((element));
			if (elementDisplayed && elementEnabled) {
				break;
			} else {
				try {
					int a = i + 1;
					System.out.println("waited for " + a + " seconds so far..");
					Thread.sleep(1000);
				} catch (InterruptedException | org.openqa.selenium.StaleElementReferenceException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected boolean checkElementIsDisplayed(WebElement element) {

		boolean displayed = true;
		try {
			element.isDisplayed();
			System.out.println("Element is displayed ");
			highlightElementLocation(element);
		} catch (NoSuchElementException e) {
			System.out.println("Element is not displayed");
			displayed = false;
		}
		return displayed;
	}

	protected boolean checkElementIsEnabled(WebElement element) {

		boolean enabled = true;
		try {
			element.isEnabled();
			highlightElementLocation(element);
			System.out.println("Element is enabled ");
		} catch (NoSuchElementException | IndexOutOfBoundsException e) {
			System.out.println("Element is not enabled");
			enabled = false;
		}
		return enabled;
	}

	public static void switchToWindow(String targetTitle) {
		String origin = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals(targetTitle)) {
				return;
			}
		}
		driver.switchTo().window(origin);
	}

	public static void hover(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	protected String getAttribute(WebElement element, String attribute) {
		highlightElementLocation(element);
		String value = element.getAttribute(attribute);
		System.out.println(attribute + " Atrribute value of element " + " is " + value);
		return value;
	}

	protected void setValue(WebElement element, String value) {
		highlightElementLocation(element);
		element.sendKeys(value);
		System.out.println(value + " has been entered in " + convertElementToString(element));
	}

	protected void setValueUsingJS(WebElement element, String value) {
		highlightElementLocation(element);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='" + value + "';", element);
		System.out.println(value + " has been entered in " + convertElementToString(element));
	}

	protected void clearField(WebElement element) {
		highlightElementLocation(element);
		element.clear();
		System.out.println("Cleared the vaules in ");
	}

	public void openNewTab() {
		((JavascriptExecutor) driver).executeScript("window.open()");
	}

	public void switchToTab1() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}

	public void switchToTab2() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}

	public void refreshBroswer() {
		driver.navigate().refresh();
		System.out.println("Refreshed the broswer");

	}

	protected WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
		} catch (org.openqa.selenium.NoSuchElementException e) {
		}

		return element;
	}

	public static void selectDropDownItemByValue(WebElement elememt, String value) {
		Select dropDown = new Select(elememt);
		dropDown.selectByValue(value);
	}

	public static void selectDropDownItemByName(WebElement elememt, String visibletext) {
		Select dropDown = new Select(elememt);
		dropDown.selectByVisibleText(visibletext);
	}

	public static void selectDropDownItemByIndex(WebElement elememt, int index) {
		Select dropDown = new Select(elememt);
		dropDown.selectByIndex(index);
	}

}
