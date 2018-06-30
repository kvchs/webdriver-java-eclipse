package keyWordsFrameWork.gloryroad.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyBoardUtil {
	
	//按Tab键的封装方法
	public static void pressTabKey(){
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
	
	//按Enter 键的封装方法
	public static void pressEnterKey(){
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
	
	/**
	 * 将指定的字符串设置为剪切板内容，然后执行粘贴操作
	 * 将页面焦点切换到输入框后，调用此函数可以将指定字符串
	 * 粘贴到输入框
	 * 
	 * @param string
	 */
	public static void setAndCtrlVClipboardData(String string){
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

}
