package com.webdriver.run;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestOperatorAreaText {
	private WebDriver driver;
	String baseUrlString;
	
	@BeforeMethod
	public void beforeMethod(){
		baseUrlString = "";
		driver = new FirefoxDriver();
		driver.get(baseUrlString);
	}
	
	@AfterMethod
	public void afterMethod(){
		driver.quit();
	}
	
	@Test
	public void testSoHMailWriteEMail(){
		WebElement usernameElement = driver.findElement(By.id(""));
		usernameElement.sendKeys("");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("")));
		WebElement writeMailButton = driver.findElement(By.id(""));
		writeMailButton.click();
		
		//�л������ı����ڵ�Frame�����У������FrameID����ʹ��FirePath���߻�ȡ��
		driver.switchTo().frame("");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//document.getElementByTagName('body')[0] ���Ի�ȡ���ı���ı༭�������
		//ʹ�ñ༭�������.innerHTML���Կ����趨����HTML��ʽ����������
		js.executeScript("document.getElementByTagName('body')[0].innerHTML='<b>�ʼ�Ҫ���͵�����<b>'");
		driver.switchTo().defaultContent();
		/**
		 * �˴�����Firefox�п���ִ�У���IE������п��Գ��ּ����������Ŀǰδ��ȷ�ϲ���IE������ԭ��
		 * 
		 */
	}

}
