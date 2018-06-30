// Selenium 方法分类:driver相关，操作浏览器本身设置 ; 元素相关,元素点击，输入等操作; 工具支持类,截图、日志文件输出、事件监听

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
