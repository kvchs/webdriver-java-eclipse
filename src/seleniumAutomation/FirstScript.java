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
			String baidu_title = "��һ�£����֪��";
	
			
			//Java����һ��������assert,������˼�ж��ԣ�assert������ŵ���һ�����ʽ�����ʽ��ֵ�ǲ����͡����жϱ��ʽֵ��true���ʹ�ӡTess Pass���ҰѶ��Բ��ִ������try cathc����С�
			//assert baidu_title == driver.getTitle(): "TEST pass";
			assert baidu_title == driver.getTitle();
			System.out.println("Test title pass");
		}catch(Exception e){
			e.printStackTrace();
		}
		driver.quit();
	}

}
