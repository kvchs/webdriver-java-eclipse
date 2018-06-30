/**
 * 下载不同版本的数据库连接驱动文件
 * https://mvnrepository.com/artifact/mysql/mysql-connector-java
 */
package com.automation.frame;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.sql.Statement;

import sun.launcher.resources.launcher;

public class TestDataDrivernByMysqlDatabase {
	
	WebDriver driver;
	String baseUrlString = "http://www.sogou.com";
	
	@DataProvider(name = "testData")
	public static Object[][] words() throws IOException{
		return getTestData("testdate");
	}
	
	
	@Test(dataProvider="testData")
	public void testSearch(String searchWord1, String searchWord2, String searchResult) throws InterruptedException {
		driver.get(baseUrlString);
		driver.findElement(By.id("query")).sendKeys(searchWord1 + " " + searchWord2);
		driver.findElement(By.id("stb")).click();
		Thread.sleep(5000);
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			
			public Boolean apply(WebDriver d) {
				// TODO Auto-generated method stub
				return d.findElement(By.xpath("//div[@safeclass~'\ball-time-box\b']/a[@innertext='全部时间']")).getText().contains("全部时间");
			}
		});
		Assert.assertTrue(driver.getPageSource().contains(searchResult));
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\tools\\chromedriver.exe");
		driver = new OperaDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	public static Object[][] getTestData(String tablename) throws IOException {
		// 声名MySQL数据库驱动
//		runtime("mysql:mysql-connector-java:6.0.5");
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/gloryroad";
		String user = "root";
		String password = "123456";
		List<Object[]> records = new ArrayList<Object[]>();
		try {
			// 设定驱动
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed()) {
				System.out.println("数据库连接成功");
				Statement statement = conn.createStatement();
				String sql = "select * from" + " " + tablename + ";";
				System.out.println(sql);
				ResultSet rs = statement.executeQuery(sql);
				ResultSetMetaData rsMetaData = rs.getMetaData();
				int cols = rsMetaData.getColumnCount();
				while (rs.next()) {
					String fields[] = new String[cols];
					int col = 0;
					for (int colIdx = 0; colIdx < cols; colIdx++) {
						fields[col] = rs.getString(colIdx + 1);
						col++;
					}
					records.add(fields);
					System.out.println(rs.getString(1) + " " + rs.getString(2)
							+ " " + rs.getString(3));
				}
				rs.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("未能找到Mysql的驱动类");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Object[][] results = new Object[records.size()][];
		for (int i = 0; i < records.size(); i++) {
			results[i] = records.get(i);
		}
		return results;

	}
}
