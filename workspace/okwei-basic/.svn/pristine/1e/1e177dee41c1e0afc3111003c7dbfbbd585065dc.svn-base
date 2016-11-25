package com.okwei.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import sun.misc.BASE64Encoder;

import com.okwei.util.MD5Encrypt;

import net.sf.json.JSONObject;

public class SignFactory 
{

	private static String Key_sign="@ai8!lk5";
	
	/**
	 * 获取签名(md5加密 +base64 加密)
	 * @param jsonObject
	 * @return
	 */
	public static String addSign(JSONObject jsonObject)
	{
		String signData = getSignData(jsonObject);
		if ("".equals(signData) || null == signData)
			return "";
		signData += "&key=" + Key_sign;
		String MD5_str = MD5Encrypt.encrypt(signData).toLowerCase();
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(MD5_str.getBytes());
	}

	/**
     * 生成待签名串
     * @param paramMap
     * @return
     * @author guoyx
     */
    public static String getSignData(JSONObject jsonObject)
    {
        StringBuffer content = new StringBuffer();

        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<String>(jsonObject.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++)
        {
            String key = (String) keys.get(i);
            if ("sign".equals(key))
            {
                continue;
            }
            String value = jsonObject.getString(key);
            // 空串不参与签名
            if (null == value || value.equalsIgnoreCase("null") || value.equals(""))
            {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&"))
        {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }
    

  
}
