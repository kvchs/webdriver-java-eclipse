package com.webdriver.run;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebDriverAPI {
	WebDriver driver;

	@Test
	public void f() {
	}

	// ������һ����ҳ(ģ�ⵥ��������ĺ��˹���)
	@Test
	public void visitRecentURL() {
		String url1 = "";
		String url2 = "";
		driver.navigate().to(url1);
		driver.navigate().to(url2);
		driver.navigate().back();
	}

	// (ģ�ⵥ���������ǰ������)
	@Test
	public void visitNextURL() {
		String url1 = "";
		String url2 = "";
		driver.navigate().to(url1);
		driver.navigate().to(url2);
		driver.navigate().back();
		driver.navigate().forward();
		// ˢ�µ�ǰҳ��
		driver.navigate().refresh();
	}

	// �������������
	@Test
	public void operateBrowser() {
		/* ����һ��Point���󣬴����������λ���������Ļ���Ͻǵĺ���������� */
		Point point = new Point(150, 150);
		// ����Dimension��������500��ʾ��������ڵĳ��ȺͿ��
		Dimension dimension = new Dimension(500, 500);
		// setPosition������ʾ�趨���������Ļ�ϵ�λ��Ϊpoint���������,��ĳЩ�汾��������´˷�����Ч
		driver.manage().window().setPosition(point);
		// setSize������ʾ�趨��������ڴ�СΪ��Ϊ500����λ����Ϊ500����λ
		driver.manage().window().setSize(dimension);
		// getPosition����Ϊ��ȡ���������Ļ��λ�ã���ĳЩ������´˷�����Ч
		System.out.println(driver.manage().window().getPosition());
		System.out.println(driver.manage().window().getSize());
		driver.manage().window().maximize();
		driver.get("");

	}

	@Test
	public void utilMethod() {
		String title = driver.getTitle();
		Assert.assertEquals("", title);
		// ��ȡҳ���ϵ�Դ��
		String pageSource = driver.getPageSource();
		Assert.assertTrue(pageSource.contains("expect string"));

		String currentPageUrl = driver.getCurrentUrl();
		// ����ı�����
		WebElement input = driver.findElement(By.id("text"));
		input.clear();
		// ��try catch�����ѡ����Ҫ���ڵȴ�3�룬�鿴������ҳ��Ľ��
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		input.sendKeys("");
		WebElement btn = driver.findElement(By.xpath(""));
		btn.click();
		// ˫��ĳ��Ԫ��
		Actions builder = new Actions(driver);
		builder.doubleClick(btn).build().perform();

	}

	@Test
	public void operateDropList() {
		// ʹ��name�����ҵ�ҳ����nameΪ��fruit���������б�Ԫ��
		Select dropList = new Select(driver.findElement(By.name("furit")));
		// isMultiple��ʾ�������б��Ƿ������ѡ
		Assert.assertFalse(dropList.isMultiple());
		// getFirstSelectdOption().getText()������ȡ��ǰ��ѡ�е������б�ѡ���ı�
		// Assert.assertEquals �������Ե�ǰѡ�е��ı��Ƿ��ǡ����ӡ�
		Assert.assertEquals("����", dropList.getFirstSelectedOption().getText());

		// selectByIndex������ʾѡ�������ĵ��ĸ�ѡ�0�����һ��ѡ��
		dropList.selectByIndex(3);

		// selectByValue������ʾʹ�������б�ѡ���value����ֵ����ѡ�в�������shanzha����ѡ��ɽ髵�����ֵ
		dropList.selectByValue("shanzha");

		// selectByVisibleText������ʾͨ��ѡ�������������ѡ��
		dropList.selectByVisibleText("��֦");
		Assert.assertEquals("��֦", dropList.getFirstSelectedOption().getText());

	}

	// ��鵥ѡ�б��ѡ�������Ƿ��������
	@Test
	public void checkSelectText() {
		// ʹ��name�����ҵ�ҳ����nameΪ��fruit���������б�Ԫ��
		Select dropList = new Select(driver.findElement(By.name("furit")));
		/*
		 * ����һ��list����洢�����б��������������ֵ�ѡ�����֣�����ͨ������<String> �޶�list����Ĵ洢�������� ��String,
		 * Array.asList��ʾ��һ������ת��Ϊһ��list����
		 */
		List<String> expect_options = Arrays
				.asList((new String[] { "����", "����" }));

		// ����һ���µ�list����������ȡ��ҳ���ϻ�ȡ��ѡ������
		List<String> actual_result = new ArrayList<String>();
		// dropList.getOptions�������ڻ�ȡҳ���������б������ѡ�����actual_options.add��������
		// ��ʵ��ҳ���е�ÿ��ѡ���ӵ�actual_option�б���
		for (WebElement option : dropList.getOptions()) {
			actual_result.add(option.getText());
			Assert.assertEquals(expect_options.toArray(),
					actual_result.toArray());
		}

		// ������ѡ�б�
		Assert.assertTrue(dropList.isMultiple());
		dropList.deselectAll();
		dropList.deselectByIndex(1);
		dropList.deselectByValue("");
		dropList.deselectByVisibleText("");

	}

	// ��ѡ��͸�ѡ��Ĳ���
	@Test
	public void operateRadio() {
		WebElement radioOption = driver.findElement(By.id(""));
		if (!radioOption.isSelected()) {
			radioOption.click();
		}
		assertTrue(radioOption.isSelected());
		// ����name����Ϊ"fruit" �����е�ѡ��ť���󣬲��洢��һ��list������
		List<WebElement> fruits = driver.findElements(By.name("fruit"));
		for (WebElement fruit : fruits) {
			if (fruit.getAttribute("value").equals("watermelon")) {
				if (!fruit.isSelected()) {
					fruit.click();
					Assert.assertTrue(fruit.isSelected());
					break;
				}
			}
		}
	}

	@Test
	public void operateWindowsProcess() {
		WindowsUtils.killByName("firefox.exe");
		WindowsUtils.killByName("iexplore.exe");
		WindowsUtils.killByName("chrome.exe");
		String processID = "";
		WindowsUtils.killPID(processID);
	}

	// ��ͼ����
	@Test
	public void captureScreenInCurrentWindow() {
		driver.get("");
		// ����getScreenshotAs�����ѵ�ǰ������򿪵�ҳ����н�ͼ�����浽һ��File������
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("D:\\testing\\test.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void isElementTextPresent() {
		WebElement text = driver.findElement(By.cssSelector(""));
		String contentText = text.getText();
		Assert.assertEquals("", text);
		Assert.assertTrue(contentText.contains(""));
		Assert.assertTrue(contentText.startsWith(""));
		Assert.assertTrue(contentText.endsWith(""));
	}

	@Test
	public void executeJavaScript() {
		driver.get("");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String title = (String) js.executeScript("return document.title");
		Assert.assertEquals("", title);
		// document.getElementById('') ��JavaScript���룬��ʾ��ȡҳ��������ť����
		// return button.value��ʾ����������ť�ϵ�����
		String searchButtonText = (String) js
				.executeScript("var button=document.getElementById('stb');return button.value");
		System.out.println(searchButtonText);
	}
	
	//��ҳ��Ԫ����ק��ָ��λ��
	@Test
	public void dragPageElement(){
		driver.get("");
		WebElement draggable = driver.findElement(By.id(""));
		
		//�����϶�10�����أ����϶�5��
		for (int i = 0; i < 5; i++){
			new Actions(driver).dragAndDropBy(draggable, 0, 10).build().perform();
		}
		//�����϶�10�����أ����϶�5��
		for(int i = 0; i < 5; i++){
			new Actions(driver).dragAndDropBy(draggable, 10, 0);
		}
	}
	
	@Test
	public void clickKeys(){
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL);  //����Ctrl��
		actions.keyDown(Keys.SHIFT);
		actions.keyDown(Keys.ALT);
		actions.keyUp(Keys.SHIFT); //�ͷ�shift��
		actions.keyUp(Keys.ALT);
		actions.keyDown(Keys.CONTROL);
		//ģ�������������������д��'ABC'
		actions.keyDown(Keys.SHIFT).sendKeys("abc").perform();
		
		//ģ������һ�����
		//����Action�����contextClick��������IDΪquery��������ڵ����Ҽ�
		actions.contextClick(driver.findElement(By.id(""))).perform();
		
		//�����ͣ
		actions.moveToElement(driver.findElement(By.xpath(""))).perform();
		
	}
	
	
	/**
	 *                                        ���õ���ʽ�ȴ�����
	 *  elementToBeClickable(By locator)            ҳ��Ԫ���Ƿ���ҳ���Ͽ��úͿɱ����
	 *  elementToBeSelected(WebElement element)     ҳ��Ԫ�ش��ڱ�ѡ�е�״̬
	 *  presenceOfElementLocated(By locator)        ҳ��Ԫ����ҳ���д���
	 *  textToBePresentInElement(By locator)        ��ҳ��Ԫ�����Ƿ�����ض����ı�
	 *  textToBePresentInElementValue(By locator.java.lang.String text) ҳ��Ԫ��ֵ
	 *  titleContains(java.lang.String title)       ����
 	 * 
	 */
	//������ʽ�ȴ�
	@Test
	public void testExplicitWait(){
		//����һ��WebDriverWait�����趨�������������ȴ�ʱ��Ϊ10��
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//����ExpectedConditions��titleContains�����ж�ҳ��title
		wait.until(ExpectedConditions.titleContains("ˮ��"));
		
		WebElement select = driver.findElement(By.xpath(""));
		wait.until(ExpectedConditions.elementToBeSelected(select));
		
		wait.until(ExpectedConditions.elementToBeClickable(select));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("")));
		wait.until(ExpectedConditions.textToBePresentInElement(select, ""));
		
	}
	
	/**
	 *                              �Զ�����ʽ�ȴ�
	 */
	
