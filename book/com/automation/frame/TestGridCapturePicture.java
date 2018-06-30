//https://makandracards.com/makandra/29465-install-chromedriver-on-linux
//配置chrome驱动在Linux环境

package com.automation.frame;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestGridCapturePicture {
	/** 配置方法
	 * http://www.blogjava.net/qileilove/archive/2013/12/26/408057.html
	 */
	WebDriver driver;
	public static String baseUrlString = "http://www.sogou.com";
	public static String nodeUrlString  = "http://192.168.0.106:6655/wd/hub";
	
	
	@Test
	public void testSogouSearch() throws InterruptedException, IOException {
		driver.get(baseUrlString);
		Thread.sleep(5000);
		
		driver.findElement(By.id("query")).sendKeys("python");
		driver = new Augmenter().augment(driver);//only remote capture picture use
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\screenshots\\RemoteScreenshot.png"));
		driver.findElement(By.id("stb")).click();
		Thread.sleep(5000);
//		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
//			
//			public Boolean apply(WebDriver d) {
//				// TODO Auto-generated method stub
//				return d.findElement(By.xpath("//div[@safeclass~'\ball-time-box\b']/a[@innertext='全部时间']")).getText().contains("全部时间");
//			}
//		});
//		Assert.assertTrue(driver.getPageSource().contains("全部时间"));
	}

	@BeforeMethod
	public void beforeMethod() throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
     	capability.setCapability("marionette", true);  
		capability.setPlatform(Platform.LINUX);
		driver = new RemoteWebDriver(new URL(nodeUrlString), capability);
		

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
