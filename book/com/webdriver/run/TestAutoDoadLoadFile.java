package com.webdriver.run;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Administrator
 * How to Create Firefox Profile in Selenium WebDriver
 *https://www.guru99.com/firefox-profile-selenium-webdriver.html
 *
 */
public class TestAutoDoadLoadFile {
	public static String downloadFilePath = System.getProperty("user.dir")
			+ "\\download";
	WebDriver driver;
	String baseUrl;
	JavascriptExecutor js;
	
	

	@Test
	public void testDataPicker() throws Exception {
		
//		driver = new FirefoxProfile(firefoxDriverProfile());
		FirefoxOptions Options = new FirefoxOptions();
        Options.setProfile(firefoxDriverProfile());
        WebDriver driver = new FirefoxDriver(Options);
		driver.get(baseUrl);
		driver.findElement(By.cssSelector("body > table > tbody > tr:nth-child(3) > td:nth-child(2) > a")).click();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}

	@BeforeMethod
	public void beforeMethod() {
		baseUrl = "http://ftp.mozilla.org/pub/firefox/releases/59.0b9/win32/zh-CN/";
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\tools\\geckodriver.exe");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	public static FirefoxProfile firefoxDriverProfile() throws Exception {
		
//		FirefoxProfile profile = new FirefoxProfile();
		
		 ProfilesIni profile2 = new ProfilesIni();
		 
	     FirefoxProfile profile = profile2.getProfile("default");
	     //FirefoxProfile profile = allProfiles.getProfile("default");
	   
		/**
		 * 设置Firefox的browser.download.folderList的属性为2
		 * 如果没有进行显式设置，则使用默认值1，表示下载文件保存在"下载"文件夹 设定为0，则下载文件会被保存在用户的桌面上
		 * 设定为2，则下载文件会被保存在指定的文件夹下
		 * 
		 */
		profile.setPreference("browser.download.folderList", 2);
		/**
		 * prowser.download.manager.showWhenStarting的属性默认值为true
		 * 设定为true，用户启动下载的时候显示Firefox浏览器的文件下载窗口
		 * 
		 */
		profile.setPreference("browser.download.manager.showWhenStarting",
				false);
		profile.setPreference("browser.download.dir", downloadFilePath);
		/**
		 * browser.helperApps.neverAsk.openFile表示直接打开下载文件，不显示确认狂
		 * 默认值为空字符串，下列代码行设定了多种文件的MIME类型，
		 */
		profile.setPreference("browser.helperApps.neverAsk.openFile",
				"application/octet-stream, application/exe, text/csv");
		/**
		 * browser.helperApps.neverAsk.saveToDisk表示下载文件是否直接保存到磁盘 默认值为空字符串,
		 */
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/octet-stream, application/exe, text/csv");
		/**
		 * browser.helperAssp.alwaysAsk.force对于未知的MIME类型文件会弹出窗口
		 * 让用户处理，默认值为true，设定为false表示不会记录打开未知类型的MIME类型文件
		 */
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		//browser.download.manager.useWindow设定下载是否显示下载框
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.closeWhenDone", false);
		return profile;

	}

}
