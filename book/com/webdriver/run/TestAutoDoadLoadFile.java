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
		 * ����Firefox��browser.download.folderList������Ϊ2
		 * ���û�н�����ʽ���ã���ʹ��Ĭ��ֵ1����ʾ�����ļ�������"����"�ļ��� �趨Ϊ0���������ļ��ᱻ�������û���������
		 * �趨Ϊ2���������ļ��ᱻ������ָ�����ļ�����
		 * 
		 */
		profile.setPreference("browser.download.folderList", 2);
		/**
		 * prowser.download.manager.showWhenStarting������Ĭ��ֵΪtrue
		 * �趨Ϊtrue���û��������ص�ʱ����ʾFirefox��������ļ����ش���
		 * 
		 */
		profile.setPreference("browser.download.manager.showWhenStarting",
				false);
		profile.setPreference("browser.download.dir", downloadFilePath);
		/**
		 * browser.helperApps.neverAsk.openFile��ʾֱ�Ӵ������ļ�������ʾȷ�Ͽ�
		 * Ĭ��ֵΪ���ַ��������д������趨�˶����ļ���MIME���ͣ�
		 */
		profile.setPreference("browser.helperApps.neverAsk.openFile",
				"application/octet-stream, application/exe, text/csv");
		/**
		 * browser.helperApps.neverAsk.saveToDisk��ʾ�����ļ��Ƿ�ֱ�ӱ��浽���� Ĭ��ֵΪ���ַ���,
		 */
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/octet-stream, application/exe, text/csv");
		/**
		 * browser.helperAssp.alwaysAsk.force����δ֪��MIME�����ļ��ᵯ������
		 * ���û�����Ĭ��ֵΪtrue���趨Ϊfalse��ʾ�����¼��δ֪���͵�MIME�����ļ�
		 */
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		//browser.download.manager.useWindow�趨�����Ƿ���ʾ���ؿ�
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.closeWhenDone", false);
		return profile;

	}

}
