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
	 * Robot对象操作键盘
	 * 能够通过Robot对象操作键盘上的按键完成复制，粘贴，切换焦点和回车等常用操作
	 * 
	 */
	WebDriver driver;
	String Url;
	
	
	@Test
	public void testRobotOperateKeyboard() throws InterruptedException {
		driver.get(Url);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("query")));
		setAndCtrlVClipboardData("自动化");
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
		//声名StringSelection对象，并使用函数的String参数完成实例化
		StringSelection stringSelection = new StringSelection(string);
		//使用Toolkit对象的setContents方法将字符串放到剪切板中
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		//声名Robot对象
		Robot robot = null;
		try {
			robot = new Robot();
			
		} catch (AWTException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//调用keyPress方法实现按下Ctrl键
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		//按下V键
		robot.keyPress(KeyEvent.VK_V);
		//释放V键
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
