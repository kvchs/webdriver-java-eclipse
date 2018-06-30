package com.webdriver.run;

import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;





public class TestRobot {
	/**
	 * Robot�����������
	 * �ܹ�ͨ��Robot������������ϵİ�����ɸ��ƣ�ճ�����л�����ͻس��ȳ��ò���
	 * 
	 */
	WebDriver driver;
	String Url;
	
	
	@Test
	public void testRobotOperateKeyboard() throws InterruptedException {
		driver.get(Url);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("query")));
		setAndCtrlVClipboardData("�Զ���");
		pressTabKey();
		pressEnterKey();
		Thread.sleep(3000);
		
		
		
	}

	@BeforeMethod
	public void beforeMethod() {
		Url = "http://www.sogou.com";
		driver = new FirefoxDriver();
		
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	public void setAndCtrlVClipboardData(String string){
		//����StringSelection���󣬲�ʹ�ú�����String�������ʵ����
		StringSelection stringSelection = new StringSelection(string);
		//ʹ��Toolkit�����setContents�������ַ����ŵ����а���
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		//����Robot����
		Robot robot = null;
		try {
			robot = new Robot();
			
		} catch (AWTException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//����keyPress����ʵ�ְ���Ctrl��
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		//����V��
		robot.keyPress(KeyEvent.VK_V);
		//�ͷ�V��
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
	}
	
	public void pressTabKey(){
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}
	
	public void pressEnterKey(){
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

}
