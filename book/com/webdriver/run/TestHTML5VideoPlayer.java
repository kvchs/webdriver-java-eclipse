package com.webdriver.run;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;


import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FileUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class TestHTML5VideoPlayer {
	public WebDriver driver;
	String baseUrlString = "http://www.w3school.com.cn/tiy/t.asp?f=html_video";
	@Test
	public void testVideoPlayer() throws InterruptedException, IOException {
		File captureFileScreenFile = null;
		driver.get(baseUrlString);
//		driver.switchTo().frame(1);
//		driver.findElement(By.xpath("//*[@id='my-video']/button/span[1]")).click();
		System.out.println(driver.getPageSource());
		WebElement videoPlayer = driver.findElement(By.tagName("video"));
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		//currentSrc属性获取视频文件的网络存储地址
		String videoSrc = (String) javascriptExecutor.executeScript("return arguments[0].currentSrc;", videoPlayer);
		Assert.assertEquals(videoSrc, "http://www.w3school.com.cn/tiy/t.asp?f=html_video");
		//使用JavascriptExecutor对象执行语句，通过播放器内部的duration属性获取视频文件的播放时长
		Double videoDuration = (Double) javascriptExecutor.executeScript("return argument[0].duration;", videoPlayer);
		System.out.println(videoDuration.intValue());
		Thread.sleep(5000);
		javascriptExecutor.executeScript("return arguments[0].play()", videoPlayer);
		javascriptExecutor.executeScript("return arguments[0].pause()", videoPlayer);
		Thread.sleep(3000);
		File captureScreenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(captureScreenFile, new File(System.getProperty("user.dir") + "\\screenshots\\videoPlay_pause.jpg"));
	}

	@BeforeMethod
	public void beforeMethod() {
	
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
		
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
