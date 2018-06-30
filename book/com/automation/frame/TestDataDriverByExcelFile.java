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
//				{"������", "����", "���˶�"},
//				{"����", "����", "����"},
//				{"����Σ��", "���", "����ɭ"}
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
//				return d.findElement(By.id("s_footer")).getText().contains("��������");
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
		//���ݲ�������������ļ�·�������ƣ���ϳ��ļ��ľ���·��
		File file = new File(filePath + "\\" + fileName);
		//����FileInputStream�������ڶ�ȡExcel�ļ�
		FileInputStream inputStream = new FileInputStream(file);
		//����Workbook����
		Workbook workbook = null;
		//�ж��ļ����� .xlsx��ʹ��XSSFWorkbook�������ʵ����
		//�ж��ļ�����.xls��ʹ��SSFWorkbook�������ʵ����
		//��ȡ�ļ���չ��
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")){
			workbook = new XSSFWorkbook(inputStream);
		}else if (fileExtensionName.equals(".xls")) {
			workbook = new HSSFWorkbook(inputStream);
		}
		//ͨ��sheetName����������Sheet����
		Sheet sheet = workbook.getSheet(sheetName);
		//��ȡExcel�����ļ�Sheet1�е�����������getLaseRowNum������ȡ�������һ���к�
		//getFirstRowNum������ȡ���ݵĵ�һ���кţ����������Եõ�����
		//Excel�ļ����кź��кŶ��Ǵ�0��ʼ��
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		//������Ϊrecords��list�������洢��Excel�����ļ���ȡ������
		List<Object[]> records = new ArrayList<Object[]>();
		//ʹ������forѭ�����������ļ�����������(���˵�һ��)������i��1��ʼ
		for (int i = 1; i < rowCount + 1; i ++) {
			Row row = sheet.getRow(i);
			//����һ�����飬�����洢Excel�����ļ�ÿ���е��������ݣ����ݴ�С��getLastCellNum���ж�̬����
			//ʵ�ֲ������ݸ����������Сһ��
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
			//��fields�����ݶ���洢��records��list��
			records.add(fields);
		}
		/**
		 * ���庯������ֵ,Object[][]
		 * ���洢�������ݵ�listת��Ϊһ��Object�Ķ�ά����
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
