package com.okwei.util;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Configuration;

public class OperateXML {
	public static String getVersion(String name)
	{
		try {
			 String ver = "";
			 String filename = "appSettings.xml";
			 URL url = Configuration.class.getClassLoader().getResource(filename);
			 File conf = new File(url.getFile());
			 SAXReader reader = new SAXReader();
			 Document document = reader.read(conf);
			 Element root= document.getRootElement();
			 List<Element> chirdlist= root.element("version").elements("veritem");
			 if(chirdlist!=null&&chirdlist.size()>0)
			 {
				 for (Element ite : chirdlist) {
					if(ite.attributeValue("name").equals(name))
					{
						ver = ite.attributeValue("ver");
						break;
					}
				}
			 }
			 return ver;
		} catch (Exception e) {
			// TODO: handle exception
            return "";
		}

	}
	
	public static String getValueVal(String node,String nodeitem,String namekey,String valuekey,String namevalue)
	{
		try {
			 String valueVal = "";
			 String filename = "appSettings.xml";
			 URL url = Configuration.class.getClassLoader().getResource(filename);
			 File conf = new File(url.getFile());
			 SAXReader reader = new SAXReader();
			 Document document = reader.read(conf);
			 Element root= document.getRootElement();
			 List<Element> chirdlist= root.element(node).elements(nodeitem);
			 if(chirdlist!=null&&chirdlist.size()>0)
			 {
				 for (Element ite : chirdlist) {
					if(ite.attributeValue(namekey).equals(namevalue))
					{
						valueVal = ite.attributeValue(valuekey);
						break;
					}
				}
			 }
			 return valueVal;
		} catch (Exception e) {
			// TODO: handle exception
            return "";
		}

	}
}
