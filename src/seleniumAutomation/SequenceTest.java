//���ȼ���װ 0 1 2 3 ....��˳�򽵵�

//Total tests run: 3, Failures: 0, Skips: 0
//note:���������skip������ʾ�ڽ�����������ʱ����ǰ�õĲ��Է���δ��ִ�гɹ��������δִ�е��������Է�������
//	      �ᱻ���Ϊskip����ʾ����

package seleniumAutomation;

import org.testng.annotations.Test;

public class SequenceTest {
	@Test(priority = 2, enabled=false)
	public void test3(){
		System.out.println("test3 ����������");
	}
	
	@Test(priority = 3)
	public void test4(){
		System.out.println("test4 ����������");
	}
	
	@Test(priority = 0)
	public void test1(){
		System.out.println("test1 ����������");
	}
	
	@Test(priority = 1)
	public void test2(){
		System.out.println("test2����������");
	}

}
