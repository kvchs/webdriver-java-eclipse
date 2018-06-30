package com.webdriver.run;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

/**
 * @author Administrator
 *Global Index
                     The HTML 5 JavaScript API Index
                           http://html5index.org/
 */
public class TestHTML5Storage {
	/**
	 * 读取HTML5的localStorage和sessionStorage的内容，并删除存储的对象
	 * localStorage:  http://www.w3school.com.cn/tiy/loadtext.asp?f=html5_webstorage_local
	 * sessionStorage: http://www.w3school.com.cn/tiy/loadtext.asp?f=html5_webstorage_session
	 */
	
	WebDriver driver;
	String localStorage = "http://www.w3school.com.cn/tiy/loadtext.asp?f=html5_webstorage_local";
	String sessionStorage = "http://www.w3school.com.cn/tiy/loadtext.asp?f=html5_webstorage_session";
	JavascriptExecutor javascriptExecutor;
	
	@Test
	public void testHtml5localStorage() {
		driver.get(localStorage);
		javascriptExecutor = (JavascriptExecutor) driver;
		String lastname = (String) javascriptExecutor.executeScript("return localStorage.lastname;");
		Assert.assertEquals(lastname, "Gates");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		javascriptExecutor.executeScript("localStorage.clear();");
	}
	
	
	@Test
	public void testHtml5SessionStorage() throws InterruptedException{
		String lastname;
		driver.get(sessionStorage);
		javascriptExecutor  = (JavascriptExecutor) driver;
		lastname = (String) javascriptExecutor.executeScript("return sessionStorage.lastname;");
		javascriptExecutor.executeScript("sessionStorage.removeItem('lastname');");
		Thread.sleep(5000);
		javascriptExecutor.executeScript("localStorage.clear();");
		
	}
	

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\tools\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
