package com.cipl.webpages;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.cipl.webpages.Reporter.MessageType;

public class TestBase {
	
	public static WebDriver driver;

	public static WebDriverWait wait;

	public static WebDriver getDriver() {
		if (driver != null) {
			return driver;
		} else {
			SetDriver();
			return driver;
		}
	}

	private static void SetDriver() {

		String strBrowserType = getProperty("browser");

		switch (strBrowserType) {
		case "chrome":
			driver = initChromeDriver();
			break;

		case "firefox":
			driver = initFirefoxDriver();
			break;
		
		case "IE":
			driver = initFirefoxDriver();
			break;
			
		default:
			Reporter.log(
					"Default browser type is not provided in base.properties so that by choice chrome driver is opening...");
			driver = initChromeDriver();
			break;
		}

	}

	public static WebDriver initFirefoxDriver() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(getProperty("BASE_URL"));
		pause(3000);
		return driver;
	}

	public static WebDriver initChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "resources\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(getProperty("BASE_URL"));
		pause(3000);
		return driver;
	}

	public static String getProperty(String strKey) {
		try {
			InputStream input;

			input = new FileInputStream(new File("resources\\base.properties"));
			Properties property = new Properties();

			property.load(input);

			return property.getProperty(strKey);

		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	public static void pause(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void WaitforPresent(WebElement Element) {
		int timeout = Integer.parseInt(getProperty("timeout"));
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
		wait.until(ExpectedConditions.visibilityOf(Element));
	}

	public static void VerifyPresent(WebElement element, String strElementName) {
		if (element.isDisplayed() == true) {
			Reporter.log(
					"Expected : " + strElementName + " should be present. Actual : " + strElementName + " is present.");
		} else {
			Reporter.log("Expected : " + strElementName + " should be present. Actual : " + strElementName
					+ " is not present.");
		}
	}
	
	public static boolean verifyTrue(boolean condition, String SuccessMessage, String FailureMessage) {
		if (condition) {
			Reporter.log(SuccessMessage, MessageType.PASS);
			return true;
		} else {
			Reporter.log(FailureMessage, MessageType.FAIL);
			return false;
		}
	}

	public static boolean verifyFalse(boolean condition, String SuccessMessage, String FailureMessage) {
		
		if (!condition) {
			Reporter.log(SuccessMessage, MessageType.PASS);
			return true;
		} else {
			Reporter.log(FailureMessage, MessageType.FAIL);
			return false;
		}
	}

	
	
	
}
