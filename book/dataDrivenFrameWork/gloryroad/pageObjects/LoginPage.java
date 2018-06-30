package dataDrivenFrameWork.gloryroad.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dataDrivenFrameWork.gloryroad.util.ObjectMap;

public class LoginPage {
	private WebElement element = null;
	private ObjectMap objectMap = new ObjectMap(System.getProperty("user.dir")
			+ "\\DataDrivenFrameWord\\objectMap.properties");
	private WebDriver driver;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
	}
	
	//���ص�¼ҳ����û��������ҳ��Ԫ�ض���
	public WebElement username() throws Exception{
		element = driver.findElement(objectMap.getLocator("126mail.loginPage.username"));
		return element;
	}
	
	public WebElement password() throws Exception{
		element = driver.findElement(objectMap.getLocator("126mail.loginPage.password"));
		return element;
	}
	
	public WebElement loginButton() throws Exception{
		element = driver.findElement(objectMap.getLocator("126mail.loginPage.loginbutton"));
		return element;
	}

}
