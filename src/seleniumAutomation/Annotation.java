package seleniumAutomation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Annotation {
	@Test
	public void f1() {
		System.out.println("测试用例1被执行");
	}
	@Test
	public void f2() {
		System.out.println("测试用例2被执行");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("每个测试方法开始运行前执行");
	}

	@AfterMethod //测试方法前后function
	public void afterMethod() {
		System.out.println("每个测试方法开始运行后执行");

	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("在当前测试类的第一个测试方法开始调用前执行");
	}

	@AfterClass  //在第一个测试方法开始之前执行
	public void afterClass() {
		System.out.println("在当前测试类的第一个测试方法开始调用后执行");

	}

	@BeforeTest  //在每个测试方法运行后执行
	public void beforeTest() {
		System.out.println("在测试类中的Test开始运行前执行");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("在测试类中的Test开始运行后执行");

	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("在当前测试集合中的所有测试程序开始之前执行");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("在当前测试集合中的所有测试程序开始之后执行");

	}

}
