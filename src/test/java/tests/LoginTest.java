package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {

	public WebDriver driver;

	@Test(dataProvider = "getLoginData")
	public void Login(String username, String password)
			throws IOException, InterruptedException {

		Thread.sleep(3000);

		LoginPage loginPage = new LoginPage(driver);

		loginPage.usernameField().sendKeys(username);
		loginPage.passwordField().sendKeys(password);
		loginPage.loginButton().click();

		HomePage homePage = new HomePage(driver);

		Assert.assertTrue(homePage.loggedUserName().isDisplayed());
	}

	@BeforeMethod
	public void openApplication() throws IOException {
		driver = initializeBrowser();
		driver.get(prop.getProperty("url"));
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = { { "vikram", "123" }, { "", "Abc@" },{ "xyz", "" },{"",""} };
		return data;
	}
}
