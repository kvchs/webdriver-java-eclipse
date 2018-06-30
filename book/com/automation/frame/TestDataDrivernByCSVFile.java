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
		//�趨UTF-8�ַ�����ʹ�ô����������ַ�������BufferedReader��ȡ�ļ�����
		BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
		//���Զ�ȡCSV�ļ��ı�����
		file.readLine();
		/**
		 * ������ȡ�ļ��г���һ��֮�����������������
		 * ���洢����Ϊrecords��ArrayList��
		 * ÿһ��records�д洢�Ķ���Ϊһ��String����
		 */
		while (((record=file.readLine())!=null)) {
			String fields[] = record.split(",");
			records.add(fields);
		}
		file.close();
		//���庯���ķ���ֵ�� Object[][]
		//���洢�������ݵ�listת��Ϊһ��Object�Ķ�ά����
		Object[][] results = new Object[records.size()][];
		for (int i = 0; i < records.size(); i++){
			results[i] = records.get(i);
		}
		return results;
	}
	
	@DataProvider(name = "testData")
	public static Object[][] words() throws IOException {
		//�������еľ�̬������getTestData,��ȡCSV �ļ��Ĳ�������
		return getTestData(System.getProperty("user.dir") + "\\testData.rtf");
	}
	@Test(dataProvider="testData")
	public void testSearch(String searchWord1, String searchWord2, String searchResult) {
		driver.get("http://www.sogou.com");
		driver.findElement(By.id("query")).sendKeys(searchWord1 + " " + searchWord2);
		
		driver.findElement(By.id("stb")).click();
		Assert.assertTrue(driver.getPageSource().contains(searchResult));
//		Assert.assertTrue(driver.findElement(By.id("s_footer")).getText().contains("��������"));
//		(new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
//			@Override
//			public Boolean apply(WebDriver d){
//				return d.findElement(By.id("s_footer")).getText().contains("��������");
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
