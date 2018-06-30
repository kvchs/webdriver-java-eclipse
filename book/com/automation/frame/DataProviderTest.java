package com.automation.frame;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class DataProviderTest {
	private static WebDriver driver;
	@DataProvider(name = "searchWords")
	public static Object[] [] words(){
		return new Object[] [] {
				{"蝙蝠侠", "主演", "迈克尔"},
				{"超人", "导演", "唐纳"},
				{"生化危机", "编剧", "安德森"}
		};
	}
	
	
	@Test(dataProvider = "searchWords")
	public void test(String searchWord1, String searchWord2, String searchResult) {
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir") + "\\tools\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.sogou.com");
		driver.findElement(By.id("query")).sendKeys(searchWord1 + " " + searchWord2);
		driver.findElement(By.id("stb")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Assert.assertTrue(driver.getPageSource().contains(searchResult));
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

}
