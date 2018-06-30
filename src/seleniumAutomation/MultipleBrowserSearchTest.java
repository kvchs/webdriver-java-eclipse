package seleniumAutomation;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;


public class MultipleBrowserSearchTest {
	public WebDriver driver;
	String baseUrl = "http://www.sogou.com";
	@Parameters("browser")
	@BeforeClass
	public void beforeTest(String Browser){
		if(Browser.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}else if(Browser.equalsIgnoreCase("ie")){
			driver = new InternetExplorerDriver();
		}else{
			driver = new ChromeDriver();
		}
		driver.get("");
	}
	
	@Test
	public void testSogouSearch(){
		driver.get(baseUrl);
		WebElement inputBox = driver.findElement(By.id("query"));
		Assert.assertTrue(inputBox.isDisplayed());
		inputBox.sendKeys("automation");
		driver.findElement(By.id("stb")).click();
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		Assert.assertTrue(driver.getPageSource().contains("automation"));
	}
	
	@AfterClass
	public void afterTest(){
		driver.quit();
	}

}
