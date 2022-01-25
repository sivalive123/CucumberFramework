package StepDefinitions;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import Utilities.TestContext;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartSteps {

    TestContext testContext;
    HomePage homePage;
    LoginPage loginPage;

    public CartSteps(TestContext context) {
        testContext = context;
        homePage = new HomePage(testContext.getDriverManager().getDriver());
		loginPage = new LoginPage(testContext.getDriverManager().getDriver());
    }

    @When("Go to cart page without authorizations")
    public void goToCartPageWithoutAuthorizations() {
        homePage.clickCartButton();
        Assert.assertTrue(loginPage.emailLoginPageIsDisplayed());
    }
}
