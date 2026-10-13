
package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	protected WebDriver driver;
	public Logger logger;
	public Properties pro;

	
	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "browser", "os" })
	public void setup(String br, String os) throws IOException {

		try (FileReader file = new FileReader("./src/test/resources/config.properties")) {
			pro = new Properties();
			pro.load(file);
		}

		logger = LogManager.getLogger(this.getClass());

		String executionEnv = pro.getProperty("execution_env");

		if (executionEnv == null) {
			throw new IllegalArgumentException("execution_env is missing in config.properties");
		}

		if (executionEnv.equalsIgnoreCase("remote")) {

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new RemoteWebDriver(new URL("http://localhost:4444"), new ChromeOptions());
				break;

			case "firefox":
				driver = new RemoteWebDriver(new URL("http://localhost:4444"), new FirefoxOptions());
				break;

			case "edge":
				driver = new RemoteWebDriver(new URL("http://localhost:4444"), new EdgeOptions());
				break;

			default:
				throw new IllegalArgumentException("Unsupported remote browser: " + br);
			}

		} else if (executionEnv.equalsIgnoreCase("local")) {

			switch (br.toLowerCase()) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				driver = new ChromeDriver(chromeOptions);
				break;

			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				driver = new EdgeDriver(edgeOptions);
				break;

			default:
				throw new IllegalArgumentException("Unsupported local browser: " + br);
			}

		} else {
			throw new IllegalArgumentException("execution_env must be local or remote");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		String appUrl = pro.getProperty("appurl");
		logger.info("Opening application: {}", appUrl);
		driver.get(appUrl);
	}

	public String randomstring() {
		return RandomStringUtils.randomAlphabetic(5);
	}

	public String randomAlphanumeric() {
		return RandomStringUtils.randomNumeric(4) + "@" + RandomStringUtils.randomAlphabetic(4);
	}

	public String captureScreen(String tname) throws IOException {

		if (driver == null) {
			logger.error("Cannot capture screenshot: WebDriver is null");
			return null;
		}

		if (!(driver instanceof TakesScreenshot)) {
			logger.error("WebDriver does not support screenshots");
			return null;
		}

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		File screenshotDir = new File(System.getProperty("user.dir"), "screenshots");

		if (!screenshotDir.exists() && !screenshotDir.mkdirs()) {
			throw new IOException("Could not create screenshots directory");
		}

		File targetFile = new File(screenshotDir, tname + "_" + timeStamp + ".png");

		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

		logger.info("Screenshot saved: {}", targetFile.getAbsolutePath());

		return targetFile.getAbsolutePath();
	}

	@AfterClass(alwaysRun = true, groups = { "Sanity", "Regression", "Master" })
	public void tearDown() {
		if (driver != null) {
			try {
				logger.info("Terminating browser session...");
				driver.quit();
			} catch (Exception e) {
				logger.error("Failed to terminate browser session", e);
			} finally {
				driver = null;
			}
		}
	}

}
