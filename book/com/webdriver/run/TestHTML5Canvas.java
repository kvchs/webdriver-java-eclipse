package com.webdriver.run;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class TestHTML5Canvas {
	public WebDriver driver;
	String baseUrl = "http://www.w3school.com.cn/tiy/loadtext.asp?f=html5_canvas_line";
	JavascriptExecutor javascriptExecutor;
	
	@Test
	public void testHTML5Canvas() throws IOException, InterruptedException {
		File captureScreenFile = null;
		driver.get(baseUrl);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		
		/**
		 * ����JavascriptExecutorִ��javaScript��䣬�ڻ����ϻ�һ����ɫ���ε�ͼ��
		 * var cxt=c.getContext('2d'); �趨����Ϊ2d
		 * cxt.fillStyle='#FF0000';  �趨�����ɫΪ��ɫ
		 * "cxt.fillRect(0,0,150,150);" �ڻ����ϻ��ƾ���
		 */
		
		javascriptExecutor.executeScript("var c = document.getElementById('myCanvas');" +
		"var cxt=c.getContext('2d');" + "cxt.fillStyle='#FF0000';" + 
				"cxt.fillRect(0,0,150,150);");
		//���ƺ�ɫ���к󣬽�����Ļ��ͼ
		captureScreenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(captureScreenFile, new File(System.getProperty("user.dir") + "\\screenshots\\HTML5Canvas.png"));
		Thread.sleep(5000);
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new InternetExplorerDriver();
		
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
