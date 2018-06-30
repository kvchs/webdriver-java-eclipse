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
		 * 调用JavascriptExecutor执行javaScript语句，在画布上画一个红色矩形的图案
		 * var cxt=c.getContext('2d'); 设定画布为2d
		 * cxt.fillStyle='#FF0000';  设定填充颜色为红色
		 * "cxt.fillRect(0,0,150,150);" 在画布上绘制矩形
		 */
		
		javascriptExecutor.executeScript("var c = document.getElementById('myCanvas');" +
		"var cxt=c.getContext('2d');" + "cxt.fillStyle='#FF0000';" + 
				"cxt.fillRect(0,0,150,150);");
		//绘制红色举行后，进行屏幕截图
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
