package seleniumAutomation;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class InterfaceMethod {

	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		
		System.setProperty("webdriver.gecko.driver", ".\\tools\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://www.baidu.com");	
		
//		Ԫ�ز�������
//		driver.findElement(By.id("kw")).sendKeys("java selenium");
//		driver.findElement(By.xpath("//*[@id='kw']")).sendKeys("java");
//		driver.findElement(By.xpath("//*[@id='su']")).click();
		driver.findElement(By.linkText("����")).click();
//		assert driver.getCurrentUrl() == "http://new.baidu.com";
//		driver.findElement(By.className("s_ipt")).sendKeys("java");
//		note: ������className������λ���пո������
//		driver.findElement(By.className("bg s_btn_wr")).click();
//		driver.findElement(By.name("wd")).sendKeys("selenium");
//		driver.findElement(By.partialLinkText("��Ϊ��ҳ")).click();
//		driver.findElement(By.cssSelector("#kw")).sendKeys("selnium");
//		driver.findElement(By.cssSelector("#su")).click();
//		driver.findElement(By.tagName("body")).sendKeys(Keys.F11);
//		driver.get("https://news.baidu.com");
//		List <WebElement> links = driver.findElements(By.xpath("//*[@id='pane-news']/ul[1]/li/a"));
//		
//		for (int i=0; i<links.size(); i++){
//			System.out.println(links.get(i).getText());
//		}
//		Thread.sleep(3000);
//		driver.quit();
//		driver.findElement(By.id("kw")).sendKeys("selenium");
//		driver.findElement(By.id("su")).click();
//		WebElement element = driver.findElement(By.id("kw"));
//		element.sendKeys("java selenium");
//		element.clear();
//		String title = driver.getTitle();
//		System.out.println(title);
//		String url = driver.getCurrentUrl();
//		String source = driver.getPageSource();
//		System.out.println(source);
//		System.out.println(driver.getWindowHandle());
//		WebElement setHomeLInk = driver.findElement(By.partialLinkText("��Ϊ��ҳ"));
//		setHomeLInk.click();
//		Thread.sleep(2000);
//		System.out.println(driver.getWindowHandles());
//		driver.close();
//		Thread.sleep(2000);
//		driver.get("http://blog.csdn.net/u011541946");
//		WebElement element = driver.findElement(By.xpath("//*/dd[@class='//*[@id='pageBox']"));
//		JavascriptExecutor je = (JavascriptExecutor) driver;
//		je.executeScript("arguments[0].scrollIntoView(true);", element);
//		driver.switchTo().frame("ID Name Object");
//		driver.switchTo().defaultContent();
//		driver.get("http://news.baidu.com");
//		Thread.sleep(2000);
////		ArrayList<WebElement> search_type = (ArrayList<WebElement>) driver.findElement(By.xpath("//*/p[@class='search-radios'/input]"));
//		List<WebElement> search_type = driver.findElements(By.xpath("//*/p[@class='search-radios']/input"));
//		for (WebElement ele : search_type){
//			ele.click();
//			Thread.sleep(1000);
//		}
//		���ַ����Ƿ���Լ�
//		String error_message = driver.findElement(By.xpath("//")).getText();
//		assert (error_message == "��������֤��")
//		assert element.isDisplayed();
//		������
//		System.out.println(driver.switchTo().alert().getText());
//		driver.switchTo().alert().accept();
//		driver.switchTo().alert().
		driver.quit();
		
		
		
		
		
//		�ര���л�
//		driver.get("https://www.jd.com/");
//		Thread.sleep(2000);
//		WebElement phone_link = driver.findElement(By.linkText("�ֻ�"));
//		phone_link.click();
//		String handle = driver.getWindowHandle();
//		for (String handles : driver.getWindowHandles()){
//			if (handles.equals(handle)){
//				continue;
//			}
//			driver.switchTo().window(handles);
//		}
//		WebElement xiaomi_link = driver.findElement(By.linkText("С��"));
//		xiaomi_link.click();
//		Thread.sleep(2000);
//		driver.quit();
		
//		driver.get("https://www.jd.com/");
//		Thread.sleep(2000);
//		WebElement phone_link = driver.findElement(By.linkText("�ֻ�"));
//		phone_link.click();
//		String handle = driver.getWindowHandle();
//		for (String temhandle : driver.getWindowHandles()){
//			if (!temhandle.equals(handle)){
//				driver.close();
//				driver.switchTo().window(temhandle);
//			}
//			
//			WebElement xiaomi_link = driver.findElement(By.linkText("С��"));
//			xiaomi_link.click();
//			Thread.sleep(2000);
//			
//		}
		
		
		
//		Navagation�ӿ�ʹ��
//		Thread.sleep(2000);
//		driver.navigate().to("http://news.baidu.com");
//		Thread.sleep(2000);
//		driver.navigate().back();
//		Thread.sleep(2000);
//		driver.navigate().forward();
//		driver.navigate().refresh();
//		
		
		
			
//		ȫ�������ĵ���
//		driver.manage().window().fullscreen();   //�ᱨ��
//		driver.findElement(By.tagName("body")).sendKeys(Keys.F11);
		
		
//		����������Ĵ�С�ͻ�ȡ���ڵĴ�С
//		Dimension targetSize = new Dimension(1024, 768);
//		driver.manage().window().setSize(targetSize);
//		System.out.println(driver.manage().window().getSize());
		
		
//      ���������λ��
//		Point targetPosition = new Point(300, 600);
//		driver.manage().window().setPosition(targetPosition);
//		System.out.println(driver.manage().window().getPosition());

	}

}
