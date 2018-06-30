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
		
		//切换到富文本所在的Frame对象中，具体的FrameID可以使用FirePath工具获取到
		driver.switchTo().frame("");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//document.getElementByTagName('body')[0] 可以获取富文本框的编辑区域对象
		//使用编辑区对象的.innerHTML属性可以设定任意HTML格式的文字内容
		js.executeScript("document.getElementByTagName('body')[0].innerHTML='<b>邮件要发送的内容<b>'");
		driver.switchTo().defaultContent();
		/**
		 * 此代码在Firefox中可以执行，在IE浏览器中可以出现假死的情况，目前未能确认产生IE假死的原因
		 * 
		 */
	}

}
