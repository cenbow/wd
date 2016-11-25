package com.okwei.cartportal.util;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JavaIdentifierTransformer;
import antlr.RecognitionException;
import antlr.TokenStreamException;

import com.google.gson.Gson;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;
import com.sdicons.json.model.JSONArray;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;


public class JsonUtil {

	/**
	 * JAVA对象转换成JSON字符串
	 * @param obj
	 * @return
	 * @throws MapperException
	 */	
	public static String objectToJsonStr(Object obj) throws MapperException{
        JSONValue jsonValue = JSONMapper.toJSON(obj);  
        String jsonStr = jsonValue.render(false);
        String sreturn =jsonStr.replace("basemodle", "BaseModle").replace("statusreson", "StatusReson").replace("statu", "Statu");
        return sreturn;
	}
	/**
	 * 将对象装换成 jsonString
	 * @param obj
	 * @return
	 */
	public static String objectToJson(Object obj)
	{
		try {
			 JSONValue jsonValue = JSONMapper.toJSON(obj);  
		        String jsonStr = jsonValue.render(false);
		        return jsonStr.replace("basemodle", "BaseModle").replace("statusreson", "StatusReson").replace("statu", "Statu");
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 重载objectToJsonStr方法
	 * @param obj 需要转换的JAVA对象
	 * @param format 是否格式化
	 * @return
	 * @throws MapperException
	 */
	public static String objectToJsonStr(Object obj,boolean format) throws MapperException{
        JSONValue jsonValue = JSONMapper.toJSON(obj);  
        String jsonStr = jsonValue.render(format);
        return jsonStr.replace("basemodle", "BaseModle").replace("statusreson", "StatusReson").replace("statu", "Statu");
	}	
	
	/**
	 * JSON字符串转换成JAVA对象
	 * @param jsonStr
	 * @param cla
	 * @return
	 * @throws MapperException
	 * @throws TokenStreamException
	 * @throws RecognitionException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object jsonStrToObject(String jsonStr,Class<?> cla){
		Object obj = null;
		try{
	        JSONParser parser = new JSONParser(new StringReader(jsonStr));    
	        JSONValue jsonValue = parser.nextValue();  	        
	        if(jsonValue instanceof com.sdicons.json.model.JSONArray){
	        	List list = new ArrayList();
	        	JSONArray jsonArray = (JSONArray) jsonValue;
	        	for(int i=0;i<jsonArray.size();i++){
	        		JSONValue jsonObj = jsonArray.get(i);
	        		try
	        		{
	        			Object javaObj = JSONMapper.toJava(jsonObj,cla); 
	        			list.add(javaObj);
	        		}
	        		catch(Exception ex)
	        		{
	        			continue;
	        		}
	        		
	        	}
	        	obj = list;
	        }else if(jsonValue instanceof com.sdicons.json.model.JSONObject){
	        	obj = JSONMapper.toJava(jsonValue,cla); 
	        }else{
	        	obj = jsonValue;
	        }
        }catch(Exception e){
        	e.printStackTrace();
        }
        return obj; 
		}
	
	
	/**
	 * 将JAVA对象转换成JSON字符串
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	public static String simpleObjectToJsonStr(Object obj,List<Class> claList) throws IllegalArgumentException, IllegalAccessException{
		if(obj==null){
			return "null";
		}
		String jsonStr = "{";
		Class<?> cla = obj.getClass();
		Field fields[] = cla.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if(field.getType() == long.class){
				jsonStr += "\""+field.getName()+"\":"+field.getLong(obj)+",";
			}else if(field.getType() == double.class){
				jsonStr += "\""+field.getName()+"\":"+field.getDouble(obj)+",";
			}else if(field.getType() == float.class){
				jsonStr += "\""+field.getName()+"\":"+field.getFloat(obj)+",";
			}else if(field.getType() == int.class){
				jsonStr += "\""+field.getName()+"\":"+field.getInt(obj)+",";
			}else if(field.getType() == boolean.class){
				jsonStr += "\""+field.getName()+"\":"+field.getBoolean(obj)+",";
			}else if(field.getType() == Integer.class||field.getType() == Boolean.class
					||field.getType() == Double.class||field.getType() == Float.class					
					||field.getType() == Long.class){				
				jsonStr += "\""+field.getName()+"\":"+field.get(obj)+",";
			}else if(field.getType() == String.class){
				jsonStr += "\""+field.getName()+"\":\""+field.get(obj)+"\",";
			}else if(field.getType() == List.class){
				String value = simpleListToJsonStr((List<?>)field.get(obj),claList);
				jsonStr += "\""+field.getName()+"\":"+value+",";				
			}else{		
				if(claList!=null&&claList.size()!=0&&claList.contains(field.getType())){
					String value = simpleObjectToJsonStr(field.get(obj),claList);
					jsonStr += "\""+field.getName()+"\":"+value+",";					
				}else{
					jsonStr += "\""+field.getName()+"\":null,";
				}
			}
		}
		jsonStr = jsonStr.substring(0,jsonStr.length()-1);
		jsonStr += "}";
			return jsonStr;		
	}
	
	/**
	 * 将JAVA的LIST转换成JSON字符串
	 * @param list
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	public static String simpleListToJsonStr(List<?> list,List<Class> claList) throws IllegalArgumentException, IllegalAccessException{
		if(list==null||list.size()==0){
			return "[]";
		}
		String jsonStr = "[";
		for (Object object : list) {
			jsonStr += simpleObjectToJsonStr(object,claList)+",";
		}
		jsonStr = jsonStr.substring(0,jsonStr.length()-1);
		jsonStr += "]";		
		return jsonStr;
	}	
	
	/**
	 * 将JAVA的MAP转换成JSON字符串，
	 * 只转换第一层数据
	 * @param map
	 * @return
	 */
	public static String simpleMapToJsonStr(Map<?,?> map){
		if(map==null||map.isEmpty()){
			return "null";
		}
		String jsonStr = "{";
		Set<?> keySet = map.keySet();
		for (Object key : keySet) {
			jsonStr += "\""+key+"\":\""+map.get(key)+"\",";		
		}
		jsonStr = jsonStr.substring(0,jsonStr.length()-1);
		jsonStr += "}";
		return jsonStr;
	}
	
	/**
	 * json字符串转实体类，支持大小写
	 */
	public static Object jsonStrToObjectWithUpCase(String json,Class<?> cla)
	{
	    JSONObject jsonObject = JSONObject.fromObject(json);
	    JsonConfig config = new JsonConfig();
	    config.setJavaIdentifierTransformer(new JavaIdentifierTransformer() {


	      @Override
	      public String transformToJavaIdentifier(String str) {
	        char[] chars = str.toCharArray();
	        chars[0] = Character.toLowerCase(chars[0]);
	        return new String(chars);
	      }
	      
	    });
	    config.setRootClass(cla);
	    Object bean = JSONObject.toBean(jsonObject , config);
	    return bean;
	}
	
	/**
	 * object转jsonstr 支持驼峰
	 */
	public static String objectToJsonStrWithUpCase(Object obj)
	{		 
		Gson gson= new Gson();
		String json = gson.toJson(obj);
		return json;
	}
	
}
