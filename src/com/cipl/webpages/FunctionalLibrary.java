package com.cipl.webpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FunctionalLibrary extends TestBase {

	public FunctionalLibrary() {
		PageFactory.initElements(TestBase.getDriver(), this);
	}

	@FindBy(xpath = "//div[contains(@class,'button-menu')]")
	private WebElement btnMenu; 

	@FindBy(xpath = "//ul[@id='menu']/li/a")
	private List<WebElement> MainMenus;

	@FindBy(xpath = "//ul[@id='menu']//a[contains(@class,'menuclick ')]")
	private List<WebElement> SubMenus;

	public WebElement getbtnMenu() {
		return btnMenu;
	}

	public List<WebElement> getMainMenus() {
		return MainMenus;
	}

	public List<WebElement> getChildMenus() {
		return SubMenus;
	}

	public void MoveTomenu(String strMainMenu, String strChildMenu) {
		TestBase.WaitforPresent(getbtnMenu());
		getbtnMenu().click();

		for (int i = 0; i < getMainMenus().size(); i++) {
			if (getMainMenus().get(i).getText().equals(strMainMenu)) {
				getMainMenus().get(i).click();
				break;
			}
		}
		TestBase.pause(2000);
		DragAndDrop("//div[@id='mCSB_1_dragger_vertical']/div", ".//*[@id='mCSB_1_scrollbar_vertical']/div/div[2]");

		List<WebElement> ChildMenus = TestBase.getDriver()
				.findElements(By.xpath("//a[contains(@class,'menuclick ') and @id='" + strMainMenu + "']"));
		for (int j = 0; j < ChildMenus.size(); j++) {
			if (ChildMenus.get(j).getText().equals(strChildMenu)) {
				ChildMenus.get(j).click();
				break;
			}
		}
		TestBase.pause(3000);
	}

	public void DragAndDrop(String strStartElem, String strEndElem) {
		Actions act = new Actions(driver);
		WebElement drag = driver.findElement(By.xpath(strStartElem));
		WebElement drop = driver.findElement(By.xpath(strEndElem));
		act.dragAndDrop(drag, drop).build().perform();
		TestBase.pause(2000);
	}

}
