package seleniumAutomation;

import org.testng.annotations.Test;
import org.testng.Reporter;

public class TestngReporter {
	@Test
	public void OpenBrowser(){
		System.out.println("OpenBrowser ����������");
		Reporter.log("���ô�������ķ���");
	}
	
	@Test
	public void SignIn(){
		System.out.println("SignIn ����������");
		Reporter.log("���õ�¼����");
	}

	
	@Test
	public void LogOut(){
		System.out.println("LogOut����������");
		Reporter.log("����ע������");
	}
	
	
}
