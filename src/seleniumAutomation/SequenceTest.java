//优先级安装 0 1 2 3 ....的顺序降低

//Total tests run: 3, Failures: 0, Skips: 0
//note:报告里面的skip参数表示在进行依赖测试时如有前置的测试方法未被执行成功，则后续未执行的依赖测试方法个数
//	      会被标记为skip的显示数量

package seleniumAutomation;

import org.testng.annotations.Test;

public class SequenceTest {
	@Test(priority = 2, enabled=false)
	public void test3(){
		System.out.println("test3 方法被调用");
	}
	
	@Test(priority = 3)
	public void test4(){
		System.out.println("test4 方法被调用");
	}
	
	@Test(priority = 0)
	public void test1(){
		System.out.println("test1 方法被调用");
	}
	
	@Test(priority = 1)
	public void test2(){
		System.out.println("test2方法被调用");
	}

}
