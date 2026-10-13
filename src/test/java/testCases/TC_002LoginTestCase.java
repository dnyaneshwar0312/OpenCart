package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.Loginclass;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002LoginTestCase extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void verifylogin() {

        logger.info("********** Starting TC_002_LoginTest **********");

        try {

            logger.info("Creating HomePage object");
            HomePage hPage = new HomePage(driver);

            logger.info("Clicking on My Account");
            hPage.clickmyaccout();

            logger.info("Clicking on Login");
            hPage.loginclick();

            logger.info("Creating LoginPage object");
            Loginclass loginclass = new Loginclass(driver);

            logger.info("Entering email");
            loginclass.setemail(pro.getProperty("email"));

            logger.info("Entering password");
            loginclass.setpassw(pro.getProperty("password"));

            logger.info("Clicking Login button");
            loginclass.setloginbtn();

            logger.info("Creating MyAccountPage object");
            MyAccountPage macc = new MyAccountPage(driver);

            logger.info("Checking whether My Account page exists");
            boolean targetPage = macc.ismyaccountpageexist();

            logger.info("My Account page exists: {}", targetPage);

            Assert.assertTrue(targetPage, "Login failed - My Account page not displayed");

            logger.info("Login Test PASSED");

        } catch (Exception e) {

            logger.error("Login Test FAILED", e);

            Assert.fail("Login Test Failed: " + e.getMessage());
        }

        logger.info("********** Finished TC_002_LoginTest **********");
    }
}