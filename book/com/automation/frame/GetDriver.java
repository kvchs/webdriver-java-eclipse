package com.automation.frame;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GetDriver {
	public static WebDriver driver;
	public static String nodeUrl = "http://192.168.0.106:6655/wd/hub";

	// ��ȡIE�������Driver�����װ����
	public static WebDriver getRemoteIEDriver() throws MalformedURLException {
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setBrowserName("internetExplorer");
		capability.setPlatform(Platform.LINUX);
		driver = new RemoteWebDriver(new URL(nodeUrl), capability);
		return driver;
	}

	// ��ȡChrome�������Driver�����װ����
	public static WebDriver getRemoteChromeDriver()
			throws MalformedURLException {
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.LINUX);
		driver = new RemoteWebDriver(new URL(nodeUrl), capability);
		return driver;
	}

	// ��ȡFirefox�������Driver�����װ����
	public static WebDriver getRemoteFirefoxDriver() throws MalformedURLException {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		capability.setPlatform(Platform.LINUX);
		driver = new RemoteWebDriver(new URL(nodeUrl), capability);
		return driver;
	}

}
