package dataDrivenFrameWork.gloryroad.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test  {
	public static String[][] getTestData(String excelFilePath, String sheetName) throws IOException{
		File file = new File(excelFilePath);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		String fileExtensionName = excelFilePath.substring(excelFilePath.indexOf("."));
		System.out.println(fileExtensionName);
		if (fileExtensionName.equals(".xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		}else if (fileExtensionName.equals(".xls")) {
			workbook = new HSSFWorkbook(inputStream);
		}
		
		Sheet sheet = workbook.getSheet(sheetName);
		 DataFormatter formatter = new DataFormatter();
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		List<String[]> records = new ArrayList<String[]>();
		for (int i = 1; i <= rowCount; i++) {
			Row row  = sheet.getRow(i);
//			System.out.println(row);
			//此处-4是因为Excel数据表结构错误，多了两列空格
			String fields[] = new String[row.getLastCellNum() - 4];
//			System.out.println(formatter.formatCellValue(row.getCell(10)));
//			System.out.println("+++++++++"+row.getLastCellNum() +"+++++++");
//			if (row.getCell(row.getLastCellNum()-3).getStringCellValue().equals("y")) {
			if (formatter.formatCellValue(row.getCell(10)).equals("y")) {
				for(int j = 0; j < row.getLastCellNum()- 4; j++) {
//					fields[j] = (String) (row.getCell(j).getCellType() == XSSFCell.CELL_TYPE_STRING?
//							row.getCell(j).getStringCellValue() :"" + row.getCell(j).getNumericCellValue());
		
					fields[j] = formatter.formatCellValue(row.getCell(j));
				}
				records.add(fields);
			}
		}
		String[][] results = new String[records.size()][];
		for (int i = 0; i < records.size(); i++) {
			results[i] = records.get(i);
		}
		workbook.close();
		return results;
	
	}
	
}