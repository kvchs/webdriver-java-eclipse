package dataDrivenFrameWork.gloryroad.appModules;

import org.openqa.selenium.WebDriver;

import com.webdriver.run.TestRobot;

import dataDrivenFrameWork.gloryroad.pageObjects.LoginPage;
import dataDrivenFrameWork.gloryroad.util.Log;

public class Login_Action {
	public static void execute(WebDriver driver, String username, String password) throws Exception{
		Log.info("访问网站: https://mail.126.com/");
		driver.get("https://mail.126.com/");
		Thread.sleep(5000);
		driver.switchTo().frame("x-URS-iframe");
		LoginPage loginPage = new LoginPage(driver);
		TestRobot robot = new TestRobot();
		Log.info("在126邮箱登录页面输入用户名" + username);
		robot.setAndCtrlVClipboardData(username);
		//loginPage.username().clear();
//		loginPage.username().click();
//		loginPage.username().sendKeys(username);
		//loginPage.password().clear();
		Log.info("在126邮箱登录页面输入密码" + password);
		loginPage.password().sendKeys(password);
		Log.info("单击登录按钮");
		loginPage.loginButton().click();
		Log.info("单击登录按钮后，等待一段时间，进入邮箱主页");
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
	}

}
