package com.webdriver.run;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.webdriver.run.ObjectMap;

public class TestSohuMailLoginByObjectMap {
	
	private WebDriver driver;
	private ObjectMap objectMap;
	String baseUrl;
	
	@Test
	public void testSohuMailLogin() throws Exception {
		try {
			objectMap = new ObjectMap(System.getProperty("user.dir") + "/ObjectMap.properties");
			System.out.println(System.getProperty("user.dir") + "/ObjectMap.properties");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("���� ObjectMap����ʧ��");
		}
		
		// ����ObjectMapʵ����getLocator������ȡ
		WebElement username = driver.findElement(objectMap.getLocator("SohuMai.HomePage.username"));
		WebElement password = driver.findElement(objectMap.getLocator("SohuMai.HomePage.password"));
		WebElement submitbutton = driver.findElement(objectMap.getLocator("SohuMai.HomePage.submitButton"));
		username.sendKeys("username");
		password.sendKeys("123456");
		submitbutton.click();
		Thread.sleep(5000);
//		Assert.assertTrue(driver.getPageSource().contains("��������ȷ���˺�����"));
		
		
	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		baseUrl = "http://mail.sohu.com";
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(baseUrl);
		Thread.sleep(5000);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
