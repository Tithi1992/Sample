package com.cipl.webpages;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.testng.ITestResult;

import com.cipl.utility.Utility;

public class Reporter extends org.testng.Reporter {

	final static String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";

	public enum MessageType {
		INFO, PASS, FAIL;
	}

	public static void log(String Message, MessageType messageType) {

		System.setProperty(ESCAPE_PROPERTY, "false");

		if (messageType == MessageType.FAIL) {
			org.testng.Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);

			if (Boolean.parseBoolean(getProperty("screenshotOnFailure")) == true) {

				Utility.takeScreenShot();

				// log("<font color='red'>FAIL </font> " + Message + "<a
				// href='https://www.youtube.com/'>[View ScreenShot]</a><br>");
				log("<font color='red'>FAIL </font> " + Message + "<a href=" + generateLinkForScreenShot()
						+ ">[View ScreenShot]</a><br>");
			} else {
				log("<font color='red'>FAIL </font> " + Message + "<br>");
			}

		} else if (messageType == MessageType.PASS) {
			log("<font color='green'>PASS </font> " + Message + "<br>");
		} else {
			log("<font color='blue'>INFO </font> " + Message + "<br>");
		}
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

	private static String generateLinkForScreenShot() {
		String classpathRoot = System.getProperty("user.dir");

		String fileName = org.testng.Reporter.getCurrentTestResult().getTestClass().getRealClass().getSimpleName() + "_"
				+ org.testng.Reporter.getCurrentTestResult().getName() + "_" + "Screenshot.jpg";

		System.out.println(
				"Hiii  " + classpathRoot + "\\" + getProperty("test.results.screenshots.dir") + "\\" + fileName);
		return classpathRoot + "\\" + getProperty("test.results.screenshots.dir") + "\\" + fileName;
	}

}
