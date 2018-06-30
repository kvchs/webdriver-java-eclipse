package com.webdriver.run;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;

public class TestScroll {
	WebDriver driver;
	String baseUrl;
	
	@Test(priority =1)//   priority=1表示测试用例以第一优先级运行
	public void ScrollingToBottomofAPage() {
		//使用JavaScript的scrollTo函数和document.body.scrollHeight函数将页面的滚动条滑到页面最下方
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	@Test(priority=2)
	public void scrollingToElementOfAPage(){
//		driver.switchTo().frame("");
		WebElement element = driver.findElement(By.xpath("//*[@id='container']/div[2]/div[4]/div[2]/div[1]/h3/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollTntoView();", element);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test(priority=3)
	public void scrollingByCoordinatesofAPage(){
		((JavascriptExecutor) driver).executeScript("window.scrollBy (0, 800)");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void beforeMethod() {
		baseUrl = "http://v.sogou.com/";
		driver = new FirefoxDriver();
		driver.get(baseUrl);
	}

	@AfterMethod
	public void afterMethod() {
	}

}
