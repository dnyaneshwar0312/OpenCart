package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginclass extends BasePage {

	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	public Loginclass(WebDriver driver) {
		super(driver);
	
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailElement;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement passwordElement;
	
	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement loginbtnElement;
	
	
	public void setemail(String mail) {
		
		emailElement.sendKeys(mail);
		
	}
	
	public void setpassw(String pass) {
		
		passwordElement.sendKeys(pass);
		
	}
	
	public void setloginbtn()
	{
		js.executeScript("arguments[0].click();", loginbtnElement);
	}
	
	

}
