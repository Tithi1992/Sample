package com.cipl.webtests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumGrid {

	public RemoteWebDriver driver;
	public DesiredCapabilities desiredCapabilities;

	@BeforeClass
	@Parameters("myBrowser")
	public void BeforeClass(String myBrowser) throws MalformedURLException {

		String strBrowsertype = myBrowser;

		switch (strBrowsertype) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
			desiredCapabilities = new DesiredCapabilities().chrome();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\geckodriver.exe");
			desiredCapabilities = new DesiredCapabilities().firefox();
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", "C:\\Selenium\\IEDriverServer.exe");
			desiredCapabilities = new DesiredCapabilities().internetExplorer();
			break;

		default:
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
			desiredCapabilities = new DesiredCapabilities().chrome();
			break;
		}
		driver = new RemoteWebDriver(new URL("http://192.192.8.190:4444/wd/hub"), desiredCapabilities);
		driver.manage().deleteAllCookies();

		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void TC1() throws InterruptedException {
		driver.get("http://192.192.8.4:706/login");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement txtUserName = driver.findElement(By.xpath("//*[@id='username']"));

		txtUserName.sendKeys("mcgajjar");

		WebElement txtPassword = driver.findElement(By.xpath("//*[@id='password']"));

		txtPassword.sendKeys("Admin@123");

		WebElement rbnFrmBasedUser = driver.findElement(By.xpath("//*[@id='Forms']"));

		rbnFrmBasedUser.click();

		driver.findElement(By.xpath("//button[@title='Login']")).click();

		Thread.sleep(10000);

		WebElement NavBar = driver.findElement(By.xpath("//*[@id='navbar']/ul/li[1]/a/img"));
		NavBar.click();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement lnkLogout = driver.findElement(By.xpath("//*[@id='navbar']//a[contains(text(),'Sign Out')]"));

		lnkLogout.click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.quit();

	}
}
