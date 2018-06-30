package dataDrivenFrameWork.gloryroad.appModules;

import org.openqa.selenium.WebDriver;

import dataDrivenFrameWork.gloryroad.pageObjects.AddressBookPage;
import dataDrivenFrameWork.gloryroad.pageObjects.HomePage;
import dataDrivenFrameWork.gloryroad.util.Log;

public class AddContactPerson_Action {
	
	public static void execute(WebDriver driver, String username,
			String password, String contactName, String contactEmail,
			String contactMobile) throws Exception {
		Log.info("���� Login_Action��execute����");
		Login_Action.execute(driver, username, password);
		HomePage homePage = new HomePage(driver);
		Log.info("�ڵ�¼�û���ҳ�󣬵�����ͨѶ¼�� ����");
		homePage.addressLink().click();
		AddressBookPage addressBookPage = new AddressBookPage(driver);
		Thread.sleep(3000);
		addressBookPage.createContactPersonButton().click();
		Thread.sleep(3000);
		Log.info("����ϵ��������������У����룺" + contactName);
		addressBookPage.contactPersonName().sendKeys(contactName);
		Log.info("����ϵ�˵ĵ����ʼ������������: " + contactEmail);
		addressBookPage.contactPersonEmail().sendKeys(contactEmail);
		Log.info("����ϵ�˵ĵ绰���������������: " + contactMobile);
		addressBookPage.contactPersonMobile().sendKeys(contactMobile);
		Log.info("����ϵ���ֻ���������У����ȷ�ϰ�ť");
		addressBookPage.saveButton().click();
		Thread.sleep(5000);
	}

}
