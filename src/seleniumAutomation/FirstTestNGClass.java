// https://blog.csdn.net/u011541946/article/details/75070425
package seleniumAutomation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.By;  
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.chrome.ChromeDriver; 
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;  


public class FirstTestNGClass {
	WebDriver driver;
	
  @Test
  public void OpenBaiDu() {
	  driver.get("http://www.baidu.com");
	  driver.findElement(By.id("kw")).sendKeys("selenium java");
	  driver.findElement(By.id("su")).click();
	  System.out.println("now url is: " + driver.getCurrentUrl());
      System.out.println("now title is : " + driver.getTitle());
  }
  @BeforeClass
  public void beforeClass() {
	  
	  
	  //System.setProperty("webdriver.firefox.driver", "C:\\Program Files\\Firefox Developer Edition\\firefox.exe");
	  //����48�汾�Ļ��������geckodirver.exe�������Ҫʹ��������һ�д��롣
	  //System.setProperty("webdriver.firefox.marionette", ".\\tools\\geckodriver.exe"); 
	  //System.setProperty("webdriver.gecko.driver", ".\\tools\\geckodriver.exe");
	  //driver = new FirefoxDriver();
	  
	  
	  //System.setProperty("webdriver.chrome.driver", ".\\tools\\chromedriver.exe");  
	  //driver = new ChromeDriver();
	  
	  
	  //��Ҫ�Ѱ�ȫ���õ�������ͬ��ģʽ��������Բο���Internetѡ��->��ȫ; ��Internetվ�㣬����Intrant,������վ�� �����ط��İ�ȫ���涼������ͬ�ȼ������綼�����У� �ٴ����д���Ϳ�����IE�򿪰ٶ��ˡ�
	  System.setProperty("webdriver.ie.driver", ".\\tools\\IEDriverServer.exe");
	  driver = new InternetExplorerDriver();
	  driver.manage().window().maximize();
	  //�������Եȴ�ʱ��  
      //driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
      
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
