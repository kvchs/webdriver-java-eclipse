package keyWordsFrameWork.gloryroad.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

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
			System.out.println("读取对象文件出错");
			e.printStackTrace();
		}
	}
	
	
	public By getLocator(String ElementNameInpropFile) throws Exception{
		//根据变量，从属性配置文件中读取对应的配置对象
		String locator = properties.getProperty(ElementNameInpropFile);
		String locatorType = locator.split(">")[0];
		String locatorValue = locator.split(">")[1];
		/**
		 * 在eclipse中配置文件默认为ISO-8859-1编码存储，使用getBytes方法可以将字符串编码转换为
		 * UTF-8，解决配置文件读取中文乱码的问题
		 */
		locatorValue = new String(locatorValue.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("获取的定位类型：" + locatorType + "\t 获取的定位表达式： " + locatorValue);
		if (locatorType.toLowerCase().equals("id")){
			return By.id(locatorValue);
		}else if (locatorType.toLowerCase().equals("name")){
			return By.name(locatorValue);
		}else if (locatorType.toLowerCase().equals("classname") || (locatorType.toLowerCase().equals("class"))){
			return By.className(locatorValue);
		}else if (locatorType.toLowerCase().equals("tagname") || (locatorType.toLowerCase().equals("tag"))){
			return By.tagName(locatorValue);
		}else if (locatorType.toLowerCase().equals("linktext") || (locatorType.toLowerCase().equals("link"))){
			return By.linkText(locatorValue);
		}else if (locatorType.toLowerCase().equals("partiallinktext")){
			return By.partialLinkText(locatorValue);
		}else if (locatorType.toLowerCase().equals("cssselector") || (locatorType.toLowerCase().equals("css"))){
			return By.cssSelector(locatorValue);
		}else if (locatorType.toLowerCase().equals("xpath")){
			return By.xpath(locatorValue);
		}else {
			throw new Exception("输入的 locator type未在程序中被定义：" + locatorType);
		}
	}

}