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

	// 访问上一个网页(模拟单击浏览器的后退功能)
	@Test
	public void visitRecentURL() {
		String url1 = "";
		String url2 = "";
		driver.navigate().to(url1);
		driver.navigate().to(url2);
		driver.navigate().back();
	}

	// (模拟单击浏览器的前进功能)
	@Test
	public void visitNextURL() {
		String url1 = "";
		String url2 = "";
		driver.navigate().to(url1);
		driver.navigate().to(url2);
		driver.navigate().back();
		driver.navigate().forward();
		// 刷新当前页面
		driver.navigate().refresh();
	}

	// 操作浏览器窗口
	@Test
	public void operateBrowser() {
		/* 声名一个Point对象，代表浏览器的位置相对于屏幕左上角的横纵坐标距离 */
		Point point = new Point(150, 150);
		// 声名Dimension对象，两个500表示浏览器窗口的长度和宽度
		Dimension dimension = new Dimension(500, 500);
		// setPosition方法表示设定浏览器在屏幕上的位置为point对象的坐标,在某些版本的浏览器下此方法无效
		driver.manage().window().setPosition(point);
		// setSize方法表示设定浏览器窗口大小为长为500个单位，宽为500个单位
		driver.manage().window().setSize(dimension);
		// getPosition方法为获取浏览器在屏幕的位置，在某些浏览器下此方法无效
		System.out.println(driver.manage().window().getPosition());
		System.out.println(driver.manage().window().getSize());
		driver.manage().window().maximize();
		driver.get("");

	}

	@Test
	public void utilMethod() {
		String title = driver.getTitle();
		Assert.assertEquals("", title);
		// 获取页面上的源码
		String pageSource = driver.getPageSource();
		Assert.assertTrue(pageSource.contains("expect string"));

		String currentPageUrl = driver.getCurrentUrl();
		// 清除文本内容
		WebElement input = driver.findElement(By.id("text"));
		input.clear();
		// 此try catch代码可选，主要用于等待3秒，查看操作后页面的结果
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		input.sendKeys("");
		WebElement btn = driver.findElement(By.xpath(""));
		btn.click();
		// 双击某个元素
		Actions builder = new Actions(driver);
		builder.doubleClick(btn).build().perform();

	}

	@Test
	public void operateDropList() {
		// 使用name属性找到页面上name为“fruit”的下拉列表元素
		Select dropList = new Select(driver.findElement(By.name("furit")));
		// isMultiple表示此下拉列表是否允许多选
		Assert.assertFalse(dropList.isMultiple());
		// getFirstSelectdOption().getText()方法获取当前被选中的下拉列表选项文本
		// Assert.assertEquals 方法断言当前选中的文本是否是“桃子”
		Assert.assertEquals("桃子", dropList.getFirstSelectedOption().getText());

		// selectByIndex方法表示选中下拉的第四个选项，0代表第一个选项
		dropList.selectByIndex(3);

		// selectByValue方法表示使用下拉列表选项的value属性值进行选中操作，“shanzha”是选项山楂的属性值
		dropList.selectByValue("shanzha");

		// selectByVisibleText方法表示通过选项的文字来进行选中
		dropList.selectByVisibleText("荔枝");
		Assert.assertEquals("荔枝", dropList.getFirstSelectedOption().getText());

	}

	// 检查单选列表的选项文字是否符合期望
	@Test
	public void checkSelectText() {
		// 使用name属性找到页面上name为“fruit”的下拉列表元素
		Select dropList = new Select(driver.findElement(By.name("furit")));
		/*
		 * 声名一个list对象存储下拉列表中所有期望出现的选项文字，并且通过泛型<String> 限定list对象的存储对象类型 是String,
		 * Array.asList表示将一个数组转换为一个list对象
		 */
		List<String> expect_options = Arrays
				.asList((new String[] { "桃子", "西瓜" }));

		// 声名一个新的list对象，用来存取从页面上获取的选项文字
		List<String> actual_result = new ArrayList<String>();
		// dropList.getOptions方法用于获取页面上下拉列表的所有选项对象，actual_options.add方法用于
		// 将实际页面中的每个选项都添加到actual_option列表中
		for (WebElement option : dropList.getOptions()) {
			actual_result.add(option.getText());
			Assert.assertEquals(expect_options.toArray(),
					actual_result.toArray());
		}

		// 操作多选列表
		Assert.assertTrue(dropList.isMultiple());
		dropList.deselectAll();
		dropList.deselectByIndex(1);
		dropList.deselectByValue("");
		dropList.deselectByVisibleText("");

	}

	// 单选框和复选框的操作
	@Test
	public void operateRadio() {
		WebElement radioOption = driver.findElement(By.id(""));
		if (!radioOption.isSelected()) {
			radioOption.click();
		}
		assertTrue(radioOption.isSelected());
		// 查找name属性为"fruit" 的所有单选按钮对象，并存储到一个list容器中
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

	// 截图操作
	@Test
	public void captureScreenInCurrentWindow() {
		driver.get("");
		// 调用getScreenshotAs方法把当前浏览器打开的页面进行截图，保存到一个File对象中
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
		// document.getElementById('') 是JavaScript代码，表示获取页面搜索按钮对象
		// return button.value表示返回搜索按钮上的文字
		String searchButtonText = (String) js
				.executeScript("var button=document.getElementById('stb');return button.value");
		System.out.println(searchButtonText);
	}
	
	//将页面元素拖拽到指定位置
	@Test
	public void dragPageElement(){
		driver.get("");
		WebElement draggable = driver.findElement(By.id(""));
		
		//向下拖动10个像素，共拖动5次
		for (int i = 0; i < 5; i++){
			new Actions(driver).dragAndDropBy(draggable, 0, 10).build().perform();
		}
		//向右拖动10个像素，共拖动5次
		for(int i = 0; i < 5; i++){
			new Actions(driver).dragAndDropBy(draggable, 10, 0);
		}
	}
	
	@Test
	public void clickKeys(){
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL);  //按下Ctrl键
		actions.keyDown(Keys.SHIFT);
		actions.keyDown(Keys.ALT);
		actions.keyUp(Keys.SHIFT); //释放shift键
		actions.keyUp(Keys.ALT);
		actions.keyDown(Keys.CONTROL);
		//模拟键盘在输入框中输入大写的'ABC'
		actions.keyDown(Keys.SHIFT).sendKeys("abc").perform();
		
		//模拟鼠标右击操作
		//调用Action对象的contextClick方法，在ID为query的输入框内单击右键
		actions.contextClick(driver.findElement(By.id(""))).perform();
		
		//鼠标悬停
		actions.moveToElement(driver.findElement(By.xpath(""))).perform();
		
	}
	
	
	/**
	 *                                        常用的显式等待条件
	 *  elementToBeClickable(By locator)            页面元素是否在页面上可用和可被点击
	 *  elementToBeSelected(WebElement element)     页面元素处于被选中的状态
	 *  presenceOfElementLocated(By locator)        页面元素在页面中存在
	 *  textToBePresentInElement(By locator)        在页面元素中是否包含特定的文本
	 *  textToBePresentInElementValue(By locator.java.lang.String text) 页面元素值
	 *  titleContains(java.lang.String title)       标题
 	 * 
	 */
	//测试显式等待
	@Test
	public void testExplicitWait(){
		//声名一个WebDriverWait对象，设定触发条件的最大等待时间为10秒
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//调用ExpectedConditions的titleContains方法判定页面title
		wait.until(ExpectedConditions.titleContains("水果"));
		
		WebElement select = driver.findElement(By.xpath(""));
		wait.until(ExpectedConditions.elementToBeSelected(select));
		
		wait.until(ExpectedConditions.elementToBeClickable(select));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("")));
		wait.until(ExpectedConditions.textToBePresentInElement(select, ""));
		
	}
	
	/**
	 *                              自定义显式等待
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
//							return d.findElement(By.xpath("")).getText().contains("爱吃");
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
//		//显式等待Ajax请求是否加载完成
//		Boolean ajaxRequestFinishFlag = (new WebDriverWait(driver, 10))
//				.until(new ExpectedCondition<Boolean>() {
//					@Override
//					public boolean apply(WebDriver d){
//						JavascriptExecutor jsExecutor = (JavascriptExecutor) d;
//						return (boolean)jsExecutor.executeScript("return jQuery.active==0");
//					}
//				});
//	}
	
	
	
	//在指定元素上进行点击左键和释放操作
	@Test
	public void mouseClickAndRelease(){
		WebElement div = driver.findElement(By.xpath(""));
		//查看元素属性
		String inpuTextString = div.getAttribute("value");
		//获取CSS属性值
		String inputWidth = div.getCssValue("width");
		//隐式等待
		/**
		 * 使用implicitlyWait方法设定查找页面元素的等待时间，调用findElement方法时没有立刻找到该定位元素会
		 * 等待设定的时长，如果还没有找到就会抛出NoSuchElementException
		 */
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			WebElement findElement = driver.findElement(By.cssSelector(""));
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			//使用fail方法在找不到元素的时候，让测试用例执行失败
			Assert.fail("测试失败");
			//打印错误的堆栈信息
			e.printStackTrace();
		}
		Actions action = new Actions(driver);
		//调用Action对象的clickAndHold方法，在元素单击鼠标左键不释放
		action.clickAndHold(div).perform();
		try{
			Thread.sleep(3);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		//调用Action对象的release方法，释放左键
		action.release(div).perform();
		try {
			Thread.sleep(3);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//判断一个页面元素是否存在的函数
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
	
	
	
	//使用Title属性识别和操作新弹出的浏览器窗口
	@Test
	public void identifyPopUpWindowByTitle(){
		//先将当前浏览器窗口的句柄存储到parentWindowHandle变量中
		String parentWindowHandle = driver.getWindowHandle();
		//找到页面上唯一的link，存储到sougouLink变量中
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
	
	//使用页面的文字内容识别处理新弹出的浏览器窗口
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
	
	//操作JavaScript的Alert弹窗
	@Test
	public void testAlert(){
		WebElement alert1 = driver.findElement(By.xpath(""));
		alert1.click();
		try {
			Alert alert2 = driver.switchTo().alert();
			Assert.assertEquals("", alert2.getText());
			alert2.accept();
			//模拟点击confirm框上的取消按钮
			alert2.dismiss();
			//向prompt框体内容输入文本内容
			alert2.sendKeys("content");
			
		} catch (NoAlertPresentException exception) {
			// TODO: handle exception
			Assert.fail("失败");
			exception.printStackTrace();
		}
	}
	
	
	//操作页面的Frame元素
	@Test
	public void testFrame(){
		driver.switchTo().frame("");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		driver.switchTo().defaultContent();
		
		//使用Frame中的HTML源码内容来操作Frame
		List<WebElement> frames = driver.findElements(By.tagName("frame"));
		for(WebElement frame: frames){
			driver.switchTo().frame(frame);
			if (driver.getPageSource().contains("中间 frame")){
				WebElement diddleFrameText = driver.findElement(By.xpath(""));
				Assert.assertEquals("", diddleFrameText.getText());
				break;
			}else{
				driver.switchTo().defaultContent();
			}
			driver.switchTo().defaultContent();
		
		}
	}
	
	//操作浏览器的Cookie
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
		//删除cookie的三种方法
		//1.通过cookie的name属性
		driver.manage().deleteCookieNamed("cookieName");
		//2.通过Cookie对象
		driver.manage().deleteCookie(Cookie);
		//3.全部删除
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
		// 访问某网站的网址
		String baseUrl = "http://www.sogou.com";
		driver.get(baseUrl);
		driver.navigate().to(baseUrl);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
