package cn.gloryroad;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.w3c.dom.DOMConfiguration;

public class TestLog4j {
	public WebDriver driver;
	String baseUrl = "http://www.sogou.com";
	
	
	@Test
	public void testSearch() {
		Log.startTestCase("search");
		driver.get(baseUrl);
		Log.info("打开sogou首页");
		driver.findElement(By.id("query")).sendKeys("automation");
		Log.info("输入关键字");
		driver.findElement(By.id("stb")).click();
		Log.info("点击搜索button");
		Log.endTestCase("search");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\tools\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	@BeforeClass
	public void beforeClass(){
		DOMConfigurator.configure("log4j.xml");
	}

}
