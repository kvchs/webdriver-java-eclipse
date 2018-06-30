package cn.gloryroad;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import sun.security.krb5.internal.crypto.crc32;


public class Table {
	private WebElement _table;
	//为构造函数传入页面表格元素对象参数，调用Table类的settable方法，将页面表格元素赋值
	//给Table类的_table成员
	public Table(WebElement table){
		setTable(table);
	}
	
	//获取页面表格对象的方法
	public WebElement getTable(){
		return _table;
	}
	
	//将页面表格元素赋值给Table类中的_table成员变量的方法
	public void setTable(WebElement _table){
		this._table = _table;
	}
	
	/**
	 * 获取表格元素的行数，查找表格元素有几个tr元素
	 * 有几个tr元素，就可以知道表格有多少行，tr数量和表格行数一致
	 * 
	 */
	
	public int getRowCount(){
		List<WebElement> tableRows = _table.findElements(By.tagName("tr"));
		return tableRows.size();
	}
	
	/**
	 * 获取表格元素的列数
	 * 使用get(0) 从容器中取出表格第一行的元素，查找有几个“td”,td 数量和列数一致
	 */
	
	public int getColumnCount(){
		List<WebElement> tableRows = _table.findElements(By.tagName("tr"));
		return tableRows.get(0).findElements(By.tagName("td")).size();
	}
	
	//获取表格中某行某列的单元格对象
	public WebElement getCell(int rowNo, int colNo) throws NoSuchElementException {
		try {
			List<WebElement> tableRows = _table.findElements(By.tagName("tr"));
			System.out.println("总行数:" + tableRows.size());
			System.out.println("行号：" + rowNo);
			WebElement currentRow = tableRows.get(rowNo - 1);
			List<WebElement> tablecols = currentRow.findElements(By.tagName("td"));
			System.out.println("总列数：" + tablecols.size());
			WebElement cell = tablecols.get(colNo - 1);
			System.out.println("列号:" + colNo);
			return cell;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			throw new NoSuchElementException("没有找到相关元素");
		}
	}
	
	/**
	 * 获取表格中某行某列的单元格中的某个页面对象，by参数用于定位某个表
	 */
	public WebElement getWebElementInElementCell(int rowNo, int colNo, By by) throws 
	NoSuchElementException {
		try {
			List<WebElement> tableRows = _table.findElements(By.tagName("tr"));
			//找到表格中的某一行，因为行号从0开始
			
			WebElement currentRow = tableRows.get(rowNo-1);
			List<WebElement> tablecols = currentRow.findElements(By.tagName("td"));
			/**
			 * 找到表格中的某一列，因为列号也从0开始，
			 */
			WebElement cell = tablecols.get(colNo-1);
			return cell.findElement(by);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			throw new NoSuchElementException("没有相关元素");
		}
	}

}
