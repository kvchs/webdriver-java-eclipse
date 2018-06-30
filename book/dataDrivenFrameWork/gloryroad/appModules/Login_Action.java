package dataDrivenFrameWork.gloryroad.appModules;

import org.openqa.selenium.WebDriver;

import com.webdriver.run.TestRobot;

import dataDrivenFrameWork.gloryroad.pageObjects.LoginPage;
import dataDrivenFrameWork.gloryroad.util.Log;

public class Login_Action {
	public static void execute(WebDriver driver, String username, String password) throws Exception{
		Log.info("������վ: https://mail.126.com/");
		driver.get("https://mail.126.com/");
		Thread.sleep(5000);
		driver.switchTo().frame("x-URS-iframe");
		LoginPage loginPage = new LoginPage(driver);
		TestRobot robot = new TestRobot();
		Log.info("��126�����¼ҳ�������û���" + username);
		robot.setAndCtrlVClipboardData(username);
		//loginPage.username().clear();
//		loginPage.username().click();
//		loginPage.username().sendKeys(username);
		//loginPage.password().clear();
		Log.info("��126�����¼ҳ����������" + password);
		loginPage.password().sendKeys(password);
		Log.info("������¼��ť");
		loginPage.loginButton().click();
		Log.info("������¼��ť�󣬵ȴ�һ��ʱ�䣬����������ҳ");
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
	}

}
