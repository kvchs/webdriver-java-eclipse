package com.webdriver.run;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class FirstWebDriverDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver;
		String baseUrl;
		//无法打开的时候可以用下面的语句
//		System.setProperty("webdriver.firefox.bin", "firefox.ext path");
		
		driver = new InternetExplorerDriver();
		baseUrl = "http://www.sogou.com";
		driver.get(baseUrl);
		driver.findElement(By.id("query")).sendKeys("自动化测试");
		
		driver.findElement(By.id("stb")).click();
		
		driver.quit();
		
		

	}

}
