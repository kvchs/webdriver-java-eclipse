package dataDrivenFrameWork.gloryroad.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.RowIdLifetime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

/**
 * @author Administrator 实现文件扩展名为.xlsx的Excel文件操作
 */
public class ExcelUtil {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	private static MissingCellPolicy xRow;

	// 设定要操作的Excel文件的路径和Sheet名称
	// 读写Excel时，都需要此方法，
	public static void setExcelFile(String Path, String SheetName)
			throws Exception {
		FileInputStream ExcelFile;
		try {
			ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			// TODO: handle exception
			throw (e);
		}
	}

	// 读取Excel文件指定单元格函数
	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {
			String CellData = "";

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			if (Cell.getCellTypeEnum() == CellType.STRING) {
				CellData = Cell.getStringCellValue() + "";
			} else if (Cell.getCellTypeEnum() == CellType.NUMERIC) {
				CellData = String
						.valueOf(Math.round(Cell.getNumericCellValue()));
			}
			return CellData;
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	// 在Excel文件的执行单元格中写入数据
	public static void setCellData(int RowNum, int ColNum, String Result)
			throws Exception {
		try {
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}
			FileOutputStream fileOut = new FileOutputStream(
					Constant.TestDataExcelFilePath);
			ExcelWBook.write(fileOut);
			// 调用flush方法强制刷新写入的文件
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}

	
	/**
	 * 
	 * @param excelFilePath
	 * @param sheetName
	 * @return
	 * @throws IOException
	 */
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
	
	// 从Excel文件获取测试数据的静态方法
	public static String[][] getTestDataOld(String excelFilePath, String sheetName)
			throws IOException {
		
		File file = new File(excelFilePath);
		FileInputStream inputStream = new FileInputStream(file);
		
		Workbook workbook = null;
		//判定文件类型 .xlsx，使用XSSFWorkbook对象进行实例化
		//判定文件类型.xls，使用SSFWorkbook对象进行实例化
		//获取文件扩展名
		String fileExtensionName = excelFilePath.substring(excelFilePath.indexOf("."));
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
		List<String[]> records = new ArrayList<String[]>();
		//使用两个for循环遍历数据文件的所有数据(除了第一行)，所有i从1开始
		for (int i = 1; i < rowCount + 1; i ++) {
			Row row = sheet.getRow(i);
			//声名一个数组，用来存储Excel数据文件每行中的三个数据，数据大小用getLastCellNum进行动态声名
			//实现测试数据个数和数组大小一致
			String fields[] = new String[row.getLastCellNum() - 2];
			if(row.getCell(row.getLastCellNum() - 2).getStringCellValue()
					.equals("y")){
				for(int j = 0; j < row.getLastCellNum()-2; j++){
					
					// Cannot get a STRING value from a NUMERIC cell
//					fields[j] = row.getCell(j).getStringCellValue();
//					
//					DataFormatter formatter = new DataFormatter();
//					String val = formatter.formatCellValue(row.getCell(j).getStringCellValue());
					
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					Cell cell = row.getCell(j);
					fields[j] = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
							
				}
				//将fields的数据对象存储到records的list中
				records.add(fields);
				
			}
			
		}
		/**
		 * 定义函数返回值,String[][]
		 * 将存储测试数据的list转换为一个String的二维数组
		 * 
		 */
		
		String[][] results = new String[records.size()][];
		for (int i = 0; i < records.size(); i++) {
			results[i] = records.get(i);
			System.out.println(records.get(i));
		}
//		workbook.close();
		for (int i=0;i<results.length;i++){
			System.out.println(results[i]);
			for (int j =0;j<3;j++){
				results.toString();
				System.out.println(results[i][j]);
			}
		}
		System.out.println("ddddd");
		return results;
	}
		
		
//		Workbook Workbook = null;
//		String FileExtensionName = excelFilePath.substring(excelFilePath
//				.indexOf("."));
//		if (FileExtensionName.equals(".xlsx")) {
//			Workbook = new XSSFWorkbook(inputStream);
//		} else if (FileExtensionName.equals(".xls")) {
//			Workbook = new HSSFWorkbook(inputStream);
//		}
//
//		Sheet sheet = Workbook.getSheet(sheetName);
//		// 注意： Excel文件的行号和列号都是从0开始
//		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
//		List<String[]> records = new ArrayList<String[]>();
//		for (int i = 1; i < rowCount; i++) {
//			org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
//			String fields[] = new String[row.getLastCellNum() - 2];
//			if (row.getCell(row.getLastCellNum() - 2).getStringCellValue()
//					.equals("y")) {
//				for (int j = 0; j < row.getLastCellNum() - 2; j++) {
//					fields[j] = (String) (row.getCell(j).getCellType() == XSSFCell.CELL_TYPE_STRING ? row
//							.getCell(j).getStringCellValue() : ""
//							+ row.getCell(j).getNumericCellValue());
//				}
//				records.add(fields);
//			}
//		}
//		String[][] results = new String[records.size()][];
//		for (int i = 0; i< records.size(); i ++){
//			results[i] = records.get(i);
//			System.out.println(results[i]);
//		}
////		Workbook.close();
//		return results;

	//}
	
	
	
	public static String[][] read_xlsx(String filepath, String tab)throws Exception {
        try{                                                                        // create a new "excel" file with location"filename"
        FileInputStream input_document = new FileInputStream(new File(filepath));// link "excel" to "program"as InputStream
        XSSFWorkbook xlsx_workbook = new XSSFWorkbook(input_document);// connect the excel using InputStream
        XSSFSheet xlsx_worksheet = xlsx_workbook.getSheet(tab);// connect to the tab of excel
        int rownum = xlsx_worksheet.getLastRowNum() - xlsx_worksheet.getFirstRowNum();;
        int colnum = xlsx_worksheet.getRow(0).getLastCellNum() - 2;
        
        String[][] data = new String[rownum][colnum];
        for (int i = 1; i < rownum + 1; i++) {
            Row row = xlsx_worksheet.getRow(i);
            System.out.println(row.getCell(row.getLastCellNum()-2).getStringCellValue());
            if (row != null && row.getCell(row.getLastCellNum()-2).getStringCellValue().equals("y")) {
                for (int j = 0; j < colnum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        try {
                            data[i][j] = cell.getStringCellValue();
                        	//data[i][j] = cell.getCellType() == XSSFCell.CELL_TYPE_STRING ? cell.getStringCellValue(): "" + cell.getNumericCellValue();
                            
                            System.out.println(data[i][i]);
                        } catch (IllegalStateException e) {
                            data[i][j] = new BigDecimal(cell.getNumericCellValue()).toPlainString().toString().trim();
                        }
                    }
                }
            }
        }
         return data; 

        }
        catch(Exception e){
            System.out.print("ERROR OCCURED WHILE READING EXCEL"+filepath+e);
        }
        return null;

 }
	
	public static int getLastColumnNum(){
		//返回数据文件最后一列的列号，如何有12列，则结果为11
		return ExcelWSheet.getRow(0).getLastCellNum() - 1;
	}

}
