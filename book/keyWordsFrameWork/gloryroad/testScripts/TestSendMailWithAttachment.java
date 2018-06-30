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
 * ��̬���ã����Բ�д�����Ƶ�����£�ֱ�ӵ������еľ�̬������eg. pressTabKey();
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
		waitWebElement(driver, "//div[@id='_dvModuleContainer_compose.ComposeModule_0']/div[@class='frame-main-cont-body nui-scroll']//header[@class='qa0']//a[@title='�������ռ���']");
		WebElement recipients = driver.findElement(By.xpath("//div[@id='_dvModuleContainer_compose.ComposeModule_0']/div[@class='frame-main-cont-body nui-scroll']//header[@class='qa0']//div[@title='��������ʱ��ַ���ԷֺŸ���']/label[@class='js-component-emailtips nui-ipt-placeholder']"));
		WebElement mailSubject = driver.findElement(By.xpath("//div[@aria-label='�ʼ�����������������ʼ�����']/input"));
//		recipients.sendKeys("test@qq.com");
		setAndCtrlVClipboardData("test@qq.com");
		mailSubject.sendKeys("�����ʼ��ı���");
		pressTabKey();
		setAndCtrlVClipboardData("����һ���Զ������͵��ʼ�");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@title='һ�οɷ���2000����Ƭ��600��MP3��һ�������Ӱ']")).click();
		sleep(2000);
		setAndCtrlVClipboardData("D:\\BugReport.txt");
		sleep(2000);
		pressEnterKey();
		sleep(4000);
		driver.findElements(By.cssSelector(".jp0 [role='button']:nth-of-type(1) .nui-btn-text")).get(0).click();
		sleep(5000);
		waitWebElement(driver, By.xpath("//div[@id='_dvModuleContainer_compose.ComposeModule_0']//section[@class='sQ1']/h1[@role='tooltip']"));
		
		Assert.assertTrue(driver.getPageSource().contains("���ͳɹ�"));
		
		
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
