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
		//��sogou��ҳ���н�ͼ
		FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\screenshots\\sogouHomePage.jpg"));
		System.out.println(System.getProperty("user.dir") + "\\screenshots\\sogouHomePage.jpg");
		//�����������ļ�����һ����������ͼƬ��һ����ʵ�����Թ����в�����ͼƬ
		File fileInput = new File(System.getProperty("user.dir") + "\\screenshots\\expected.jpg");
		File fileOutput = new File(System.getProperty("user.dir") + "\\screenshots\\sogouHomePage.jpg");
		
		/**
		 * ���²���Ϊ�����ļ��������رȶԵ��㷨ʵ�֣���ȡ�ļ������ظ�����С��Ȼ��ʹ��ѭ���ķ�ʽ������ͼƬ��������Ŀ
		 * ����һһ�ȶԣ�����κ�һ�����ز���ͬ�����˳�ѭ������matchFlag������ֵ����Ϊflase�����ʹ�ö���Assert
		 * �ж�matchFlag�Ƿ�Ϊtrue�����true��ʾ����ͼƬ��ȫһ�£����Ϊflase��ʾ����ͼƬ��������ȫƥ�䣬���Խ��
		 * Ϊʧ��
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
		Assert.assertTrue(matchFlag, "���Թ����еĽ�ͼ�������Ľ�ͼ��һ��");
		
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
