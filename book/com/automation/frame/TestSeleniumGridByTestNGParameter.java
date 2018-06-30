package com.automation.frame;

import java.net.MalformedURLException;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.frame.GetDriverNew;

public class TestSeleniumGridByTestNGParameter {
	public WebDriver driver;
	public String baseUrl = "http://www.sogou.com";
	
	@Parameters({"remoteNodeUrl","browser"})
	@BeforeClass
	public void beforeTest(String remoteNodeUrl, String browser) throws MalformedURLException{
		if(browser.equalsIgnoreCase("firefox")){
			driver = GetDriverNew.getRemoteFirefoxDriver(remoteNodeUrl);
		}else if(browser.equalsIgnoreCase("ie")){
			driver = GetDriverNew.getRemoteIEDriver(remoteNodeUrl);
		}else if(browser.equalsIgnoreCase("chrome")){
			driver = GetDriverNew.getRemoteChromeDriver(remoteNodeUrl);
		}
	}
	

	@Test
	public void testSogouSearch1() throws MalformedURLException {
		driver.get(baseUrl);
		driver.findElement(By.id("query")).sendKeys("chrome");
		driver.findElement(By.id("stb")).click();
		Assert.assertTrue(driver.getPageSource().contains("chrome"));
		driver.quit();
	}
	
	@Test
	public void testSogouSearch2() throws MalformedURLException {
		driver.get(baseUrl);
		driver.findElement(By.id("query")).sendKeys("firefox");
		driver.findElement(By.id("stb")).click();
		Assert.assertTrue(driver.getPageSource().contains("firefox"));
		driver.quit();
	}
	
	@AfterClass
	public void afterTest(){
		driver.quit();
	}
}
