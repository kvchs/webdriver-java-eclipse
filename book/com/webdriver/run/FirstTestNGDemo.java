package com.webdriver.run;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class FirstTestNGDemo {

	public WebDriver driver;
	String URL = "http://www.sougou.com";

	@Test
	public void testSearch() {
		driver.get(URL);
		driver.findElement(By.id("query")).sendKeys("selenium");
		driver.findElement(By.id("stb")).click();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
