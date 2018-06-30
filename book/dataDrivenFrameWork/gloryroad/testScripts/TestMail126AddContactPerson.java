package dataDrivenFrameWork.gloryroad.testScripts;

//ע��TestNG���Ա�����Skips������ʾ�ڽ�����������ʱ����ǰ�õĲ��Է���δ��ִ�гɹ���
//�����δִ�е��������Է��������ᱻ���ΪSkips����ʾ����
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataDrivenFrameWork.gloryroad.appModules.AddContactPerson_Action;
import dataDrivenFrameWork.gloryroad.util.Constant;
import dataDrivenFrameWork.gloryroad.util.ExcelUtil;
import dataDrivenFrameWork.gloryroad.util.Log;

public class TestMail126AddContactPerson {
	public WebDriver driver;
	String baseUrl = Constant.BaseUrl;

	// ����dataPrivider��������ΪtestData
	/**
	 * @return
	 * @throws Exception 
	 */
	@DataProvider(name = "testData")
	public static String[][] data() throws Exception {

		/**
		 * ����ExcelUtil���е�getTestData��̬��������ȡ�����ļ��е����ڶ��б��Ϊ��y���Ĳ��������У� ������������
		 */

		 return ExcelUtil.getTestData(Constant.TestDataExcelFilePath,
				Constant.TestDataExcelFileSheet);
	}

	// ���Է���һ��ʹ����10����������Ӧ��Excel�������ļ���1~10��

	@Test(dataProvider = "testData")
	public void testAddressBook(String CaseRowNumber, String TestCaseName,
			String mailUsername, String mailPassword, String contactPersonName,
			String contactPersonEmail, String contactPersonMobile,
			String assertContactPersonName, String assertContactPersonEmail,
			String assertContactPersonMobile) throws Exception {
		Log.info(TestCaseName);
		Log.info("���� AddContactPerson_Action ��� execute ����");
		try {
			AddContactPerson_Action.execute(driver, mailUsername, mailPassword,
					contactPersonName, contactPersonEmail, contactPersonMobile);
		} catch (AssertionError e) {
			// TODO: handle exception
			Log.error("�����ϵ��ʧ��");
			ExcelUtil.setCellData(
					Integer.parseInt(CaseRowNumber.split("[.]")[0]),
					ExcelUtil.getLastColumnNum(), "ִ��ʧ��");
		}
		/**
		 * ִ��AddContactPerson_Action���execute����ʧ��ʱ��catch�����Բ���AssertionError�쳣��
		 * ������Excel�в��������еĽ��Ϊʧ�ܡ�����Excel�е���ŵĸ�ʽ��Ĭ������Ϊ����һ��С��������
		 * Ҫ��split("[.]")[0]����ȡ�������֣�������setCellData������Ӧ��ŵĲ��������е����һ�� �趨������ִ��ʧ�ܡ�
		 */
		Log.info("����ͨѶ¼ҳ���Ƿ������ϵ�������Ĺؼ���");
		try {
			Assert.assertTrue(driver.getPageSource().contains(
					assertContactPersonName));
		} catch (AssertionError e) {
			// TODO: handle exception
			ExcelUtil.setCellData(
					Integer.parseInt(CaseRowNumber.split("[.]")[0]),
					ExcelUtil.getLastColumnNum(), "ִ��ʧ��");
			Assert.fail("����ͨѶ¼ҳ���Ƿ������ϵ�������ؼ���ʧ��");
		}

		Log.info("����ͨѶ¼ҳ���Ƿ������ϵ������Ĺؼ���");
		try {
			Assert.assertTrue(driver.getPageSource().contains(
					assertContactPersonEmail));
		} catch (AssertionError e) {
			// TODO: handle exception
			ExcelUtil.setCellData(
					Integer.parseInt(CaseRowNumber.split("[.]")[0]),
					ExcelUtil.getLastColumnNum(), "ִ��ʧ��");
			Assert.fail("����ͨѶ¼ҳ���Ƿ������ϵ������ؼ���ʧ��");
		}

		Log.info("����ͨѶ¼ҳ���Ƿ������ϵ���ֻ��ŵĹؼ���");
		try {
			Assert.assertTrue(driver.getPageSource().contains(
					assertContactPersonMobile));
		} catch (AssertionError e) {
			// TODO: handle exception
			ExcelUtil.setCellData(
					Integer.parseInt(CaseRowNumber.split("[.]")[0]),
					ExcelUtil.getLastColumnNum(), "ִ��ʧ��");
			Assert.fail("����ͨѶ¼ҳ���Ƿ������ϵ���ֻ�����ؼ���ʧ��");
		}

		Log.info("���ж��Զ��ɹ����ڲ��������ļ��б�ǲ��������Ľ��Ϊ'ִ�гɹ�'");
		ExcelUtil.setCellData(Integer.parseInt(CaseRowNumber.split("[.]")[0]),
				ExcelUtil.getLastColumnNum(), "ִ�гɹ�");
		Log.info("���Խ���ɹ�д�뵽excel�����ļ��ġ�ִ�н������");
		Log.endTestCase(TestCaseName);
	}

	@Test(enabled=false)
	public void testAddContactPerson() throws Exception {
		Log.startTestCase(ExcelUtil.getCellData(1, 0));
		String MailUsername = ExcelUtil.getCellData(1, 1);
		String MailPassword = ExcelUtil.getCellData(1, 2);
		String ContactPersonName = ExcelUtil.getCellData(1, 3);
		String ContactPersonEmail = ExcelUtil.getCellData(1, 4);
		String ContactpersonMobile = ExcelUtil.getCellData(1, 5);

		// AddContactPerson_Action.execute(driver, Constant.MailUsername,
		// Constant.MailPassword, Constant.ContactPersonName,
		// Constant.ContactPersonEmail, Constant.ContactpersonMobile);

		Log.info("���� AddContactPerson_Action ��� execute ����");
		AddContactPerson_Action.execute(driver, MailUsername, MailPassword,
				ContactPersonName, ContactPersonEmail, ContactpersonMobile);
		Log.info("����ͨѶ¼ҳ���Ƿ������ϵ�������Ĺؼ���");
		Assert.assertTrue(driver.getPageSource().contains(ContactPersonName));
		Log.info("����ͨѶ¼ҳ���Ƿ������ϵ������Ĺؼ���");
		Assert.assertTrue(driver.getPageSource().contains(ContactPersonEmail));
		Log.info("����ͨѶ¼ҳ���Ƿ������ϵ���ֻ��ŵĹؼ���");
		Assert.assertTrue(driver.getPageSource().contains(ContactpersonMobile));
		Log.info("���ж��Զ��ɹ����ڲ��������ļ��б�ǲ��������Ľ��Ϊ'ִ�гɹ�'");
		ExcelUtil.setCellData(1, 9, "ִ�гɹ�");
		Log.endTestCase(ExcelUtil.getCellData(1, 0));
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\tools\\chromedriver.exe");
		System.setProperty("webdriver.ie.driver",
				System.getProperty("user.dir") + "\\tools\\IEDriverServer.exe");
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + "\\tools\\geckodriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@BeforeClass
	public void BeforeClass() throws Exception {
		ExcelUtil.setExcelFile(Constant.TestDataExcelFilePath,
				Constant.TestDataExcelFileSheet);
		DOMConfigurator.configure("DataDrivenFrameWord\\log4j.xml");
	}

}
