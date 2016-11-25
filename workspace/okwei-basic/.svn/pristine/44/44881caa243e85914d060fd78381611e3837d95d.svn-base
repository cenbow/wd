package com.okwei.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: AbstractCacheBase
 * @Description: 缓存处理抽象基类
 * @author xiehz
 * @date 2015年5月7日 上午10:27:00
 *
 */
@SuppressWarnings("unchecked")
abstract class AbstractCacheBase {
 
	
	public Map map = new HashMap();

	/**
	 * 
	 * @Title: init 
	 * @Description: 初始化缓存 
	 * @param 
	 * @return void
	 * @throws
	 */
	public abstract void init();

	/**
	 * 
	 * @Title: refresh 
	 * @Description: 刷新缓存 
	 * @param 
	 * @return void
	 * @throws
	 */
	public abstract void refresh();

	/**
	 * 
	 * @Title: addOrUpdate 
	 * @Description: 通用添加或者更新缓存对象 
	 * @param @param key
	 * @param @param value
	 * @return void
	 * @throws
	 */
	public void addOrUpdate(Object key, Object value) {
		map.put(key, value);
	}

	/**
	 * 
	 * @Title: remove 
	 * @Description: 通用移除缓存对象 
	 * @param @param key
	 * @return void
	 * @throws
	 */
	public void remove(Object key) {
		if (map.containsKey(key)) {
			map.remove(key);
		}
	}

	/**
	 * 
	 * @Title: get 
	 * @Description: 通用获取缓存中的对象 
	 * @param @param key
	 * @param @return
	 * @return Object
	 * @throws
	 */
	public Object get(Object key) {
		return map.get(key);
	}

	/**
	 * 
	 * @Title: size 
	 * @Description: 获取缓存当前容量 
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int size() {
		return map.size();
	}
}
