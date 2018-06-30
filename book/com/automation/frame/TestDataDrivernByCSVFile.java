package com.automation.frame;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class TestDataDrivernByCSVFile {
	public WebDriver driver;
	String baseUrl = "http://www.sogou.com";
	
	public static Object[][] getTestData(String fileName) throws IOException {
		List<Object[]> records = new ArrayList<Object[]>();
		String record;
		//设定UTF-8字符集，使用带缓冲区的字符输入流BufferedReader读取文件内容
		BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
		//忽略读取CSV文件的标题行
		file.readLine();
		/**
		 * 遍历读取文件中除第一行之外的其他行所有内容
		 * 并存储在名为records的ArrayList中
		 * 每一个records中存储的对象为一个String数组
		 */
		while (((record=file.readLine())!=null)) {
			String fields[] = record.split(",");
			records.add(fields);
		}
		file.close();
		//定义函数的返回值， Object[][]
		//将存储测试数据的list转换为一个Object的二维数组
		Object[][] results = new Object[records.size()][];
		for (int i = 0; i < records.size(); i++){
			results[i] = records.get(i);
		}
		return results;
	}
	
	@DataProvider(name = "testData")
	public static Object[][] words() throws IOException {
		//调用类中的静态方法，getTestData,获取CSV 文件的测试数据
		return getTestData(System.getProperty("user.dir") + "\\testData.rtf");
	}
	@Test(dataProvider="testData")
	public void testSearch(String searchWord1, String searchWord2, String searchResult) {
		driver.get("http://www.sogou.com");
		driver.findElement(By.id("query")).sendKeys(searchWord1 + " " + searchWord2);
		
		driver.findElement(By.id("stb")).click();
		Assert.assertTrue(driver.getPageSource().contains(searchResult));
//		Assert.assertTrue(driver.findElement(By.id("s_footer")).getText().contains("搜索帮助"));
//		(new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
//			@Override
//			public Boolean apply(WebDriver d){
//				return d.findElement(By.id("s_footer")).getText().contains("搜索帮助");
//			}
//		});
		
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
