package dataDrivenFrameWork.gloryroad.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dataDrivenFrameWork.gloryroad.util.ObjectMap;

public class AddressBookPage {
	private WebElement element = null;
	private ObjectMap objectMap = new ObjectMap(System.getProperty("user.dir")
			+ "\\DataDrivenFrameWord\\objectMap.properties");
	
	private WebDriver driver;
	
    public AddressBookPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
    	this.driver = driver;
	}
    
    public WebElement createContactPersonButton() throws Exception{
    	element = driver.findElement(objectMap.getLocator("126mail.addressBook.createContactPerson"));
    	return element;
    }
    
    public WebElement contactPersonName() throws Exception{
    	element = driver.findElement(objectMap.getLocator("126mail.addressBook.contactPersonName"));
    	return element;
    }
    
    public WebElement contactPersonEmail() throws Exception{
    	element = driver.findElement(objectMap.getLocator("126mail.addressBook.contactPersonEmail"));
    	return element;
    }
    
    public WebElement contactPersonMobile() throws Exception{
    	element = driver.findElement(objectMap.getLocator("126mail.addressBook.contactPersonMobile"));
    	return element;
    }
    
    public WebElement saveButton() throws Exception{
    	element = driver.findElement(objectMap.getLocator("126mail.addressBook.saveButton"));
    	return element;
    }
		

}
