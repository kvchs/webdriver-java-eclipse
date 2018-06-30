package cn.gloryroad;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import sun.security.krb5.internal.crypto.crc32;


public class Table {
	private WebElement _table;
	//Ϊ���캯������ҳ����Ԫ�ض������������Table���settable��������ҳ����Ԫ�ظ�ֵ
	//��Table���_table��Ա
	public Table(WebElement table){
		setTable(table);
	}
	
	//��ȡҳ�������ķ���
	public WebElement getTable(){
		return _table;
	}
	
	//��ҳ����Ԫ�ظ�ֵ��Table���е�_table��Ա�����ķ���
	public void setTable(WebElement _table){
		this._table = _table;
	}
	
	/**
	 * ��ȡ���Ԫ�ص����������ұ��Ԫ���м���trԪ��
	 * �м���trԪ�أ��Ϳ���֪������ж����У�tr�����ͱ������һ��
	 * 
	 */
	
	public int getRowCount(){
		List<WebElement> tableRows = _table.findElements(By.tagName("tr"));
		return tableRows.size();
	}
	
	/**
	 * ��ȡ���Ԫ�ص�����
	 * ʹ��get(0) ��������ȡ������һ�е�Ԫ�أ������м�����td��,td ����������һ��
	 */
	
	public int getColumnCount(){
		List<WebElement> tableRows = _table.findElements(By.tagName("tr"));
		return tableRows.get(0).findElements(By.tagName("td")).size();
	}
	
	//��ȡ�����ĳ��ĳ�еĵ�Ԫ�����
	public WebElement getCell(int rowNo, int colNo) throws NoSuchElementException {
		try {
			List<WebElement> tableRows = _table.findElements(By.tagName("tr"));
			System.out.println("������:" + tableRows.size());
			System.out.println("�кţ�" + rowNo);
			WebElement currentRow = tableRows.get(rowNo - 1);
			List<WebElement> tablecols = currentRow.findElements(By.tagName("td"));
			System.out.println("��������" + tablecols.size());
			WebElement cell = tablecols.get(colNo - 1);
			System.out.println("�к�:" + colNo);
			return cell;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			throw new NoSuchElementException("û���ҵ����Ԫ��");
		}
	}
	
	/**
	 * ��ȡ�����ĳ��ĳ�еĵ�Ԫ���е�ĳ��ҳ�����by�������ڶ�λĳ����
	 */
	public WebElement getWebElementInElementCell(int rowNo, int colNo, By by) throws 
	NoSuchElementException {
		try {
			List<WebElement> tableRows = _table.findElements(By.tagName("tr"));
			//�ҵ�����е�ĳһ�У���Ϊ�кŴ�0��ʼ
			
			WebElement currentRow = tableRows.get(rowNo-1);
			List<WebElement> tablecols = currentRow.findElements(By.tagName("td"));
			/**
			 * �ҵ�����е�ĳһ�У���Ϊ�к�Ҳ��0��ʼ��
			 */
			WebElement cell = tablecols.get(colNo-1);
			return cell.findElement(by);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			throw new NoSuchElementException("û�����Ԫ��");
		}
	}

}
