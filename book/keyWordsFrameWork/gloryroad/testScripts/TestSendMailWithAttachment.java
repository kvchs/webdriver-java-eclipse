package keyWordsFrameWork.gloryroad.testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import static keyWordsFrameWork.gloryroad.util.KeyBoardUtil.*;
import static keyWordsFrameWork.gloryroad.util.WaitUtil.*;
/**
 * 
 * 静态引用，可以不写类名称的情况下，直接调用类中的静态方法：eg. pressTabKey();
 *
 */

public class TestSendMailWithAttachment {
	WebDriver driver;
	String baseUrl = "https://mail.126.com";
	
	@Test
	public void testSendMailWithAttachment() throws InterruptedException {
		driver.get(baseUrl);
		driver.switchTo().frame("x-URS-iframe");
		WebElement userName = driver.findElement(By.xpath("//div[@id='account-box']//input[@name='email']"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.id("dologin"));
		userName.clear();
		userName.sendKeys("testemaillogin");
		password.clear();
		password.sendKeys("ma1111de");
		loginButton.click();
		try {
			Thread.sleep(5000);
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		waitWebElement(driver, "/html//div[@class='tY0']/ul[1]/li[18]/a[@title='']");
		WebElement writeMailLink = driver.findElement(By.xpath("//div[@id='dvNavTop']/ul[@class='js-component-component tJ0']/li[2]/span[@class='oz0']"));
		writeMailLink.click();
		waitWebElement(driver, "//div[@id='_dvModuleContainer_compose.ComposeModule_0']/div[@class='frame-main-cont-body nui-scroll']//header[@class='qa0']//a[@title='点击添加收件人']");
		WebElement recipients = driver.findElement(By.xpath("//div[@id='_dvModuleContainer_compose.ComposeModule_0']/div[@class='frame-main-cont-body nui-scroll']//header[@class='qa0']//div[@title='发给多人时地址请以分号隔开']/label[@class='js-component-emailtips nui-ipt-placeholder']"));
		WebElement mailSubject = driver.findElement(By.xpath("//div[@aria-label='邮件主题输入框，请输入邮件主题']/input"));
//		recipients.sendKeys("test@qq.com");
		setAndCtrlVClipboardData("test@qq.com");
		mailSubject.sendKeys("这是邮件的标题");
		pressTabKey();
		setAndCtrlVClipboardData("这是一封自动化发送的邮件");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@title='一次可发送2000张照片，600首MP3，一部高清电影']")).click();
		sleep(2000);
		setAndCtrlVClipboardData("D:\\BugReport.txt");
		sleep(2000);
		pressEnterKey();
		sleep(4000);
		driver.findElements(By.cssSelector(".jp0 [role='button']:nth-of-type(1) .nui-btn-text")).get(0).click();
		sleep(5000);
		waitWebElement(driver, By.xpath("//div[@id='_dvModuleContainer_compose.ComposeModule_0']//section[@class='sQ1']/h1[@role='tooltip']"));
		
		Assert.assertTrue(driver.getPageSource().contains("发送成功"));
		
		
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\tools\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
