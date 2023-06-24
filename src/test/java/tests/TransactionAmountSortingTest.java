package tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import resources.Base;

public class TransactionAmountSortingTest extends Base {

	public WebDriver driver;

	@Test(dataProvider = "getLoginData")
	public void AmountSortingTest(String username, String password) throws InterruptedException {
		
		Thread.sleep(3000);

		LoginPage loginPage = new LoginPage(driver);

		loginPage.usernameField().sendKeys(username);
		loginPage.passwordField().sendKeys(password);
		loginPage.loginButton().click();

		HomePage homePage = new HomePage(driver);

		Thread.sleep(3000);
		
		WebElement amountHeader = homePage.getTransactionsTable().findElement(By.id("amount"));
		amountHeader.click();
		
		Thread.sleep(3000);
		
		List<Double> transactionAmounts = homePage.getTransactionAmounts();
		
		// Check if the transaction amounts are sorted
		boolean isSorted = homePage.isSorted(transactionAmounts);
		if (isSorted) {
		    Assert.assertTrue(true, "Transaction amounts are sorted.");
		} else {
		    Assert.fail("Transaction amounts are not sorted.");
		}
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
		Object[][] data = { { "vikram", "123"}, { "ramu", "456"}};
		return data;
	}
}
