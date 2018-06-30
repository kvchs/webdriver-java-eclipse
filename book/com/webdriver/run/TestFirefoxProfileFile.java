//Win +R     input     firefox.exe -p
package com.webdriver.run;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestFirefoxProfileFile {
	WebDriver driver;
	String baseUrl;
	
	@AfterMethod
	public void afterMethod(){
//		driver.quit();
		
	}
	
	@BeforeMethod
	public void beforeMethod(){
		baseUrl = "http://www.sogou.com";
		
	}
	
	@Test
	public void testFirefoxProfile() throws InterruptedException{
//		FirefoxProfile profile = new FirefoxProfile();
//		ProfilesIni allProfiles = new ProfilesIni();
//		FirefoxProfile profile = allProfiles.getProfile("WebDriver");
		ProfilesIni allProfiles = new ProfilesIni();
		 
	    FirefoxProfile profile = allProfiles.getProfile("WebDriver");
		profile.setPreference("browser.startup.homepage", baseUrl);
		
		FirefoxOptions Options = new FirefoxOptions();
        Options.setProfile(profile);
        driver = new FirefoxDriver(Options);
        Thread.sleep(10000);
		WebElement searchInputBox = driver.findElement(By.id("query"));
		searchInputBox.sendKeys("webdriver");
		driver.findElement(By.id("stb")).click();
				
				
		
		
	}
}