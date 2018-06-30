package keyWordsFrameWork.gloryroad.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Administrator
 *�����ڲ���ִ�й�������ͣ����ִ�е����߷���
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
		//����ExpectedConditions ��presenceOfElementLocated�����ж�ҳ��Ԫ���Ƿ����
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
	}
	
	//��ʾ�ȴ�ҳ��Ԫ�س��ֵķ�װ������������ʾΪҳ��Ԫ�ص�By���󣬿���֧�ָ���Ķ�λ���ʽ
	public static void waitWebElement(WebDriver driver, By by){
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

}
