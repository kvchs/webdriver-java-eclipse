package com.automation.frame;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestSeleniumGridMutiBrowser {
	public static String baseUrl = "http://www.sogou.com";
	
	@Test
	public void testSogouSearch1() throws MalformedURLException {
		WebDriver driver = GetDriver.getRemoteChromeDriver();
		driver.get(baseUrl);
		driver.findElement(By.id("query")).sendKeys("chrome");
		driver.findElement(By.id("stb")).click();
		Assert.assertTrue(driver.getPageSource().contains("chrome"));
		driver.quit();
	}
	
	@Test
	public void testSogouSearch2() throws MalformedURLException {
		WebDriver driver = GetDriver.getRemoteFirefoxDriver();
		driver.get(baseUrl);
		driver.findElement(By.id("query")).sendKeys("firefox");
		driver.findElement(By.id("stb")).click();
		Assert.assertTrue(driver.getPageSource().contains("firefox"));
		driver.quit();
	}
}
