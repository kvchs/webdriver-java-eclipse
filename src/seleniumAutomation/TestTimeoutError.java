// Selenium ��������:driver��أ������������������ ; Ԫ�����,Ԫ�ص��������Ȳ���; ����֧����,��ͼ����־�ļ�������¼�����

package seleniumAutomation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestTimeoutError {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver", ".\\tools\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get("htts://www.baidu.com");

	}

}
