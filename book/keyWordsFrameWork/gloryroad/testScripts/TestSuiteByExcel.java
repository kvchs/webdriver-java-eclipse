package keyWordsFrameWork.gloryroad.testScripts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import keyWordsFrameWork.gloryroad.configuration.KeyWordsAction;
import keyWordsFrameWork.gloryroad.data.Constants;
import keyWordsFrameWork.gloryroad.util.ExcelUtil;
import keyWordsFrameWork.gloryroad.util.Log;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class TestSuiteByExcel {
	public static Method method[];
	public static String keyword;
	public static String value;
	public static KeyWordsAction keyWordsAction;
	public static int testStep;
	public static int testLastStep;
	public static String testCaseID;
	public static String testCaseRunFlag;
	
	@Test
	public void testTestSuite() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {
		keyWordsAction = new KeyWordsAction();
		method = keyWordsAction.getClass().getMethods();
		for (Method method1 : method){
			//System.out.println(method1.getName());
		}
		
		String excelFilePath = Constants.Path_ExcelFile;
		ExcelUtil.setExcelFile(excelFilePath);
		int testCasesCount = ExcelUtil.getRowCount(Constants.Sheet_TestSteps);
		
		System.out.println(testCasesCount);
		for (int testCaseNo = 1; testCaseNo <=testCasesCount; testCaseNo++){
			testCaseID = ExcelUtil.getCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_TestCaseID);
			System.out.println(testCaseID);
			testCaseRunFlag = ExcelUtil.getCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_RunFlag);
			if(testCaseRunFlag.equalsIgnoreCase("y")){
				Log.startTestCase(testCaseID);
				testStep = ExcelUtil.getFirstRowContainsTestCaseID(Constants.Sheet_TestSteps, testCaseID, Constants.Col_TestCaseID);
				testLastStep = ExcelUtil.getTestCaseLastStepRow(Constants.Sheet_TestSteps, testCaseID, testStep);
				for (; testStep < testLastStep; testStep++){
					keyword = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_KeyWordAction);
					Log.info("��Excel�ļ���ȡ���Ĺؼ�����: " + keyword);
					value = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_ActionValue);
					Log.info("��Excel�ļ���ȡ���Ĳ���ֵ��: " + value);
					execute_Actions();
				}
				Log.endTestCase(testCaseID);
			}
		}
	}

	@BeforeMethod
	public void beforeMethod() {
		DOMConfigurator.configure(System.getProperty("user.dir") + "\\KeyWordsFrameWork\\log4j.xml");
	}

	@AfterMethod
	public void afterMethod() {
	}
	
	private static void execute_Actions() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException{
		for (int i = 0; i < method.length; i++) {
			//System.out.println(keywords + "======keywords====="+ i);
			//System.out.println(method[i].getName() + "======getName====="+ i);
			if (method[i].getName().equals(keyword)) {
				//�ҵ�KeyWordsaction���е�ӳ�䷽����ͨ������ invoke ������ɺ����ĵ���
				//method[i].invoke(keyWordsaction, value);
				//System.out.println(method[i].getName())
				System.out.println(method[i].getName() + "   -----   " + i);
				Thread.sleep(1000);
				try {
					method[i].invoke(keyWordsAction, value);
				} catch (Exception e) {
					// TODO: handle exception
					method[i].invoke(keyWordsAction);
				}
				
			}
		}
	}

}
