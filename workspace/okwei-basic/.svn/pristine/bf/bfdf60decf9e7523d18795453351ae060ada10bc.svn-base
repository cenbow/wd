package com.okwei.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @ClassName: CacheBaseData 
 * @Description: 系统启动缓存基础数据 
 * @author xiehz 
 * @date 2015年5月7日 上午10:30:41 
 *
 */
public class CacheBaseData extends AbstractCacheBase{
	
	private static final Log logger = LogFactory.getLog(CacheBaseData.class);
	private static CacheBaseData instance = null;
	
	private CacheBaseData(){
		init();
	}
	
	/**
	 * 
	 * @Title: getInstance 
	 * @Description: 获取实例 
	 * @param @return
	 * @return CacheBaseData
	 * @throws
	 */
	public static CacheBaseData getInstance() {
		if (instance == null) {
			instance = new CacheBaseData();
		}
		return instance;
	}

	@Override
	public void init() {
		/*Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
			Map<String, String> bookSubjectCodeMap = new HashMap<String, String>();
			bookSubjectCodeMap.put("Y", "语文");
			bookSubjectCodeMap.put("M", "数学");
			bookSubjectCodeMap.put("E", "英语");
			bookSubjectCodeMap.put("W", "物理");
			bookSubjectCodeMap.put("H", "化学");
			bookSubjectCodeMap.put("S", "生物");
			bookSubjectCodeMap.put("D", "地理");
			bookSubjectCodeMap.put("L", "历史");
			bookSubjectCodeMap.put("Z", "政治");
			bookSubjectCodeMap.put("K", "科学");
			bookSubjectCodeMap.put("LZ", "理综");
			bookSubjectCodeMap.put("ZH", "综合");
			jedis.hmset("testKey", bookSubjectCodeMap);
			logger.info("初始化书本科目代码与名称完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			RedisUtil.returnResource(jedis);
		}*/
		
	}

	@Override
	public void refresh() {
		init();
	}

}
