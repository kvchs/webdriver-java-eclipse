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
 * @author Administrator ��Ajax��ʽ��ʽ�����ĸ������У����ѡ�����ĳ���ؼ��ֵ�ѡ��
 * �еı�����ҳ�����Ajax�ľֲ�ˢ�»��ƣ����һ���ʾ�������ݵĸ�������Ҫ����ѡ�񸡶����а��� ��ĳ���ؼ���ѡ��
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
	
	//���ڸ�������������Ƿ����仯�����ֻ��ѡ�񸡶����еĵ�һ��������²���
	@Test
	public void testAjaxDivOptionTwo() throws InterruptedException{
	
		driver.get(baseUrl);
		WebElement serarchInputBox = driver.findElement(By.xpath("//input[@id='query']"));
		serarchInputBox.click();
		Thread.sleep(3000);
		//ע��:�˴�liԪ�ص�������1��ʼ
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
