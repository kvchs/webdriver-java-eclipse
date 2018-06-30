package com.webdriver.run;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

/**
 * @author Administrator
 *设置一个页面对象的属性值
 *org.openqa.selenium.WebDriverException: unknown error: call function result missing 'value'
 *更新chromedriver.exe为最新版本可以解决此问题
 */
public class TestSetElementAttribute {
	WebDriver driver;
	String baseUrl;
	JavascriptExecutor js;
	
	public void setAttribute(WebDriver driver, WebElement element, String attributeName, String value){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeName, value);
	}
	
	public void removeAttribute(WebDriver driver, WebElement element, String attributeName){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute(arguments[1])",element, attributeName);
	}
	
	@Test
	public void testDataPicker() throws InterruptedException {
		driver.get(baseUrl);
		WebElement serarchInputBox = driver.findElement(By.xpath("//input[@id='query']"));
		setAttribute(driver, serarchInputBox, "value", "文本框的value值被修改了");
		Thread.sleep(3000);
		setAttribute(driver, serarchInputBox, "maxlength", "5");
		Thread.sleep(3000);
		System.out.println(serarchInputBox.getAttribute("maxlength"));
		removeAttribute(driver, serarchInputBox, "name");
		System.out.println(serarchInputBox.getAttribute("name"));
		
	}

	@BeforeMethod
	public void beforeMethod() {
		baseUrl = "https://www.sogou.com";
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\tools\\chromedriver.exe" );
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
