package cn.gloryroad.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
/*
 * 实现在外部配置文件中配置页面元素的定位方式
 */
public class ObjectMap {
	Properties properties;
	public ObjectMap(String propFile){
		properties=new Properties();
		try {
			FileInputStream in= new FileInputStream(propFile);
			properties.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("读取对象文件出错");
			e.printStackTrace();
		}
	}

public By getLocator(String ElementNameInpropFile) throws Exception{
	String locator=properties.getProperty(ElementNameInpropFile);
	String locatorType=locator.split(">")[0];
	String locatorValue=locator.split(">")[1];
	locatorValue=new String(locatorValue.getBytes("ISO-8859-1"),"UTF-8");
	System.out.println("获取的定位类型"+locatorType+"\t 获取的定位表达式"+locatorValue);
	if(locatorType.toLowerCase().equals("id"))
		return By.id(locatorValue);
	else if(locatorType.toLowerCase().equals("name"))
		return By.name(locatorValue);
	else if(locatorType.toLowerCase().equals("classname"))
		return By.className(locatorValue);
	else if(locatorType.toLowerCase().equals("tagname"))
		return By.tagName(locatorValue);
	else if(locatorType.toLowerCase().equals("linktext"))
		return By.linkText(locatorValue);
	else if(locatorType.toLowerCase().equals("partiallinktext"))
		return By.partialLinkText(locatorValue);
	else if(locatorType.toLowerCase().equals("cssselector"))
		return By.cssSelector(locatorValue);
	else if(locatorType.toLowerCase().equals("xpath"))
		return By.xpath(locatorValue);
	else 
		throw new Exception("输入的locator type未在程序中被定义"+locatorType);
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
