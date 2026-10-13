package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.Loginclass;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003LoginDataDrivenTest extends BaseClass {

	@Test(priority = 3, dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups = "Datadriventest")
	public void verifyloginDDT(String eamil, String pws, String expe) throws InterruptedException {

		try {
			
			HomePage hPage = new HomePage(driver);
			hPage.clickmyaccout();
			hPage.loginclick();

			Loginclass loginclass = new Loginclass(driver);
			loginclass.setemail(eamil);
			loginclass.setpassw(pws);
			loginclass.setloginbtn();

			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.ismyaccountpageexist();

			/*
			 * Data is valid - login success - test pass - logout login failed - test fail
			 * 
			 * Data is invalid - login success - test fail - logout login failed - test pass
			 */

			if (expe.equalsIgnoreCase("Valid")) {

				if (targetPage == true) {

					macc.logout();
					Assert.assertTrue(true);

				} else {
					Assert.assertTrue(false);
				}

			}
			if (expe.equalsIgnoreCase("Invalid")) {

				if (targetPage == true) {

					macc.logout();
					Assert.assertTrue(false);

				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
		    logger.error("Login test failed", e);
		    Assert.fail("Login test failed: " + e.getMessage());
		}
		Thread.sleep(1000);

	}

}
