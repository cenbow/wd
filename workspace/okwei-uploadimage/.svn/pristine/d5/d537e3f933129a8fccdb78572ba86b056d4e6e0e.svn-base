package com.okwei.restful.utils;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Configuration;



public class AppSettingUtil 
{

	/**
	 * 获取appSetting.xml的某个元素的值（默认为属性value的值）
	 * @param arg
	 * @return
	 */
	public static String getSingleValue(String arg)
	{
		String result="";
		try {
			 String filename = "appSettings.xml";
			 URL url = Configuration.class.getClassLoader().getResource(filename);
			 String str = url.getFile();
			 //转换编码
			 str = URLDecoder.decode(str,"utf-8");
	         File conf = new File(str);
			 SAXReader reader = new SAXReader();
			 Document document = reader.read(conf);
			 Element root= document.getRootElement();
			 Element chird= root.element(arg);
			 if(chird!=null)
				 result= chird.attributeValue("value");
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			 
		}
		return result;
	}
	/**
	 * 获取appSetting.xml的某个元素的属性值
	 * @param arg
	 * @param attributeName
	 * @return
	 */
	public static String getSingleValue(String arg,String attributeName)
	{
		String result="";
		try {
			 String filename = "appSettings.xml";
			 URL url = Configuration.class.getClassLoader().getResource(filename);
			 String str = url.getFile();
			 //转换编码
			 str = URLDecoder.decode(str,"utf-8");
	         File conf = new File(str);
			 SAXReader reader = new SAXReader();
			 Document document = reader.read(conf);
			 Element root= document.getRootElement();
			 Element chird= root.element(arg);
			 if(chird!=null)
				 result= chird.attributeValue(attributeName);
		} catch (Exception e) {
			// TODO: handle exception
			 
		}
		return result;
	}
	
	/**
	 * 
	 * @param parentNodeName
	 * @return
	 */
	public static List<Map<String, String>> getMaplist(String parentNodeName)
	{
		List<Map<String, String>> maplist=new ArrayList<Map<String,String>>();
		try {
			 String filename = "appSettings.xml";
			 URL url = Configuration.class.getClassLoader().getResource(filename);
			 String str = url.getFile();
			 //转换编码
			 str = URLDecoder.decode(str,"utf-8");
	         File conf = new File(str);
			 SAXReader reader = new SAXReader();
			 Document document = reader.read(conf);
			 Element root= document.getRootElement();
			 Element parent= root.element(parentNodeName);
			 if(parent!=null)
			 {
				 List<Element> list=parent.elements();
				 if(list!=null&&list.size()>0)
				 {
					 for (Element ee : list) {
						 Map<String, String> map=new HashMap<String, String>();
						 
						 for(int i=0;i<ee.attributes().size();i++)
						 {
							org.dom4j.Attribute aa= (org.dom4j.Attribute)ee.attributes().get(i);
							map.put(aa.getName(), aa.getValue());
						 }
						 maplist.add(map);	
					}
				 }
			 }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maplist;
	}
	
	/**
	 * 获取图片域名
	 * @return
	 */
	public  static Map<Integer, String> getImgDomains() 
	{
		Map<Integer, String> imgDomains=new HashMap<Integer,String>();
		try {
			List<Map<String, String>> maplist =getMaplist("imgDomains");
			if(maplist!=null&&maplist.size()>0)
			{
				int i=1;
				for (Map<String, String> map : maplist) {
					imgDomains.put(i, map.get("value"));
					i++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return imgDomains;
	}
}
