package com.okwei.common;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JavaIdentifierTransformer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.util.ParseHelper;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;
import com.sdicons.json.model.JSONArray;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;

/**
 * 
 * @ClassName: JsonUtil
 * @Description: json 简单操作的工具类
 * @author xiehz
 * @date 2015年5月15日 下午6:59:36
 *
 */
public class JsonUtil {

	private static Gson gson = null;
	static {
		if (gson == null) {
			gson = new Gson();
		}
	}

	private JsonUtil() {
	}

	/**
	 * 
	 * @Title: objectToJson 
	 * @Description: 将对象转换成json格式
	 * @param @param ts
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String objectToJson(Object ts) {
		String jsonStr = null;
		if (gson != null) {
			jsonStr = gson.toJson(ts);
		}
		return jsonStr;
	}

	/**
	 * 
	 * @Title: objectToJsonDateSerializer 
	 * @Description: 将对象转换成json格式(并自定义日期格式) 
	 * @param @param ts
	 * @param @param dateformat
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String objectToJsonDateSerializer(Object ts, final String dateformat) {
		String jsonStr = null;
		gson = new GsonBuilder().registerTypeHierarchyAdapter(Date.class, new JsonSerializer<Date>() {
			public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
				SimpleDateFormat format = new SimpleDateFormat(dateformat);
				return new JsonPrimitive(format.format(src));
			}
		}).setDateFormat(dateformat).create();
		if (gson != null) {
			jsonStr = gson.toJson(ts);
		}
		return jsonStr;
	}

	/**
	 * 
	 * @Title: jsonToList 
	 * @Description: 将json格式转换成list对象 
	 * @param @param jsonStr
	 * @param @return
	 * @return List<?>
	 * @throws
	 */
	public static List<?> jsonToList(String jsonStr) {
		List<?> objList = null;
		if (gson != null) {
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<?>>() {
			}.getType();
			objList = gson.fromJson(jsonStr, type);
		}
		return objList;
	}

	/**
	 * 
	 * @Title: jsonToList 
	 * @Description: 将Json转为对应的List 
	 * @param @param jsonStr
	 * @param @param type
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	public static <T> List<T> jsonToList(String jsonStr, Class<T> type) {
		Type listType = new TypeToken<List<T>>() {
		}.getType();
		List<T> list = null;
		if (gson != null) {
			list = gson.fromJson(jsonStr, listType);
		}
		return list;
	}

	/**
	 * 
	 * @Title: json2Collection 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param jsonStr
	 * @param @param type
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	public static <T> List<T> json2Collection(String jsonStr, Type type) {
		return new Gson().fromJson(jsonStr, type);
	}

	/**
	 * 将json格式转换成map对象
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map<?, ?> jsonToMap(String jsonStr) {
		Map<?, ?> objMap = null;
		if (gson != null) {
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
			}.getType();
			objMap = gson.fromJson(jsonStr, type);
		}
		return objMap;
	}

	/**
	 * 将json转换成bean对象
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Object jsonToBean(String jsonStr, Class<?> cl) {
		Object obj = null;
		if (gson != null) {
			obj = gson.fromJson(jsonStr, cl);
		}
		return obj;
	}

	/**
	 * 将json转换成bean对象
	 * 
	 * @param jsonStr
	 * @param cl
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToBeanDateSerializer(String jsonStr, Class<T> cl, final String pattern) {
		Object obj = null;
		gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				SimpleDateFormat format = new SimpleDateFormat(pattern);
				String dateStr = json.getAsString();
				try {
					return format.parse(dateStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return null;
			}
		}).setDateFormat(pattern).create();
		if (gson != null) {
			obj = gson.fromJson(jsonStr, cl);
		}
		return (T) obj;
	}

	/**
	 * 根据
	 * 
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static Object getJsonValue(String jsonStr, String key) {
		Object rulsObj = null;
		Map<?, ?> rulsMap = jsonToMap(jsonStr);
		if (rulsMap != null && rulsMap.size() > 0) {
			rulsObj = rulsMap.get(key);
		}
		return rulsObj;
	}
	
	/**
	 * JAVA对象转换成JSON字符串
	 * @param obj
	 * @return
	 * @throws MapperException
	 */	
	public static String objectToJsonStr(Object obj) throws MapperException{
        JSONValue jsonValue = JSONMapper.toJSON(obj);  
        String jsonStr = jsonValue.render(false);
        String sreturn =jsonStr.replace("\"basemodle\"", "\"BaseModle\"").replace("\"statusreson\"", "\"StatusReson\"").replace("\"statu\"", "\"Statu\"");
        return sreturn;
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
	 * json转成对象（非List）可以过滤不需要的属性
	 * @param jsonStr
	 * @param cla
	 * @return
	 */
	public static Object json2Object(String jsonStr,Class<?> cla)
	{
		try {
			Object obj= cla.newInstance();
			Map<String, Object> map=ParseHelper.json2Map(jsonStr);
			if(map!=null)
			{
				org.apache.commons.beanutils.BeanUtils.populate(obj, map);
				return jsonStrToObject(objectToJson(obj),cla);
//				return obj;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;		
	}
	/**
	 * json转成List泛型对象 可以过滤不需要的属性
	 * @param jsonStr
	 * @param cla
	 * @return
	 */
	public static Object json2Objectlist(String jsonStr,Class<?> cla)
	{
		List<Object> list=new ArrayList<Object>();
		try {
			List<Map<String, Object>> maplist=ParseHelper.json2MapList(jsonStr);
			if(maplist!=null&&maplist.size()>0)
			{
				for (Map<String, Object> map2 : maplist) {
					Object obj= cla.newInstance();
					org.apache.commons.beanutils.BeanUtils.populate(obj, map2);
					list.add(jsonStrToObject(objectToJson(obj),cla));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;		
	}
	
	/**
	 * json转成对象（非List）可以过滤不需要的属性
	 * @param jsonStr
	 * @param cla
	 * @return
	 */
	public static Object json2Object2(String jsonStr,Class<?> cla)
	{
		try {
			
				return jsonStrToObject(jsonStr,cla);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;		
	}
	/**
	 * json转成List泛型对象 可以过滤不需要的属性
	 * @param jsonStr
	 * @param cla
	 * @return
	 */
	public static Object json2Objectlist2(String jsonStr,Class<?> cla)
	{
		List<Object> list=new ArrayList<Object>();
		try {
			return jsonStrToObject(jsonStr,cla);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;		
	}
	
	/**
	 * object转jsonstr 支持驼峰
	 */
	public static String objectToJsonStrWithUpCaseForReturnModel(Object obj)
	{		 
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.registerTypeAdapter(ReturnStatus.class, new ReturnModelAdapter() ).create();
		String json = gson.toJson(obj);
		return json;
	}
}