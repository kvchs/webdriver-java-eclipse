package keyWordsFrameWork.gloryroad.configuration;

import keyWordsFrameWork.gloryroad.data.Constants;
import keyWordsFrameWork.gloryroad.util.Log;
import keyWordsFrameWork.gloryroad.util.ObjectMap;
import keyWordsFrameWork.gloryroad.util.WaitUtil;
import static keyWordsFrameWork.gloryroad.util.WaitUtil.*;
import static keyWordsFrameWork.gloryroad.util.KeyBoardUtil.*;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import dataDrivenFrameWork.gloryroad.appModules.Login_Action;

public class KeyWordsAction {
	public static WebDriver driver;
	private static ObjectMap objectMap = new ObjectMap(Constants.Path_ConfigurationFile);
	
	
	static{
		//指定Log4j配置文件
		DOMConfigurator.configure(System.getProperty("user.dir") + "\\KeyWordsFrameWork\\log4j.xml");
	}

	// 此方法名称对于Excel关键字列中的open_browser关键字
	public static void open_browser(String browserName) {
		System.out.println(browserName);
		if (browserName.toLowerCase().equals("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir")
							+ "\\tools\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			Log.info("IE浏览器的实例已经声名");
		} else if (browserName.toLowerCase().equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\tools\\geckodriver.exe");
			driver = new FirefoxDriver();
			Log.info("Firefox浏览器的实例已经声名");
		} else if(browserName.toLowerCase().equals("chrome")){
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")
							+ "\\tools\\chromedriver.exe");
			driver = new ChromeDriver();
			Log.info("Chrome浏览器的实例已经声名");
		}
	}

	// 此方法名称对于Excel关键字列中的navigate关键字
	// 读取Excel文件"操作值" 列中的网站地址内容作为访问的网址
	public static void navigate(String url) {
		Log.info("访问网址" + url);
		driver.get(url);
		driver.manage().window().maximize();
	}

	// 此方法名称对于Excel关键字列中的switch_to_frame关键字
		public static void switch_to_frame(String frameId) {
			System.out.println("收到的参数值: " + frameId);
			try {
				driver.switchTo().frame(frameId);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	
	// 此方法名称对于Excel关键字列中的input_username关键字
	public static void input_username(String userName) {
		System.out.println("收到的参数值: " + userName);
		try {
			Log.info("输入用户名: " + userName);
			driver.findElement(
					objectMap.getLocator("126mail.loginPage.username")).clear();
			driver.findElement(
					objectMap.getLocator("126mail.loginPage.username"))
					.sendKeys(userName);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 此方法名称对于Excel关键字列中的input_password关键字
	public static void input_password(String password) {
		System.out.println("收到的参数值: " + password);
		try {
//			driver.findElement(
//					objectMap.getLocator("126mail.loginPage.password"))
//					.sendKeys(password);
			press_tab();
			setAndCtrlVClipboardData(password);
			Log.info("输入密码");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.info("在密码输入框输入密码出现异常：" + e.getMessage());
		}
	}

	// 此方法名称对于Excel关键字列中的click_login关键字
	public static void click_login() throws InterruptedException {
		System.out.println("run function click_login()");
		try {
			driver.findElement(
					objectMap.getLocator("126mail.loginPage.loginbutton"))
					.click();
			Log.info("单击登录按钮");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		Thread.sleep(10000);
	}
	
	// 此方法名称对于Excel关键字列中的switch_to_default关键字
			public static void switch_to_default() {
				try {
					driver.switchTo().defaultContent();

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

	// 此方法名称对于Excel关键字列中的WaitFor_Element关键字
	public static void WaitFor_Element(String xpathExpression) {
		try {
			waitWebElement(driver, objectMap.getLocator(xpathExpression));
			Log.info("等待logo元素存在");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 此方法名称对于Excel关键字列中的click_writeLetterLink关键字
	public static void click_writeLetterLink() {
		try {
			driver.findElement(objectMap.getLocator("homepage.writeLetterLink"))
					.click();
			Log.info("点击写信按钮");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 此方法名称对于Excel关键字列中的input_recipients关键字
	public static void input_recipients(String recipients) {
		System.out.println("收到的参数值: " + recipients);
		recipients = "testemaillogin@126.com";
		try {
			setAndCtrlVClipboardData(recipients);
			Log.info("输入收件人邮箱");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 此方法名称对于Excel关键字列中的input_mailSubject关键字
	public static void input_mailSubject(String mailSubject) {
		System.out.println("收到的参数值: " + mailSubject);
		try {
			driver.findElement(
					objectMap.getLocator("writemailpage.mailsubject"))
					.sendKeys(mailSubject);
			Log.info("输入邮件主题");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 此方法名称对于Excel关键字列中的press_tab关键字
	public static void press_tab() {
		try {
			Thread.sleep(2000);
			pressTabKey();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 此方法名称对于Excel关键字列中的paste_mailContent关键字
	public static void paste_mailContent(String mailContent) {
		System.out.println("收到的参数值: " + mailContent);
		try {
			setAndCtrlVClipboardData(mailContent);
			Log.info("输入邮件正文");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 此方法名称对于Excel关键字列中的click_addAttachment关键字
	public static void click_addAttachment() {
		try {
			driver.findElement(
					objectMap.getLocator("writemailpage.addattachmentlink"))
					.click();
			Log.info("单击上传附件功能");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 此方法名称对于Excel关键字列中的paste_uploadFileName关键字
	public static void paste_uploadFileName(String uladFileName) {
		System.out.println("收到的参数值: " + uladFileName);
		try {
			setAndCtrlVClipboardData(uladFileName);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 此方法名称对于Excel关键字列中的press_tab关键字
	public static void press_enter() {
		try {
			pressEnterKey();
			Log.info("上传附件");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 此方法名称对于Excel关键字列中的sleep关键字
	public static void sleep() {
		try {
			WaitUtil.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 此方法名称对于Excel关键字列中的click_addAttachment关键字
	public static void click_sendMailButton() throws InterruptedException {
		try {
			driver.findElements(
					objectMap.getLocator("writemailpage.sendmailbutton"))
					.get(0).click();
			Log.info("点击发送邮件按钮");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Thread.sleep(5000);
	}

	// 此方法名称对于Excel关键字列中的Assert_String关键字
	public static void Assert_String(String assertString) {
		try {
			Assert.assertTrue(driver.getPageSource().contains(assertString));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("断言失败");
		}
	}

	// 此方法名称对于Excel关键字列中的close_browser关键字
	public static void close_browser() {
		try {
			driver.quit();
			Log.info("关闭浏览器");
		} catch (Exception e) {
			// TODO: handle exception
			Log.info("关闭浏览器出现异常，具体异常信息: " + e.getMessage());
			e.printStackTrace();
			System.out.println("断言失败");
		}
	}

}
