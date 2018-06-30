package com.webdriver.run;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

/**
 * @author Administrator 在Ajax方式方式产生的浮动框中，点击选择包含某个关键字的选项
 * 有的被测试页面包含Ajax的局部刷新机制，并且会显示多条数据的浮动框，需要单击选择浮动框中包含 的某个关键字选项
 */
public class TestAjax {
	WebDriver driver;
	String baseUrl;
	JavascriptExecutor js;

	@Test
	public void testAjaxDivOption() throws InterruptedException {
		driver.get(baseUrl);
		WebElement serarchInputBox = driver.findElement(By.xpath("//input[@id='query']"));
		serarchInputBox.click();
		Thread.sleep(3000);
		List<WebElement> suggetionElements = driver.findElements(By.xpath("//*[@id='vl']/div[1]/ul/li"));
		for (WebElement element:suggetionElements){
			if (element.getText().contains("2112")){
				System.out.println(element.getText());
				element.click();
				Thread.sleep(3000);
				break;
			}
		}
	}
	
	//由于浮动框的内容总是发生变化，如何只想选择浮动框中的第一项，可以如下操作
	@Test
	public void testAjaxDivOptionTwo() throws InterruptedException{
	
		driver.get(baseUrl);
		WebElement serarchInputBox = driver.findElement(By.xpath("//input[@id='query']"));
		serarchInputBox.click();
		Thread.sleep(3000);
		//注意:此处li元素的索引从1开始
		WebElement suggectionOptions = driver.findElement(By.xpath("//*[@id='vl']/div[1]/ul/li[1]"));
		suggectionOptions.click();
		Thread.sleep(3000);
		
	}

	@BeforeMethod
	public void beforeMethod() {
		baseUrl = "https://www.sogou.com/";
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
