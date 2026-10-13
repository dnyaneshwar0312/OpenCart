
package testCases;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void accountregistrationtest() {

        logger.info("===== Account Registration Test Started =====");

        try {
            logger.info("Creating HomePage object");
            HomePage hP = new HomePage(driver);

            logger.info("Clicking on My Account");
            hP.clickmyaccout();

            logger.info("Clicking on Register");
            hP.registerclick();

            logger.info("Creating AccountRegisterPage object");
            AccountRegisterPage ar = new AccountRegisterPage(driver);

            // Debug information for Selenium Grid
            logger.info("Current URL: {}", driver.getCurrentUrl());
            logger.info("Page title: {}", driver.getTitle());

            logger.info("First Name element count: {}",
                    driver.findElements(By.id("input-firstname")).size());

            logger.info("Page source contains input-firstname: {}",
                    driver.getPageSource().contains("input-firstname"));

            String passwordString = randomAlphanumeric();
            String firstName = randomstring().toUpperCase();
            String lastName = randomstring().toUpperCase();
            String email = randomstring() + "@gmail.com";

            logger.info("Entering First Name: {}", firstName);
            ar.setfirstname(firstName);

            logger.info("Entering Last Name: {}", lastName);
            ar.setlastname(lastName);

            logger.info("Entering Email: {}", email);
            ar.setemail(email);

            logger.info("Entering Password");
            ar.setpassword(passwordString);

            logger.info("Selecting Newsletter option");
            ar.setnewslatter();

            logger.info("Accepting Privacy Policy");
            ar.setprivacy();

            logger.info("Clicking Continue button");
            ar.setcontinuebtn();

            logger.info("Getting account creation confirmation message");
            String confirmationMessage = ar.setcomformsg();

            logger.info("Confirmation message received: {}",
                    confirmationMessage);

            assertEquals(
                    confirmationMessage,
                    "Your Account Has Been Created!",
                    "Account registration confirmation did not match"
            );

            logger.info("Account Registration Test PASSED");

        } catch (RuntimeException | AssertionError e) {
            logger.error("Account Registration Test FAILED", e);
            throw e;
        }

        logger.info("===== Account Registration Test Completed =====");
    }
}
