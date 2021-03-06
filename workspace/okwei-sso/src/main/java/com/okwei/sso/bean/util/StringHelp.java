package com.okwei.sso.bean.util;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;

public class StringHelp { 
	/**
	 * 判断字符串是为空
	 * */
	public static boolean IsNullOrEmpty(String str){
		if(str == null || "".equals(str)){
			return true;
		}
		else {
			str=str.trim();
			if("".equals(str))
				return true;
			return false;
		}
	}
	
	/**
	 * 简单的SQL防注入
	 * @param param
	 * @return
	 */
	public static String convertSql(String param)
    {
		param = param.trim();
		param = param.replace("'", "''");
		param = param.replace(";--", "");
		param = param.replace("=", "");
		param = param.replace(" or ", "");
		param = param.replace(" and ", "");
        return param ;
    }
	
	/**
	 * 截取字符串,可根据想要的编码方式进行截取字符串，UTF-8编码下汉字占3个字节。以往按字数截取长度是不准确的。
	 * @param targetString
	 * @param byteIndex 截取字节数，英文1个字节，中文3个字节算
	 * @param needSuffix 是否需要后缀 ..
	 * @return
	 * @throws Exception
	 */
	public static String cutString(String targetString, int byteIndex,boolean needSuffix)
            throws Exception {
        if (targetString.getBytes("UTF-8").length < byteIndex) {
            return targetString;
        }
        String temp = targetString;
        for (int i = 0; i < targetString.length(); i++) {
            if (temp.getBytes("UTF-8").length <= byteIndex) {
                break;
            }
            temp = temp.substring(0, temp.length() - 1);
        }
        return temp+ (needSuffix?" ...":"");
    }
}
