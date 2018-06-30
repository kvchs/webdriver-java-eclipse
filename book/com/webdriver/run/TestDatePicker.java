package com.webdriver.run;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

/**
 * @author Administrator
 *有些日期选择器字段不允许输入，必须通过选择器才可以进行日期的选择。
 *可以设置页面对象的属性，设置日期字段为可以编辑的属性
 */
public class TestDatePicker {
	WebDriver driver;
	String baseUrl;
	JavascriptExecutor js;

	@Test
	public void testdataPicker() throws InterruptedException {
		driver.get(baseUrl);
		WebElement dataInputBox = driver.findElement(By.id("datepicker"));
		dataInputBox.sendKeys("12/31/2015");
		Thread.sleep(3000);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\tools\\chromedriver.exe");

		baseUrl = "http://jqueryui.com/resources/demos/datepicker/other-months.html";
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
