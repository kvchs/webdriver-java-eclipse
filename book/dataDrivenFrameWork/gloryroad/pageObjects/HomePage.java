package dataDrivenFrameWork.gloryroad.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dataDrivenFrameWork.gloryroad.util.ObjectMap;

public class HomePage {
	private WebElement element = null;
	private ObjectMap objectMap = new ObjectMap(System.getProperty("user.dir")
			+ "\\DataDrivenFrameWord\\objectMap.properties");
	
	private WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
	}
	
	public WebElement addressLink() throws Exception{
		element = driver.findElement(objectMap.getLocator("126mail.homePage.addressBook"));
		return element;
	}

}
