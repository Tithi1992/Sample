package com.cipl.webtests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginTest {
	
	@Test
	public void TC1() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://cygnet.hrinnova.com/Login");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		
		WebElement username=driver.findElement(By.xpath("//input[@id='username']"));
		username.sendKeys("tyshah");
		
		WebElement password=driver.findElement(By.xpath("//input[@id='password']"));
		password.sendKeys("Mar@2018");
		
		WebElement btnLogin=driver.findElement(By.xpath("//button[@title='Login']"));
		btnLogin.click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
		driver.quit();
	}
}
