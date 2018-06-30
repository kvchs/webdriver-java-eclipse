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
	  //低于48版本的火狐上运行geckodirver.exe，代码就要使用以下这一行代码。
	  //System.setProperty("webdriver.firefox.marionette", ".\\tools\\geckodriver.exe"); 
	  //System.setProperty("webdriver.gecko.driver", ".\\tools\\geckodriver.exe");
	  //driver = new FirefoxDriver();
	  
	  
	  //System.setProperty("webdriver.chrome.driver", ".\\tools\\chromedriver.exe");  
	  //driver = new ChromeDriver();
	  
	  
	  //需要把安全设置调整成相同的模式。具体可以参考：Internet选项->安全; 把Internet站点，本地Intrant,受信任站点 三个地方的安全界面都设置相同等级，例如都设置中； 再次运行代码就可以用IE打开百度了。
	  System.setProperty("webdriver.ie.driver", ".\\tools\\IEDriverServer.exe");
	  driver = new InternetExplorerDriver();
	  driver.manage().window().maximize();
	  //设置隐性等待时间  
      //driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
      
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
