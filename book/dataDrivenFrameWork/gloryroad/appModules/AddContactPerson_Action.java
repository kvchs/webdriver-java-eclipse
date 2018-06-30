package dataDrivenFrameWork.gloryroad.appModules;

import org.openqa.selenium.WebDriver;

import dataDrivenFrameWork.gloryroad.pageObjects.AddressBookPage;
import dataDrivenFrameWork.gloryroad.pageObjects.HomePage;
import dataDrivenFrameWork.gloryroad.util.Log;

public class AddContactPerson_Action {
	
	public static void execute(WebDriver driver, String username,
			String password, String contactName, String contactEmail,
			String contactMobile) throws Exception {
		Log.info("调用 Login_Action的execute方法");
		Login_Action.execute(driver, username, password);
		HomePage homePage = new HomePage(driver);
		Log.info("在登录用户主页后，单击“通讯录” 链接");
		homePage.addressLink().click();
		AddressBookPage addressBookPage = new AddressBookPage(driver);
		Thread.sleep(3000);
		addressBookPage.createContactPersonButton().click();
		Thread.sleep(3000);
		Log.info("在联系人姓名的输入框中，输入：" + contactName);
		addressBookPage.contactPersonName().sendKeys(contactName);
		Log.info("在联系人的电子邮件输入框中输入: " + contactEmail);
		addressBookPage.contactPersonEmail().sendKeys(contactEmail);
		Log.info("在联系人的电话号码输入框中输入: " + contactMobile);
		addressBookPage.contactPersonMobile().sendKeys(contactMobile);
		Log.info("在联系人手机号输入框中，点击确认按钮");
		addressBookPage.saveButton().click();
		Thread.sleep(5000);
	}

}
