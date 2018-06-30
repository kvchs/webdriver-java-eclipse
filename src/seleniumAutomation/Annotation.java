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
		System.out.println("��������1��ִ��");
	}
	@Test
	public void f2() {
		System.out.println("��������2��ִ��");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("ÿ�����Է�����ʼ����ǰִ��");
	}

	@AfterMethod //���Է���ǰ��function
	public void afterMethod() {
		System.out.println("ÿ�����Է�����ʼ���к�ִ��");

	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("�ڵ�ǰ������ĵ�һ�����Է�����ʼ����ǰִ��");
	}

	@AfterClass  //�ڵ�һ�����Է�����ʼ֮ǰִ��
	public void afterClass() {
		System.out.println("�ڵ�ǰ������ĵ�һ�����Է�����ʼ���ú�ִ��");

	}

	@BeforeTest  //��ÿ�����Է������к�ִ��
	public void beforeTest() {
		System.out.println("�ڲ������е�Test��ʼ����ǰִ��");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("�ڲ������е�Test��ʼ���к�ִ��");

	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("�ڵ�ǰ���Լ����е����в��Գ���ʼ֮ǰִ��");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("�ڵ�ǰ���Լ����е����в��Գ���ʼ֮��ִ��");

	}

}
