/**
 * LoadableComponent
��page object pattern�ÿ��ҳ�涼�����漰�ж�ҳ��Ԫ���Ƿ������ɵ����⡣���ڶ�̬����ҳ����˵��
����������ܷ�����Ҳ�����ۡ�selenium �ṩ�� LoadableComponent �����࣬��Ϊpage object pattern����չ��
�ṩ��һ�������ŵĽ��������

���巽��Ϊ

1.������ʱ�̳�LoadableComponent<>��
2.��дload������isLoaded����
3.����������get��������ҳ��
load�����ڰ������Ƿ���ҳ��Ĳ�����isLoaded�����ڰ������Ǽ��ҳ��Ԫ���Ƿ���سɹ��Ĳ�����
��Դ������Կ�����get�������ȵ���isLoaded�����ж��Ƿ���سɹ���û�гɹ��Ļ��Ჶ��Error��
Ȼ�����load�������أ�֮����ٴε���isLoaded�����жϣ������ء�����ڶ��μ�����δ�ɹ�����׳�Error��
 */

package cn.gloryroad.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

public class LoginPage extends LoadableComponent<LoginPage> {
	// ʹ��FindByע�ͣ���λ����Ҫ������ҳ��Ԫ��
	@FindBy(xpath = "//*[@id='account-box']/div[2]/input")
	public WebElement userName;

	@FindBy(css = "[autocomplete='new-password']")
	public WebElement password;

	@FindBy(id = "dologin")
	public WebElement loginButton;

	// ���캯����������������󣬳�ʼ��PageFactory����
	private String url = "https://mail.126.com/";
	public WebDriver driver;
	private String title = "���������";

	public LoginPage(WebDriver driver) {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\tools\\chromedriver.exe");

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ���ʱ�������ַ�ķ�װ����

	// public void load(){
	// driver.get(url);
	// }

	// ��������Ҫ���ǵķ���load
	@Override
	protected void load() {
		this.driver.get(url);
	}

	// �ر������
	public void quit() {
		driver.quit();
	}

	// ��¼������װ
	public void login() throws InterruptedException {
		driver.switchTo().frame("x-URS-iframe");
		userName.clear();
		userName.sendKeys("testemaillogin");
		password.clear();
		password.sendKeys("********");
		loginButton.click();
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
//		return new HomePage(driver);
	}

	public WebDriver getDriver() {
		return driver;
	}

	// ��������Ҫ���ǵķ���isLoaded
	@Override
	protected void isLoaded() {
		System.out.println("isLoaded()�����Ѿ��Զ���������");
		Assert.assertTrue(driver.getTitle().contains(title));
	}
	
	public String getPageSource(){
		return driver.getPageSource();
	}
	
	public LoginPage LoginExpectingFailure(){
//		load();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/h1/a/img")));
		driver.switchTo().frame("x-URS-iframe");
		userName.clear();
		userName.sendKeys("testemaillogin");
		password.clear();
		password.sendKeys("123456");
		loginButton.click();
		//��¼ʧ�ܺ�ҳ�治����ת����������һ��LoginPage����
		return new LoginPage(driver);
	}

}
