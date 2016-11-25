package com.okwei.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * 
 * @ClassName: BaseConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author xiehz
 * @date 2015年6月6日 上午10:59:43
 *
 */
// @Component("baseConfig")
public class BaseConfig {
	static Properties prop = new Properties();
	public static final Date startTime = new Date();
	static {
		try {
			InputStream in = BaseConfig.class.getResourceAsStream("/exception.properties");
			prop.load(in);
			in = BaseConfig.class.getResourceAsStream("/init.properties");
			prop.load(in);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		if (prop.containsKey(key)) {
			return prop.get(key).toString();
		}
		return "";
	}
}
