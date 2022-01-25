package StepDefinitions;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import Utilities.FilePaths;
import Utilities.PropertiesFileReader;
import Utilities.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	TestContext testContext;
	LoginPage loginPage;
	HomePage homePage;

	public LoginSteps(TestContext context) {
		testContext = context;
		homePage = new HomePage(testContext.getDriverManager().getDriver());
		loginPage = new LoginPage(testContext.getDriverManager().getDriver());
	}

	@Then("Home page of bhinneka.com")
	public void test() {

		PropertiesFileReader configFileReader = new PropertiesFileReader(FilePaths.CONFIG_FILEPATH);
		String url = configFileReader.properties.getProperty("url");
		testContext.getDriverManager().getDriver().get(url);

	}

	@Then("Login page is displayed")
	public void loginPageIsDisplayed() {
		Assert.assertTrue(loginPage.emailLoginPageIsDisplayed());

		String actualLoginHeaderTitle = loginPage.getLoginHeaderTitle();
		Assert.assertEquals("Silakan masuk ke akun Anda", actualLoginHeaderTitle);

		String actualRegisterQuestionText = loginPage.getQuestionRegisterText();
		Assert.assertEquals("Belum punya akun Bhinneka? Daftar", actualRegisterQuestionText);
	}

	@When("Input {string} as email, {string} as password, {string} as account type")
	public void inputAsEmailAsPasswordAsAccountType(String email, String password, String account) {
		System.out.println("---" + email);
		System.out.println("---" + password);
		System.out.println("---" + account);
	}

	@When("Input credentials to login without headers")
	public void inputCredentialsToLoginWithoutHeaders(DataTable dataTable) {
		List<String> dataRow = dataTable.row(0);
		String email = dataRow.get(0);

		System.out.println("row index 0 --- " + email);
		loginPage.fillEmailData(email);
		System.out.println("row index 1 --- " + dataRow.get(1));
		System.out.println("row index 2 --- " + dataRow.get(2));
	}

	@When("Input credentials to login with headers table")
	public void inputCredentialsToLoginWithHeadersTable(DataTable dataTable) {
		List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);

		// Use for...loop if you have multiple data table
		for (Map<String, String> dataMap : dataRow) {
			String email = dataMap.get("Email");
			loginPage.fillEmailData(email);
			System.out.println("row header email " + email);
			System.out.println("row index 1 --- " + dataMap.get("Password"));
			System.out.println("row index 2 --- " + dataMap.get("Account Type"));
		}
	}

	@When("Click selanjutnya button")
	public void clickSelanjutnyaButton() {
		loginPage.clickSelanjutnyaButton();
	}

}
