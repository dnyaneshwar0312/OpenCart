package pageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage {

	JavascriptExecutor js = (JavascriptExecutor) driver;

	public AccountRegisterPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement firstnameElement;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement lastnamElement;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailElement;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement passwordElement;

	@FindBy(xpath = "//input[@id='input-newsletter']")
	WebElement newslaterElement;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement policyElement;

	@FindBy(xpath = "//button[normalize-space()='Continue']")
	WebElement continubtnElement;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement webElement;

	public void setfirstname(String name) {

		firstnameElement.sendKeys(name);

	}

	public void setlastname(String lastname) {

		lastnamElement.sendKeys(lastname);

	}

	public void setemail(String emails) {

		emailElement.sendKeys(emails);

	}

	public void setpassword(String paswt) {

		passwordElement.sendKeys(paswt);

	}


	public void setnewslatter() {

		js.executeScript("arguments[0].click();", newslaterElement);

	}

	public void setprivacy() {

		js.executeScript("arguments[0].click();", policyElement);

	}

	public void setcontinuebtn() {

		js.executeScript("arguments[0].click();", continubtnElement);

	}

	public String setcomformsg() {

		try {

			return webElement.getText();

		} catch (Exception e) {
			return e.getMessage();
		}

	}

}