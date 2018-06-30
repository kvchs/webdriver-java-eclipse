package seleniumAutomation;

import org.testng.annotations.Test;

public class DependentTest {
	@Test (dependsOnMethods={"OpenBrowser"})
	public void SignIn(){
		System.out.println("SignIn方法被调用");
	}
	
	@Test 
	public void OpenBrowser(){
		System.out.println("OpenBrowser方法被调用");
	}
	
	@Test (dependsOnMethods={"SignIn"})
	public void LogOut(){
		System.out.println("LogOut方法被调用");
	}
	
}
