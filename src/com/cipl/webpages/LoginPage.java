package com.cipl.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public LoginPage() {
		PageFactory.initElements(TestBase.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='username']")
	private WebElement txtUserName;

	@FindBy(xpath = "//*[@id='password']")
	private WebElement txtPassword;

	@FindBy(xpath = "//button[@title='Login']")
	private WebElement BtnLogin;

	@FindBy(xpath = ".//*[@id='Forms']")
	private WebElement rbnForms;

	@FindBy(xpath = "//div[contains(@class,'header-profile')]/img")
	private WebElement ProfileImg;

	@FindBy(xpath = "//*[@id='MainContent_txtFirstName']")
	private WebElement txtName;

	@FindBy(xpath = "//*[@id='MainContent_btnSearch']")
	private WebElement btnSearch;

	@FindBy(xpath = "//table[@id='MainContent_gvEmp']")
	private WebElement tblEmp;

	public WebElement gettxtUsername() {
		return txtUserName;
	}

	public WebElement gettxtPassword() {
		return txtPassword;
	}

	public WebElement getBtnLogin() {
		return BtnLogin;
	}

	public WebElement getrbnForms() {
		return rbnForms;
	}

	public WebElement getProfileImg() {
		return ProfileImg;
	}

	public WebElement gettxtName() {
		return txtName;
	}

	public WebElement getbtnSearch() {
		return btnSearch;
	}

	public WebElement gettblEmp() {
		return tblEmp;
	}

	public void doLogin(String strUserName, String strPassword) {
		TestBase.WaitforPresent(gettxtUsername());
		gettxtUsername().sendKeys(strUserName);
		gettxtPassword().sendKeys(strPassword);
		getrbnForms().click();
		TestBase.pause(2000);
		getBtnLogin().click();
		TestBase.pause(10000);
	}

	public void VerifyLogin() {
		doLogin(TestBase.getProperty("Login.username"), TestBase.getProperty("Login.password"));
		TestBase.WaitforPresent(getProfileImg());
		TestBase.VerifyPresent(getProfileImg(), "Profile Image on dashboard");
	}

	public void VerifySearchPanel() {
		FunctionalLibrary functionlibrary=new FunctionalLibrary();
		functionlibrary.MoveTomenu("Employee", "Job Offers");
		TestBase.WaitforPresent(gettxtName());
		gettxtName().sendKeys("Tithi ");
		getbtnSearch().click();
		TestBase.pause(4000);
		TestBase.WaitforPresent(gettblEmp());
		WebElement EmpName = TestBase.getDriver()
				.findElement(By.xpath("//*[@id='MainContent_gvEmp']/tbody/tr[2]/td[1]"));
		WebElement Empdept = TestBase.getDriver()
				.findElement(By.xpath("//*[@id='MainContent_gvEmp']/tbody/tr[2]/td[2]"));
		WebElement EmpDesignetion = TestBase.getDriver()
				.findElement(By.xpath("//*[@id='MainContent_gvEmp']/tbody/tr[2]/td[3]"));
		WebElement EmpReptManager = TestBase.getDriver()
				.findElement(By.xpath("//*[@id='MainContent_gvEmp']/tbody/tr[2]/td[4]"));

		TestBase.verifyTrue(EmpName.getText().trim().equals("Tithi Y Shah"),
				"Actual: Employee name is '" + EmpName.getText() + "',which is as expected.",
				"Expected: Employee name should be 'Tithi Y Shah'.Actual: Employee name is '" + EmpName.getText()
						+ "'.");
		TestBase.verifyTrue(Empdept.getText().trim().equals("Testing"),
				"Actual: Employee department is '" + Empdept.getText() + "',which is as expected.",
				"Expected: Employee department should be 'Testing'.Actual: Employee name is '" + Empdept.getText()
						+ "'.");
		TestBase.verifyTrue(EmpDesignetion.getText().trim().equals("QA Analyst"),
				"Actual: Employee designation is '" + EmpDesignetion.getText() + "',which is as expected.",
				"Expected: Employee designation should be 'QA Analyst'.Actual: Employee designation is '"
						+ Empdept.getText() + "'.");
		TestBase.verifyTrue(EmpReptManager.getText().trim().equals("Chintan M Javiya"),
				"Actual: Employee Reporting Manager is '" + EmpReptManager.getText() + "',which is as expected.",
				"Expected: Employee designation should be 'Chintan M Javiya'.Actual: Employee designation is '"
						+ Empdept.getText() + "'.");

	}

}
