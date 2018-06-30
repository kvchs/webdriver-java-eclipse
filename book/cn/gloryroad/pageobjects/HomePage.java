/**
 * ʹ��PageObjectҪע������ԭ��
 * 1.��PageObject���ж���public�����������ṩ����
 * 2.��Ҫ��¶PageObject�е��ڲ��߼�
 * 3.��Ҫ��PageObject���н��ж��Բ���
 * 4.ֻ��Ҫ��PageObject���ж�����Ҫ������Ԫ�غͲ�������
 * 5.PageObjectҳ������ͬ�Ķ�������������ͬ�Ľ������Ҫ��PageObject���ж�������������
 * 
 * 
 * 
 * 
 * 
 * 
 */

package cn.gloryroad.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webdriver.run.TestRobot;

public class HomePage {
	public WebDriver driver;
	
	@FindBy(xpath="//div[@id='dvNavTop']/ul[@class='js-component-component tJ0']/li[2]")
	public WebElement writeMaillink;
	
	@FindBy(xpath="//div[@id='_dvModuleContainer_compose.ComposeModule_0']/div[@class='frame-main-cont-body nui-scroll']//footer/div[1]/span[@class='nui-btn-text']")
	public WebElement sendMailbutton;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//д�ŵķ�װ����
	public void writeMail() throws InterruptedException{
		writeMaillink.click();
		Thread.sleep(5000);
		
		TestRobot robot = new TestRobot();
		robot.setAndCtrlVClipboardData("1490337751@qq.com");
		//note:�˴�Email�ֶ���sendKey�����޷��������
		//org.openqa.selenium.ElementNotInteractableException: Element <label id="_mail_emailtips_0_219" class="js-component-emailtips nui-ipt-placeholder"> is not reachable by keyboard
		//driver.findElement(By.xpath("//div[@id='_dvModuleContainer_compose.ComposeModule_0']/div[@class='frame-main-cont-body nui-scroll']//header[@class='qa0']//div[@title='��������ʱ��ַ���ԷֺŸ���']/label[@class='js-component-emailtips nui-ipt-placeholder']")).("1490337751@qq.com");
		robot.pressTabKey();
		robot.setAndCtrlVClipboardData("test");
		robot.pressTabKey();
		robot.setAndCtrlVClipboardData("aaaaa");
		robot.pressTabKey();
		robot.pressEnterKey();
//		driver.findElement(By.cssSelector("[aria-label='�ʼ�����������������ʼ�����'] .nui-ipt-input")).sendKeys("test");
//		driver.findElement(By.cssSelector(".nui-scroll")).sendKeys("test");
//		sendMailbutton.click();
		Thread.sleep(3000);
		
		
	}
	
	public String getPageSource(){
		return driver.getPageSource();
	}
	
	public void close(){
//		this.driver.close();
	}

}
