package com.okwei.common.transfor;

import org.hibernate.transform.ResultTransformer;

/**
 * 
 * @ClassName: MyTransformer
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author xiehz
 * @date 2015年5月19日 下午4:30:31
 *
 */
public class MyTransformer {
	public static final ResultTransformer ALIAS_TO_ENTITY_MAP = new MapResultTransformer();
}
