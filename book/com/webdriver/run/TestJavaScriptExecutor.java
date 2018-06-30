
package com.webdriver.run;


import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * @author Administrator
 *使用JavaScriptExecutor对象来实现页面元素的单击动作，此种方法主要用于解决在某些情况下，页面元素的.click()方法
 *无法生效的问题
 */
public class TestJavaScriptExecutor {
	WebDriver driver;
	String baseUrl;
	JavascriptExecutor js;
	
	@BeforeMethod
	public void beforeMethod(){
		baseUrl = "https://www.sogou.com/";
		System.out.println(System.getProperty("user.dir") + "/tools/IEDriverServer.exe");
		//System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "tools/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void afterMethod(){
		driver.quit();
	}
	
	
	@Test
	public void testHandleiFrame() throws Exception {
		WebElement searchInputBox = driver.findElement(By.xpath("//input[@id='query']"));
		WebElement searchButton = driver.findElement(By.cssSelector("#stb"));
		searchInputBox.sendKeys("JS Executor");
		JavaScriptClick(searchButton);
		Thread.sleep(5000);
		Assert.assertEquals("JavascriptExecutor - 白色单车 - 博客园", driver.findElement(By.name("dttl")).getText());
		
		
	}
	
	public void JavaScriptClick(WebElement element) throws Exception{
		try{
			if (element.isEnabled() && element.isDisplayed()){
				System.out.println("使用JavaScript进行页面元素单击");
				//执行JavaScript语句的arguments[0].click();
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
			}else{
				System.out.println("页面上的元素无法进行单击操作");
			}
		}catch (StaleElementReferenceException e){
			System.out.println("页面元素没有附加在网页中" + e.getStackTrace());
		}catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("在页面中没有找到要操作的元素" + e.getStackTrace());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("无法完成单击操作" + e.getStackTrace());
		}
	}
	

}
