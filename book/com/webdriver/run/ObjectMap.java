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
 *�ܹ�ʹ�������ļ��洢������ҳ����Ԫ�صĶ�λ��ʽ�Ͷ�λ���ʽ�����Ķ�λ���ݺͳ���ķ��롣���Գ���д���Ժ�
 *���Է��㲻�߱����������Ĳ�����Ա�����Զ����޸ĺ����ã��˲������ݿ�����Ϊ�Զ���߼��Զ������Կ�ܵ����
 *����
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
		//���ݱ���ElementNameInpropFile�������������ļ��ж�ȡ��Ӧ�����ö���
		String locator = properties.getProperty(ElementNameInpropFile);
		//�����ö����еĶ�λ���ʹ洢��locatorType����������λ���ʽ��ֵ�洢��locatorValue������
		String locatorType = locator.split(":")[0];
		String locatorValue = locator.split(":")[1];
		
		//���locatorType ����ֵ��locatorValue����ֵ����֤�Ƿ�ֵ��ȷ
		System.out.println("��ȡ�Ķ�λ����: " + locatorType + "\t ��ȡ�Ķ�λ���ʽ" + locatorValue);
		
		//����locatorType�ı���ֵ�����жϷ��غ��ֶ�λ��ʽ��By����
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
			throw new Exception("����� locator type δ���� ��" + locatorType);
		}
	}

}
