package com.okwei.restful.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class ParseHelper {

	/**
	 * String 转换成INT
	 * 
	 * @param strValue
	 *            需要转换成INT的字符
	 * @return
	 */
	public static int toInt(String strValue) {
		int num = 0;
		try {
			num = Integer.parseInt(strValue);
		} catch (Exception e) {
			num = 0;
		}
		return num;
	}

	/**
	 * String 转换成INT
	 * 
	 * @param strValue
	 *            需要转换成INT的字符
	 * @param defValue
	 *            默认值
	 * @return
	 */
	public static int toInt(String strValue, int defValue) {
		if (strValue == null || "".equals(strValue)) {
			return defValue;
		}
		int num = toInt(strValue);
		if (num == 0)
			return defValue;
		return num;
	}

	public static short toShort(String strValue, short defValue) {
		if (strValue == null || "".equals(strValue)) {
			return defValue;
		}
		short num = toShort(strValue);
		if (num == 0)
			return defValue;
		return num;
	}

	public static short toShort(String strValue) {
		short num = 0;
		try {
			num = Short.parseShort(strValue);
		} catch (Exception e) {
			num = 0;
		}
		return num;
	}

	public static double toDouble(String strValue) {
		double num = 0;
		try {
			num = Double.parseDouble(strValue);
		} catch (Exception e) {
			num = 0;
		}
		return num;
	}

	public static double toDouble(String strValue, double defValue) {
		double num = toDouble(strValue);
		if (num == 0)
			return defValue;
		return num;
	}

	/**
	 * 字符串转换成长整型
	 * 
	 * @param strValue
	 * @return
	 */
	public static long toLong(String strValue) {
		long result = 0;
		try {
			result = Long.parseLong(strValue);
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static double getDoubleDefValue(Double obj) {
		if (obj == null)
			return 0d;
		return obj;
	}

	/**
	 * 获取随机数
	 * 
	 * @param length
	 */
	public static String getRandNumber(int length) {
		char[] arrChar = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuilder buffer = new StringBuilder();
		Random rnd = new Random();
		for (int i = 0; i < length; i++) {
			buffer.append(arrChar[rnd.nextInt(10)]);
		}

		return buffer.toString();
	}

	/**
	 * 手机号码验证
	 * 
	 * @param phoneNum
	 * @return
	 */
	public static boolean isMobile(String phoneNum) {
		if ("".equals(phoneNum) || phoneNum == null)
			return false;
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		String regStr = AppSettingUtil.getSingleValue("mobilephoneReg");
		if ("".equals(regStr) || null == regStr) {
			regStr = "^[1][3,4,5,7,8][0-9]{9}$";
		}
		p = Pattern.compile(regStr); // Pattern.compile("^[1][3,4,5,8][0-9]{9}$");
										// // 验证手机号
		m = p.matcher(phoneNum);
		b = m.matches();
		return b;
		// return
		// Pattern.compile("^((13[0-9])|(17[0-9])|(15[0-9])|(18[0-9]))\\d{8}").matcher(phoneNum).matches();

	}

	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null, p2 = null, p3 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][0-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p3 = Pattern.compile("^[0][0-9]{2,3}-[0-9]{1,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		String[] arr = str.split("-");
		if (arr.length <= 1) {
			return isMobile(str);
			// m = p2.matcher(str);
			// b = m.matches();
		} else if (arr.length == 2) {
			m = p1.matcher(str);
			b = m.matches();
		} else if (arr.length == 3) {
			m = p3.matcher(str);
			b = m.matches();
		}
		return b;
	}

	/**
	 * @Title:isEmpty
	 * @Description:字符串是否为空
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @author xiehz
	 * @date 2015年4月27日
	 */
	public static boolean isEmpty(String s) {
		if (null == s) {
			return true;
		}
		return s.toString().trim().length() <= 0;
	}

	/**
	 * json装换成 Map<String,Object>
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map<String, Object> json2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(json2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	/**
	 * json转换成 List<map<String,Object>>
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static List<Map<String, Object>> json2MapList(String jsonStr) {
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(json2Map(json2.toString()));
		}
		return list;
	}

	/**
	 * 获取url 的参数值
	 * 
	 * @param url
	 * @param name
	 *            参数名称
	 * @return
	 */
	public static String getUrlParam(String url, String name) {
		if (url.length() > 1) {
			String paramString = url.substring(url.indexOf('?') + 1);
			String[] arrOne = paramString.split("&");
			if (arrOne != null && arrOne.length > 0) {
				for (String Str : arrOne) {
					String[] param = Str.split("=");
					if (param != null && param.length > 1) {
						if (name.equals(param[0]))
							return param[1];
					}
				}
			}
			return "";
		}
		return "";
	}

	/**
	 * 获取url的weiid
	 * 
	 * @param url
	 * @param name
	 * @return
	 */
	public static String getUrlWid(String url) {
		if (!"".equals(url) && url.length() > 0) {
			url = url.replace("http://", "").replace("www.", "");
			if (url.indexOf("?") > 1)
				url = url.substring(0, url.indexOf("?"));
			return url.substring(0, url.indexOf("."));
		}
		return "";
	}

	

	/**
	 * 功能：判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}

	private static Hashtable GetAreaCode() {
		Hashtable hashtable = new Hashtable();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}


}
