
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
	
	
	//���Գɹ��ĵ�¼����
	@Test
	public void testLogin() throws InterruptedException {
		
		LoginPage loginpage = new LoginPage(driver);
		/**
		 * �̳�LoadableComponent���ֻҪʵ���˸��ǵ�load��������ʹ��û�ж���get�����������
		 * Ҳ���Խ���get�����ĵ��ã�get������Ĭ�ϵ���ҳ��������е�load����
		 */
		loginpage.get();
		loginpage.login();
		Thread.sleep(5000);
		Assert.assertTrue(loginpage.getDriver().getPageSource().contains("δ���ʼ�"));
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
	
	//���Ե�¼ʧ�ܵĲ�������
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
	
	
	//���Է����ʼ��ɹ��Ĳ�������
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
