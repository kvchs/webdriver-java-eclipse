package cn.gloryroad;

import org.apache.log4j.Logger;

public class Log {
	// 初始化一个Logger对象
	private static Logger Log = Logger.getLogger(Log.class.getName());

	// 定义一个静态方法，可以打印自定义的某个测试用例开始执行的日志信息
	public static void startTestCase(String sTestCaseName) {
		Log.info("----------------------------------------------------");
		Log.info("************     " + sTestCaseName + "      **************");
	}

	// 定义一个静态方法，可以打印自定义的某个测试用例执行结束的日志信息
	public static void endTestCase(String sTestCaseName) {
		Log.info("************     " + "测试用例执行结束" + "      **************");
		Log.info("----------------------------------------------------");

	}

	// 定义一个静态的info方法，打印自定义的info级别日志信息
	public static void info(String message) {
		Log.info(message);
	}

	// 定义一个静态的warn方法，打印自定义的warn级别日志信息
	public static void warn(String message) {
		Log.warn(message);
	}

	// 定义一个静态的error方法，打印自定义的error级别日志信息
	public static void error(String message) {
		Log.error(message);
	}

	// 定义一个静态的fatal方法，打印自定义的fatal级别日志信息
	public static void fatal(String message) {
		Log.fatal(message);
	}

	// 定义一个静态的debug方法，打印自定义的debug级别日志信息
	public static void debug(String message) {
		Log.debug(message);
	}
}
