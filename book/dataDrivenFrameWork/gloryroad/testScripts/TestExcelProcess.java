package dataDrivenFrameWork.gloryroad.testScripts;

import java.io.IOException;

import dataDrivenFrameWork.gloryroad.util.Constant;
import dataDrivenFrameWork.gloryroad.util.ExcelUtil;
import dataDrivenFrameWork.gloryroad.util.Test;

public class TestExcelProcess {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
    String[][] data = Test.getTestData(Constant.TestDataExcelFilePath, Constant.TestDataExcelFileSheet);

    
    for (int i = 0 ; i < data.length; i++) {
    	for (int j = 0; j < data[i].length; j++) {
    		System.out.println("data 二维数组的值是： " + data[i][j]);
    	}
    }
	}

}