
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
 *ʹ��JavaScriptExecutor������ʵ��ҳ��Ԫ�صĵ������������ַ�����Ҫ���ڽ����ĳЩ����£�ҳ��Ԫ�ص�.click()����
 *�޷���Ч������
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
		Assert.assertEquals("JavascriptExecutor - ��ɫ���� - ����԰", driver.findElement(By.name("dttl")).getText());
		
		
	}
	
	public void JavaScriptClick(WebElement element) throws Exception{
		try{
			if (element.isEnabled() && element.isDisplayed()){
				System.out.println("ʹ��JavaScript����ҳ��Ԫ�ص���");
				//ִ��JavaScript����arguments[0].click();
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
			}else{
				System.out.println("ҳ���ϵ�Ԫ���޷����е�������");
			}
		}catch (StaleElementReferenceException e){
			System.out.println("ҳ��Ԫ��û�и�������ҳ��" + e.getStackTrace());
		}catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("��ҳ����û���ҵ�Ҫ������Ԫ��" + e.getStackTrace());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("�޷���ɵ�������" + e.getStackTrace());
		}
	}
	

}
