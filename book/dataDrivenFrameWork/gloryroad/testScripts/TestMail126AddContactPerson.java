package dataDrivenFrameWork.gloryroad.testScripts;

//注：TestNG测试报告中Skips参数表示在进行依赖测试时如有前置的测试方法未被执行成功，
//则后续未执行的依赖测试方法个数会被标记为Skips的显示数量
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

	// 定义dataPrivider，名命名为testData
	/**
	 * @return
	 * @throws Exception 
	 */
	@DataProvider(name = "testData")
	public static String[][] data() throws Exception {

		/**
		 * 调用ExcelUtil类中的getTestData静态方法，获取数据文件中倒数第二列标记为“y”的测试数据行， 函数参数如下
		 */

		 return ExcelUtil.getTestData(Constant.TestDataExcelFilePath,
				Constant.TestDataExcelFileSheet);
	}

	// 测试方法一共使用了10个参数，对应到Excel的数据文件的1~10列

	@Test(dataProvider = "testData")
	public void testAddressBook(String CaseRowNumber, String TestCaseName,
			String mailUsername, String mailPassword, String contactPersonName,
			String contactPersonEmail, String contactPersonMobile,
			String assertContactPersonName, String assertContactPersonEmail,
			String assertContactPersonMobile) throws Exception {
		Log.info(TestCaseName);
		Log.info("调用 AddContactPerson_Action 类的 execute 方法");
		try {
			AddContactPerson_Action.execute(driver, mailUsername, mailPassword,
					contactPersonName, contactPersonEmail, contactPersonMobile);
		} catch (AssertionError e) {
			// TODO: handle exception
			Log.error("添加联系人失败");
			ExcelUtil.setCellData(
					Integer.parseInt(CaseRowNumber.split("[.]")[0]),
					ExcelUtil.getLastColumnNum(), "执行失败");
		}
		/**
		 * 执行AddContactPerson_Action类的execute方法失败时，catch语句可以捕获AssertionError异常，
		 * 并设置Excel中测试数据行的结果为失败。由于Excel中的序号的格式被默认设置为带有一个小数，所有
		 * 要用split("[.]")[0]语句获取整数部分，并传给setCellData函数对应序号的测试数据行的最后一列 设定“测试执行失败”
		 */
		Log.info("断言通讯录页面是否包含联系人姓名的关键字");
		try {
			Assert.assertTrue(driver.getPageSource().contains(
					assertContactPersonName));
		} catch (AssertionError e) {
			// TODO: handle exception
			ExcelUtil.setCellData(
					Integer.parseInt(CaseRowNumber.split("[.]")[0]),
					ExcelUtil.getLastColumnNum(), "执行失败");
			Assert.fail("断言通讯录页面是否包含联系人姓名关键字失败");
		}

		Log.info("断言通讯录页面是否包含联系人邮箱的关键字");
		try {
			Assert.assertTrue(driver.getPageSource().contains(
					assertContactPersonEmail));
		} catch (AssertionError e) {
			// TODO: handle exception
			ExcelUtil.setCellData(
					Integer.parseInt(CaseRowNumber.split("[.]")[0]),
					ExcelUtil.getLastColumnNum(), "执行失败");
			Assert.fail("断言通讯录页面是否包含联系人邮箱关键字失败");
		}

		Log.info("断言通讯录页面是否包含联系人手机号的关键字");
		try {
			Assert.assertTrue(driver.getPageSource().contains(
					assertContactPersonMobile));
		} catch (AssertionError e) {
			// TODO: handle exception
			ExcelUtil.setCellData(
					Integer.parseInt(CaseRowNumber.split("[.]")[0]),
					ExcelUtil.getLastColumnNum(), "执行失败");
			Assert.fail("断言通讯录页面是否包含联系人手机号码关键字失败");
		}

		Log.info("所有断言都成功，在测试数据文件中标记测试用例的结果为'执行成功'");
		ExcelUtil.setCellData(Integer.parseInt(CaseRowNumber.split("[.]")[0]),
				ExcelUtil.getLastColumnNum(), "执行成功");
		Log.info("测试结果成功写入到excel数据文件的“执行结果”列");
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

		Log.info("调用 AddContactPerson_Action 类的 execute 方法");
		AddContactPerson_Action.execute(driver, MailUsername, MailPassword,
				ContactPersonName, ContactPersonEmail, ContactpersonMobile);
		Log.info("断言通讯录页面是否包含联系人姓名的关键字");
		Assert.assertTrue(driver.getPageSource().contains(ContactPersonName));
		Log.info("断言通讯录页面是否包含联系人邮箱的关键字");
		Assert.assertTrue(driver.getPageSource().contains(ContactPersonEmail));
		Log.info("断言通讯录页面是否包含联系人手机号的关键字");
		Assert.assertTrue(driver.getPageSource().contains(ContactpersonMobile));
		Log.info("所有断言都成功，在测试数据文件中标记测试用例的结果为'执行成功'");
		ExcelUtil.setCellData(1, 9, "执行成功");
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
