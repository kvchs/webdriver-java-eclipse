package com.webdriver.run;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstJunit4WebDriverDemo {

	public WebDriver driver;
	String baseURL = "http://www.sogou.com";

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		driver.get(baseURL);
		driver.findElement(By.id("query")).sendKeys("selenium");
		driver.findElement(By.id("stb")).click();
	}

}
