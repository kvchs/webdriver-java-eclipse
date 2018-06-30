package cn.gloryroad;

import java.util.Date;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class TestFailCaptureScreen {
	public WebDriver driver;
	String baseUrl = "http://www.sogou.com";
	
	@Test
	public void testSearch() throws InterruptedException{
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.findElement(By.id("query")).sendKeys("java");
		driver.findElement(By.id("stb")).click();
		try {
			Assert.assertTrue(driver.getPageSource().contains("��"));
			System.out.println("finish assert");
		} catch (AssertionError e) {
			// TODO: handle exception
			System.out.println("Run catch coding");
			takeTakesScreenshot(driver);
			Thread.sleep(5000);
			System.out.println("��ɽ�ͼ");
		}
	}
	
	
	public void takeTakesScreenshot(WebDriver driver){
		try {
			Date date = new Date();
			//����DateUtil���еķ��������ɽ�ͼ�����ļ��е����ں�����
			String picDir = System.getProperty("user.dir") + "\\" + String.valueOf(DateUtil.getYear(date)) + "-" +
			String.valueOf(DateUtil.getMonth(date)) + "-" + String.valueOf(DateUtil.getDay(date));
			if (!new File(picDir).exists()){
				FileUtil.createDir(picDir);
			}
			
			//����DateUtil���еķ��������ɽ�ͼ�ļ���ʱ������
			String filePath = picDir + "\\" + String.valueOf(DateUtil.getHour(new Date())) + "-" + 
					String.valueOf(DateUtil.getMinute(new Date())) + "-" + String.valueOf(DateUtil.getSecond(new Date()))
					 + ".png";
			//���н�ͼ�������ļ����ݱ�����srcFile������
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			//����ͼ�ļ�д����̣����ɽ�ͼ�ļ�
			FileUtils.copyFile(srcFile, new File(filePath));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void beforeMethod(){
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\tools\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}
	
	@AfterMethod
	public void afterMethod(){
		driver.quit();
	}

}
