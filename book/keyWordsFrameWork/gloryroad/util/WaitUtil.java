package keyWordsFrameWork.gloryroad.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Administrator
 *用于在测试执行过程中暂停程序执行的休眠方法
 */
public class WaitUtil {
	public static void sleep(long millisecond){
		try {
			Thread.sleep(millisecond);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void waitWebElement(WebDriver driver, String xpathExpression){
		WebDriverWait wait = new WebDriverWait(driver, 15);
		//调用ExpectedConditions 的presenceOfElementLocated方法判断页面元素是否存在
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
	}
	
	//显示等待页面元素出现的封装方法，参数表示为页面元素的By对象，可以支持更多的定位表达式
	public static void waitWebElement(WebDriver driver, By by){
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

}
