package seleniumAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class FirstTestNGDemo2 {
	public WebDriver driver;
	String BaseURl = "http://www.sogou.com";

	@Test
	public void testSearch() {
		driver.get(BaseURl);
		driver.findElement(By.id("query")).sendKeys("selenium");
		driver.findElement(By.id("stb")).click();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
