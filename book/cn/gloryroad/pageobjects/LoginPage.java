/**
 * LoadableComponent
在page object pattern里，每个页面都可能涉及判断页面元素是否加载完成的问题。对于动态加载页面来说，
这个工作量很繁琐，也不美观。selenium 提供了 LoadableComponent 基础类，作为page object pattern的扩展，
提供更一致且优雅的解决方法。

具体方法为

1.声明类时继承LoadableComponent<>类
2.覆写load方法和isLoaded方法
3.调用类对象的get方法访问页面
load方法内包含的是访问页面的操作，isLoaded方法内包含的是检查页面元素是否加载成功的操作。
从源代码可以看出，get方法首先调用isLoaded方法判断是否加载成功，没有成功的话会捕获Error，
然后调用load方法加载，之后会再次调用isLoaded方法判断，并返回。如果第二次加载仍未成功则会抛出Error。
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
	// 使用FindBy注释，定位到需要操作的页面元素
	@FindBy(xpath = "//*[@id='account-box']/div[2]/input")
	public WebElement userName;

	@FindBy(css = "[autocomplete='new-password']")
	public WebElement password;

	@FindBy(id = "dologin")
	public WebElement loginButton;

	// 构造函数，生成浏览器对象，初始化PageFactory对象
	private String url = "https://mail.126.com/";
	public WebDriver driver;
	private String title = "网易免费邮";

	public LoginPage(WebDriver driver) {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\tools\\chromedriver.exe");

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// 访问被测试网址的封装方法

	// public void load(){
	// driver.get(url);
	// }

	// 增加了需要覆盖的方法load
	@Override
	protected void load() {
		this.driver.get(url);
	}

	// 关闭浏览器
	public void quit() {
		driver.quit();
	}

	// 登录操作封装
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

	// 增加了需要覆盖的方法isLoaded
	@Override
	protected void isLoaded() {
		System.out.println("isLoaded()方法已经自动被调用了");
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
		//登录失败后，页面不会跳转，函数返回一个LoginPage对象
		return new LoginPage(driver);
	}

}
