package com.webdriver.run;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

/**
 * @author Administrator
 *能够使用配置文件存储被测试页面上元素的定位方式和定位表达式，做的定位数据和程序的分离。测试程序写好以后
 *可以方便不具备编码能力的测试人员进行自定义修改和配置，此部分内容可以作为自定义高级自动化测试框架的组成
 *部分
 */
public class ObjectMap {
	Properties properties;
	
	public ObjectMap(String propFile){
		
		properties = new Properties();
		
		try {
			FileInputStream in = new FileInputStream(propFile);
			properties.load(in);
			in.close();
			
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("read file error");
			e.printStackTrace();
		}
	}
	
	public By getLocator(String ElementNameInpropFile) throws Exception{
		//根据变量ElementNameInpropFile，从属性配置文件中读取对应的配置对象
		String locator = properties.getProperty(ElementNameInpropFile);
		//将配置对象中的定位类型存储到locatorType变量，将定位表达式的值存储到locatorValue变量中
		String locatorType = locator.split(":")[0];
		String locatorValue = locator.split(":")[1];
		
		//输出locatorType 变量值和locatorValue变量值，验证是否赋值正确
		System.out.println("获取的定位类型: " + locatorType + "\t 获取的定位表达式" + locatorValue);
		
		//根据locatorType的变量值内容判断返回何种定位方式的By对象
		if (locatorType.toLowerCase().equals("id")){
			return By.id(locatorValue);
		}else if (locatorType.toLowerCase().equals("name")){
			return By.name(locatorValue);
		}else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class"))){
			return By.className(locatorValue);
		}else if (locatorType.toLowerCase().equals("tagname")){
			return By.tagName(locatorValue);
		}else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link"))){
			return By.linkText(locatorValue);
		}else if (locatorType.toLowerCase().equals("partiallinktext")){
			return By.partialLinkText(locatorValue);
		}else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css"))){
			return By.cssSelector(locatorValue);
		}else if (locatorType.toLowerCase().equals("xpath")){
			return By.xpath(locatorValue);
		}else {
			throw new Exception("输入的 locator type 未定义 ：" + locatorType);
		}
	}

}
