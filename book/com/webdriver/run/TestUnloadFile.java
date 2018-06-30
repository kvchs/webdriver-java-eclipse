package com.webdriver.run;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class TestUnloadFile {
	WebDriver driver;
	String baseUrl;
	@Test
	public void testUploadFile() {
		WebElement fineInputBox = driver.findElement(By.xpath(""));
		fineInputBox.sendKeys("D:\\a.txt");
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("")));
		WebElement fileSubmitBtn = driver.findElement(By.xpath(""));
		fileSubmitBtn.click();
		wait.until(ExpectedConditions.titleContains("文件上传成功"));
	}
	
	/**
	 * 使用第三方工具上传文件
	 * 能够使用第三方工具AutoIt操作一些WebDriver无法操作的文件上传对象
	 */

	@BeforeMethod
	public void beforeMethod() {
		baseUrl = "";
		driver.get(baseUrl);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
