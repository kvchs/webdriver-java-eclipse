package seleniumAutomation;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class SecondTestNGDemo {
	
	public WebDriver driver;
	String URL = "http://www.sougou.com";

  @Test
  public void testSearch() {
	driver.get(URL);
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
