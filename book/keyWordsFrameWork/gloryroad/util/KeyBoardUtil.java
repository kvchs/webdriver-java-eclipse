package keyWordsFrameWork.gloryroad.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyBoardUtil {
	
	//��Tab���ķ�װ����
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
	
	//��Enter ���ķ�װ����
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
	 * ��ָ�����ַ�������Ϊ���а����ݣ�Ȼ��ִ��ճ������
	 * ��ҳ�潹���л��������󣬵��ô˺������Խ�ָ���ַ���
	 * ճ���������
	 * 
	 * @param string
	 */
	public static void setAndCtrlVClipboardData(String string){
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

}
