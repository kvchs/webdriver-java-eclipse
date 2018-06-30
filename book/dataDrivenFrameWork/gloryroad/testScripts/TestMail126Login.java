/**
 * 
 Selenium�¸������driver������
��������
System.setProperty("webdriver.firefox.bin","C:\\ProgramFiles\\MozillaFirefox\\firefox.exe");
driver = new FirefoxDriver();
��� ff �İ�װ·������Ĭ��·��������£���Ҫ����


IE�����
System.setProperty("webdriver.ie.driver","C:\\IEDriverServer_32.exe");
driver = new InternetExplorerDriver();
IEDriverServer_32.exe �� IE ������� Driver ����

Chrome�����
System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
driver = new ChromeDriver();
chromedriver.exe �� Chrome ������� Driver ����


��Ҫע����ǻ��������ܷ�������ȡ����selenium֪��֧�ֵ�ǰ�Ļ���汾
IEһ�㶼���� ������
Chrome�ܷ����� ȡ���� ChromeDriver�İ汾�ܷ�֧�ֵ����chrome������İ汾 

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
//		Assert.assertTrue(driver.getPageSource().contains("д��"));
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
