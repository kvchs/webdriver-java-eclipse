package com.automation.frame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.internal.FileExtension;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class TestDataDriverByExcelFile {
	WebDriver driver;
	String baseUrl = "http://www.sogou.com";
	
	@DataProvider(name = "testData")
	public static Object[] [] words() throws IOException {
		System.out.println(System.getProperty("user.dir"));
		return getTestData(System.getProperty("user.dir"), "testData.xlsx", "Sheet1");
	}
	
	
//	@DataProvider(name = "testData")
//	public static Object[] [] words(){
//		return new Object[] [] {
//				{"蝙蝠侠", "主演", "迈克尔"},
//				{"超人", "导演", "唐纳"},
//				{"生化危机", "编剧", "安德森"}
//		};
//	}
	
	@Test(dataProvider = "testData")
	public void testSearch(String searchWord1, String searchWord2, String searchResult) {
		driver.get("http://www.sogou.com");
		driver.findElement(By.id("query")).sendKeys(searchWord1 + " " + searchWord2);
		driver.findElement(By.id("stb")).click();
		
//		(new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
//			@Override
//			public Boolean apply(WebDriver d){
//				return d.findElement(By.id("s_footer")).getText().contains("搜索帮助");
//			}
//		});
		Assert.assertTrue(driver.getPageSource().contains(searchResult));
		
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	public static Object[] [] getTestData(String filePath, String fileName, String sheetName) throws IOException {
		//根据参数传入的数据文件路径和名称，组合出文件的绝对路径
		File file = new File(filePath + "\\" + fileName);
		//创建FileInputStream对象用于读取Excel文件
		FileInputStream inputStream = new FileInputStream(file);
		//声名Workbook对象
		Workbook workbook = null;
		//判定文件类型 .xlsx，使用XSSFWorkbook对象进行实例化
		//判定文件类型.xls，使用SSFWorkbook对象进行实例化
		//获取文件扩展名
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")){
			workbook = new XSSFWorkbook(inputStream);
		}else if (fileExtensionName.equals(".xls")) {
			workbook = new HSSFWorkbook(inputStream);
		}
		//通过sheetName参数，生成Sheet对象
		Sheet sheet = workbook.getSheet(sheetName);
		//获取Excel数据文件Sheet1中的数据行数，getLaseRowNum方法获取数据最后一行行号
		//getFirstRowNum方法获取数据的第一行行号，做减法可以得到行数
		//Excel文件的行号和列号都是从0开始的
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		//创建名为records的list对象来存储从Excel数据文件读取的数据
		List<Object[]> records = new ArrayList<Object[]>();
		//使用两个for循环遍历数据文件的所有数据(除了第一行)，所有i从1开始
		for (int i = 1; i < rowCount + 1; i ++) {
			Row row = sheet.getRow(i);
			//声名一个数组，用来存储Excel数据文件每行中的三个数据，数据大小用getLastCellNum进行动态声名
			//实现测试数据个数和数组大小一致
			String fields[] = new String[row.getLastCellNum()];
			for(int j = 0; j < row.getLastCellNum(); j++){
				
				// Cannot get a STRING value from a NUMERIC cell
//				fields[j] = row.getCell(j).getStringCellValue();
//				
//				DataFormatter formatter = new DataFormatter();
//				String val = formatter.formatCellValue(row.getCell(j).getStringCellValue());
				
				DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
				Cell cell = row.getCell(j);
				fields[j] = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
						
			}
			//将fields的数据对象存储到records的list中
			records.add(fields);
		}
		/**
		 * 定义函数返回值,Object[][]
		 * 将存储测试数据的list转换为一个Object的二维数组
		 * 
		 */
		
		Object[][] results = new Object[records.size()][];
		for (int i = 0; i < records.size(); i++) {
			results[i] = records.get(i);
//			System.out.println(records.get(i));
		}
		workbook.close();
		for (int i=0;i<results.length;i++){
			System.out.println(results[i]);
			for (int j =0;j<3;j++){
				results.toString();
				System.out.println(results[i][j]);
			}
		}
		return results;
	}

}
