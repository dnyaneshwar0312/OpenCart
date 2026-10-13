package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage {

	WebDriverWait wait;

	public MyAccountPage(WebDriver driver) {
		super(driver);

		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@FindBy(xpath = "//h1[normalize-space()='My Account']")
	WebElement msglElement;

	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logoutElement;

	public boolean ismyaccountpageexist() {
		try {
			return msglElement.isDisplayed();

		} catch (Exception e) {
			return false;
		}

	}

	public void logout() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(logoutElement));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutElement);
	}

}
