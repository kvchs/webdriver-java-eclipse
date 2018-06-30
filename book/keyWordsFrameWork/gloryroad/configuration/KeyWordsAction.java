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
		//ָ��Log4j�����ļ�
		DOMConfigurator.configure(System.getProperty("user.dir") + "\\KeyWordsFrameWork\\log4j.xml");
	}

	// �˷������ƶ���Excel�ؼ������е�open_browser�ؼ���
	public static void open_browser(String browserName) {
		System.out.println(browserName);
		if (browserName.toLowerCase().equals("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir")
							+ "\\tools\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			Log.info("IE�������ʵ���Ѿ�����");
		} else if (browserName.toLowerCase().equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\tools\\geckodriver.exe");
			driver = new FirefoxDriver();
			Log.info("Firefox�������ʵ���Ѿ�����");
		} else if(browserName.toLowerCase().equals("chrome")){
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")
							+ "\\tools\\chromedriver.exe");
			driver = new ChromeDriver();
			Log.info("Chrome�������ʵ���Ѿ�����");
		}
	}

	// �˷������ƶ���Excel�ؼ������е�navigate�ؼ���
	// ��ȡExcel�ļ�"����ֵ" ���е���վ��ַ������Ϊ���ʵ���ַ
	public static void navigate(String url) {
		Log.info("������ַ" + url);
		driver.get(url);
		driver.manage().window().maximize();
	}

	// �˷������ƶ���Excel�ؼ������е�switch_to_frame�ؼ���
		public static void switch_to_frame(String frameId) {
			System.out.println("�յ��Ĳ���ֵ: " + frameId);
			try {
				driver.switchTo().frame(frameId);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	
	// �˷������ƶ���Excel�ؼ������е�input_username�ؼ���
	public static void input_username(String userName) {
		System.out.println("�յ��Ĳ���ֵ: " + userName);
		try {
			Log.info("�����û���: " + userName);
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

	// �˷������ƶ���Excel�ؼ������е�input_password�ؼ���
	public static void input_password(String password) {
		System.out.println("�յ��Ĳ���ֵ: " + password);
		try {
//			driver.findElement(
//					objectMap.getLocator("126mail.loginPage.password"))
//					.sendKeys(password);
			press_tab();
			setAndCtrlVClipboardData(password);
			Log.info("��������");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.info("�����������������������쳣��" + e.getMessage());
		}
	}

	// �˷������ƶ���Excel�ؼ������е�click_login�ؼ���
	public static void click_login() throws InterruptedException {
		System.out.println("run function click_login()");
		try {
			driver.findElement(
					objectMap.getLocator("126mail.loginPage.loginbutton"))
					.click();
			Log.info("������¼��ť");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		Thread.sleep(10000);
	}
	
	// �˷������ƶ���Excel�ؼ������е�switch_to_default�ؼ���
			public static void switch_to_default() {
				try {
					driver.switchTo().defaultContent();

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

	// �˷������ƶ���Excel�ؼ������е�WaitFor_Element�ؼ���
	public static void WaitFor_Element(String xpathExpression) {
		try {
			waitWebElement(driver, objectMap.getLocator(xpathExpression));
			Log.info("�ȴ�logoԪ�ش���");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// �˷������ƶ���Excel�ؼ������е�click_writeLetterLink�ؼ���
	public static void click_writeLetterLink() {
		try {
			driver.findElement(objectMap.getLocator("homepage.writeLetterLink"))
					.click();
			Log.info("���д�Ű�ť");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// �˷������ƶ���Excel�ؼ������е�input_recipients�ؼ���
	public static void input_recipients(String recipients) {
		System.out.println("�յ��Ĳ���ֵ: " + recipients);
		recipients = "testemaillogin@126.com";
		try {
			setAndCtrlVClipboardData(recipients);
			Log.info("�����ռ�������");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// �˷������ƶ���Excel�ؼ������е�input_mailSubject�ؼ���
	public static void input_mailSubject(String mailSubject) {
		System.out.println("�յ��Ĳ���ֵ: " + mailSubject);
		try {
			driver.findElement(
					objectMap.getLocator("writemailpage.mailsubject"))
					.sendKeys(mailSubject);
			Log.info("�����ʼ�����");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// �˷������ƶ���Excel�ؼ������е�press_tab�ؼ���
	public static void press_tab() {
		try {
			Thread.sleep(2000);
			pressTabKey();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// �˷������ƶ���Excel�ؼ������е�paste_mailContent�ؼ���
	public static void paste_mailContent(String mailContent) {
		System.out.println("�յ��Ĳ���ֵ: " + mailContent);
		try {
			setAndCtrlVClipboardData(mailContent);
			Log.info("�����ʼ�����");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// �˷������ƶ���Excel�ؼ������е�click_addAttachment�ؼ���
	public static void click_addAttachment() {
		try {
			driver.findElement(
					objectMap.getLocator("writemailpage.addattachmentlink"))
					.click();
			Log.info("�����ϴ���������");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// �˷������ƶ���Excel�ؼ������е�paste_uploadFileName�ؼ���
	public static void paste_uploadFileName(String uladFileName) {
		System.out.println("�յ��Ĳ���ֵ: " + uladFileName);
		try {
			setAndCtrlVClipboardData(uladFileName);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// �˷������ƶ���Excel�ؼ������е�press_tab�ؼ���
	public static void press_enter() {
		try {
			pressEnterKey();
			Log.info("�ϴ�����");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// �˷������ƶ���Excel�ؼ������е�sleep�ؼ���
	public static void sleep() {
		try {
			WaitUtil.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// �˷������ƶ���Excel�ؼ������е�click_addAttachment�ؼ���
	public static void click_sendMailButton() throws InterruptedException {
		try {
			driver.findElements(
					objectMap.getLocator("writemailpage.sendmailbutton"))
					.get(0).click();
			Log.info("��������ʼ���ť");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Thread.sleep(5000);
	}

	// �˷������ƶ���Excel�ؼ������е�Assert_String�ؼ���
	public static void Assert_String(String assertString) {
		try {
			Assert.assertTrue(driver.getPageSource().contains(assertString));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("����ʧ��");
		}
	}

	// �˷������ƶ���Excel�ؼ������е�close_browser�ؼ���
	public static void close_browser() {
		try {
			driver.quit();
			Log.info("�ر������");
		} catch (Exception e) {
			// TODO: handle exception
			Log.info("�ر�����������쳣�������쳣��Ϣ: " + e.getMessage());
			e.printStackTrace();
			System.out.println("����ʧ��");
		}
	}

}
