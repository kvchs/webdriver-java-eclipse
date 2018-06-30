package seleniumAutomation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", ".\\tools\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.baidu.com");
		try{
			String baidu_title = "度一下，你就知道";
	
			
			//Java中有一个方法叫assert,中文意思叫断言，assert后面跟着的是一个表达式，表达式的值是布尔型。当判断表达式值是true，就打印Tess Pass，我把断言部分代码放在try cathc语句中。
			//assert baidu_title == driver.getTitle(): "TEST pass";
			assert baidu_title == driver.getTitle();
			System.out.println("Test title pass");
		}catch(Exception e){
			e.printStackTrace();
		}
		driver.quit();
	}

}
