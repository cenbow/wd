package com.okwei.cache;

import java.util.Map;

import org.apache.log4j.Logger;

import com.okwei.util.RedisUtil;


/**
 * 
 * @ClassName: CacheRoleData 
 * @Description: 角色 cache 
 * @author xiehz 
 * @date 2015年5月12日 下午9:05:50 
 *
 */
public class CacheRoleData extends AbstractCacheBase {

	private static Logger logger = Logger.getLogger(CacheRoleData.class);
	private static CacheRoleData instance = null;
	
	private CacheRoleData(){
		init();
	}
	
	public static CacheRoleData getInstance(){
		if(instance == null){
			instance = new CacheRoleData();
		}
		return instance;
	}
	@Override
	public void init() {
		logger.info("CacheRoleData 初始化");
		
		/*Jedis jedis = null;
		List<Role> roleList = null;
		
		try {
			jedis = JedisUtil.getJedis();
			IRoleDAO iRoleDAO = (IRoleDAO)SpringContextUtil.getBean("roleDAO");
			roleList = new ArrayList<Role>();
			List<Role> roles = iRoleDAO.queryAllRoles();
			
			if(Util.isNotEmpty(roles)){
				for(Role role:roles){
					if(role!=null && Util.isNotEmpty(role.getCode())){
						jedis.setnx((RedisConstant.CACHE_ROLE + role.getCode()).getBytes(), JedisUtil.getBytesFromObject(role));
						roleList.add(role);
					}
				}
				jedis.setnx(RedisConstant.CACHE_ROLE.getBytes(), JedisUtil.getBytesFromObject(roleList));
				logger.info("初始化角色信息到缓存完成："+roleList.size());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			JedisUtil.getJedisPool().returnResource(jedis);
		}*/
	}
	
	@Override
	public void refresh() {
		init();
	}

}