//	@Test
//	public void testExplicitWait2(){
//		try{
//			WebElement textInputBox = (new WebDriverWait(driver, 10))
//					.until(new ExpectedCondition<WebElement>(){
//				@Override
//				public WebElement apply(WebDriver d){
//					return d.findElement(By.xpath(""));
//				}
//			});
//			Assert.assertEquals("", textInputBox.getAttribute("value"));
//			
//			Boolean containTextFlag = (new WebDriverWait(driver, 10))
//					.until(new ExpectedCondition<Boolean>() {
//						@Override
//						public Boolean apply(WebDriver d){
//							return d.findElement(By.xpath("")).getText().contains("����");
//						}
//					});
//			Assert.assertTrue(containTextFlag);
//			
//			Boolean inputTextVisibleFlag = (new WebDriverWait(driver, 10))
//					.until(new ExpectedCondition<Boolean>() {
//						@Override
//						public Boolean apply(WebDriver d){
//							return d.findElement(By.xpath("")).isDisplayed();
//						}
//					});
//			Assert.assertTrue(inputTextVisibleFlag);
//		}catch (NoSuchElementException e){
//			Assert.fail("error message");
//			e.printStackTrace();
//		}
//		
//		
//		
//		//��ʽ�ȴ�Ajax�����Ƿ�������
//		Boolean ajaxRequestFinishFlag = (new WebDriverWait(driver, 10))
//				.until(new ExpectedCondition<Boolean>() {
//					@Override
//					public boolean apply(WebDriver d){
//						JavascriptExecutor jsExecutor = (JavascriptExecutor) d;
//						return (boolean)jsExecutor.executeScript("return jQuery.active==0");
//					}
//				});
//	}
	
	
	
	//��ָ��Ԫ���Ͻ��е��������ͷŲ���
	@Test
	public void mouseClickAndRelease(){
		WebElement div = driver.findElement(By.xpath(""));
		//�鿴Ԫ������
		String inpuTextString = div.getAttribute("value");
		//��ȡCSS����ֵ
		String inputWidth = div.getCssValue("width");
		//��ʽ�ȴ�
		/**
		 * ʹ��implicitlyWait�����趨����ҳ��Ԫ�صĵȴ�ʱ�䣬����findElement����ʱû�������ҵ��ö�λԪ�ػ�
		 * �ȴ��趨��ʱ���������û���ҵ��ͻ��׳�NoSuchElementException
		 */
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			WebElement findElement = driver.findElement(By.cssSelector(""));
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			//ʹ��fail�������Ҳ���Ԫ�ص�ʱ���ò�������ִ��ʧ��
			Assert.fail("����ʧ��");
			//��ӡ����Ķ�ջ��Ϣ
			e.printStackTrace();
		}
		Actions action = new Actions(driver);
		//����Action�����clickAndHold��������Ԫ�ص������������ͷ�
		action.clickAndHold(div).perform();
		try{
			Thread.sleep(3);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		//����Action�����release�������ͷ����
		action.release(div).perform();
		try {
			Thread.sleep(3);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//�ж�һ��ҳ��Ԫ���Ƿ���ڵĺ���
	private boolean IsElementPresent(By by){
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	@Test
	public void testIsElementPresent(){
		Assert.assertTrue(IsElementPresent(By.id("")));
	}
	
	
	
	//ʹ��Title����ʶ��Ͳ����µ��������������
	@Test
	public void identifyPopUpWindowByTitle(){
		//�Ƚ���ǰ��������ڵľ���洢��parentWindowHandle������
		String parentWindowHandle = driver.getWindowHandle();
		//�ҵ�ҳ����Ψһ��link���洢��sougouLink������
		WebElement sogouLink = driver.findElement(By.xpath(""));
		sogouLink.click();
		
		Set<String> allWindowsHandles = driver.getWindowHandles();
		if (!allWindowsHandles.isEmpty()){
			for (String windowHandle: allWindowsHandles){
				try {
					driver.switchTo().window(windowHandle).getTitle();
					if (driver.switchTo().window(windowHandle).getTitle().equals("")){
						driver.findElement(By.xpath("")).sendKeys("");
					}
				} catch (NoSuchElementException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		driver.switchTo().window(parentWindowHandle);
		Assert.assertEquals(driver.getTitle(), "");
	}
	
	//ʹ��ҳ�����������ʶ�����µ��������������
	@Test
	public void identifyPopUpWindowByPageSource(){
		String parentWindowHandle = driver.getWindowHandle();
		WebElement sogouLink = driver.findElement(By.xpath(""));
		sogouLink.click();
		
		Set<String> allWindowsHandles = driver.getWindowHandles();
		if (!allWindowsHandles.isEmpty()){
			for(String windowHandle:allWindowsHandles){
				try {
					driver.switchTo().window(windowHandle).getPageSource().contains("");
					driver.findElement(By.id("")).sendKeys("");
				} catch (NoSuchElementException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		driver.switchTo().window(parentWindowHandle);
		Assert.assertEquals(driver.getTitle(), "");
	}
	
	//����JavaScript��Alert����
	@Test
	public void testAlert(){
		WebElement alert1 = driver.findElement(By.xpath(""));
		alert1.click();
		try {
			Alert alert2 = driver.switchTo().alert();
			Assert.assertEquals("", alert2.getText());
			alert2.accept();
			//ģ����confirm���ϵ�ȡ����ť
			alert2.dismiss();
			//��prompt�������������ı�����
			alert2.sendKeys("content");
			
		} catch (NoAlertPresentException exception) {
			// TODO: handle exception
			Assert.fail("ʧ��");
			exception.printStackTrace();
		}
	}
	
	
	//����ҳ���FrameԪ��
	@Test
	public void testFrame(){
		driver.switchTo().frame("");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		driver.switchTo().defaultContent();
		
		//ʹ��Frame�е�HTMLԴ������������Frame
		List<WebElement> frames = driver.findElements(By.tagName("frame"));
		for(WebElement frame: frames){
			driver.switchTo().frame(frame);
			if (driver.getPageSource().contains("�м� frame")){
				WebElement diddleFrameText = driver.findElement(By.xpath(""));
				Assert.assertEquals("", diddleFrameText.getText());
				break;
			}else{
				driver.switchTo().defaultContent();
			}
			driver.switchTo().defaultContent();
		
		}
	}
	
	//�����������Cookie
	@Test
	public void testCookie() throws Exception{
		driver.get("");
		Set<Cookie> cookies = driver.manage().getCookies();
		Cookie  Cookie = new Cookie("cookieName", "cookieValue");
		System.out.println(String.format("Domain->name->value->expiry->path"));
		for (Cookie cookie:cookies){
			System.out.println(String.format("%s->%s->%s->%s->%s", cookie.getDomain(),cookie.getName(),
					cookie.getValue(),cookie.getExpiry(),cookie.getPath()));
			
		}
		//ɾ��cookie�����ַ���
		//1.ͨ��cookie��name����
		driver.manage().deleteCookieNamed("cookieName");
		//2.ͨ��Cookie����
		driver.manage().deleteCookie(Cookie);
		//3.ȫ��ɾ��
		driver.manage().deleteAllCookies();
		try {
			Thread.sleep(1500);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		// ����ĳ��վ����ַ
		String baseUrl = "http://www.sogou.com";
		driver.get(baseUrl);
		driver.navigate().to(baseUrl);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
