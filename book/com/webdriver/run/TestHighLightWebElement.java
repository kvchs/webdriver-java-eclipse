package com.webdriver.run;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class TestHighLightWebElement {
	
	public WebDriver driver;
	private String baseUrl;
	
	@Test
	public void testHighLightWebElement() throws InterruptedException {
		
		WebElement searchInputBox = driver.findElement(By.id("query"));
		WebElement submitButton = driver.findElement(By.id("stb"));
		
		hightlightElement(searchInputBox);
		searchInputBox.sendKeys("js");
		Thread.sleep(5000);
		
		hightlightElement(submitButton);
		Thread.sleep(3000);
		submitButton.click();
		Thread.sleep(2000);
		
	}
	
	public void hightlightElement(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//使用JavaScript语句将传入参数的页面元素对象的背景颜色和边框分别设置为黄色和红色
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "background: yellow;border:"
				+ "2px solid green;");
		
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new InternetExplorerDriver();
		baseUrl = "http://www.sogou.com";
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.navigate().to(baseUrl);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
