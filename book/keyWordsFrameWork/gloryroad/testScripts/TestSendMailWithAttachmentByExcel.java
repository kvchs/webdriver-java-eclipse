package keyWordsFrameWork.gloryroad.testScripts;

import static org.testng.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import keyWordsFrameWork.gloryroad.configuration.KeyWordsAction;
import keyWordsFrameWork.gloryroad.data.Constants;
import keyWordsFrameWork.gloryroad.util.ExcelUtil;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;



public class TestSendMailWithAttachmentByExcel {
	public static Method[] method;
	public static String keywords;
	public static String value;
	public static KeyWordsAction keyWordsaction;
	public static Method[] method2;

	@Test
	public void testSendMailWithAttachmentByExcel() throws Exception {
		// ����һ���ؼ��������ʵ��
		keyWordsaction = new KeyWordsAction();
		// ʹ��Java�ķ�����ƻ�ȡKeyWordsaction���е����з�������
		method = keyWordsaction.getClass().getMethods();

		String excelFilePathString = Constants.Path_ExcelFile;
		ExcelUtil.setExcelFile(excelFilePathString, Constants.Sheet_TestSteps);
		System.out.println(ExcelUtil.getLastColumnNum());
		for (int iRow = 1; iRow <= 24; iRow++) {
			
			keywords = ExcelUtil.getCellData(iRow, 3);
			//System.out.println(keywords + "=====");
			value = ExcelUtil.getCellData(iRow, 4);
			execute_Actions();
		}
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\tools\\chromedriver.exe");

	}

	@AfterMethod
	public void afterMethod() {
	}

	private static void execute_Actions() throws InterruptedException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			//ͨ���������жϹؼ��ֺ�KeyWordsaction���е���һ����������һ��
			for (int i = 0; i < method.length; i++) {
				//System.out.println(keywords + "======keywords====="+ i);
				//System.out.println(method[i].getName() + "======getName====="+ i);
				if (method[i].getName().equals(keywords)) {
					//�ҵ�KeyWordsaction���е�ӳ�䷽����ͨ������ invoke ������ɺ����ĵ���
					//method[i].invoke(keyWordsaction, value);
					//System.out.println(method[i].getName())
					System.out.println(method[i].getName() + "   -----   " + i);
					Thread.sleep(1000);
					try {
						method[i].invoke(keyWordsaction, value);
					} catch (Exception e) {
						// TODO: handle exception
						method[i].invoke(keyWordsaction);
					}
					
				}
			}
			/**
open_browser
navigate
switch_to_frame
input_username
input_password
click_login
switch_to_default
WaitFor_Element
click_writeLetterLink
WaitFor_Element
input_recipients
input_mailSubject
press_tab
paste_mailContent
click_addAttachment
paste_uploadFileName
press_enter
sleep
click_sendMailButton
sleep
WaitFor_Element
Assert_String
close_browser
			 */
	}

}
