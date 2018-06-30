
/**
 * 
 * @author Administrator
 * TestNG≥£”√∂œ—‘
 * assertTrue
 * assertFalse
 * assertSame
 * assertNotSame
 * assertNull
 * assertNotNull
 * assertEquals
 * assertNotEquals
 * assertEqualsNoOrder
 *
 */
package seleniumAutomation;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AssertTest {
	public WebDriver driver;
	String urlString = "http://www.baidu.com";
	@Test
	public void testSougouSearch() throws InterruptedException{
		driver.get(urlString);
		WebElement inputBox = driver.findElement(By.xpath("//input[@id='su']"));
		Assert.assertTrue(inputBox.isDisplayed());
		inputBox.sendKeys("java");
		
	}
	
	@BeforeMethod
	public void beforeMethod(){
		driver = new FirefoxDriver();
	}
	
	@AfterMethod
	public void afterMethod(){
		driver.quit();
	}

}
