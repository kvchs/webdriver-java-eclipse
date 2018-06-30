package seleniumAutomation;

import org.testng.annotations.Test;
import org.testng.Reporter;

public class TestngReporter {
	@Test
	public void OpenBrowser(){
		System.out.println("OpenBrowser 方法被调用");
		Reporter.log("调用打开浏览器的方法");
	}
	
	@Test
	public void SignIn(){
		System.out.println("SignIn 方法被调用");
		Reporter.log("调用登录方法");
	}

	
	@Test
	public void LogOut(){
		System.out.println("LogOut方法被调用");
		Reporter.log("调用注销方法");
	}
	
	
}
