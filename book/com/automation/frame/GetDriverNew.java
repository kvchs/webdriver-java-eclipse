package com.automation.frame;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GetDriverNew {
	public static WebDriver driver;
	

	// 获取IE浏览器的Driver对象封装方法
	public static WebDriver getRemoteIEDriver(String remoteNodeUrl) throws MalformedURLException {
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setBrowserName("internetExplorer");
		capability.setPlatform(Platform.LINUX);
		driver = new RemoteWebDriver(new URL(remoteNodeUrl), capability);
		return driver;
	}

	// 获取Chrome浏览器的Driver对象封装方法
	public static WebDriver getRemoteChromeDriver(String remoteNodeUrl)
			throws MalformedURLException {
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.LINUX);
		driver = new RemoteWebDriver(new URL(remoteNodeUrl), capability);
		return driver;
	}

	// 获取Firefox浏览器的Driver对象封装方法
	public static WebDriver getRemoteFirefoxDriver(String remoteNodeUrl) throws MalformedURLException {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		capability.setPlatform(Platform.LINUX);
		driver = new RemoteWebDriver(new URL(remoteNodeUrl), capability);
		return driver;
	}

}
