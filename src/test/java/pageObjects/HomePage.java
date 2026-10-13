package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(xpath = "//span[contains(normalize-space(), 'My Account')]")
    WebElement myaccountElement;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement registerElement;

    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement loginElement;

    public void clickmyaccout() {
        wait.until(
            ExpectedConditions.elementToBeClickable(myaccountElement)
        ).click();
    }

    public void registerclick() {
        wait.until(
            ExpectedConditions.elementToBeClickable(registerElement)
        ).click();
    }

    public void loginclick() {
        wait.until(
            ExpectedConditions.elementToBeClickable(loginElement)
        ).click(); // Corrected
    }
}