package keyWordsFrameWork.gloryroad.util;

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

import keyWordsFrameWork.gloryroad.data.Constants;

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
					System.getProperty("user.dir")
							+ "\\KeyWordsFrameWork\\关键字驱动测试用例");
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
	public static String[][] getTestData(String excelFilePath, String sheetName)
			throws IOException {
		File file = new File(excelFilePath);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		String fileExtensionName = excelFilePath.substring(excelFilePath
				.indexOf("."));
		System.out.println(fileExtensionName);
		if (fileExtensionName.equals(".xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			workbook = new HSSFWorkbook(inputStream);
		}

		Sheet sheet = workbook.getSheet(sheetName);
		DataFormatter formatter = new DataFormatter();
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		List<String[]> records = new ArrayList<String[]>();
		for (int i = 1; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			// System.out.println(row);
			// 此处-4是因为Excel数据表结构错误，多了两列空格
			String fields[] = new String[row.getLastCellNum() - 4];
			// System.out.println(formatter.formatCellValue(row.getCell(10)));
			// System.out.println("+++++++++"+row.getLastCellNum() +"+++++++");
			// if
			// (row.getCell(row.getLastCellNum()-3).getStringCellValue().equals("y"))
			// {
			if (formatter.formatCellValue(row.getCell(10)).equals("y")) {
				for (int j = 0; j < row.getLastCellNum() - 4; j++) {
					// fields[j] = (String) (row.getCell(j).getCellType() ==
					// XSSFCell.CELL_TYPE_STRING?
					// row.getCell(j).getStringCellValue() :"" +
					// row.getCell(j).getNumericCellValue());

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

	public static int getLastColumnNum() {
		// 返回数据文件最后一列的列号，如何有12列，则结果为11
		return ExcelWSheet.getRow(0).getLastCellNum();
	}

	// 在读写Excel文件的时候，要设置要操作的Excel文件路径
	public static void setExcelFile(String Path) {
		FileInputStream ExcelFile;
		try {
			ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 读取指定Sheet的单元格函数，此函数值支持.xlsx的Excel文件
	public static String getCellData(String sheetName, int RowNum, int colNum) {
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(colNum);
			DataFormatter formatter = new DataFormatter();
			String CellData = formatter.formatCellValue(Cell);
			return CellData;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}
	}

	// 指定Sheet中的数据总行数
	public static int getRowCount(String SheetName) {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int number = ExcelWSheet.getLastRowNum();
		return number;
	}
	public static void main(String[] args) {
		int testCasesCount = ExcelUtil.getRowCount(Constants.Sheet_TestSuite);
		System.out.println(Constants.Sheet_TestSuite);
		//System.out.println(testCasesCount);
	}
	

	// 在Excel中，获取第一次包含指定测试用例序号文字的行号
	public static int getFirstRowContainsTestCaseID(String sheetName,
			String testCaseName, int colNum) {
		int i;
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		int rowCount = ExcelUtil.getRowCount(sheetName);
		for (i = 0; i < rowCount; i++) {
			if (ExcelUtil.getCellData(sheetName, i, colNum).equalsIgnoreCase(
					testCaseName)) {
				break;
			}
		}
		return i;
	}

	// 指定Sheet中某个测试用例步骤的个数
	public static int getTestCaseLastStepRow(String sheetName,
			String testCaseID, int testCaseStartRowNumber) {
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		for (int i = testCaseStartRowNumber; i < ExcelUtil
				.getRowCount(sheetName) - 1; i++) {
			if (!testCaseID.equals(ExcelUtil.getCellData(sheetName, i,
					Constants.Col_TestCaseID))) {
				int number = i;
				return number;
			}
		}
		int number = ExcelWSheet.getLastRowNum() + 1;
		return number;
	}

}
