package com.okwei.common.transfor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.ResultTransformer;

/**
 * 
 * @ClassName: MapResultTransformer 
 * @Description:  第一个为key 第二个为value
 * @author xiehz 
 * @date 2015年5月19日 下午4:31:07 
 *
 */
public class MapResultTransformer implements ResultTransformer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1484488468026814715L;

	public MapResultTransformer() {
	}

	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map result = new HashMap(1);
		if (tuple.length > 1) {
			result.put(tuple[0], tuple[1]);// 默认第一个为
		}
		return result;
	}

	public List transformList(List collection) {
		return collection;
	}

}
