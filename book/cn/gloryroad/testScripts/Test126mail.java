
package cn.gloryroad.testScripts;

import org.apache.poi.hpbf.HPBFDocument;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cn.gloryroad.pageobjects.HomePage;
import cn.gloryroad.pageobjects.LoginPage;

public class Test126mail {
	public WebDriver driver;
	
	
	//测试成功的登录用例
	@Test
	public void testLogin() throws InterruptedException {
		
		LoginPage loginpage = new LoginPage(driver);
		/**
		 * 继承LoadableComponent类后，只要实现了覆盖的load方法，即使在没有定义get方法的情况下
		 * 也可以进行get方法的调用，get方法会默认调用页面对象类中的load方法
		 */
		loginpage.get();
		loginpage.login();
		Thread.sleep(5000);
		Assert.assertTrue(loginpage.getDriver().getPageSource().contains("未读邮件"));
		loginpage.quit();
		
	}
	
	@BeforeMethod
	public void beforeMethod(){
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\tools\\chromedriver.exe");

		driver = new ChromeDriver();
	}
	
	@AfterMethod
	public void afterMehtod(){
		driver.quit();
	}
	
	//测试登录失败的测试用例
	@Test
	public void testLoginFail(){
		LoginPage loginpage = new LoginPage(driver);
		loginpage.get();
		loginpage.LoginExpectingFailure();
		ElementExist(driver, By.xpath("//div[@id='nerror']/div[@class='ferrorhead']"));
		loginpage.quit();
	}
	
	public boolean ElementExist(WebDriver driver,By locator)
    {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
	
	
	//测试发送邮件成功的测试用例
	@Test
	public void testWriteMail() throws InterruptedException{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.get();
		loginPage.login();
		Thread.sleep(5000);
		HomePage homepage = new HomePage(driver);
		homepage.writeMail();
		homepage.close();
	}

}
