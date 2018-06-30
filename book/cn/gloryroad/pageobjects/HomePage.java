/**
 * 使用PageObject要注意的设计原则：
 * 1.在PageObject类中定义public方法来对外提供服务
 * 2.不要暴露PageObject中的内部逻辑
 * 3.不要在PageObject类中进行断言操作
 * 4.只需要在PageObject类中定义需要操作的元素和操作方法
 * 5.PageObject页面中相同的动作如果会参数不同的结果，需要在PageObject类中定义多个操作方法
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
	
	//写信的封装方法
	public void writeMail() throws InterruptedException{
		writeMaillink.click();
		Thread.sleep(5000);
		
		TestRobot robot = new TestRobot();
		robot.setAndCtrlVClipboardData("1490337751@qq.com");
		//note:此处Email字段用sendKey方法无法完成输入
		//org.openqa.selenium.ElementNotInteractableException: Element <label id="_mail_emailtips_0_219" class="js-component-emailtips nui-ipt-placeholder"> is not reachable by keyboard
		//driver.findElement(By.xpath("//div[@id='_dvModuleContainer_compose.ComposeModule_0']/div[@class='frame-main-cont-body nui-scroll']//header[@class='qa0']//div[@title='发给多人时地址请以分号隔开']/label[@class='js-component-emailtips nui-ipt-placeholder']")).("1490337751@qq.com");
		robot.pressTabKey();
		robot.setAndCtrlVClipboardData("test");
		robot.pressTabKey();
		robot.setAndCtrlVClipboardData("aaaaa");
		robot.pressTabKey();
		robot.pressEnterKey();
//		driver.findElement(By.cssSelector("[aria-label='邮件主题输入框，请输入邮件主题'] .nui-ipt-input")).sendKeys("test");
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
