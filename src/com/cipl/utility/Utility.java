package com.cipl.utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.cipl.webpages.TestBase;

public class Utility {

	public static void takeScreenShot() {
		try {
			File scrFile = ((TakesScreenshot) TestBase.getDriver()).getScreenshotAs(OutputType.FILE);
		
			String fileName = Reporter.getCurrentTestResult().getTestClass().getRealClass().getSimpleName() + "_"
					+ Reporter.getCurrentTestResult().getName() + "_" + "Screenshot.jpg";

			FileUtils.copyFile(scrFile, new File("ScreenShots/" + fileName));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
