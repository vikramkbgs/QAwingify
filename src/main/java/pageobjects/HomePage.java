package pageobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "logged-user-name")
	WebElement loggedUserName;

	public WebElement loggedUserName() {
		return loggedUserName;
	}
	
	@FindBy(id = "transactionsTable")
    WebElement transactionsTable;
	
	public WebElement getTransactionsTable() {
        return transactionsTable;
    }

	public List<Double> getTransactionAmounts() {
	    List<WebElement> amountCells = transactionsTable.findElements(By.xpath("//tbody/tr/td[last()]"));
	    List<Double> amounts = new ArrayList<>();

	    for (WebElement cell : amountCells) {
	        String amountText = cell.getText();
	        double amountValue;
	        
	        if (amountText.contains("-")) {
	            amountValue = -Double.parseDouble(amountText.replaceAll("[^0-9.]", ""));
	        } else {
	            amountValue = Double.parseDouble(amountText.replaceAll("[^0-9.]", ""));
	        }
	        
	        amounts.add(amountValue);
	    }

	    return amounts;
	}
	
	public boolean isSorted(List<Double> transactionAmounts) {
        List<Double> sortedValues = new ArrayList<>(transactionAmounts);
        Collections.sort(sortedValues);

        return sortedValues.equals(transactionAmounts);
    }
}
