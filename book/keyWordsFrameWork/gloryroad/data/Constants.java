package keyWordsFrameWork.gloryroad.data;

public class Constants {
	public static final String Path_ExcelFile = System.getProperty("user.dir")
			+ "\\KeyWordsFrameWork\\关键字驱动测试用例.xlsx";
	public static final String Path_ConfigurationFile = System.getProperty("user.dir")
			+ "\\KeyWordsFrameWork\\objectMap.properties";
	
	//测试数据sheet中的列号常量设置
	public static final int Col_TestCaseID = 0;
	public static final int Col_KeyWordAction = 3;
	public static final int Col_ActionValue = 4;
	public static final int Col_RunFlag = 2;
	public static final String Sheet_TestSteps = "Sheet1";//发送邮件
	public static final String Sheet_TestSuite = "Sheet2";//测试用例集合

}
