/**
 * 
 Selenium下各浏览器driver的配置
火狐浏览器
System.setProperty("webdriver.firefox.bin","C:\\ProgramFiles\\MozillaFirefox\\firefox.exe");
driver = new FirefoxDriver();
如果 ff 的安装路径不是默认路径的情况下，需要配置


IE浏览器
System.setProperty("webdriver.ie.driver","C:\\IEDriverServer_32.exe");
driver = new InternetExplorerDriver();
IEDriverServer_32.exe 是 IE 浏览器的 Driver 驱动

Chrome浏览器
System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
driver = new ChromeDriver();
chromedriver.exe 是 Chrome 浏览器的 Driver 驱动


需要注意的是火狐浏览器能否启动，取决于selenium知否支持当前的火狐版本
IE一般都是能 启动的
Chrome能否启动 取决于 ChromeDriver的版本能否支持到你的chrome浏览器的版本 

 */
package dataDrivenFrameWork.gloryroad.testScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.mysql.jdbc.Driver;

import dataDrivenFrameWork.gloryroad.appModules.Login_Action;
import dataDrivenFrameWork.gloryroad.pageObjects.LoginPage;

public class TestMail126Login {
	public WebDriver driver;
	String baseUrl = "https://mail.126.com/";
	
	
	@Test
	public void testMailLogin() throws Exception {
//		driver.get(baseUrl);
//		LoginPage loginPage = new LoginPage(driver);
//		loginPage.username().clear();
//		loginPage.username().sendKeys("testemaillogin");
//		loginPage.password().clear();
//		loginPage.password().sendKeys("ma1111de");
//		loginPage.loginButton().click();
//		Thread.sleep(5000);
//		Assert.assertTrue(driver.getPageSource().contains("写信"));
		Login_Action.execute(driver, "testemaillogin", "ma1111de");
	}

	@BeforeMethod
	public void beforeMethod() {
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\tools\\chromedriver.exe");
//		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\tools\\IEDriverServer.exe");
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\tools\\geckodriver.exe");

		driver = new InternetExplorerDriver();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
