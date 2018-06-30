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
		wait.until(ExpectedConditions.titleContains("�ļ��ϴ��ɹ�"));
	}
	
	/**
	 * ʹ�õ����������ϴ��ļ�
	 * �ܹ�ʹ�õ���������AutoIt����һЩWebDriver�޷��������ļ��ϴ�����
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
