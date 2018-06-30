package com.webdriver.run;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.Assert;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class TestCompareImage {
	
	public WebDriver driver;
	private String baseUrl;
	
	@Test
	public void TestImageComparison() throws InterruptedException, IOException {
		driver.navigate().to(baseUrl);
		driver.manage().window().maximize();
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Thread.sleep(3000);
		//对sogou首页进行截图
		FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\screenshots\\sogouHomePage.jpg"));
		System.out.println(System.getProperty("user.dir") + "\\screenshots\\sogouHomePage.jpg");
		//生成了两个文件对象，一个是期望的图片，一个是实践测试过程中产生的图片
		File fileInput = new File(System.getProperty("user.dir") + "\\screenshots\\expected.jpg");
		File fileOutput = new File(System.getProperty("user.dir") + "\\screenshots\\sogouHomePage.jpg");
		
		/**
		 * 以下部分为两个文件进行像素比对的算法实现，获取文件的像素个数大小，然后使用循环的方式将两种图片的所有项目
		 * 进行一一比对，如果任何一个像素不相同，则退出循环，将matchFlag变量的值设置为flase，最后使用断言Assert
		 * 判断matchFlag是否为true，如果true表示两张图片完全一致；如果为flase表示两张图片并不是完全匹配，测试结果
		 * 为失败
		 */
		
		BufferedImage bufileInput = ImageIO.read(fileInput);
		DataBuffer dafileInput = bufileInput.getData().getDataBuffer();
		int sizefileInput = dafileInput.getSize();
		BufferedImage bufileOutPut = ImageIO.read(fileOutput);
		DataBuffer datileOutPut  = bufileOutPut.getData().getDataBuffer();
		int sizefileOutPut = datileOutPut.getSize();
		Boolean matchFlag = true;
		
		if (sizefileInput == sizefileOutPut){
			for (int j = 0; j < sizefileInput; j++ ){
				if (dafileInput.getElem(j) != datileOutPut.getElem(j)){
					matchFlag = false;
					break;
				}
			}
		}else {
			matchFlag = false;
			
		}
		Assert.assertTrue(matchFlag, "测试过程中的截图和期望的截图不一致");
		
	}

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		baseUrl = "http://www.sogou.com";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
